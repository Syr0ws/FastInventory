package com.github.syr0ws.fastinventory.api.transform.item;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.inventory.ItemStack;

/**
 * Defines a parser for processing and dynamically modifying {@link ItemStack} instances.
 */
public interface ItemParser {

    /**
     * Replaces dynamic content (e.g., placeholders) in the specified {@link ItemStack}.
     *
     * @param provider The {@link InventoryProvider} that owns the inventory requesting the parsing.
     *                 Must not be {@code null}.
     * @param item     The {@link ItemStack} to parse. Can be {@code null}.
     * @param context  A {@link Context} instance containing additional data for parsing.
     *                 Must not be {@code null}.
     * @return The parsed {@link ItemStack} with dynamic content replaced.
     *         If no changes are required, the original {@link ItemStack} may be returned.
     * @throws IllegalArgumentException If {@code provider} or {@code context} is {@code null}.
     */
    ItemStack parse(InventoryProvider provider, ItemStack item, Context context);
}
