package com.github.syr0ws.fastinventory.api.inventory.hook;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryEvent;

@FunctionalInterface
public interface Hook<E extends FastInventoryEvent> {

    void onEvent(E event);
}
