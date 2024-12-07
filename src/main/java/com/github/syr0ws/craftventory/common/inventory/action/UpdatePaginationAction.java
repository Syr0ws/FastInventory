package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;

import java.util.Set;

public class UpdatePaginationAction extends CommonAction {

    public static final String ACTION_NAME = "UPDATE_PAGINATIONS";

    private final Set<String> paginationIds;

    public UpdatePaginationAction(Set<ClickType> clickTypes, Set<String> paginationIds) {
        super(clickTypes);

        if(paginationIds == null || paginationIds.isEmpty()) {
            throw new IllegalArgumentException("paginationIds cannot be null or empty");
        }

        this.paginationIds = paginationIds;
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {
        CraftVentory inventory = event.getInventory();
        this.paginationIds.forEach(inventory::updatePagination);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
