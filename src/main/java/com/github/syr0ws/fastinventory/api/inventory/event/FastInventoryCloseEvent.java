package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;

/**
 * Represents an event triggered when a {@link FastInventory} is closed.
 */
public class FastInventoryCloseEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, InventoryViewer)
     */
    public FastInventoryCloseEvent(FastInventory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
