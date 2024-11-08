package com.github.syr0ws.fastinventory.common.inventory.action;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;

import java.util.Set;

public class NextPageAction extends CommonAction {

    public static final String ACTION_NAME = "NEXT_PAGE";

    private final String paginationId;

    public NextPageAction(Set<ClickType> clickTypes, String paginationId) {
        super(clickTypes);

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
