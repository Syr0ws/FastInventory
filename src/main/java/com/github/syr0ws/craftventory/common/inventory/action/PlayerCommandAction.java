package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.util.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerCommandAction extends CommonAction {

    public static final String ACTION_NAME = "PLAYER_COMMAND";

    private final List<String> commands = new ArrayList<>();

    public PlayerCommandAction(Set<ClickType> clickTypes, List<String> commands) {
        super(clickTypes);

        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("commands cannot null or empty");
        }

        this.commands.addAll(commands);
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {

        CraftVentory inventory = event.getInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Context context = inventory.getDefaultContext();
        InventoryViewer viewer = event.getViewer();

        this.commands.stream()
                .map(command -> placeholderManager.parse(command, context))
                .forEach(command -> viewer.getPlayer().performCommand(command));
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
