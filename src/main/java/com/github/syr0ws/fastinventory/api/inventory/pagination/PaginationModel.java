package com.github.syr0ws.fastinventory.api.inventory.pagination;

import java.util.Collection;
import java.util.List;

public interface PaginationModel<T> {

    void nextPage();

    void previousPage();

    int countItems();

    int countPages();

    Collection<T> getItems();

    void setItems(Collection<T> items);

    int getCurrentPage();

    void setCurrentPage(int page);

    List<T> getCurrentItems();

    int getPerPage();

    int getFirstPage();

    int getLastPage();

    int getPreviousPage();

    int getNextPage();

    boolean hasPreviousPage();

    boolean hasNextPage();

    Class<T> getDataType();
}
