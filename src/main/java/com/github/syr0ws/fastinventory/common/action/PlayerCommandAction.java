package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.entity.Player;

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
    public void execute(FastInventoryClickEvent event) {

        FastInventory inventory = event.getFastInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Context context = inventory.getDefaultContext();
        Player player = event.getPlayer();

        this.commands.stream()
                .map(command -> placeholderManager.parse(command, context))
                .forEach(player::performCommand);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
