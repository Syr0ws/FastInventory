package com.github.syr0ws.fastinventory.internal.config;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SimpleInventoryItemConfig implements InventoryItemConfig {

    private final ItemStack item;
    private final List<ClickAction> actions;
    private String id;

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
        this.actions = actions;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
