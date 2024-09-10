package com.github.syr0ws.fastinventory.common.placeholder.inventory;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;

public class InventoryTypePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%inventory_type%";
    }

    @Override
    public String getValue(Context context) {
        FastInventory inventory = context.getData(CommonContextKey.INVENTORY.name(), FastInventory.class);
        return inventory.getType().name();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.INVENTORY.name());
    }
}
