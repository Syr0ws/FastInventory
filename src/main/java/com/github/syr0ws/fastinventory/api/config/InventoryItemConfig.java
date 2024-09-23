package com.github.syr0ws.fastinventory.api.config;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface InventoryItemConfig extends Configuration {

    String getId();

    ItemStack getItemStack();

    List<ClickAction> getActions();
}
