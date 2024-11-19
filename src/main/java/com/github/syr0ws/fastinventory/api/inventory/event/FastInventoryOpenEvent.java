package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

/**
 * An abstract class that serves as base for events triggered when a {@link FastInventory} is opened.
 */
public abstract class FastInventoryOpenEvent extends FastInventoryEvent {

    /**
     * @see FastInventoryEvent#FastInventoryEvent(FastInventory, Player)
     */
    public FastInventoryOpenEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
