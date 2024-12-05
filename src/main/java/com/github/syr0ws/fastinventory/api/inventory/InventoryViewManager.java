package com.github.syr0ws.fastinventory.api.inventory;

import java.util.Optional;

public interface InventoryViewManager {

    void openView(FastInventory inventory);

    void openView(FastInventory inventory, boolean newHistory);

    void clear();

    void home();

    void backward();

    void backward(String inventoryId);

    boolean hasBackward();

    void forward();

    void forward(String inventoryId);

    boolean hasForward();

    boolean hasActionInProgress();

    boolean contains(String inventoryId);

    boolean isEmpty();

    boolean hasOpenedInventory();

    Optional<FastInventory> getOpenedInventory();
}
