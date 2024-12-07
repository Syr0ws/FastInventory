package com.github.syr0ws.craftventory.common.transform.dto;

import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryItemDto implements DTO {

    private final List<ClickAction> actions;
    private final Set<Integer> slots;
    private String itemId;
    private ItemStack item;

    public InventoryItemDto() {
        this.actions = new ArrayList<>();
        this.slots = new HashSet<>();
    }

    public InventoryItemDto(String itemId, ItemStack item, List<ClickAction> actions, Set<Integer> slots) {
        this.itemId = itemId;
        this.actions = actions;
        this.slots = slots;
        this.item = item;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
        return DtoNameEnum.INVENTORY_ITEM.name();
    }
}
