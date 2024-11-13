package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public abstract class FastInventoryOpenEvent extends FastInventoryEvent {

    public FastInventoryOpenEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
