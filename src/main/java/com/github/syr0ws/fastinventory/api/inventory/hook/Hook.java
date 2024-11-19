package com.github.syr0ws.fastinventory.api.inventory.hook;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryEvent;

/**
 * Represents a functional interface for handling events of a specific type.
 *
 * @param <E> The type of event this hook handles, extending {@link FastInventoryEvent}.
 */
@FunctionalInterface
public interface Hook<E extends FastInventoryEvent> {

    /**
     * Handles the given event. This method is invoked when the associated event is triggered,
     * allowing custom logic to be executed in response to the event.
     *
     * @param event The event instance to handle.
     */
    void onEvent(E event);
}
