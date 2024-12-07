package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Set;

public class BroadcastAction extends CommonMessageAction {

    public static final String ACTION_NAME = "BROADCAST";

    public BroadcastAction(Set<ClickType> clickTypes, List<String> messages) {
        super(clickTypes, messages);
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {

        String[] messages = super.getMessages().stream()
                .map(message -> super.parseMessage(message, event))
                .toArray(String[]::new);

        String message = String.join("\n", messages);

        Bukkit.broadcastMessage(message);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
