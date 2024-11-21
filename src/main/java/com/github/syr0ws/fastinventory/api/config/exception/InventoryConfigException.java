package com.github.syr0ws.fastinventory.api.config.exception;

import java.io.IOException;

/**
 * Exception thrown to indicate an issue related to an inventory configuration.
 */
public class InventoryConfigException extends IOException {

    /**
     * @see IOException#IOException(String)
     */
    public InventoryConfigException(String message) {
        super(message);
    }

    /**
     * @see IOException#IOException(String, Throwable)
     */
    public InventoryConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
