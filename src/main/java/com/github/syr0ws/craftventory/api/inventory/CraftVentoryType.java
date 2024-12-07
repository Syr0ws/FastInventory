package com.github.syr0ws.craftventory.api.inventory;

import org.bukkit.event.inventory.InventoryType;

/**
 * Enum that defines the supported inventory types.
 */
public enum CraftVentoryType {

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

    CraftVentoryType(int size, int rows, int columns, InventoryType type) {
        this.size = size;
        this.rows = rows;
        this.columns = columns;
        this.type = type;
    }

    /**
     * Gets the total size (number of slots) of the inventory.
     *
     * @return The size of the inventory.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the number of rows in the inventory.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Gets the number of columns in the inventory.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Gets the corresponding Bukkit {@link InventoryType} for this CraftVentoryType.
     *
     * @return The corresponding Bukkit inventory type.
     */
    public InventoryType getBukkitType() {
        return this.type;
    }
}
