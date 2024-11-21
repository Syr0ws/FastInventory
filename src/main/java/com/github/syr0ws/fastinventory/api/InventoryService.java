package com.github.syr0ws.fastinventory.api;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Service interface for managing inventories and inventory providers.
 */
public interface InventoryService {

    /**
     * Registers a newly opened {@link FastInventory} in the service.
     *
     * @param inventory The {@link FastInventory} to be added. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code inventory} is {@code null}.
     */
    void addInventory(FastInventory inventory);

    /**
     * Removes the {@link FastInventory} associated with the given player from the service.
     *
     * @param player The {@link Player} whose inventory should be removed. Must not be {@code null}.
     * @throws IllegalArgumentException If the {@code player} is {@code null}.
     */
    void removeInventory(Player player);

    /**
     * Checks if there is a {@link FastInventory} associated with the given player.
     *
     * @param player The {@link Player} to check. Must not be {@code null}.
     * @return {@code true} if there is an inventory associated with the player, otherwise {@code false}.
     * @throws IllegalArgumentException If the {@code player} is {@code null}.
     */
    boolean hasInventory(Player player);

    /**
     * Retrieves the {@link FastInventory} associated with the given player.
     *
     * @param player The {@link Player} whose inventory is to be retrieved. Must not be {@code null}.
     * @return An {@link Optional} containing the {@link FastInventory} if it exists,
     *         or an empty {@link Optional} if no inventory is found.
     * @throws IllegalArgumentException If the {@code player} is {@code null}.
     */
    Optional<FastInventory> getInventory(Player player);

    /**
     * Retrieves all players and there associated inventories registered in the service.
     *
     * @return An unmodifiable {@link Map} where the keys are {@link Player} instances
     *         and the values are their associated {@link FastInventory}.
     */
    Map<Player, FastInventory> getInventories();

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
