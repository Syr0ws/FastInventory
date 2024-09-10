package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.item.ItemParser;

public class CommonPreviousPageItemProvider extends CommonPageItemProvider {

    public CommonPreviousPageItemProvider(ItemParser parser) {
        super(parser);
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
