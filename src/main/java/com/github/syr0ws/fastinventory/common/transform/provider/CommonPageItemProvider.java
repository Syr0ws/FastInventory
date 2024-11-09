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

public abstract class CommonPageItemProvider implements Provider<InventoryItem> {

    private final InventoryItemMapper mapper;

    protected CommonPageItemProvider(InventoryItemMapper mapper) {
        this.mapper = mapper;
    }

    protected abstract InventoryItemConfig getPageItemConfig(PaginationConfig config);

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        PaginationConfig paginationConfig = config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id %s", paginationId)));

        InventoryItemConfig itemConfig = this.getPageItemConfig(paginationConfig);

        InventoryItemDto dto = this.mapper.toDto(itemConfig, provider, context);
        dto = this.mapper.enhance(dto, provider, context);

        return this.mapper.fromDto(dto, provider, context);
    }

    @Override
    public Class<InventoryItem> getType() {
        return InventoryItem.class;
    }
}
