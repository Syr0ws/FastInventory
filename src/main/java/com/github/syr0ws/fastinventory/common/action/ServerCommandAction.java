package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import org.bukkit.Bukkit;

public class ServerCommandAction implements ClickAction {

    public static final String ACTION_NAME = "SERVER_COMMAND";

    private final String command;

    public ServerCommandAction(String command) {

        if (command == null || command.isEmpty()) {
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

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}