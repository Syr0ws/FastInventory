package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;

/**
 * An abstract class that serves as base for events triggered when a {@link FastInventory} is opened.
 */
public abstract class FastInventoryOpenEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, InventoryViewer)
     */
    public FastInventoryOpenEvent(FastInventory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
