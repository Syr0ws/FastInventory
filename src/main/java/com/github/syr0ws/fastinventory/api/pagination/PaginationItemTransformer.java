package com.github.syr0ws.fastinventory.api.pagination;

import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.util.Context;

@FunctionalInterface
public interface PaginationItemTransformer<T> {

    InventoryItem transform(Context context, T value, InventoryItem item);
}
