package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.entity.Player;

import java.util.Set;

public class CloseAction extends CommonAction {

    public static final String ACTION_NAME = "CLOSE";

    public CloseAction(Set<ClickType> clickTypes) {
        super(clickTypes);
    }

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
