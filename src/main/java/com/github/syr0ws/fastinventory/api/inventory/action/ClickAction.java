package com.github.syr0ws.fastinventory.api.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;

import java.util.Set;

/**
 * Represents an action that is executed when a player clicks on an item in the inventory.
 */
public interface ClickAction {

    /**
     * Executes the action based on the provided inventory click event.
     *
     * @param event The {@link FastInventoryClickEvent} that triggered the action. Never {@code null}.
     */
    void execute(FastInventoryClickEvent event);

    /**
     * Retrieves the set of {@link ClickType} that can trigger the action.
     *
     * @return An immutable set of {@link ClickType}. Must not be {@code null}.
     */
    Set<ClickType> getClickTypes();

    /**
     * Retrieves the name of the action.
     *
     * @return The name of the action. Must not be {@code null}.
     */
    String getName();
}
