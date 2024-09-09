package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.entity.Player;

public class CloseAction implements ClickAction {

    public static final String ACTION_NAME = "CLOSE";

    @Override
    public void execute(FastInventoryClickEvent event) {
        Player player = event.getPlayer();
        player.closeInventory();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
