package com.github.syr0ws.fastinventory.internal.config.yaml.item.property;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import org.bukkit.inventory.ItemStack;

public interface ItemPropertyLoader<T> {

    void loadProperty(T dataSource, ItemStack item) throws InventoryConfigException;

    String getPropertyName();
}
