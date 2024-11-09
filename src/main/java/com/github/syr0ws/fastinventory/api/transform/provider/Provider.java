package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;

public interface Provider<T> {

    T provide(InventoryProvider provider, Context context);

    String getName();

    Class<T> getType();
}
