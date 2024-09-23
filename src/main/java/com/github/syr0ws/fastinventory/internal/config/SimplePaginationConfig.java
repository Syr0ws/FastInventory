package com.github.syr0ws.fastinventory.internal.config;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;

import java.util.List;
import java.util.Set;

public class SimplePaginationConfig implements PaginationConfig {

    private final String id;
    private final List<Integer> slots;
    private final InventoryItemConfig item;
    private final InventoryItemConfig previousPageItem;
    private final InventoryItemConfig nextPageItem;
    private final Set<Integer> previousPageItemSlots;
    private final Set<Integer> nextPageItemSlots;

    public SimplePaginationConfig(String id, Set<Integer> slots, InventoryItemConfig item, InventoryItemConfig prevousPageItem, InventoryItemConfig nextPageItem, List<Integer> previousPageItemSlots, List<Integer> nextPageItemSlots) {

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
        this.previousPageItem = prevousPageItem;
        this.nextPageItem = nextPageItem;
        this.previousPageItemSlots = Set.copyOf(previousPageItemSlots);
        this.nextPageItemSlots = Set.copyOf(nextPageItemSlots);
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

    @Override
    public InventoryItemConfig getPreviousPageItem() {
        return this.previousPageItem;
    }

    @Override
    public InventoryItemConfig getNextPageItem() {
        return this.nextPageItem;
    }

    @Override
    public Set<Integer> getPreviousPageItemSlots() {
        return this.previousPageItemSlots;
    }

    @Override
    public Set<Integer> getNextPageItemSlots() {
        return this.nextPageItemSlots;
    }
}
