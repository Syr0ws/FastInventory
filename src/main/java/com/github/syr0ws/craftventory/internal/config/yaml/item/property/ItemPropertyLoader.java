package com.github.syr0ws.craftventory.internal.config.yaml.item.property;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.inventory.ItemStack;

public interface ItemPropertyLoader<T> {

    void loadProperty(T dataSource, ItemStack item) throws InventoryConfigException;

    String getPropertyName();
}
