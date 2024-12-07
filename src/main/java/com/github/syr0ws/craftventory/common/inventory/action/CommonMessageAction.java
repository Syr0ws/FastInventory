package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.internal.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class CommonMessageAction extends CommonAction {

    private final List<String> messages = new ArrayList<>();

    public CommonMessageAction(Set<ClickType> clickTypes, List<String> messages) {
        super(clickTypes);

        if (messages == null) {
            throw new IllegalArgumentException("message cannot be null");
        }

        this.messages.addAll(messages);
    }

    protected String parseMessage(String message, CraftVentoryClickEvent event) {

        CraftVentory inventory = event.getInventory();
        InventoryProvider provider = inventory.getProvider();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Context context = inventory.getDefaultContext();

        String parsed = provider.getI18n()
                .map(i18n -> i18n.getText(event.getViewer(), message))
                .orElse(message);

        parsed = placeholderManager.parse(parsed, context);

        return TextUtil.parseColors(parsed);
    }

    public List<String> getMessages() {
        return this.messages;
    }
}
