package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public abstract class FastInventoryEvent {

    private final FastInventory inventory;
    private final Player player;

    public FastInventoryEvent(FastInventory inventory, Player player) {
        this.inventory = inventory;
        this.player = player;
    }

    public FastInventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }
}
