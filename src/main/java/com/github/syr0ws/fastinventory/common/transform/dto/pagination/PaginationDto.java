package com.github.syr0ws.fastinventory.common.transform.dto.pagination;

import com.github.syr0ws.fastinventory.api.transform.dto.DTO;

import java.util.List;
import java.util.function.Supplier;

public record PaginationDto<T>(String paginationId,
                               List<Integer> slots,
                               Class<T> paginationDataType,
                               Supplier<List<T>> dataSupplier) implements DTO {

    @Override
    public String getId() {
        return this.paginationId;
    }
}
