package com.github.syr0ws.fastinventory.api;

import org.bukkit.event.inventory.InventoryType;

public enum FastInventoryType {

    CHEST_9x1(9, 1, 9, InventoryType.CHEST),
    CHEST_9x2(18, 2, 9, InventoryType.CHEST),
    CHEST_9x3(27, 3, 9, InventoryType.CHEST),
    CHEST_9x4(36, 4, 9, InventoryType.CHEST),
    CHEST_9x5(45, 5, 9, InventoryType.CHEST),
    CHEST_9x6(54, 6, 9, InventoryType.CHEST),
    DROPPER(9, 3, 3, InventoryType.DROPPER),
    HOPPER(5, 1, 5, InventoryType.HOPPER);

    private final int size;
    private final int rows, columns;
    private final InventoryType type;

    FastInventoryType(int size, int rows, int columns, InventoryType type) {
        this.size = size;
        this.rows = rows;
        this.columns = columns;
        this.type = type;
    }

    public int getSize() {
        return this.size;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public InventoryType getBukkitType() {
        return this.type;
    }
}
