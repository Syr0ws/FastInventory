package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;

/**
 * Represents an event that is triggered after a {@link FastInventory} has been opened.
 */
public class FastInventoryAfterOpenEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, InventoryViewer)
     */
    public FastInventoryAfterOpenEvent(FastInventory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
