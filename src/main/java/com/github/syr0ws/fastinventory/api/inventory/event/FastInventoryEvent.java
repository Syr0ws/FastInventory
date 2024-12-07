package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import org.bukkit.entity.Player;

/**
 * Represents a base class for all events related to a {@link FastInventory}.
 */
public abstract class FastInventoryEvent {

    private final FastInventory inventory;
    private final InventoryViewer viewer;

    /**
     * Constructs a new {@code FastInventoryEvent}.
     *
     * @param inventory The {@link FastInventory} instance associated with this event.
     * @param viewer    The player who triggered this event.
     */
    public FastInventoryEvent(FastInventory inventory, InventoryViewer viewer) {
        this.inventory = inventory;
        this.viewer = viewer;
    }

    /**
     * Retrieves the {@link FastInventory} associated with this event.
     *
     * @return The {@link FastInventory} instance.
     */
    public FastInventory getInventory() {
        return this.inventory;
    }

    /**
     * Retrieves the {@link InventoryViewer} that triggered this event.
     *
     * @return The {@link InventoryViewer} instance.
     */
    public InventoryViewer getViewer() {
        return this.viewer;
    }
}
