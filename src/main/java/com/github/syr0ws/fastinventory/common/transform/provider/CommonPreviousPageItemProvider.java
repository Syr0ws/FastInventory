package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.common.transform.mapping.InventoryItemMapper;

public class CommonPreviousPageItemProvider extends CommonPageItemProvider {

    public CommonPreviousPageItemProvider(InventoryItemMapper mapper) {
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
