package com.github.syr0ws.fastinventory.api.inventory.action;

import java.util.Arrays;

/**
 * Represents the type of player's click in an inventory.
 */
public enum ClickType {

    LEFT, RIGHT, MIDDLE, ALL;

    /**
     * Determines whether a given name matches any defined {@link ClickType}.
     *
     * @param name The name to check. Must not be {@code null}.
     * @return {@code true} if the name matches a {@link ClickType}, ignoring case; {@code false} otherwise.
     */
    public static boolean isClickType(String name) {
        return Arrays.stream(values()).anyMatch(value -> value.name().equalsIgnoreCase(name));
    }
}
