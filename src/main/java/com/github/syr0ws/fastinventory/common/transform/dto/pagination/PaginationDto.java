package com.github.syr0ws.fastinventory.common.transform.dto.pagination;

import com.github.syr0ws.fastinventory.api.transform.dto.DTO;

import java.util.List;
import java.util.function.Supplier;

public class PaginationDto<T> implements DTO {

    private final String paginationId;
    private final List<Integer> slots;
    private final Class<T> paginationDataType;
    private final Supplier<List<T>> dataSupplier;

    public PaginationDto(String paginationId, List<Integer> slots, Class<T> paginationDataType, Supplier<List<T>> dataSupplier) {
        this.paginationId = paginationId;
        this.slots = slots;
        this.paginationDataType = paginationDataType;
        this.dataSupplier = dataSupplier;
    }

    public String getPaginationId() {
        return this.paginationId;
    }

    public List<Integer> getSlots() {
        return this.slots;
    }

    public Class<T> getPaginationDataType() {
        return this.paginationDataType;
    }

    public Supplier<List<T>> getDataSupplier() {
        return this.dataSupplier;
    }

    @Override
    public String getId() {
        return this.paginationId;
    }
}
