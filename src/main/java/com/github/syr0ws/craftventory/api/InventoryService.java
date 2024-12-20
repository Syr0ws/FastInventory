package com.github.syr0ws.craftventory.api;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.transform.InventoryDescriptor;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

/**
 * Service interface for managing inventory viewers and providers.
 */
public interface InventoryService {

    /**
     * Retrieves the {@link InventoryViewer} instance associated with the specified player.
     * <p>
     * By default, an {@link InventoryViewer} instance is created for each player joining the server.
     * This instance is removed when the player leaves the server.
     * </p>
     *
     * @param player The {@link Player} whose {@link InventoryViewer} is to be retrieved. Must not be {@code null}.
     * @return The {@link InventoryViewer} associated with the specified player.
     * @throws IllegalArgumentException If the player is {@code null}.
     */
    InventoryViewer getInventoryViewer(Player player);

    /**
     * Retrieves all the {@link InventoryViewer} instances currently managed by this service.
     *
     * @return A {@link Set} of all registered {@link InventoryViewer}. Never {@code null}.
     */
    Set<InventoryViewer> getInventoryViewers();

    /**
     * Loads the inventory configurations for all registered {@link InventoryProvider} instances.
     */
    void loadInventoryConfigs();

    /**
     * Creates and register a new {@link InventoryProvider} from an {@link InventoryDescriptor}.
     *
     * @param descriptor The {@link InventoryDescriptor} that describes the inventory properties. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code provider} is {@code null}.
     */
    void createProvider(InventoryDescriptor descriptor);

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
