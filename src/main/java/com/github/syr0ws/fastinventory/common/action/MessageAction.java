package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.entity.Player;

public class MessageAction implements ClickAction {

    public static final String ACTION_NAME = "MESSAGE";

    private final String message;

    public MessageAction(String message) {

        if(message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }

        this.message = message;
    }

    @Override
    public void execute(FastInventoryClickEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(this.message);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }

}
