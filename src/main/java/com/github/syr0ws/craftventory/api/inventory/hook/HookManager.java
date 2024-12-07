package com.github.syr0ws.craftventory.api.inventory.hook;

import com.github.syr0ws.craftventory.api.inventory.event.FastInventoryEvent;

import java.util.Set;

/**
 * Manages the lifecycle of hooks, including their registration, execution, and removal.
 * Hooks are associated with specific event types and can be triggered based on those events.
 */
public interface HookManager {

    /**
     * Execute all registered hooks that handle the given event.
     *
     * @param event      The event to process. Hooks registered for this event's type will be executed.
     * @param eventClass The Java class representing the type of the event.
     *                   This determines which hooks are eligible to handle the event.
     * @param <E>        The type of the event, extending {@link FastInventoryEvent}.
     */
    <E extends FastInventoryEvent> void executeHooks(E event, Class<E> eventClass);

    /**
     * Registers a new hook for a specific event type.
     *
     * @param hookId     A unique identifier for the hook. This id is used to manage the hook lifecycle.
     * @param eventClass The Java class representing the type of event the hook handles.
     * @param hook       The hook to register, which contains the logic to execute when the event occurs.
     * @param <E>        The type of the event, extending {@link FastInventoryEvent}.
     */
    <E extends FastInventoryEvent> void addHook(String hookId, Class<E> eventClass, Hook<E> hook);

    /**
     * Removes a registered hook by its unique identifier.
     *
     * @param hookId The unique identifier of the hook to be removed.
     *               If no hook with the given id exists, this method has no effect.
     */
    void removeHook(String hookId);

    /**
     * Retrieves all currently registered hooks.
     *
     * @return A set containing all registered hooks, regardless of their associated event type.
     */
    Set<Hook<?>> getHooks();
}
