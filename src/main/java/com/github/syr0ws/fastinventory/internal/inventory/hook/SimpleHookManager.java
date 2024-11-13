package com.github.syr0ws.fastinventory.internal.inventory.hook;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryEvent;
import com.github.syr0ws.fastinventory.api.inventory.hook.Hook;
import com.github.syr0ws.fastinventory.api.inventory.hook.HookManager;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleHookManager implements HookManager {

    private final Map<Class<? extends FastInventoryEvent>, HookList<? extends FastInventoryEvent>> hooks = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <E extends FastInventoryEvent> void executeHooks(E event, Class<E> eventClass) {

        if(event == null) {
            throw new IllegalArgumentException("event cannot be null");
        }

        if(eventClass == null) {
            throw new IllegalArgumentException("eventClass cannot be null");
        }

        int modifiers = eventClass.getModifiers();

        if(Modifier.isAbstract(modifiers)) {
            throw new IllegalArgumentException("event class cannot be abstract");
        }

        HookList<E> list = (HookList<E>) this.hooks.getOrDefault(eventClass, null);

        // No hook registered for this event.
        if(list == null) {
            return;
        }

        list.getHooks().forEach(hook -> hook.onEvent(event));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends FastInventoryEvent> void addHook(Hook<E> hook) {

        if(hook == null) {
            throw new IllegalArgumentException("hook cannot be null");
        }

        Class<E> eventClass = hook.getEventClass();

        if(eventClass == null) {
            throw new IllegalArgumentException("hook.eventClass cannot be null");
        }

        int modifiers = eventClass.getModifiers();

        if(Modifier.isAbstract(modifiers)) {
            throw new IllegalArgumentException("hook.eventClass cannot be abstract");
        }

        HookList<E> list = this.hooks.containsKey(eventClass) ?
                (HookList<E>) this.hooks.get(eventClass) :
                new HookList<>(eventClass);

        list.getHooks().add(hook);

        this.hooks.replace(eventClass, list);
    }

    @Override
    public void removeHook(String hookId) {

        if(hookId == null) {
            throw new IllegalArgumentException("hookId cannot be null");
        }

        this.hooks.values().removeIf(list -> {
            list.getHooks().removeIf(hook -> hook.getId().equals(hookId));
            return list.getHooks().isEmpty();
        });
    }

    @Override
    public Set<Hook<?>> getHooks() {
        return this.hooks.values().stream()
                .flatMap(list -> list.getHooks().stream())
                .collect(Collectors.toSet());
    }

    private static class HookList<E extends FastInventoryEvent> {

        private final Class<E> eventClass;
        private final List<Hook<E>> hooks;

        private HookList(Class<E> eventClass) {
            this.eventClass = eventClass;
            this.hooks = new ArrayList<>();
        }

        public Class<E> getEventClass() {
            return eventClass;
        }

        public List<Hook<E>> getHooks() {
            return this.hooks;
        }
    }
}
