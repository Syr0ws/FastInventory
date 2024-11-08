package com.github.syr0ws.fastinventory.internal.inventory;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryContent;
import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;

import java.util.*;

public class SimpleInventoryContent implements InventoryContent {

    private final FastInventory inventory;
    private final Map<Integer, InventoryItem> items = new HashMap<>();

    public SimpleInventoryContent(FastInventory inventory) {

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
    }

    @Override
    public void setItem(InventoryItem item, Set<Integer> slots) {

        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
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
    public void removeItem(int slot) {
        this.checkSlot(slot);
        this.items.remove(slot);
    }

    @Override
    public void removeItems(Set<Integer> slots) {
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

    @Override
    public void setItems(Map<InventoryItem, Set<Integer>> items) {
        items.forEach((item, slots) -> {
            try {
                this.setItem(item, slots);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    private void checkSlot(int slot) {

        if (!this.isValidSlot(slot)) {
            throw new IllegalArgumentException(String.format("Invalid slot %d", slot));
        }
    }
}
