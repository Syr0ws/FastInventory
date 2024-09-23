package com.github.syr0ws.fastinventory.api.provider;

import com.github.syr0ws.fastinventory.api.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;

public interface Provider<T> {

    T provide(InventoryProvider provider, Context context);

    String getName();

    Class<T> getType();
}
