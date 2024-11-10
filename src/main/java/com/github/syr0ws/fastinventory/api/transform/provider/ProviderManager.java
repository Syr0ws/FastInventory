package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.Optional;
import java.util.Set;

public interface ProviderManager {

    /**
     * Provides a DTO instance.
     *
     * @param providerName The name of the provider.
     * @param dtoClass     The Java class type of the DTO the provider handles.
     * @param provider     The inventory provider that makes the call.
     * @param context      A Context instance that contains additional data.
     * @return An instance of the provided DTO class.
     */
    <T extends DTO> Optional<T> provide(String providerName, Class<T> dtoClass, InventoryProvider provider, Context context);

    /**
     * Add a new provider.
     *
     * @param provider The provider instance to add.
     */
    void addProvider(Provider<?> provider);

    /**
     * Remove an existing provider.
     *
     * @param providerName The name of the provider to remove.
     * @return true if the provider exists and has been removed or else false.
     */
    boolean removeProvider(String providerName);

    /**
     * Get a provider.
     *
     * @param providerName The name of the provider to get.
     * @param dtoClass     The Java class type of the DTO the provider handles.
     * @return An Optional that contains the provider instance if the provider exists or else
     * an empty optional.
     */
    <T extends DTO> Optional<Provider<T>> getProvider(String providerName, Class<T> dtoClass);

    /**
     * Get all the registered providers.
     *
     * @return A Set of providers.
     */
    Set<Provider<?>> getProviders();
}
