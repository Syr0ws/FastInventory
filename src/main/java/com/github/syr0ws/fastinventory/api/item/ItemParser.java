package com.github.syr0ws.fastinventory.api.item;

import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.inventory.ItemStack;

public interface ItemParser {

    ItemStack parse(InventoryProvider provider, ItemStack item, Context context);
}
