package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.FastInventoryClickEvent;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;

import java.util.Set;

public class PreviousPageAction extends CommonAction {

    public static final String ACTION_NAME = "PREVIOUS_PAGE";

    private final String paginationId;

    public PreviousPageAction(Set<ClickType> clickTypes, String paginationId) {
        super(clickTypes);

        if (paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        this.paginationId = paginationId;
    }

    @Override
    public void execute(FastInventoryClickEvent event) {

        CraftVentory inventory = event.getInventory();

        Pagination<?> pagination = inventory.getPaginationManager()
                .getPagination(this.paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id %s", this.paginationId)));

        pagination.previousPage();

        inventory.updateContent();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
