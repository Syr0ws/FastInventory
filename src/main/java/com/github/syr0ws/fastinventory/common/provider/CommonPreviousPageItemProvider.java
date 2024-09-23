package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.common.mapping.InventoryItemMapper;

public class CommonPreviousPageItemProvider extends CommonPageItemProvider {

    protected CommonPreviousPageItemProvider(InventoryItemMapper mapper) {
        super(mapper);
    }

    @Override
    protected InventoryItemConfig getPageItemConfig(PaginationConfig config) {
        return config.getPreviousPageItem();
    }

    @Override
    public String getName() {
        return CommonProviderType.PAGINATION_PREVIOUS_PAGE_ITEM.name();
    }
}
