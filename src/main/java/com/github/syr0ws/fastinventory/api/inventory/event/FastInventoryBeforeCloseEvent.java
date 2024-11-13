package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public class FastInventoryBeforeCloseEvent extends FastInventoryCloseEvent {

    public FastInventoryBeforeCloseEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
