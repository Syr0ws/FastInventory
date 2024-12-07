package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * Represents an event triggered when a {@link CraftVentory} is closed.
 */
public class CraftVentoryCloseEvent extends CraftVentoryEvent {

    /**
     * @see CraftVentoryEvent#CraftVentoryEvent(CraftVentory, InventoryViewer)
     */
    public CraftVentoryCloseEvent(CraftVentory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
