package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * An abstract class that serves as base for events triggered when a {@link CraftVentory} is opened.
 */
public abstract class CraftVentoryOpenEvent extends CraftVentoryEvent {

    /**
     * @see CraftVentoryEvent#CraftVentoryEvent(CraftVentory, InventoryViewer)
     */
    public CraftVentoryOpenEvent(CraftVentory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }
}
