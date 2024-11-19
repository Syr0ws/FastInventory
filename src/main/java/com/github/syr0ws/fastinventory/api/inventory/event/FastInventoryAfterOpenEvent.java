package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

/**
 * Represents an event that is triggered after a {@link FastInventory} has been opened.
 */
public class FastInventoryAfterOpenEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, Player)
     */
    public FastInventoryAfterOpenEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
