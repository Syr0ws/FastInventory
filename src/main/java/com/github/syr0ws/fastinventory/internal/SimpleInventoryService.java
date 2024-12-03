package com.github.syr0ws.fastinventory.internal;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;

import java.util.*;

public class SimpleInventoryService implements InventoryService {

    private final Map<String, InventoryProvider> providers = new HashMap<>();

    @Override
    public void addProvider(InventoryProvider provider) {

        if (provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        this.providers.put(provider.getId(), provider);
    }

    @Override
    public void removeProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        this.providers.remove(providerId);
    }

    @Override
    public boolean hasProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        return this.providers.containsKey(providerId);
    }

    @Override
    public Optional<InventoryProvider> getProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        return Optional.ofNullable(this.providers.get(providerId));
    }

    @Override
    public Set<InventoryProvider> getProviders() {
        return new HashSet<>(this.providers.values());
    }
}
