package com.github.syr0ws.craftventory.api.inventory;

import com.github.syr0ws.craftventory.api.inventory.data.DataStore;

import java.util.Optional;

/**
 * Manages the inventory views opened for a player. This includes tracking the history
 * of inventories to enable backward and forward between recently opened inventories.
 */
public interface InventoryViewManager {

    /**
     * Opens the specified {@link CraftVentory} for the viewer.
     * <p>
     * This is the same as {@code InventoryViewManager#openView(inventory, false)}
     * </p>
     *
     * @param inventory The {@link CraftVentory} to open. Must not be {@code null}.
     * @throws IllegalArgumentException if the {@code inventory} is {@code null}.
     */
    void openView(CraftVentory inventory);

    /**
     * Opens the specified {@link CraftVentory} for the viewer.
     *
     * @param inventory  The {@link CraftVentory} to open. Must not be {@code null}.
     * @param newHistory Whether to create a new history entry for this view.
     * @throws IllegalArgumentException If the {@code inventory} is {@code null}.
     */
    void openView(CraftVentory inventory, boolean newHistory);

    /**
     * Clears the history for the viewer and optionally closes the currently opened inventory.
     *
     * @param closeView Whether to close the currently opened inventory.
     */
    void clear(boolean closeView);

    /**
     * Opens the first opened inventory in the history.
     */
    void home();

    /**
     * Backward the viewer to the previous inventory in the history.
     * This does nothing is there is no previous inventory in the history.
     */
    void backward();

    /**
     * Backward the viewer to the previous inventory in the history with the specified id.
     * This does nothing is there is no previous inventory with the specified id in the history.
     *
     * @param inventoryId The id of the inventory to go back to. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code inventoryId} is {@code null}.
     */
    void backward(String inventoryId);

    /**
     * Checks whether the viewer can navigate backward in the history.
     *
     * @return {@code true} if there is a previous inventory in the history; {@code false} otherwise.
     */
    boolean hasBackward();

    /**
     * Forward the viewer to the next inventory in the history.
     * This does nothing is there is no next inventory in the history.
     */
    void forward();

    /**
     * Forward the viewer to the next inventory in the history with the specified id.
     * This does nothing is there is no next inventory with the specified id in the history.
     *
     * @param inventoryId The id of the inventory to go forward to. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code inventoryId} is {@code null}.
     */
    void forward(String inventoryId);

    /**
     * Checks whether the viewer can navigate forward in the history.
     *
     * @return {@code true} if there is a next inventory in the history; {@code false} otherwise.
     */
    boolean hasForward();

    /**
     * Checks whether there is a backward or forward action in progress for the viewer. This is mainly
     * for internal use.
     *
     * @return {@code true} if there is an action in progress; {@code false} otherwise.
     */
    boolean hasActionInProgress();

    /**
     * Checks whether there is an inventory with the specified id that exists in the history.
     *
     * @param inventoryId The id of the inventory to check. Must not be {@code null}.
     * @return {@code true} if the inventory id exists in the history; {@code false} otherwise.
     * @throws IllegalArgumentException If the {@code inventoryId} is {@code null}.
     */
    boolean contains(String inventoryId);

    /**
     * Checks whether the history is empty.
     *
     * @return {@code true} if the history is empty; {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Checks whether the viewer currently has an inventory open.
     *
     * @return {@code true} if the viewer has an inventory open; {@code false} otherwise.
     */
    boolean hasOpenedInventory();

    /**
     * Retrieves the currently opened {@link CraftVentory}, if any.
     *
     * @return An {@link Optional} containing the currently opened {@link CraftVentory},
     * or an empty {@link Optional} if no inventory is open.
     */
    Optional<CraftVentory> getOpenedInventory();

    /**
     * Retrieves the shared storage between inventories in the history.
     *
     * @return The {@link DataStore} instance shared among inventories. Never {@code null}.
     */
    DataStore getSharedStore();
}
