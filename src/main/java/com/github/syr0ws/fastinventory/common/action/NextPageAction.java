package com.github.syr0ws.fastinventory.common.action;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.pagination.Pagination;

public class NextPageAction implements ClickAction {

    public static final String ACTION_NAME = "NEXT_PAGE";

    private final String paginationId;

    public NextPageAction(String paginationId) {

        if (paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        this.paginationId = paginationId;
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

        FastInventory inventory = event.getFastInventory();

        Pagination<?> pagination = inventory.getPagination(this.paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id %s", this.paginationId)));

        pagination.nextPage();

        inventory.update();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
