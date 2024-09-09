package com.github.syr0ws.fastinventory.internal.config.yaml.item.property;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class YamlAmountLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String AMOUNT_KEY = "amount";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if(!section.isInt(AMOUNT_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not an int", AMOUNT_KEY));
        }

        int amount = section.getInt(AMOUNT_KEY);
        item.setAmount(amount);
    }

    @Override
    public String getPropertyName() {
        return AMOUNT_KEY;
    }
}
