package com.github.syr0ws.fastinventory.api.transform.item;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.inventory.ItemStack;

public interface ItemParser {

    /**
     * Replace dynamic contents (ex: placeholders) in an ItemStack.
     *
     * @param provider The inventory provider of the inventory which asks the parsing of the item.
     * @param item     The item to parse.
     * @param context  A Context instance with additional data.
     * @return The parsed item.
     */
    ItemStack parse(InventoryProvider provider, ItemStack item, Context context);
}
