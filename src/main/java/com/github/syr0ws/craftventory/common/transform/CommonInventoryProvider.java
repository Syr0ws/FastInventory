package com.github.syr0ws.craftventory.common.transform;

import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.hook.HookManager;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.i18n.I18n;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.transform.provider.ProviderManager;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.pagination.PaginationDto;
import com.github.syr0ws.craftventory.common.transform.item.CommonItemStackParser;
import com.github.syr0ws.craftventory.common.transform.placeholder.CommonPlaceholder;
import com.github.syr0ws.craftventory.common.transform.provider.InventoryItemProviderBySlot;
import com.github.syr0ws.craftventory.common.transform.provider.InventoryTypeProvider;
import com.github.syr0ws.craftventory.common.transform.provider.TitleProvider;
import com.github.syr0ws.craftventory.common.transform.provider.pagination.PaginationItemProvider;
import com.github.syr0ws.craftventory.common.transform.provider.pagination.PaginationNextPageItemProvider;
import com.github.syr0ws.craftventory.common.transform.provider.pagination.PaginationPreviousPageItemProvider;
import com.github.syr0ws.craftventory.common.transform.provider.pagination.PaginationProvider;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import com.github.syr0ws.craftventory.internal.inventory.SimpleCraftVentory;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePagination;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePaginationModel;
import com.github.syr0ws.craftventory.internal.transform.enhancement.SimpleEnhancementManager;
import com.github.syr0ws.craftventory.internal.transform.placeholder.SimplePlaceholderManager;
import com.github.syr0ws.craftventory.internal.transform.provider.SimpleProviderManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class CommonInventoryProvider implements InventoryProvider {

    private final Plugin plugin;
    private final InventoryConfigDAO dao;
    private final I18n i18n;

    private final PlaceholderManager placeholderManager = new SimplePlaceholderManager();
    private final ProviderManager providerManager = new SimpleProviderManager();
    private final EnhancementManager enhancementManager = new SimpleEnhancementManager();

    private InventoryConfig config;

    public CommonInventoryProvider(I18n i18n, Plugin plugin, InventoryConfigDAO dao) {

        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        if (dao == null) {
            throw new IllegalArgumentException("dao cannot be null");
        }

        this.plugin = plugin;
        this.dao = dao;
        this.i18n = i18n;

        this.addProviders(this.providerManager);
        this.addPlaceholders(this.placeholderManager);
        this.addEnhancements(this.enhancementManager);

        this.loadConfig();
    }

    protected abstract Path getInventoryConfigFile();

    protected abstract void addPaginationProviders();

    protected abstract void addEnhancements(EnhancementManager manager);

    protected abstract void addHooks(HookManager manager);

    private void registerPaginations(CraftVentory inventory) {

        this.config.getPaginationConfigs().forEach(paginationConfig -> {

            String paginationId = paginationConfig.getId();

            Context context = inventory.getDefaultContext();
            context.addData(CommonContextKey.PAGINATION_ID.name(), paginationId, String.class);

            PaginationDto<?> dto = this.getProviderManager()
                    .provide(paginationId, PaginationDto.class, this, context)
                    .orElseThrow(() -> new NullPointerException(String.format("No provider found for pagination '%s'. Check that one has been registered", paginationId)));

            Pagination<?> pagination = this.createPagination(dto, inventory);

            inventory.getPaginationManager().addPagination(pagination);
        });
    }

    private <T> Pagination<T> createPagination(PaginationDto<T> dto, CraftVentory inventory) {

        SimplePaginationModel<T> model = new SimplePaginationModel<>(
                dto.paginationDataType(),
                dto.dataSupplier(),
                dto.slots().size()
        );

        return new SimplePagination<>(dto.paginationId(), inventory, model, dto.slots());
    }

    protected <T> void addPaginationProvider(String paginationId, Class<T> dataType, Supplier<List<T>> supplier) {
        this.providerManager.addProvider(new PaginationProvider<>(paginationId, dataType, supplier));
    }

    protected void addProviders(ProviderManager manager) {

        ItemParser itemParser = this.getItemParser();

        manager.addProvider(new TitleProvider());
        manager.addProvider(new InventoryTypeProvider());
        manager.addProvider(new InventoryItemProviderBySlot(itemParser));
        manager.addProvider(new PaginationItemProvider(itemParser));
        manager.addProvider(new PaginationPreviousPageItemProvider(itemParser));
        manager.addProvider(new PaginationNextPageItemProvider(itemParser));

        this.addPaginationProviders();
    }

    protected void addPlaceholders(PlaceholderManager manager) {
        Arrays.stream(CommonPlaceholder.values())
                .map(CommonPlaceholder::getPlaceholder)
                .forEach(manager::addPlaceholder);
    }

    protected ItemParser getItemParser() {
        return new CommonItemStackParser();
    }

    protected Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public void loadConfig() {

        Path path = this.getInventoryConfigFile();

        try {
            this.config = this.dao.loadConfig(path);
        } catch (InventoryConfigException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public CraftVentory createInventory(InventoryService service, Player player) {

        InventoryViewer viewer = service.getInventoryViewer(player);
        CraftVentory inventory = new SimpleCraftVentory(this, service, viewer);

        this.addHooks(inventory.getHookManager());
        this.registerPaginations(inventory);

        return inventory;
    }

    @Override
    public PlaceholderManager getPlaceholderManager() {
        return this.placeholderManager;
    }

    @Override
    public ProviderManager getProviderManager() {
        return this.providerManager;
    }

    @Override
    public EnhancementManager getEnhancementManager() {
        return this.enhancementManager;
    }

    @Override
    public InventoryConfig getConfig() {
        return this.config;
    }

    @Override
    public Optional<I18n> getI18n() {
        return Optional.ofNullable(this.i18n);
    }
}
