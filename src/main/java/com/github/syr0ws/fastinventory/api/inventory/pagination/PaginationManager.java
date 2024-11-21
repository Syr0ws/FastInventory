package com.github.syr0ws.fastinventory.api.inventory.pagination;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;

import java.util.List;
import java.util.Optional;

/**
 * Interface responsible for managing the paginations of an inventory.
 */
public interface PaginationManager {

    /**
     * Adds a new pagination.
     *
     * @param pagination The {@link Pagination} instance to add.
     * @throws IllegalArgumentException If {@code pagination} is null.
     */
    void addPagination(Pagination<?> pagination);

    /**
     * Removes a pagination by id.
     *
     * @param paginationId The id of the {@link Pagination} to remove.
     * @throws IllegalArgumentException If {@code paginationId} is null.
     */
    boolean removePagination(String paginationId);

    /**
     * Updates a pagination by id.
     * <p>
     * This method only updates the content internally and does not update the inventory view. To manually
     * update the inventory view, use the {@link FastInventory#updateView()} method.
     * </p>
     *
     * @param paginationId The id of the {@link Pagination} to update.
     * @throws IllegalArgumentException If {@code paginationId} is null.
     */
    void updatePagination(String paginationId);

    /**
     * Updates all the registered paginations.
     * <p>
     * This method only updates the content internally and does not update the inventory view. To manually
     * update the inventory view, use the {@link FastInventory#updateView()} method.
     * </p>
     */
    void updatePaginations();

    /**
     * Retrieves a pagination by id.
     *
     * @param paginationId The id of the pagination to retrieve.
     * @return An {@link Optional} containing the pagination, or an empty {@link Optional} if not found.
     */
    Optional<Pagination<?>> getPagination(String paginationId);

    /**
     * Retrieves a pagination by id and the expected data type.
     *
     * @param paginationId The id of the pagination to retrieve.
     * @param type The {@link Class} type of the data the pagination handles.
     * @param <T> The type of the data the pagination handles.
     * @return An {@link Optional} containing the pagination, or an empty {@link Optional} if not found.
     */
    <T> Optional<Pagination<T>> getPagination(String paginationId, Class<T> type);

    /**
     * Retrieves all the registered paginations.
     *
     * @return A list of all the registered paginations.
     */
    List<Pagination<?>> getPaginations();
}
