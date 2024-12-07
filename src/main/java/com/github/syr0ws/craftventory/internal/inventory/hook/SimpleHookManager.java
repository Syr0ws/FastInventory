package com.github.syr0ws.craftventory.internal.inventory.hook;

import com.github.syr0ws.craftventory.api.inventory.event.FastInventoryEvent;
import com.github.syr0ws.craftventory.api.inventory.hook.Hook;
import com.github.syr0ws.craftventory.api.inventory.hook.HookManager;

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

        // See comment in the addHook() method.
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
    public <E extends FastInventoryEvent> void addHook(String hookId, Class<E> eventClass, Hook<E> hook) {

        if(hook == null) {
            throw new IllegalArgumentException("hook cannot be null");
        }

        if(eventClass == null) {
            throw new IllegalArgumentException("eventClass cannot be null");
        }

        // If the event class is abstract, it is not an event but an intermediate
        // representation to reuse data / methods. Thus, it cannot be used for a hook.
        int modifiers = eventClass.getModifiers();

        if(Modifier.isAbstract(modifiers)) {
            throw new IllegalArgumentException("eventClass cannot be abstract");
        }

        // Two hooks cannot have the same id. This is to ensure that two hooks with the same id
        // cannot be registered on a different event.
        this.removeHook(hookId);

        // Registering the hook.
        if(this.hooks.containsKey(eventClass)) {

            HookList<E> list = (HookList<E>) this.hooks.get(eventClass);
            list.add(hookId, hook);

        } else {

            HookList<E> list = new HookList<>(eventClass);
            list.add(hookId, hook);

            this.hooks.put(eventClass, list);
        }
    }

    @Override
    public void removeHook(String hookId) {

        if(hookId == null) {
            throw new IllegalArgumentException("hookId cannot be null");
        }

        this.hooks.values().removeIf(list -> {
            list.remove(hookId);
            return list.isEmpty();
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
        private final LinkedHashMap<String, Hook<E>> hooks;

        private HookList(Class<E> eventClass) {
            this.eventClass = eventClass;
            this.hooks = new LinkedHashMap<>();
        }

        public void add(String id, Hook<E> hook) {
            this.hooks.put(id, hook);
        }

        public void remove(String id) {
            this.hooks.remove(id);
        }

        public boolean isEmpty() {
            return this.hooks.isEmpty();
        }

        public Class<E> getEventClass() {
            return eventClass;
        }

        public Collection<Hook<E>> getHooks() {
            return this.hooks.values();
        }
    }
}
