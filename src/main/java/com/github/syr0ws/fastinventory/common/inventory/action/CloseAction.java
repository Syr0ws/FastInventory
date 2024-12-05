package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;

import java.util.Set;

public class CloseAction extends CommonAction {

    public static final String ACTION_NAME = "CLOSE";

    public CloseAction(Set<ClickType> clickTypes) {
        super(clickTypes);
    }

    @Override
    public void execute(FastInventoryClickEvent event) {
        InventoryViewer viewer = event.getViewer();
        viewer.getViewManager().clear(true);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
