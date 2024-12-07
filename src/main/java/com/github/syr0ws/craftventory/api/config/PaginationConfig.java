package com.github.syr0ws.craftventory.api.config;

import java.util.List;
import java.util.Set;

/**
 * Represents a pagination configuration.
 */
public interface PaginationConfig extends Configuration {

    /**
     * Retrieves the unique id of the pagination.
     *
     * @return The pagination's unique id. Must not be {@code null} or empty.
     */
    String getId();

    /**
     * Retrieves the list of slots in which pagination items will be set.
     *
     * @return An immutable list of slot. Must not be {@code null}.
     */
    List<Integer> getSlots();

    /**
     * Retrieves the configuration of the pagination item.
     * <p>
     * This is the item in which each data in the pagination will be converted to
     * to be visible in the inventory.
     * </p>
     *
     * @return The {@link InventoryItemConfig} for the pagination item. Must not be {@code null}.
     */
    InventoryItemConfig getItem();

    /**
     * Retrieves the configuration of the item used for navigating to the previous page.
     *
     * @return The {@link InventoryItemConfig} for the previous page item. Must not be {@code null}.
     */
    InventoryItemConfig getPreviousPageItem();

    /**
     * Retrieves the configuration of the item used for navigating to the next page.
     *
     * @return The {@link InventoryItemConfig} for the next page item. Must not be {@code null}.
     */
    InventoryItemConfig getNextPageItem();

    /**
     * Retrieves the slots in which the previous page item will be set.
     *
     * @return An immutable set of slot indices. Must not be {@code null}.
     */
    Set<Integer> getPreviousPageItemSlots();

    /**
     * Retrieves the slots in which the next page item will be set.
     *
     * @return An immutable set of slot indices. Must not be {@code null}.
     */
    Set<Integer> getNextPageItemSlots();
}
