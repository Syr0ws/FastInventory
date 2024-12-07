package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewManager;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;

import java.util.Set;

public class OpenInventoryAction extends CommonAction {

    public static final String ACTION_NAME = "OPEN_INVENTORY";

    private final String inventoryId;
    private final boolean newHistory;

    public OpenInventoryAction(Set<ClickType> clickTypes, String inventoryId, boolean newHistory) {
        super(clickTypes);

        if(inventoryId == null || inventoryId.isEmpty()) {
            throw new IllegalArgumentException("inventoryId cannot be null or empty");
        }

        this.inventoryId = inventoryId;
        this.newHistory = newHistory;
    }


    @Override
    public void execute(FastInventoryClickEvent event) {

        InventoryViewer viewer = event.getViewer();
        InventoryViewManager manager = viewer.getViewManager();
        InventoryService service = event.getInventory().getService();

        InventoryProvider provider = service.getProvider(this.inventoryId)
                .orElseThrow(() -> new InventoryException(String.format("No inventory provider found with id %s", this.inventoryId)));

        FastInventory inventory = provider.createInventory(service, viewer.getPlayer());
        manager.openView(inventory, this.newHistory);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
