package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

/**
 * Represents a base class for all events related to a {@link FastInventory}.
 */
public abstract class FastInventoryEvent {

    private final FastInventory inventory;
    private final Player player;

    /**
     * Constructs a new {@code FastInventoryEvent}.
     *
     * @param inventory The {@link FastInventory} instance associated with this event.
     * @param player    The {@link Player} who triggered this event.
     */
    public FastInventoryEvent(FastInventory inventory, Player player) {
        this.inventory = inventory;
        this.player = player;
    }

    /**
     * Retrieves the {@link FastInventory} associated with this event.
     *
     * @return The {@link FastInventory} instance.
     */
    public FastInventory getInventory() {
        return this.inventory;
    }

    /**
     * Retrieves the {@link Player} that triggered this event.
     *
     * @return The {@link Player} instance.
     */
    public Player getPlayer() {
        return this.player;
    }
}
