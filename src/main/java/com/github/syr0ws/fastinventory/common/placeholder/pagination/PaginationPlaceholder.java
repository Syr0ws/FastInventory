package com.github.syr0ws.fastinventory.common.placeholder.pagination;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;

public abstract class PaginationPlaceholder implements Placeholder {

    protected PaginationModel<?> getPaginationModel(Context context) {

        FastInventory inventory = context.getData(CommonContextKey.INVENTORY.name(), FastInventory.class);
        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        Pagination<?> pagination = inventory.getPagination(paginationId)
                .orElseThrow(() -> new IllegalStateException(String.format("No pagination with id %s found", paginationId)));

        return pagination.getModel();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.PAGINATION_ID.name());
    }
}
