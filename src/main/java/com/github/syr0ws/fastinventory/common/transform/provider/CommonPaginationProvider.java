package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.mapping.InventoryItemMapper;
import com.github.syr0ws.fastinventory.common.transform.mapping.PaginationDto;
import com.github.syr0ws.fastinventory.common.transform.mapping.PaginationMapper;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;

import java.util.List;
import java.util.function.Supplier;

public class CommonPaginationProvider<T> implements Provider<Pagination> {

    private final String paginationId;
    private final PaginationMapper<T> paginationMapper;

    public CommonPaginationProvider(String paginationId, Class<T> dataType, Supplier<List<T>> supplier, InventoryItemMapper mapper) {

        if (paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        if (dataType == null) {
            throw new IllegalArgumentException("dataType cannot be null");
        }

        if (supplier == null) {
            throw new IllegalArgumentException("supplier cannot be null");
        }

        this.paginationId = paginationId;
        this.paginationMapper = new PaginationMapper<>(mapper, dataType, supplier);
    }

    @Override
    public Pagination<?> provide(InventoryProvider provider, Context context) {

        FastInventory inventory = context.getData(CommonContextKey.INVENTORY.name(), FastInventory.class);
        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        InventoryConfig config = provider.getConfig();

        PaginationConfig paginationConfig = config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found in config with id %s", paginationId)));

        PaginationDto dto = this.paginationMapper.toDto(paginationConfig, provider, context);
        dto = this.paginationMapper.enhance(dto, provider, context);

        return this.paginationMapper.fromDto(dto, provider, context);
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
