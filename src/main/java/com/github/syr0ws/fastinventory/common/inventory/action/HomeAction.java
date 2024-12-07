package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;

import java.util.Set;

public class HomeAction extends CommonAction {

    public static final String ACTION_NAME = "HOME";

    public HomeAction(Set<ClickType> clickTypes) {
        super(clickTypes);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {
        InventoryViewer viewer = event.getViewer();
        viewer.getViewManager().home();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
