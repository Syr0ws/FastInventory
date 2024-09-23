package com.github.syr0ws.fastinventory.common.mapping;

import com.github.syr0ws.fastinventory.api.mapping.Dto;

import java.util.List;
import java.util.Set;

public class PaginationDto implements Dto {

    private final String id;
    private final List<Integer> slots;
    private final InventoryItemDto paginationItem, previousPageItem, nextPageItem;
    private final Set<Integer> previousPageItemSlots, nextPageItemSlots;

    public PaginationDto(String id, List<Integer> slots, InventoryItemDto paginationItem, InventoryItemDto previousPageItem, InventoryItemDto nextPageItem, Set<Integer> previousPageItemSlots, Set<Integer> nextPageItemSlots) {
        this.id = id;
        this.slots = slots;
        this.paginationItem = paginationItem;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.previousPageItemSlots = previousPageItemSlots;
        this.nextPageItemSlots = nextPageItemSlots;
    }

    public String getId() {
        return this.id;
    }

    public List<Integer> getSlots() {
        return this.slots;
    }

    public InventoryItemDto getPaginationItem() {
        return this.paginationItem;
    }

    public InventoryItemDto getPreviousPageItem() {
        return this.previousPageItem;
    }

    public InventoryItemDto getNextPageItem() {
        return this.nextPageItem;
    }

    public Set<Integer> getPreviousPageItemSlots() {
        return this.previousPageItemSlots;
    }

    public Set<Integer> getNextPageItemSlots() {
        return this.nextPageItemSlots;
    }
}
