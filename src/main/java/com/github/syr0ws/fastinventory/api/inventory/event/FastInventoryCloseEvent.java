package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

/**
 * Represents an event triggered when a {@link FastInventory} is closed.
 */
public class FastInventoryCloseEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, Player)
     */
    public FastInventoryCloseEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
