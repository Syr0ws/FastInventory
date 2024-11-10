package com.github.syr0ws.fastinventory.common.transform;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.i18n.I18n;
import com.github.syr0ws.fastinventory.api.transform.item.ItemParser;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.transform.provider.ProviderManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.pagination.PaginationDto;
import com.github.syr0ws.fastinventory.common.transform.item.CommonItemStackParser;
import com.github.syr0ws.fastinventory.common.transform.placeholder.CommonPlaceholder;
import com.github.syr0ws.fastinventory.common.transform.provider.InventoryItemProviderBySlot;
import com.github.syr0ws.fastinventory.common.transform.provider.InventoryTypeProvider;
import com.github.syr0ws.fastinventory.common.transform.provider.TitleProvider;
import com.github.syr0ws.fastinventory.common.transform.provider.pagination.PaginationItemProvider;
import com.github.syr0ws.fastinventory.common.transform.provider.pagination.PaginationNextPageItemProvider;
import com.github.syr0ws.fastinventory.common.transform.provider.pagination.PaginationPreviousPageItemProvider;
import com.github.syr0ws.fastinventory.common.transform.provider.pagination.PaginationProvider;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.inventory.SimpleFastInventory;
import com.github.syr0ws.fastinventory.internal.inventory.pagination.SimplePagination;
import com.github.syr0ws.fastinventory.internal.inventory.pagination.SimplePaginationModel;
import com.github.syr0ws.fastinventory.internal.transform.enhancement.SimpleEnhancementManager;
import com.github.syr0ws.fastinventory.internal.transform.placeholder.SimplePlaceholderManager;
import com.github.syr0ws.fastinventory.internal.transform.provider.SimpleProviderManager;
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

    private void registerPaginations(FastInventory inventory) {

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

    private <T> Pagination<T> createPagination(PaginationDto<T> dto, FastInventory inventory) {

        SimplePaginationModel<T> model = new SimplePaginationModel<>(dto.getPaginationDataType(), dto.getSlots().size());
        model.setItems(dto.getDataSupplier().get());

        return new SimplePagination<>(dto.getPaginationId(), inventory, model, dto.getSlots());
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
    public FastInventory createInventory(InventoryService service, Player player) {

        FastInventory inventory = new SimpleFastInventory(this, service, player);
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
