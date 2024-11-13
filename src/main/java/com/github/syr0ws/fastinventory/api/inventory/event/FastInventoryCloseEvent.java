package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public abstract class FastInventoryCloseEvent extends FastInventoryEvent {

    public FastInventoryCloseEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
