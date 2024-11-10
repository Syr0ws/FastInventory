package com.github.syr0ws.fastinventory.common.transform.provider.pagination;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.item.ItemParser;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.pagination.PaginationItemDto;
import com.github.syr0ws.fastinventory.common.transform.provider.ProviderNameEnum;
import org.bukkit.inventory.ItemStack;

public class PaginationItemProvider extends AbstractPaginationDataProvider<PaginationItemDto> {

    private final ItemParser itemParser;

    public PaginationItemProvider(ItemParser itemParser) {

        if (itemParser == null) {
            throw new IllegalArgumentException("itemParser cannot be null");
        }

        this.itemParser = itemParser;
    }

    @Override
    public PaginationItemDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        PaginationConfig paginationConfig = super.getPaginationConfig(provider, context);
        InventoryItemConfig itemConfig = paginationConfig.getItem();

        // DTO creation
        PaginationItemDto dto = new PaginationItemDto(
                paginationConfig.getId(),
                itemConfig.getItemStack(),
                itemConfig.getActions()
        );

        // Enhancement
        EnhancementManager enhancementManager = provider.getEnhancementManager();
        enhancementManager.enhance(dto, PaginationItemDto.class, context);

        // Additional parsing
        ItemStack item = this.itemParser.parse(provider, dto.getItem(), context);
        dto.setItem(item);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.PAGINATION_ITEM.name();
    }

    @Override
    public Class<PaginationItemDto> getDTOClass() {
        return PaginationItemDto.class;
    }
}
