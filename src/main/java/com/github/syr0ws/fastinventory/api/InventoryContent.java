package com.github.syr0ws.fastinventory.api;

import com.github.syr0ws.fastinventory.api.item.InventoryItem;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface InventoryContent {

    void setItem(InventoryItem item, int slot);

    void setItem(InventoryItem item, Set<Integer> slots);

    void removeItem(int slot);

    void removeItems(Set<Integer> slots);

    boolean hasItem(int slot);

    boolean isValidSlot(int slot);

    Optional<InventoryItem> getItem(int slot);

    Map<Integer, InventoryItem> getItems();

    void setItems(Map<InventoryItem, Set<Integer>> slots);
}
