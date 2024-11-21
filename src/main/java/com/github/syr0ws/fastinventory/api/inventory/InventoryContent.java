package com.github.syr0ws.fastinventory.api.inventory;

import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Interface for managing the content of an inventory.
 * <p>
 * None of the methods in this interface trigger an inventory update. To update the inventory view,
 * use the corresponding methods provided by the {@link FastInventory} interface.
 * </p>
 */
public interface InventoryContent {

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param item The item to be added to the inventory.
     * @param slot The slot in which the item will be placed.
     * @throws IllegalArgumentException If the item is null or if the slot is invalid.
     */
    void setItem(InventoryItem item, int slot);

    /**
     * Sets an item at a set of specific slots in the inventory.
     *
     * @param item  The item to be added to the inventory.
     * @param slots The set of slots where the item will be placed.
     * @throws IllegalArgumentException If {@code slots} is null.
     * @throws IllegalArgumentException If the item is null or if one of the slots is invalid.
     */
    void setItem(InventoryItem item, Set<Integer> slots);

    /**
     * Sets multiple items in the inventory at specific slots.
     *
     * @param items A map where keys are inventory items and values are sets of slots to place the items in.
     * @throws IllegalArgumentException If {@code slots} is null or if one of the slots is invalid.
     */
    void setItems(Map<InventoryItem, Set<Integer>> items);

    /**
     * Removes an item from a specific slot in the inventory.
     *
     * @param slot The slot from which the item will be removed.
     * @throws IllegalArgumentException If the slot is invalid.
     */
    void removeItem(int slot);

    /**
     * Removes items from a set of specific slots in the inventory.
     *
     * @param slots The set of slots from which the items will be removed.
     * @throws IllegalArgumentException If {@code slots} is null one of the slots is invalid.
     */
    void removeItems(Set<Integer> slots);

    /**
     * Checks if there is an item at a specific slot in the inventory.
     *
     * @param slot The slot to check.
     * @return true if an item exists at the specified slot, false otherwise.
     * @throws IllegalArgumentException If the slot is invalid.
     */
    boolean hasItem(int slot);

    /**
     * Checks if a specific slot is valid for placing an item.
     *
     * @param slot The slot to check.
     * @return true if the slot is valid, false otherwise.
     */
    boolean isValidSlot(int slot);

    /**
     * Retrieves the item at a specific slot in the inventory.
     *
     * @param slot The slot to retrieve the item from.
     * @return An {@link Optional} containing the item at the specified slot, or an empty {@link Optional} if no item
     *         exists at that slot.
     */
    Optional<InventoryItem> getItem(int slot);

    /**
     * Retrieves all the items in the inventory.
     *
     * @return A map where the keys are slots and the values are the inventory items at those slots.
     */
    Map<Integer, InventoryItem> getItems();
}
