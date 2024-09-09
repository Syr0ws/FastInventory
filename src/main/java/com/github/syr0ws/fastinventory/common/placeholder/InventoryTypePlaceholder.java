package com.github.syr0ws.fastinventory.common.placeholder;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;

public class InventoryTypePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%inventory_type%";
    }

    @Override
    public String getValue(Context context) {
        FastInventory inventory = context.getData(CommonContextKeyEnum.INVENTORY.name(), FastInventory.class);
        return inventory.getType().name();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKeyEnum.INVENTORY.name());
    }
}
