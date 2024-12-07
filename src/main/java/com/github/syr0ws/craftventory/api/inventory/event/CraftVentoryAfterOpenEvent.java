package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * Represents an event that is triggered after a {@link CraftVentory} has been opened.
 */
public class CraftVentoryAfterOpenEvent extends CraftVentoryEvent {

    /**
     * @see CraftVentoryEvent#CraftVentoryEvent(CraftVentory, InventoryViewer)
     */
    public CraftVentoryAfterOpenEvent(CraftVentory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
