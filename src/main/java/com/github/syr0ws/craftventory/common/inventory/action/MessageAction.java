package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.FastInventoryClickEvent;

import java.util.List;
import java.util.Set;

public class MessageAction extends CommonMessageAction {

    public static final String ACTION_NAME = "MESSAGE";

    public MessageAction(Set<ClickType> clickTypes, List<String> messages) {
        super(clickTypes, messages);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

        String[] messages = super.getMessages().stream()
                .map(message -> super.parseMessage(message, event))
                .toArray(String[]::new);

        InventoryViewer viewer = event.getViewer();
        viewer.getPlayer().sendMessage(messages);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
