package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConsoleCommandAction extends CommonAction {

    public static final String ACTION_NAME = "CONSOLE_COMMAND";

    private final List<String> commands = new ArrayList<>();

    public ConsoleCommandAction(Set<ClickType> clickTypes, List<String> commands) {
        super(clickTypes);

        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("commands cannot null or empty");
        }

        this.commands.addAll(commands);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

        FastInventory inventory = event.getFastInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Context context = inventory.getDefaultContext();

        this.commands.stream()
                .map(command -> placeholderManager.parse(command, context))
                .forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}