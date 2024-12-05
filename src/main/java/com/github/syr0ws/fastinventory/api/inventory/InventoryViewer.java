package com.github.syr0ws.fastinventory.api.inventory;

import org.bukkit.entity.Player;

public interface InventoryViewer {

    Player getPlayer();

    InventoryViewManager getViewManager();
}
