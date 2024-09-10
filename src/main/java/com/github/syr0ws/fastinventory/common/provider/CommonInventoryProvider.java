package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.i18n.I18n;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.placeholder.*;
import com.github.syr0ws.fastinventory.internal.SimpleFastInventory;
import com.github.syr0ws.fastinventory.internal.placeholder.SimplePlaceholderManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.nio.file.Path;
import java.util.*;

public abstract class CommonInventoryProvider implements InventoryProvider {

    private final Plugin plugin;
    private final InventoryConfigDAO dao;
    private final I18n i18n;
    private final PlaceholderManager placeholderManager = new SimplePlaceholderManager();
    private final Map<String, Provider<?>> providers = new HashMap<>();

    private InventoryConfig config;

    public CommonInventoryProvider(I18n i18n, Plugin plugin, InventoryConfigDAO dao) {

        if(plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        if(dao == null) {
            throw new IllegalArgumentException("dao cannot be null");
        }

        this.plugin = plugin;
        this.dao = dao;
        this.i18n = i18n;

        this.addProviders();
        this.addPlaceholders(this.placeholderManager);
        this.loadConfig();
    }

    protected abstract Path getInventoryConfigFile();

    protected void addProviders() {

        ItemParser itemParser = this.getItemParser();

        this.addProvider(new CommonTitleProvider());
        this.addProvider(new CommonInventoryTypeProvider());
        this.addProvider(new CommonInventoryItemProvider(itemParser));
        this.addProvider(new CommonPaginationItemProvider(itemParser));
    }

    protected void addPlaceholders(PlaceholderManager manager) {
        manager.addPlaceholder(new PlayerNamePlaceholder());
        manager.addPlaceholder(new PlayerUUIDPlaceholder());
        manager.addPlaceholder(new InventoryTypePlaceholder());
        manager.addPlaceholder(new InventorySizePlaceholder());
        manager.addPlaceholder(new ItemSlotPlaceholder());
    }

    protected void addProvider(Provider<?> provider) {

        if(provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }

        this.providers.put(provider.getName(), provider);
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
        return new SimpleFastInventory(this, service, player);
    }

    @Override
    public <T> Optional<T> provide(String name, Class<T> type, Context context) {
        return this.getProvider(name, type).map(provider -> provider.provide(this, context));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<Provider<T>> getProvider(String name, Class<T> type) {

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if(type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        return this.providers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(name))
                .map(Map.Entry::getValue)
                .filter(provider -> provider.getType().equals(type))
                .map(provider -> (Provider<T>) provider)
                .findFirst();
    }

    @Override
    public Set<Provider<?>> getProviders() {
        return new HashSet<>(this.providers.values());
    }

    @Override
    public PlaceholderManager getPlaceholderManager() {
        return this.placeholderManager;
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
