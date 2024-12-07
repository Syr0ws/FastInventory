package com.github.syr0ws.craftventory.common.transform.placeholder.inventory;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;

public class InventoryTypePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%inventory_type%";
    }

    @Override
    public String getValue(Context context) {
        CraftVentory inventory = context.getData(CommonContextKey.INVENTORY.name(), CraftVentory.class);
        return inventory.getType().name();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.INVENTORY.name());
    }
}
