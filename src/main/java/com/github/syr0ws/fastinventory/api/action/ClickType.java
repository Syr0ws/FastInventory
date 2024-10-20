package com.github.syr0ws.fastinventory.api.action;

import java.util.Arrays;

public enum ClickType {

    LEFT, RIGHT, MIDDLE, ALL;

    public static boolean isClickType(String name) {
        return Arrays.stream(values()).anyMatch(value -> value.name().equalsIgnoreCase(name));
    }
}
