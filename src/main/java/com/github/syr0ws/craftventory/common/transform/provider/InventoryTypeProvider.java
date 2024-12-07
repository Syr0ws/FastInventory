package com.github.syr0ws.craftventory.common.transform.provider;

import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.provider.Provider;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryTypeDto;

public class InventoryTypeProvider implements Provider<InventoryTypeDto> {

    @Override
    public InventoryTypeDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        InventoryConfig config = provider.getConfig();
        CraftVentoryType type = config.getType();

        // DTO creation
        InventoryTypeDto dto = new InventoryTypeDto(type);

        // Enhancement
        EnhancementManager enhancementManager = provider.getEnhancementManager();
        enhancementManager.enhance(dto, InventoryTypeDto.class, context);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.INVENTORY_TYPE.name();
    }

    @Override
    public Class<InventoryTypeDto> getDTOClass() {
        return InventoryTypeDto.class;
    }
}
