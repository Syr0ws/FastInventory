package com.github.syr0ws.fastinventory.api.inventory;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.hook.HookManager;
import com.github.syr0ws.fastinventory.api.inventory.model.InventoryModel;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationManager;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface FastInventory {

    void open();

    void close();

    void updateContent();

    void updatePagination(String paginationId);

    void updateView();

    String getTitle();

    FastInventoryType getType();

    int getSize();

    InventoryProvider getProvider();

    InventoryContent getContent();

    InventoryModel getModel();

    InventoryService getService();

    PaginationManager getPaginationManager();

    /**
     * Retrieves the instance of the {@link HookManager} responsible for managing the hooks of the inventory.
     *
     * @return The {@link HookManager} instance for this inventory.
     */
    HookManager getHookManager();

    Player getViewer();

    Inventory getBukkitInventory();

    Context getDefaultContext();
}
