package com.github.syr0ws.craftventory.api.inventory.item;

import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.data.DataStore;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Represents an item within an inventory.
 */
public interface InventoryItem {

    /**
     * Retrieves the unique id of this inventory item.
     *
     * @return The id of this item. Must not be {@code null}.
     */
    String getId();

    /**
     * Retrieves the corresponding {@link ItemStack} set in the inventory.
     *
     * @return Retrieves the corresponding {@link ItemStack} set in the inventory.
     */
    ItemStack getItemStack();

    /**
     * Retrieves the local storage for the item.
     *
     * @return The {@link DataStore} instance associated with the item.
     */
    DataStore getLocalStore();

    /**
     * Retrieves the list of {@link ClickAction} associated with this inventory item.
     * <p>
     * These actions define the behavior of the item when clicked. Multiple actions may be
     * triggered depending on the specific click type.
     * </p>
     *
     * @return A list of {@link ClickAction}. Must not be {@code null}.
     */
    List<ClickAction> getActions();
}
