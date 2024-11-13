package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public class FastInventoryAfterCloseEvent extends FastInventoryEvent {

    public FastInventoryAfterCloseEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
