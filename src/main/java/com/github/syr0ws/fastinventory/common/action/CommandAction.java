package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.entity.Player;

public class CommandAction implements ClickAction {

    public static final String ACTION_NAME = "EXECUTE_COMMAND";

    private final String command;

    public CommandAction(String command) {

        if(command == null || command.isEmpty()) {
            throw new IllegalArgumentException("command cannot null or empty");
        }

        this.command = command;
    }

    @Override
    public void execute(FastInventoryClickEvent event) {
        Player player = event.getPlayer();
        player.performCommand(this.command);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
