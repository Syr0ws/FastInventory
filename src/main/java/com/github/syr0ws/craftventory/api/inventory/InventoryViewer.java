package com.github.syr0ws.craftventory.api.inventory;

import org.bukkit.entity.Player;

/**
 * Represents a player who is viewing inventories.
 */
public interface InventoryViewer {

    /**
     * Retrieves the {@link Player} associated with this inventory viewer.
     *
     * @return The {@link Player} instance for this viewer. Never {@code null}.
     */
    Player getPlayer();

    /**
     * Retrieves the {@link InventoryViewManager} responsible for managing this viewer's inventory views.
     *
     * @return The {@link InventoryViewManager} instance for this viewer. Never {@code null}.
     */
    InventoryViewManager getViewManager();
}
