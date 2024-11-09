package com.github.syr0ws.fastinventory.api.inventory.item;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface InventoryItem {

    String getId();

    ItemStack getItemStack();

    List<ClickAction> getActions();
}
