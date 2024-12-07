package com.github.syr0ws.craftventory.internal.config.yaml.item;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.internal.config.yaml.item.property.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class YamlItemStackLoader {

    private static final String TYPE_KEY = "type";
    private static final Map<String, ItemPropertyLoader<ConfigurationSection>> loadersMap = new HashMap<>();

    static {
        addLoaders(
                new YamlDisplayNameLoader(),
                new YamlLoreLoader(),
                new YamlAmountLoader(),
                new YamlCustomModelDataLoader(),
                new YamlGlowPropertyLoader()
        );
    }

    @SafeVarargs
    private static void addLoaders(ItemPropertyLoader<ConfigurationSection>... loaders) {

        for (ItemPropertyLoader<ConfigurationSection> loader : loaders) {
            loadersMap.put(loader.getPropertyName(), loader);
        }
    }

    public ItemStack loadItem(ConfigurationSection section) throws InventoryConfigException {

        // Loading the Material of the ItemStack.
        if (!section.isSet(TYPE_KEY)) {
            throw new InventoryConfigException(String.format("Cannot load ItemStack at '%s' : Property '%s' not found", section.getCurrentPath(), TYPE_KEY));
        }

        String type = section.getString(TYPE_KEY);
        Material material = Material.matchMaterial(type);

        if (material == null) {
            throw new InventoryConfigException(String.format("Cannot load ItemStack at '%s' : Invalid Material '%s'", section.getCurrentPath(), type));
        }

        // Loading all the properties of the ItemStack.
        ItemStack item = new ItemStack(material);

        for (String property : section.getKeys(false)) {

            // Retrieving the loader for the current property.
            ItemPropertyLoader<ConfigurationSection> loader = loadersMap.get(property);

            if (property.equals(TYPE_KEY)) {
                continue;
            }

            if (loader == null) {
                throw new InventoryConfigException(String.format("Cannot load ItemStack at '%s' : unknown property '%s'", section.getCurrentPath(), property));
            }

            // Loading the property.
            try {
                loader.loadProperty(section, item);
            } catch (Exception exception) {
                throw new InventoryConfigException(String.format("Cannot load ItemStack at '%s'", section.getCurrentPath()), exception);
            }
        }

        return item;
    }
}
