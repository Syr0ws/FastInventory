package com.github.syr0ws.fastinventory.common.transform.dto;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class InventoryItemDto implements DTO {

    private final String itemId;
    private final List<ClickAction> actions;
    private final Set<Integer> slots;
    private ItemStack item;

    public InventoryItemDto(String itemId, ItemStack item, List<ClickAction> actions, Set<Integer> slots) {
        this.itemId = itemId;
        this.actions = actions;
        this.slots = slots;
        this.item = item;
    }

    public String getItemId() {
        return this.itemId;
    }

    public List<ClickAction> getActions() {
        return this.actions;
    }

    public Set<Integer> getSlots() {
        return this.slots;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public String getId() {
        return this.itemId;
    }
}
