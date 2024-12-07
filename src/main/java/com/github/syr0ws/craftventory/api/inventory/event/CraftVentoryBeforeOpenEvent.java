package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import org.bukkit.event.Cancellable;

/**
 * Represents an event that is triggered before a {@link CraftVentory} is opened.
 */
public class CraftVentoryBeforeOpenEvent extends CraftVentoryOpenEvent implements Cancellable {

    private boolean cancelled;

    /**
     * @see CraftVentoryEvent#CraftVentoryEvent(CraftVentory, InventoryViewer)
     */
    public CraftVentoryBeforeOpenEvent(CraftVentory inventory, InventoryViewer viewer) {
        super(inventory, viewer);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
