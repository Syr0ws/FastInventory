package com.github.syr0ws.fastinventory.api.config;

import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents the configuration of an inventory.
 */
public interface InventoryConfig extends Configuration {

    /**
     * Retrieves the unique id of the inventory.
     *
     * @return The inventory's unique id. Must not be {@code null}.
     */
    String getId();

    /**
     * Retrieves the title of the inventory.
     *
     * @return The inventory's title. Must not be {@code null}.
     */
    String getTitle();

    /**
     * Retrieves the type of the inventory.
     *
     * @return The {@link FastInventoryType} of the inventory. Must not be {@code null}.
     */
    FastInventoryType getType();

    /**
     * Retrieves the content of the inventory as a map of slots and their corresponding item.
     *
     * @return An immutable map of slots and their corresponding {@link InventoryItemConfig}.
     * An {@link InventoryItemConfig} instance in this map may be {@code null} if there is no item at the specified slot.
     * Must not be {@code null}.
     */
    Map<Integer, InventoryItemConfig> getContent();

    /**
     * Retrieves the pagination configuration by id.
     *
     * @param paginationId The id of the pagination to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the {@link PaginationConfig} if it exists, or an empty {@link Optional}.
     */
    Optional<PaginationConfig> getPaginationConfig(String paginationId);

    /**
     * Retrieves all pagination configurations associated with the inventory.
     *
     * @return An immutable set of {@link PaginationConfig}. Must not be {@code null}.
     */
    Set<PaginationConfig> getPaginationConfigs();
}
