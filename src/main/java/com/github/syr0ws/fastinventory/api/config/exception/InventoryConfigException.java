package com.github.syr0ws.fastinventory.api.config.exception;

import java.io.IOException;

public class InventoryConfigException extends IOException {

    public InventoryConfigException(String message) {
        super(message);
    }

    public InventoryConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
