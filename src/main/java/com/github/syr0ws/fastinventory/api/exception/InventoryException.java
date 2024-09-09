package com.github.syr0ws.fastinventory.api.exception;

public class InventoryException extends RuntimeException {

    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
