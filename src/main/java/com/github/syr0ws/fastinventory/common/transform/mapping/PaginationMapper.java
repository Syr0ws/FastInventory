package com.github.syr0ws.fastinventory.common.transform.mapping;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.transform.mapping.Mapper;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.transform.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.inventory.pagination.SimplePagination;
import com.github.syr0ws.fastinventory.internal.inventory.pagination.SimplePaginationModel;

import java.util.List;
import java.util.function.Supplier;

public class PaginationMapper<T> implements Mapper<PaginationConfig, PaginationDto, Pagination<T>> {

    private final InventoryItemMapper itemMapper;
    private final Class<T> dataType;
    private final Supplier<List<T>> supplier;

    public PaginationMapper(InventoryItemMapper itemMapper, Class<T> dataType, Supplier<List<T>> supplier) {
        this.itemMapper = itemMapper;
        this.dataType = dataType;
        this.supplier = supplier;
    }

    @Override
    public PaginationDto toDto(PaginationConfig config, InventoryProvider provider, Context context) {
        return new PaginationDto(
                config.getId(),
                config.getSlots(),
                this.itemMapper.toDto(config.getItem(), provider, context),
                this.itemMapper.toDto(config.getPreviousPageItem(), provider, context),
                this.itemMapper.toDto(config.getNextPageItem(), provider, context),
                config.getPreviousPageItemSlots(),
                config.getNextPageItemSlots()
        );
    }

    @Override
    public PaginationDto enhance(PaginationDto dto, InventoryProvider provider, Context context) {

        // Pagination item should not be enhanced here because this is called when building the
        // pagination for the first time. That means that the context does not contain any pagination
        // item data.
        this.itemMapper.enhance(dto.getPreviousPageItem(), provider, context);
        this.itemMapper.enhance(dto.getNextPageItem(), provider, context);

        return dto;
    }

    @Override
    public Pagination<T> fromDto(PaginationDto dto, InventoryProvider provider, Context context) {

        FastInventory inventory = context.getData(CommonContextKey.INVENTORY.name(), FastInventory.class);
        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        PaginationModel<T> model = new SimplePaginationModel<>(this.dataType, dto.getSlots().size());
        model.setItems(this.supplier.get());

        return new SimplePagination<>(paginationId, inventory, model, dto.getSlots());
    }
}
