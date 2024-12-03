package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import org.bukkit.event.Cancellable;

/**
 * Represents an event that is triggered before a {@link FastInventory} is opened.
 */
public class FastInventoryBeforeOpenEvent extends FastInventoryOpenEvent implements Cancellable {

    private boolean cancelled;

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, InventoryViewer)
     */
    public FastInventoryBeforeOpenEvent(FastInventory inventory, InventoryViewer viewer) {
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
