package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

/**
 * Represents a provider that supplies instances of a specific {@link DTO} type.
 * <p>
 * A {@link Provider} is responsible for generating or retrieving instances of a {@link DTO} based on
 * the provided {@link InventoryProvider} and {@link Context}.
 * </p>
 *
 * @param <T> The type of DTO that this provider generates or provides.
 */
public interface Provider<T extends DTO> {

    /**
     * Provides an instance of the specific {@link DTO} type that the provider is responsible for.
     *
     * @param provider The {@link InventoryProvider} that makes the request. Must not be {@code null}.
     * @param context  A {@link Context} instance containing additional data required to provide the DTO.
     *                 Must not be {@code null}.
     * @return An instance of the {@link DTO} that the provider has created.
     * @throws IllegalArgumentException If {@code provider} or {@code context} is {@code null}.
     */
    T provide(InventoryProvider provider, Context context);

    /**
     * Retrieves the name of the provider.
     *
     * @return A {@link String} representing the name of the provider. Must not be {@code null}.
     */
    String getName();

    /**
     * Retrieves the {@link Class} of the {@link DTO} this provider handles.
     *
     * @return The {@link Class} of the {@link DTO} this provider handles. Must not be {@code null}.
     */
    Class<T> getDTOClass();
}
