package com.github.syr0ws.craftventory.common.transform.provider.pagination;

import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractPaginationPageItemProvider extends AbstractPaginationDataProvider<InventoryItemDto> {

    private final ItemParser itemParser;

    public AbstractPaginationPageItemProvider(ItemParser itemParser) {

        if (itemParser == null) {
            throw new IllegalArgumentException("itemParser cannot be null");
        }

        this.itemParser = itemParser;
    }

    protected void process(InventoryProvider provider, Context context, InventoryItemDto dto) {

        // Enhancement
        EnhancementManager enhancementManager = provider.getEnhancementManager();
        enhancementManager.enhance(dto, InventoryItemDto.class, context);

        // Additional parsing
        ItemStack item = this.itemParser.parse(provider, dto.getItem(), context);
        dto.setItem(item);
    }

    protected ItemParser getItemParser() {
        return this.itemParser;
    }
}
