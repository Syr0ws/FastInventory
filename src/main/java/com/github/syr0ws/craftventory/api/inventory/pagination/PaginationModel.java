package com.github.syr0ws.craftventory.api.inventory.pagination;

import java.util.Collection;
import java.util.List;

/**
 * Represents the data and the state of a pagination.
 * @param <T> The type of the paginated data.
 */
public interface PaginationModel<T> {

    /**
     * Updates the pagination data.
     */
    void update();

    /**
     * Moves to the previous page in the pagination.
     */
    void previousPage();

    /**
     * Moves to the next page in the pagination.
     */
    void nextPage();

    /**
     * Counts the total number of items in the entire paginated dataset.
     *
     * @return The total number of items in the dataset.
     */
    int countItems();

    /**
     * Counts the total number of pages in the pagination.
     * This is based on the total number of items and the number of items per page.
     *
     * @return The total number of pages in the pagination.
     */
    int countPages();

    /**
     * Retrieves the collection of all items in the pagination.
     *
     * @return A collection of all items in the pagination.
     */
    Collection<T> getItems();

    /**
     * Retrieves the current page number.
     *
     * @return The current page number.
     */
    int getCurrentPage();

    /**
     * Sets the current page to the specified page number.
     *
     * @param page The page number to set as the current page.
     * @throws IllegalArgumentException If the given {@code page} number is invalid.
     */
    void setCurrentPage(int page);

    /**
     * Retrieves the list of items for the current page.
     *
     * @return The list of items for the current.
     */
    List<T> getCurrentItems();

    /**
     * Retrieves the number of items to be displayed per page.
     *
     * @return The number of items per page.
     */
    int getPerPage();

    /**
     * Retrieves the first page number of the pagination.
     *
     * @return The first page number.
     */
    int getFirstPage();

    /**
     * Retrieves the last page number of the pagination.
     *
     * @return The last page number.
     */
    int getLastPage();

    /**
     * Retrieves the page number of the previous page.
     *
     * @return The previous page number.
     */
    int getPreviousPage();

    /**
     * Retrieves the page number of the next page.
     *
     * @return The next page number.
     */
    int getNextPage();

    /**
     * Checks whether there is a previous page available.
     *
     * @return true if there is a previous page, false otherwise.
     */
    boolean hasPreviousPage();

    /**
     * Checks whether there is a next page available.
     *
     * @return true if there is a next page, false otherwise.
     */
    boolean hasNextPage();

    /**
     * Retrieves the type of the paginated data.
     *
     * @return The class type of the paginated data.
     */
    Class<T> getDataType();
}
