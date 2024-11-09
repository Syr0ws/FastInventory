package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.mapping.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.transform.mapping.InventoryItemMapper;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;

public class CommonPaginationItemProvider implements Provider<InventoryItem> {

    private final InventoryItemMapper mapper;

    public CommonPaginationItemProvider(InventoryItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        InventoryConfig inventoryConfig = provider.getConfig();

        PaginationConfig paginationConfig = inventoryConfig.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination with id '%s' found", paginationId)));

        InventoryItemConfig itemConfig = paginationConfig.getItem();

        InventoryItemDto dto = this.mapper.toDto(itemConfig, provider, context);
        dto = this.mapper.enhance(dto, provider, context);

        return this.mapper.fromDto(dto, provider, context);
    }

    @Override
    public String getName() {
        return CommonProviderType.PAGINATION_ITEM.name();
    }

    @Override
    public Class<InventoryItem> getType() {
        return InventoryItem.class;
    }
}
