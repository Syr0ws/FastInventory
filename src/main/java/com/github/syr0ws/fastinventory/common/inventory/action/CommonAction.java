package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;

import java.util.HashSet;
import java.util.Set;

public abstract class CommonAction implements ClickAction {

    private final Set<ClickType> clickTypes = new HashSet<>();

    public CommonAction(Set<ClickType> clickTypes) {

        if (clickTypes == null) {
            throw new IllegalArgumentException("clickTypes cannot be null");
        }

        this.clickTypes.addAll(clickTypes);
    }

    @Override
    public Set<ClickType> getClickTypes() {
        return this.clickTypes;
    }
}
