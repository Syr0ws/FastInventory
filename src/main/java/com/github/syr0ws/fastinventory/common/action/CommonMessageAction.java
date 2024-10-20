package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.Set;

public abstract class CommonMessageAction extends CommonAction {

    public CommonMessageAction(Set<ClickType> clickTypes) {
        super(clickTypes);
    }

    protected String parseMessage(String message, FastInventoryClickEvent event) {

        FastInventory inventory = event.getFastInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Context context = inventory.getDefaultContext();

        String parsed = provider.getI18n()
                .map(i18n -> i18n.getText(event.getPlayer(), message))
                .orElse(message);

        return placeholderManager.parse(parsed, context);
    }
}
