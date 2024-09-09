package com.github.syr0ws.fastinventory.api.config;

import java.util.List;

public interface PaginationConfig {

    String getId();

    List<Integer> getSlots();

    InventoryItemConfig getItem();
}
