package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class FastInventoryBeforeOpenEvent extends FastInventoryOpenEvent implements Cancellable {

    private boolean cancelled;

    public FastInventoryBeforeOpenEvent(FastInventory inventory, Player player) {
        super(inventory, player);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
