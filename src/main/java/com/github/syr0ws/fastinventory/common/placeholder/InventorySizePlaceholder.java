package com.github.syr0ws.fastinventory.common.placeholder;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;

public class InventorySizePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%inventory_size%";
    }

    @Override
    public String getValue(Context context) {
        FastInventory inventory = context.getData(CommonContextKeyEnum.INVENTORY.name(), FastInventory.class);
        return Integer.toString(inventory.getSize());
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKeyEnum.INVENTORY.name());
    }
}
