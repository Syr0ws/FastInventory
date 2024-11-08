package com.github.syr0ws.fastinventory.internal.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.transform.provider.ProviderManager;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.*;

public class SimpleProviderManager implements ProviderManager {

    private final Map<String, Provider<?>> providers = new HashMap<>();

    @Override
    public <T> Optional<T> provide(String name, Class<T> type, InventoryProvider inventoryProvider, Context context) {
        return this.getProvider(name, type).map(provider -> provider.provide(inventoryProvider, context));
    }

    @Override
    public void addProvider(Provider<?> provider) {

        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }

        this.providers.put(provider.getName(), provider);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<Provider<T>> getProvider(String name, Class<T> type) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        return this.providers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(name))
                .map(Map.Entry::getValue)
                .filter(provider -> provider.getType().equals(type))
                .map(provider -> (Provider<T>) provider)
                .findFirst();
    }

    @Override
    public Set<Provider<?>> getProviders() {
        return new HashSet<>(this.providers.values());
    }
}
