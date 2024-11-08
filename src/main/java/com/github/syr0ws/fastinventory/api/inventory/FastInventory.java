package com.github.syr0ws.fastinventory.api.inventory;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.transform.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Optional;

public interface FastInventory {

    void open();

    void close();

    void update();

    String getTitle();

    FastInventoryType getType();

    int getSize();

    InventoryProvider getProvider();

    InventoryContent getContent();

    InventoryService getService();

    Player getViewer();

    Inventory getBukkitInventory();

    Optional<Pagination<?>> getPagination(String id);

    <T> Optional<Pagination<T>> getPagination(String id, Class<T> type);

    List<Pagination<?>> getPaginations();

    Context getDefaultContext();
}
