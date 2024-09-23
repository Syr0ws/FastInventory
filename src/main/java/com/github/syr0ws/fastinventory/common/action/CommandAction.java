package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
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

        FastInventory inventory = event.getFastInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        String command = placeholderManager.parse(this.command, inventory.getDefaultContext());

        Player player = event.getPlayer();
        player.performCommand(command);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
