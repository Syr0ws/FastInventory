package com.github.syr0ws.fastinventory.api.inventory.hook;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryEvent;

import java.util.Set;

public interface HookManager {

    <E extends FastInventoryEvent> void executeHooks(E event, Class<E> eventClass);

    <E extends FastInventoryEvent> void addHook(Hook<E> hook);

    void removeHook(String hookId);

    Set<Hook<?>> getHooks();
}
