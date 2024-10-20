package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.Set;

public class MessageAction extends CommonAction {

    public static final String ACTION_NAME = "MESSAGE";

    private final String message;

    public MessageAction(Set<ClickType> clickTypes, String message) {
        super(clickTypes);

        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }

        this.message = message;
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

        FastInventory inventory = event.getFastInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        String message = provider.getI18n()
                .map(i18n -> i18n.getText(event.getPlayer(), this.message))
                .orElse(this.message);

        message = placeholderManager.parse(message, inventory.getDefaultContext());

        Player player = event.getPlayer();
        player.sendMessage(message);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }

}
