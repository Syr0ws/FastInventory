package com.github.syr0ws.fastinventory.api.inventory.hook;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryEvent;

public interface Hook<E extends FastInventoryEvent> {

    void onEvent(E event);

    Class<E> getEventClass();

    String getId();
}
