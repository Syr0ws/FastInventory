package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * Represents an event triggered when a {@link CraftVentory} is closed.
 */
public class FastInventoryCloseEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(CraftVentory, InventoryViewer)
     */
    public FastInventoryCloseEvent(CraftVentory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
