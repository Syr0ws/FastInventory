package com.github.syr0ws.fastinventory.api;

import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

/**
 * Service interface for managing inventories and inventory providers.
 */
public interface InventoryService {

    InventoryViewer getInventoryViewer(Player player);

    Set<InventoryViewer> getInventoryViewers();

    /**
     * Registers a new {@link InventoryProvider} in the service.
     *
     * @param provider The {@link InventoryProvider} to be registered. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code provider} is {@code null}.
     */
    void addProvider(InventoryProvider provider);

    /**
     * Removes the {@link InventoryProvider} by id.
     *
     * @param providerId The unique identifier of the provider to remove. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code providerId} is {@code null}.
     */
    void removeProvider(String providerId);

    /**
     * Checks if an {@link InventoryProvider} with the given id is registered.
     *
     * @param providerId The unique identifier of the provider to check. Must not be {@code null}.
     * @return {@code true} if a provider with the specified id exists, otherwise {@code false}.
     * @throws IllegalArgumentException If the {@code providerId} is {@code null}.
     */
    boolean hasProvider(String providerId);

    /**
     * Retrieves the {@link InventoryProvider} associated with the given id.
     *
     * @param providerId The unique identifier of the provider to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the {@link InventoryProvider} if it exists,
     *         or an empty {@link Optional} if no provider is found.
     * @throws IllegalArgumentException If the {@code providerId} is {@code null}.
     */
    Optional<InventoryProvider> getProvider(String providerId);

    /**
     * Retrieves all currently registered {@link InventoryProvider} in the service.
     *
     * @return A {@link Set} of all registered {@link InventoryProvider} instances.
     */
    Set<InventoryProvider> getProviders();
}
