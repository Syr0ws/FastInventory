package com.github.syr0ws.fastinventory.internal.config.yaml.item.property;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YamlGlowPropertyLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String GLOW_KEY = "glow";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if(!section.isBoolean(GLOW_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not a boolean", GLOW_KEY));
        }

        boolean glow = section.getBoolean(GLOW_KEY);

        if(glow) {
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
    }

    @Override
    public String getPropertyName() {
        return GLOW_KEY;
    }
}
