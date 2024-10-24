package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Set;

public class BroadcastAction extends CommonMessageAction {

    public static final String ACTION_NAME = "BROADCAST";

    public BroadcastAction(Set<ClickType> clickTypes, List<String> messages) {
        super(clickTypes, messages);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

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
