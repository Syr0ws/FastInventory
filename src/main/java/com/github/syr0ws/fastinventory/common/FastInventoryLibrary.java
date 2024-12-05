package com.github.syr0ws.fastinventory.common;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.internal.SimpleInventoryService;
import com.github.syr0ws.fastinventory.internal.config.yaml.YamlInventoryConfigDAO;
import com.github.syr0ws.fastinventory.internal.config.yaml.action.*;
import com.github.syr0ws.fastinventory.internal.inventory.listener.FastInventoryListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * A utility class for creating and configuring the key components of the FastInventory library.
 */
public class FastInventoryLibrary {

    /**
     * Creates and configures an instance of the {@link InventoryService} for managing inventories.
     * <p>
     * This method also registers a listener to handle events related to inventories.
     * </p>
     *
     * @param plugin The plugin instance that will be used to register events.
     * @return An instance of the {@link InventoryService} to manage inventories.
     */
    public static InventoryService createInventoryService(Plugin plugin) {

        SimpleInventoryService service = new SimpleInventoryService();
        FastInventoryListener listener = new FastInventoryListener(plugin, service);

        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(listener, plugin);

        return service;
    }

    /**
     * Creates the default {@link InventoryConfigDAO} which is responsible for loading inventory
     * configurations from YAML files.
     *
     * @param factory The ClickActionLoaderFactory used for loading click actions.
     * @return An instance of {@link InventoryConfigDAO} for loading inventory configurations.
     */
    public static InventoryConfigDAO createDefaultConfigDAO(ClickActionLoaderFactory<ConfigurationSection> factory) {
        return new YamlInventoryConfigDAO(factory);
    }

    /**
     * Creates and configures a default {@link ClickActionLoaderFactory} for loading click actions
     * from YAML configuration files.
     *
     * @return A fully configured {@link ClickActionLoaderFactory} for loading click actions from YAML files.
     */
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
        factory.addLoader(new YamlUpdateContentActionLoader());
        factory.addLoader(new YamlUpdatePaginationActionLoader());

        return factory;
    }
}
