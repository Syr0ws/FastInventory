package com.github.syr0ws.craftventory.common.transform.placeholder.pagination;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationModel;
import com.github.syr0ws.craftventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;

public abstract class PaginationPlaceholder implements Placeholder {

    protected PaginationModel<?> getPaginationModel(Context context) {

        CraftVentory inventory = context.getData(CommonContextKey.INVENTORY.name(), CraftVentory.class);
        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        Pagination<?> pagination = inventory.getPaginationManager()
                .getPagination(paginationId)
                .orElseThrow(() -> new IllegalStateException(String.format("No pagination with id %s found", paginationId)));

        return pagination.getModel();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.PAGINATION_ID.name());
    }
}
