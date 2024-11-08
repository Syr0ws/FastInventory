package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.Optional;
import java.util.Set;

public interface ProviderManager {

    <T> Optional<T> provide(String name, Class<T> type, InventoryProvider provider, Context context);

    void addProvider(Provider<?> provider);

    <T> Optional<Provider<T>> getProvider(String name, Class<T> type);

    Set<Provider<?>> getProviders();
}
