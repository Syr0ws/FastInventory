package com.github.syr0ws.fastinventory.common;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.internal.SimpleInventoryService;
import com.github.syr0ws.fastinventory.internal.config.yaml.YamlInventoryConfigDAO;
import com.github.syr0ws.fastinventory.internal.config.yaml.action.*;
import com.github.syr0ws.fastinventory.internal.listener.FastInventoryListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class FastInventoryLibrary {

    public static InventoryService createInventoryService(Plugin plugin) {

        InventoryService service = new SimpleInventoryService();
        FastInventoryListener listener = new FastInventoryListener(plugin, service);

        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(listener, plugin);

        return service;
    }

    public static InventoryConfigDAO createDefaultConfigDAO(ClickActionLoaderFactory<ConfigurationSection> factory) {
        return new YamlInventoryConfigDAO(factory);
    }

    public static ClickActionLoaderFactory<ConfigurationSection> createDefaultClickActionLoaderFactory() {

        ClickActionLoaderFactory<ConfigurationSection> factory = new YamlClickActionLoaderFactory();

        factory.addLoader(new YamlCloseActionLoader());
        factory.addLoader(new YamlMessageActionLoader());
        factory.addLoader(new YamlPlayerCommandActionLoader());
        factory.addLoader(new YamlConsoleCommandActionLoader());
        factory.addLoader(new YamlPreviousPageActionLoader());
        factory.addLoader(new YamlNextPageActionLoader());
        factory.addLoader(new YamlBroadcastActionLoader());
        factory.addLoader(new YamlSoundActionLoader());

        return factory;
    }
}
