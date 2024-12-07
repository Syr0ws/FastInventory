package com.github.syr0ws.craftventory.api.inventory.exception;

/**
 * Exception thrown to indicate an issue related to an inventory.
 */
public class InventoryException extends RuntimeException {

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public InventoryException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
