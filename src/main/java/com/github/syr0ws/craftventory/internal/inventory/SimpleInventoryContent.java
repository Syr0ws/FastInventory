package com.github.syr0ws.craftventory.internal.inventory;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryContent;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;

import java.util.*;

public class SimpleInventoryContent implements InventoryContent {

    private final CraftVentory inventory;
    private final Map<Integer, InventoryItem> items = new HashMap<>();

    public SimpleInventoryContent(CraftVentory inventory) {

        if (inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        this.inventory = inventory;
    }

    @Override
    public void setItem(InventoryItem item, int slot) {

        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        this.checkSlot(slot);
        this.items.put(slot, item);
        this.inventory.getBukkitInventory().setItem(slot, item.getItemStack());
    }

    @Override
    public void setItem(InventoryItem item, Set<Integer> slots) {

        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if(slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }

        slots.forEach(slot -> {
            try {
                this.setItem(item, slot);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void setItems(Map<InventoryItem, Set<Integer>> items) {

        if(items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        items.forEach((item, slots) -> {
            try {
                this.setItem(item, slots);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void removeItem(int slot) {
        this.checkSlot(slot);
        this.items.remove(slot);
        this.inventory.getBukkitInventory().setItem(slot, null);
    }

    @Override
    public void removeItems(Set<Integer> slots) {

        if(slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }

        slots.forEach(slot -> {
            try {
                this.removeItem(slot);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    public boolean hasItem(int slot) {
        this.checkSlot(slot);
        return this.items.containsKey(slot);
    }

    @Override
    public boolean isValidSlot(int slot) {
        return slot >= 0 && slot < this.inventory.getSize();
    }

    @Override
    public Optional<InventoryItem> getItem(int slot) {
        return Optional.ofNullable(this.items.get(slot));
    }

    @Override
    public Map<Integer, InventoryItem> getItems() {
        return Collections.unmodifiableMap(this.items);
    }

    private void checkSlot(int slot) {

        if (!this.isValidSlot(slot)) {
            throw new IllegalArgumentException(String.format("Invalid slot %d", slot));
        }
    }
}
