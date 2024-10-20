package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import org.bukkit.entity.Player;

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

        Player player = event.getPlayer();
        player.sendMessage(messages);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
