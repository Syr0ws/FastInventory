package com.github.syr0ws.craftventory.common.transform.dto;

import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;
import com.github.syr0ws.craftventory.api.transform.dto.DTO;

public class InventoryTypeDto implements DTO {

    private CraftVentoryType type;

    public InventoryTypeDto(CraftVentoryType type) {
        this.type = type;
    }

    public CraftVentoryType getType() {
        return this.type;
    }

    public void setType(CraftVentoryType type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return DtoNameEnum.INVENTORY_TYPE.name();
    }
}
