package com.github.syr0ws.craftventory.internal.config.yaml.item.property;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YamlCustomModelDataLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String CUSTOM_MODEL_DATA_KEY = "custom-model-data";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if (!section.isInt(CUSTOM_MODEL_DATA_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not an int", CUSTOM_MODEL_DATA_KEY));
        }

        int customModelData = section.getInt(CUSTOM_MODEL_DATA_KEY);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
    }

    @Override
    public String getPropertyName() {
        return CUSTOM_MODEL_DATA_KEY;
    }
}
