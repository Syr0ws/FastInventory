package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;

import java.util.Set;

public class UpdateContentAction extends CommonAction {

    public static final String ACTION_NAME = "UPDATE_CONTENT";

    public UpdateContentAction(Set<ClickType> clickTypes) {
        super(clickTypes);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {
        FastInventory inventory = event.getInventory();
        inventory.updateContent();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
