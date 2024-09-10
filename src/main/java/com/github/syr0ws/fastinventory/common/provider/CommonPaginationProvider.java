package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.pagination.SimplePagination;
import com.github.syr0ws.fastinventory.internal.pagination.SimplePaginationModel;

import java.util.List;
import java.util.function.Supplier;

public class CommonPaginationProvider<T> implements Provider<Pagination> {

    private final String paginationId;
    private final Class<T> dataType;
    private final Supplier<List<T>> supplier;

    public CommonPaginationProvider(String paginationId, Class<T> dataType, Supplier<List<T>> supplier) {

        if(paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        if(dataType == null) {
            throw new IllegalArgumentException("dataType cannot be null");
        }

        if(supplier == null) {
            throw new IllegalArgumentException("supplier cannot be null");
        }

        this.paginationId = paginationId;
        this.dataType = dataType;
        this.supplier = supplier;
    }

    @Override
    public Pagination<?> provide(InventoryProvider provider, Context context) {

        FastInventory inventory = context.getData(CommonContextKey.INVENTORY.name(), FastInventory.class);
        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        InventoryConfig config = provider.getConfig();

        PaginationConfig paginationConfig = config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found in config with id %s", paginationId)));

        PaginationModel<T> model = new SimplePaginationModel<>(this.dataType, paginationConfig.getSlots().size());
        model.setItems(this.supplier.get());

        return new SimplePagination<>(paginationId, inventory, model, paginationConfig.getSlots());
    }

    @Override
    public String getName() {
        return this.paginationId;
    }

    @Override
    public Class<Pagination> getType() {
        return Pagination.class;
    }
}
