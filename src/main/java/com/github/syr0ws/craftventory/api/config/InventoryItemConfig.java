package com.github.syr0ws.craftventory.api.config;

import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Represents the configuration of an item within an inventory.
 */
public interface InventoryItemConfig extends Configuration {

    /**
     * Retrieves the unique id of the inventory item.
     *
     * @return The item's unique id. Must not be {@code null} or empty.
     */
    String getId();

    /**
     * Retrieves the {@link ItemStack} representing the item's appearance.
     *
     * @return The {@link ItemStack} of the item. Must not be {@code null}.
     */
    ItemStack getItemStack();

    /**
     * Retrieves the list of click actions associated with the item.
     *
     * @return An immutable list of {@link ClickAction}. Must not be {@code null}.
     */
    List<ClickAction> getActions();
}
