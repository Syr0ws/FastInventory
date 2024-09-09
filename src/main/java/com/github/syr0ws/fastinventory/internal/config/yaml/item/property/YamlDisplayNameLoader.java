package com.github.syr0ws.fastinventory.internal.config.yaml.item.property;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.internal.util.TextUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YamlDisplayNameLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String DISPLAY_NAME_KEY = "name";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if(!section.isString(DISPLAY_NAME_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not a string", DISPLAY_NAME_KEY));
        }

        String name = section.getString(DISPLAY_NAME_KEY);
        name = TextUtil.parseColors(name);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
    }

    @Override
    public String getPropertyName() {
        return DISPLAY_NAME_KEY;
    }
}
