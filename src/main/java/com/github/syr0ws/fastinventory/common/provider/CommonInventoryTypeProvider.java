package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.FastInventoryType;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;

public class CommonInventoryTypeProvider implements Provider<FastInventoryType> {

    @Override
    public FastInventoryType provide(InventoryProvider provider, Context context) {
        return provider.getConfig().getType();
    }

    @Override
    public String getName() {
        return CommonProviderEnum.INVENTORY_TYPE.name();
    }

    @Override
    public Class<FastInventoryType> getType() {
        return FastInventoryType.class;
    }
}
