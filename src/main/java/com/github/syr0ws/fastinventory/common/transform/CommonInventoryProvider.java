package com.github.syr0ws.fastinventory.common.transform;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.i18n.I18n;
import com.github.syr0ws.fastinventory.api.transform.item.ItemParser;
import com.github.syr0ws.fastinventory.api.transform.mapping.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.transform.provider.ProviderManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.mapping.InventoryItemMapper;
import com.github.syr0ws.fastinventory.common.transform.placeholder.CommonPlaceholder;
import com.github.syr0ws.fastinventory.common.transform.provider.*;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.inventory.SimpleFastInventory;
import com.github.syr0ws.fastinventory.internal.transform.mapping.SimpleEnhancementManager;
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

            Context context = inventory.getDefaultContext();
            context.addData(CommonContextKey.PAGINATION_ID.name(), paginationConfig.getId(), String.class);

            Pagination<?> pagination = this.getProviderManager()
                    .provide(paginationConfig.getId(), Pagination.class, this, context)
                    .orElseThrow(() -> new NullPointerException(String.format("No provider found for pagination '%s'", config.getId())));

            inventory.getPaginationManager().addPagination(pagination);
        });
    }

    protected <T> void addPaginationProvider(String paginationId, Class<T> dataType, Supplier<List<T>> supplier) {
        this.providerManager.addProvider(new CommonPaginationProvider<>(
                paginationId,
                dataType,
                supplier,
                new InventoryItemMapper(this.enhancementManager, this.getItemParser()))
        );
    }

    protected void addProviders(ProviderManager manager) {

        ItemParser itemParser = this.getItemParser();
        InventoryItemMapper mapper = new InventoryItemMapper(this.enhancementManager, itemParser);

        manager.addProvider(new CommonTitleProvider());
        manager.addProvider(new CommonInventoryTypeProvider());
        manager.addProvider(new CommonInventoryItemProvider(mapper));
        manager.addProvider(new CommonPaginationItemProvider(mapper));
        manager.addProvider(new CommonPreviousPageItemProvider(mapper));
        manager.addProvider(new CommonNextPageItemProvider(mapper));

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