package com.github.syr0ws.fastinventory.api.config.action;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;

public interface ClickActionLoader<T> {

    ClickAction load(T dataSource) throws InventoryConfigException;

    String getName();
}
