package com.github.syr0ws.craftventory.internal.transform.provider;

import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.craftventory.api.transform.InventoryDescriptor;
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
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import com.github.syr0ws.craftventory.internal.inventory.SimpleCraftVentory;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePagination;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePaginationModel;
import com.github.syr0ws.craftventory.internal.transform.enhancement.SimpleEnhancementManager;
import com.github.syr0ws.craftventory.internal.transform.placeholder.SimplePlaceholderManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;

public class SimpleInventoryProvider implements InventoryProvider {

    private final Plugin plugin;
    private final InventoryDescriptor descriptor;

    private final PlaceholderManager placeholderManager;
    private final ProviderManager providerManager;
    private final EnhancementManager enhancementManager;

    private InventoryConfig inventoryConfig;

    public SimpleInventoryProvider(Plugin plugin, InventoryDescriptor descriptor) {

        if(plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        if(descriptor == null) {
            throw new IllegalArgumentException("descriptor cannot be null");
        }

        this.plugin = plugin;
        this.descriptor = descriptor;

        // Creating managers.
        this.placeholderManager = new SimplePlaceholderManager();
        this.providerManager = new SimpleProviderManager();
        this.enhancementManager = new SimpleEnhancementManager();

        // Registering providers.
        this.addDefaultProviders();
        this.descriptor.addProviders(this.providerManager);

        // Registering placeholders.
        this.addDefaultPlaceholders();
        this.descriptor.addPlaceholders(this.placeholderManager);

        // Registering enhancements.
        this.descriptor.addEnhancements(this.enhancementManager);
    }

    @Override
    public CraftVentory createInventory(InventoryService service, Player player) {

        InventoryViewer viewer = service.getInventoryViewer(player);
        CraftVentory inventory = new SimpleCraftVentory(this, service, viewer);

        this.descriptor.addHooks(inventory.getHookManager());
        this.registerPaginations(inventory);

        return inventory;
    }

    @Override
    public void loadConfig() {

        // If a resource file is present, copying it to the plugin folder.
        String resourceFile = this.descriptor.getInventoryResourceFile();

        if(resourceFile != null) {
            this.plugin.saveResource(resourceFile, false);
        }

        // Loading the inventory configuration file from the plugin folder.
        Path path = this.descriptor.getInventoryConfigFile();

        if(!Files.exists(path)) {
            throw new InventoryException(String.format("Inventory configuration file '%s' not found", path));
        }

        try {
            this.inventoryConfig = this.descriptor.getInventoryConfigDAO().loadConfig(path);
        } catch (InventoryConfigException exception) {
            String message = String.format("An error occurred while loading the configuration file '%s'", path);
            this.plugin.getLogger().log(Level.SEVERE, message, exception);
        }
    }

    @Override
    public InventoryConfig getConfig() {
        return this.inventoryConfig;
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
    public String getId() {
        return this.descriptor.getInventoryId();
    }

    @Override
    public Optional<I18n> getI18n() {
        return Optional.ofNullable(this.descriptor.getI18n());
    }

    private void registerPaginations(CraftVentory inventory) {

        this.inventoryConfig.getPaginationConfigs().forEach(paginationConfig -> {

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
                dto.slots().size()
        );

        return new SimplePagination<>(dto.paginationId(), inventory, model, dto.dataSupplier(), dto.slots());
    }

    private void addDefaultProviders() {

        ItemParser itemParser = this.getDefaultItemParser();

        this.providerManager.addProvider(new TitleProvider());
        this.providerManager.addProvider(new InventoryTypeProvider());
        this.providerManager.addProvider(new InventoryItemProviderBySlot(itemParser));
        this.providerManager.addProvider(new PaginationItemProvider(itemParser));
        this.providerManager.addProvider(new PaginationPreviousPageItemProvider(itemParser));
        this.providerManager.addProvider(new PaginationNextPageItemProvider(itemParser));
    }

    private void addDefaultPlaceholders() {
        Arrays.stream(CommonPlaceholder.values())
                .map(CommonPlaceholder::getPlaceholder)
                .forEach(this.placeholderManager::addPlaceholder);
    }

    private ItemParser getDefaultItemParser() {
        return new CommonItemStackParser();
    }
}
