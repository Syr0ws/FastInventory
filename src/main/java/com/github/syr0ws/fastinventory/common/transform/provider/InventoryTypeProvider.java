package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryTypeDto;

public class InventoryTypeProvider implements Provider<InventoryTypeDto> {

    @Override
    public InventoryTypeDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        InventoryConfig config = provider.getConfig();
        FastInventoryType type = config.getType();

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
