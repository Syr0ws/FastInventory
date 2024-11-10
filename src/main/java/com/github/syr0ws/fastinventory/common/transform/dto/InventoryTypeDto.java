package com.github.syr0ws.fastinventory.common.transform.dto;

import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;

public class InventoryTypeDto implements DTO {

    private FastInventoryType type;

    public InventoryTypeDto(FastInventoryType type) {
        this.type = type;
    }

    public FastInventoryType getType() {
        return this.type;
    }

    public void setType(FastInventoryType type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return DtoNameEnum.INVENTORY_TYPE.name();
    }
}
