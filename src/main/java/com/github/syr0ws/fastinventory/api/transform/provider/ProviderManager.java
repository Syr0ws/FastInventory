package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.Optional;
import java.util.Set;

/**
 * Manages {@link Provider} instances' lifecycle.
 */
public interface ProviderManager {

    /**
     * Provides an instance of a {@link DTO} using the specified provider.
     * <p>
     * This method uses the provider's name and the DTO class type to resolve and provide an instance of the requested DTO.
     * The {@link InventoryProvider} and {@link Context} are used to supply any additional data required for DTO creation.
     * </p>
     *
     * @param providerName The name of the {@link Provider} to use. Must not be {@code null}.
     * @param dtoClass     The {@link Class} type of the {@link DTO} that the provider handles. Must not be {@code null}.
     * @param provider     The {@link InventoryProvider} that makes the request. Must not be {@code null}.
     * @param context      A {@link Context} instance containing additional data needed to provide the DTO. Must not be {@code null}.
     * @return An {@link Optional} containing the DTO instance provided by the {@link Provider}, or an empty {@link Optional} if the provider cannot provide the DTO.
     * @throws IllegalArgumentException If any parameter is null.
     */
    <T extends DTO> Optional<T> provide(String providerName, Class<T> dtoClass, InventoryProvider provider, Context context);

    /**
     * Registers a new {@link Provider} to the manager.
     *
     * @param provider The {@link Provider} instance to register. Must not be {@code null}.
     * @throws IllegalArgumentException If {@code provider} is {@code null}.
     */
    void addProvider(Provider<?> provider);

    /**
     * Removes an existing {@link Provider} by its name.
     *
     * @param providerName The name of the provider to remove. Must not be {@code null}.
     * @return {@code true} if the provider was found and removed, {@code false} otherwise.
     * @throws IllegalArgumentException If {@code providerName} is {@code null}.
     */
    boolean removeProvider(String providerName);

    /**
     * Retrieves a specific {@link Provider} by its name and DTO class type.
     *
     * @param providerName The name of the provider to retrieve. Must not be {@code null}.
     * @param dtoClass     The {@link Class} type of the {@link DTO} the provider handles. Must not be {@code null}.
     * @return An {@link Optional} containing the {@link Provider} if it exists, or an empty {@link Optional} otherwise.
     * @throws IllegalArgumentException If {@code providerName} or {@code dtoClass} is {@code null}.
     */
    <T extends DTO> Optional<Provider<T>> getProvider(String providerName, Class<T> dtoClass);

    /**
     * Retrieves all the currently registered {@link Provider} instances.
     *
     * @return A {@link Set} containing all the registered {@link Provider} instances.
     */
    Set<Provider<?>> getProviders();
}
