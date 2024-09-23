package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import com.github.syr0ws.fastinventory.common.mapping.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.mapping.InventoryItemMapper;

public class CommonInventoryItemProvider implements Provider<InventoryItem> {

    private final InventoryItemMapper mapper;

    public CommonInventoryItemProvider(InventoryItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        Integer slot = context.getData(CommonContextKey.SLOT.name(), Integer.class);
        InventoryItemConfig itemConfig = config.getContent().get(slot);

        InventoryItemDto dto = this.mapper.toDto(itemConfig, provider, context);
        dto = this.mapper.enhance(dto, provider, context);

        return this.mapper.fromDto(dto, provider, context);
    }

    @Override
    public String getName() {
        return CommonProviderType.CONTENT_ITEM.name();
    }

    @Override
    public Class<InventoryItem> getType() {
        return InventoryItem.class;
    }
}
