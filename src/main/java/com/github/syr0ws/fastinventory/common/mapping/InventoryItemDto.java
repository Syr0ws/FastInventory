package com.github.syr0ws.fastinventory.common.mapping;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.mapping.Dto;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryItemDto implements Dto {

    private final String id;
    private final List<ClickAction> actions;
    private ItemStack item;

    public InventoryItemDto(String id, ItemStack item, List<ClickAction> actions) {
        this.id = id;
        this.item = item;
        this.actions = actions;
    }

    public String getId() {
        return this.id;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {

        if(item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        this.item = item;
    }

    public List<ClickAction> getActions() {
        return this.actions;
    }
}
