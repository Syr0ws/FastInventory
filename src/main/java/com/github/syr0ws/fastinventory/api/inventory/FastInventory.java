package com.github.syr0ws.fastinventory.api.inventory;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.hook.HookManager;
import com.github.syr0ws.fastinventory.api.inventory.model.InventoryModel;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationManager;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Represents an inventory that can be opened, closed, and updated dynamically.
 * <p>
 * A {@link FastInventory} is an abstraction for a Bukkit inventory that provides more features
 * such as pagination, predefined interactions, dynamic update, etc.
 * </p>
 */
public interface FastInventory {

    /**
     * Opens the inventory to the {@link Player} referred by the {@code getView()} method.
     */
    void open();

    /**
     * Closes the inventory to the {@link Player} referred by the {@code getView()} method.
     */
    void close();

    /**
     * Updates all the inventory content including items and paginations.
     * <p>
     * This triggers the transformation process for items and paginations to dynamically update them
     * in the inventory.
     * </p>
     */
    void updateContent();

    /**
     * Updates a pagination by id.
     * <p>
     * This triggers the transformation process for the corresponding pagination to dynamically update
     * it in the inventory.
     * </p>
     *
     * @param paginationId The id of the pagination to update.
     */
    void updatePagination(String paginationId);

    /**
     * Update the inventory view.
     * <p>
     * This will not trigger the transformation process and only a view update will be executed.
     * </p>
     */
    void updateView();

    /**
     * Retrieves the title of the inventory.
     * <p>
     * This triggers the transformation process to get a parsed and enhanced title.
     * </p>
     *
     * @return The title of the inventory. Never {@code null}.
     */
    String getTitle();

    /**
     * Retrieves the type of the inventory.
     * <p>
     * This triggers the transformation process to get a enhanced type.
     * </p>
     *
     * @return The type of the inventory. Never {@code null}.
     */
    FastInventoryType getType();

    /**
     * Retrieves the size of the inventory.
     *
     * @return The size of the inventory.
     */
    int getSize();

    /**
     * Retrieves the provider responsible for creating and managing this inventory.
     *
     * @return The {@link InventoryProvider} instance associated with the inventory.
     */
    InventoryProvider getProvider();

    /**
     * Retrieves the instance responsible for managing the content of the inventory.
     *
     * @return The {@link InventoryContent} instance for the inventory.
     */
    InventoryContent getContent();

    /**
     * Retrieves the model for the inventory.
     *
     * @return The {@link InventoryModel} instance associated with the inventory.
     */
    InventoryModel getModel();

    /**
     * Retrieves the service managing the inventory.
     *
     * @return The {@link InventoryService} instance responsible for managing the inventory.
     */
    InventoryService getService();

    /**
     * Retrieves the pagination manager for the inventory.
     *
     * @return The {@link PaginationManager} instance responsible for managing pagination.
     */
    PaginationManager getPaginationManager();

    /**
     * Retrieves the instance of the {@link HookManager} responsible for managing the hooks of the inventory.
     *
     * @return The {@link HookManager} instance for this inventory.
     */
    HookManager getHookManager();

    /**
     * Retrieves the player viewing the inventory.
     * <p>
     * A {@link FastInventory} instance is always associated with a single {@link Player}.
     * </p>
     *
     * @return The {@link Player} instance who is viewing the inventory.
     */
    Player getViewer();

    /**
     * Retrieves the underlying {@link Inventory} from the Bukkit API.
     * <p>
     * This method returns the actual Bukkit inventory that represents the opened inventory
     * in the game.
     * </p>
     *
     * @return The {@link Inventory} instance representing the inventory in the Bukkit API.
     */
    Inventory getBukkitInventory();

    /**
     * Retrieves the default context associated with this inventory.
     * <p>
     * The context contains additional data or configuration used to customize the behavior
     * and rendering of the inventory.
     * </p>
     *
     * @return The {@link Context} instance containing default context data for the inventory.
     */
    Context getDefaultContext();
}
