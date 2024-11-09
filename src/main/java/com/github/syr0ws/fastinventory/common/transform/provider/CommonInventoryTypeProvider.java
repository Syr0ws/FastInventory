package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;

public class CommonInventoryTypeProvider implements Provider<FastInventoryType> {

    @Override
    public FastInventoryType provide(InventoryProvider provider, Context context) {
        return provider.getConfig().getType();
    }

    @Override
    public String getName() {
        return CommonProviderType.INVENTORY_TYPE.name();
    }

    @Override
    public Class<FastInventoryType> getType() {
        return FastInventoryType.class;
    }
}
