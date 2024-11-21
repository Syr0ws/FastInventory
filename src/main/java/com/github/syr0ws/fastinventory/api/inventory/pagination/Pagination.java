package com.github.syr0ws.fastinventory.api.inventory.pagination;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;

import java.util.List;

/**
 * Represents pagination for an inventory.
 * <p>
 * This interface allows to display a large dataset in an inventory by paginating it. It enables to
 * navigate through paginated content, such as moving between pages and updating the content accordingly.
 */
public interface Pagination<T> {

    /**
     * Updates the current page.
     * <p>
     * This method only updates the content internally and does not update the inventory view. To manually
     * update the inventory view, use the {@link FastInventory#updateView()} method.
     * </p>
     */
    void update();

    /**
     * Navigates to the previous page in the pagination. This method does not do anything if the current
     * page is already the first one of the pagination.
     * <p>
     * This method only updates the content internally and does not update the inventory view. To manually
     * update the inventory view, use the {@link FastInventory#updateView()} method.
     * </p>
     */
    void previousPage();

    /**
     * Navigates to the next page in the pagination. This method does not do anything if the current
     * page is already the last one of the pagination.
     * <p>
     * This method only updates the content internally and does not update the inventory view. To manually
     * update the inventory view, use the {@link FastInventory#updateView()} method.
     * </p>
     */
    void nextPage();

    /**
     * Retrieves the id of the pagination.
     *
     * @return The id of the pagination.
     */
    String getId();

    /**
     * Retrieves the model of the pagination.
     *
     * @return The model of the pagination.
     */
    PaginationModel<T> getModel();

    /**
     * Retrieves a list of slot where the pagination items are set.
     *
     * @return A list of integers representing the slots where pagination items are set.
     */
    List<Integer> getSlots();
}
