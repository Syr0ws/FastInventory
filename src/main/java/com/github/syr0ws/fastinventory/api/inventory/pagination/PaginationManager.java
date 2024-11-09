package com.github.syr0ws.fastinventory.api.inventory.pagination;

import java.util.List;
import java.util.Optional;

public interface PaginationManager {

    void addPagination(Pagination<?> pagination);

    boolean removePagination(String paginationId);

    void updatePaginations();

    Optional<Pagination<?>> getPagination(String id);

    <T> Optional<Pagination<T>> getPagination(String id, Class<T> type);

    List<Pagination<?>> getPaginations();
}
