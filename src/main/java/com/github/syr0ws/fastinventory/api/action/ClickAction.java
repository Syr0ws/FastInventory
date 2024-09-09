package com.github.syr0ws.fastinventory.api.action;

import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;

public interface ClickAction {

    void execute(FastInventoryClickEvent event);

    String getName();
}
