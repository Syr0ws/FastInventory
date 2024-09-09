package com.github.syr0ws.fastinventory.api.pagination;

import java.util.List;

public interface Pagination<T> {

    void update();

    void previousPage();

    void nextPage();

    String getId();

    PaginationModel<T> getModel();

    List<Integer> getSlots();
}
