package com.github.syr0ws.craftventory.internal.inventory;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewManager;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import org.bukkit.entity.Player;

public class SimpleInventoryViewer implements InventoryViewer {

    private final Player player;
    private final InventoryViewManager history;

    public SimpleInventoryViewer(Player player) {

        if(player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        this.player = player;
        this.history = new SimpleInventoryViewManager();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public InventoryViewManager getViewManager() {
        return this.history;
    }
}
