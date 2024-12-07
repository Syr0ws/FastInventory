package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewManager;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;

import java.util.Set;

public class ForwardAction extends CommonAction {

    public static final String ACTION_NAME = "FORWARD";

    private final String inventoryId;

    public ForwardAction(Set<ClickType> clickTypes, String inventoryId) {
        super(clickTypes);
        this.inventoryId = inventoryId;
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {

        InventoryViewer viewer = event.getViewer();
        InventoryViewManager manager = viewer.getViewManager();

        if(this.inventoryId == null) {
            manager.forward();
        } else {
            manager.forward(this.inventoryId);
        }
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
