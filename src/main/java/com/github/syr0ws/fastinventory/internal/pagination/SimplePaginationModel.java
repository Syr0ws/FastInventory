package com.github.syr0ws.fastinventory.internal.pagination;

import com.github.syr0ws.fastinventory.api.pagination.PaginationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimplePaginationModel<T> implements PaginationModel<T> {

    private final Class<T> dataType;
    private final int perPage;
    private final List<T> items = new ArrayList<>();
    private int currentPage;

    public SimplePaginationModel(Class<T> dataType, int perPage) {

        if (dataType == null) {
            throw new IllegalArgumentException("dataType cannot be null");
        }

        if (perPage <= 0) {
            throw new IllegalArgumentException("perPage must be greater than 0");
        }

        this.dataType = dataType;
        this.perPage = perPage;
        this.currentPage = 1;
    }

    @Override
    public void nextPage() {

        if (!this.hasNextPage()) {
            throw new IllegalStateException("No next page");
        }

        this.currentPage++;
    }

    @Override
    public void previousPage() {

        if (!this.hasPreviousPage()) {
            throw new IllegalStateException("No previous page");
        }

        this.currentPage--;
    }

    @Override
    public int countItems() {
        return this.items.size();
    }

    @Override
    public int countPages() {
        return (int) Math.ceil(this.countItems() / (double) this.perPage);
    }

    @Override
    public Collection<T> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    @Override
    public void setItems(Collection<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public int getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public List<T> getCurrentItems() {

        int begin = (this.currentPage - 1) * this.perPage;
        int end = Math.min(this.currentPage * this.perPage, this.countItems());

        return this.items.subList(begin, end);
    }

    @Override
    public int getPerPage() {
        return this.perPage;
    }

    @Override
    public int getFirstPage() {
        return 1;
    }

    @Override
    public int getLastPage() {
        return this.countPages();
    }

    @Override
    public int getPreviousPage() {
        return this.hasPreviousPage() ? this.currentPage - 1 : this.getFirstPage();
    }

    @Override
    public int getNextPage() {
        return this.hasNextPage() ? this.currentPage + 1 : this.getLastPage();
    }

    @Override
    public boolean hasPreviousPage() {
        return this.currentPage > 1;
    }

    @Override
    public boolean hasNextPage() {
        return this.currentPage < this.getLastPage();
    }

    @Override
    public Class<T> getDataType() {
        return this.dataType;
    }
}
