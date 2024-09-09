package com.github.syr0ws.fastinventory.internal.config;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;

import java.util.List;
import java.util.Set;

public class SimplePaginationConfig implements PaginationConfig {

    private final String id;
    private final List<Integer> slots;
    private final InventoryItemConfig item;

    public SimplePaginationConfig(String id, Set<Integer> slots, InventoryItemConfig item) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (slots == null || slots.isEmpty()) {
            throw new IllegalArgumentException("slots cannot be null or empty");
        }

        if(item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        this.id = id;
        this.slots = slots.stream().sorted().toList();
        this.item = item;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public List<Integer> getSlots() {
        return this.slots;
    }

    @Override
    public InventoryItemConfig getItem() {
        return this.item;
    }
}
