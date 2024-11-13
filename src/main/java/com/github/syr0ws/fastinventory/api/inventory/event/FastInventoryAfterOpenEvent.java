package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;

public class FastInventoryAfterOpenEvent extends FastInventoryEvent {

    public FastInventoryAfterOpenEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }
}
