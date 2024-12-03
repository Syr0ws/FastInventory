package com.github.syr0ws.fastinventory.internal.inventory;

import com.github.syr0ws.fastinventory.api.inventory.InventoryHistory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.internal.inventory.history.SimpleInventoryHistory;
import org.bukkit.entity.Player;

public class SimpleInventoryViewer implements InventoryViewer {

    private final Player player;
    private final InventoryHistory history;

    public SimpleInventoryViewer(Player player) {

        if(player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        this.player = player;
        this.history = new SimpleInventoryHistory();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public InventoryHistory getInventoryHistory() {
        return this.history;
    }
}
