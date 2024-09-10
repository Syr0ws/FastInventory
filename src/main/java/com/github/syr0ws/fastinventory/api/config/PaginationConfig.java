package com.github.syr0ws.fastinventory.api.config;

import java.util.List;
import java.util.Set;

public interface PaginationConfig {

    String getId();

    List<Integer> getSlots();

    InventoryItemConfig getItem();

    InventoryItemConfig getPreviousPageItem();

    InventoryItemConfig getNextPageItem();

    Set<Integer> getPreviousPageItemSlots();

    Set<Integer> getNextPageItemSlots();
}
