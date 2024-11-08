package com.github.syr0ws.fastinventory.api.config.action;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;

public interface ClickActionLoader<T> {

    ClickAction load(T dataSource) throws InventoryConfigException;

    String getName();
}
