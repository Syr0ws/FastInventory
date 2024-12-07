package com.github.syr0ws.craftventory.api.inventory.hook;

import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryEvent;

/**
 * Represents a functional interface for handling events of a specific type.
 *
 * @param <E> The type of event this hook handles, extending {@link CraftVentoryEvent}.
 */
@FunctionalInterface
public interface Hook<E extends CraftVentoryEvent> {

    /**
     * Handles the given event. This method is invoked when the associated event is triggered,
     * allowing custom logic to be executed in response to the event.
     *
     * @param event The event instance to handle.
     */
    void onEvent(E event);
}
