package com.github.syr0ws.craftventory.internal.transform.provider;

import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import com.github.syr0ws.craftventory.api.transform.provider.Provider;
import com.github.syr0ws.craftventory.api.transform.provider.ProviderManager;
import com.github.syr0ws.craftventory.api.util.Context;

import java.util.*;

public class SimpleProviderManager implements ProviderManager {

    private final Map<String, Provider<?>> providers = new HashMap<>();

    @Override
    public <T extends DTO> Optional<T> provide(String providerName, Class<T> dtoClass, InventoryProvider inventoryProvider, Context context) {

        if(providerName == null) {
            throw new IllegalArgumentException("providerName cannot be null");
        }

        if(dtoClass == null) {
            throw new IllegalArgumentException("dtoClass cannot be null");
        }

        if(inventoryProvider == null) {
            throw new IllegalArgumentException("inventoryProvider cannot be null");
        }

        if(context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        return this.getProvider(providerName, dtoClass)
                .map(provider -> provider.provide(inventoryProvider, context));
    }

    @Override
    public void addProvider(Provider<?> provider) {

        if (provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        this.providers.put(provider.getName(), provider);
    }

    @Override
    public boolean removeProvider(String providerName) {

        if (providerName == null || providerName.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }

        return this.providers.remove(providerName) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DTO> Optional<Provider<T>> getProvider(String providerName, Class<T> dtoClass) {

        if (providerName == null || providerName.isEmpty()) {
            throw new IllegalArgumentException("providerName cannot be null or empty");
        }

        if (dtoClass == null) {
            throw new IllegalArgumentException("dtoClass cannot be null");
        }

        return this.providers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(providerName))
                .map(Map.Entry::getValue)
                .filter(provider -> provider.getDTOClass().equals(dtoClass))
                .map(provider -> (Provider<T>) provider)
                .findFirst();
    }

    @Override
    public Set<Provider<?>> getProviders() {
        return new HashSet<>(this.providers.values());
    }
}
