package com.github.syr0ws.fastinventory.api.action;

import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;

import java.util.Set;

public interface ClickAction {

    void execute(FastInventoryClickEvent event);

    Set<ClickType> getClickTypes();

    String getName();
}
