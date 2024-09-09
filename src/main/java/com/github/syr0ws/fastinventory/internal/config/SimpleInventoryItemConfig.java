package com.github.syr0ws.fastinventory.internal.config;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class SimpleInventoryItemConfig implements InventoryItemConfig {

    private final String id;
    private final ItemStack item;
    private final List<ClickAction> actions;

    public SimpleInventoryItemConfig(String id, ItemStack item, List<ClickAction> actions) {

        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if(item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if(actions == null) {
            throw new IllegalArgumentException("actions cannot be null");
        }

        this.id = id;
        this.item = item;
        this.actions = Collections.unmodifiableList(actions);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public ItemStack getItemStack() {
        return this.item.clone();
    }

    @Override
    public List<ClickAction> getActions() {
        return this.actions;
    }
}
