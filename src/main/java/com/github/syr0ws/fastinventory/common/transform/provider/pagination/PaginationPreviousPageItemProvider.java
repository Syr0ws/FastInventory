package com.github.syr0ws.fastinventory.common.transform.provider.pagination;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.item.ItemParser;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.transform.dto.pagination.PaginationPageItemDto;
import com.github.syr0ws.fastinventory.common.transform.provider.ProviderNameEnum;

public class PaginationPreviousPageItemProvider extends AbstractPaginationPageItemProvider {

    public PaginationPreviousPageItemProvider(ItemParser itemParser) {
        super(itemParser);
    }

    @Override
    public InventoryItemDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        PaginationConfig paginationConfig = super.getPaginationConfig(provider, context);
        InventoryItemConfig itemConfig = paginationConfig.getPreviousPageItem();

        // DTO creation
        InventoryItemDto dto = new PaginationPageItemDto(
                itemConfig.getId(),
                itemConfig.getItemStack(),
                itemConfig.getActions(),
                paginationConfig.getPreviousPageItemSlots(),
                paginationConfig.getId(),
                PaginationPageItemDto.PageItemType.PREVIOUS_PAGE
        );

        super.process(provider, context, dto);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.PAGINATION_PREVIOUS_PAGE_ITEM.name();
    }

    @Override
    public Class<InventoryItemDto> getDTOClass() {
        return InventoryItemDto.class;
    }
}
