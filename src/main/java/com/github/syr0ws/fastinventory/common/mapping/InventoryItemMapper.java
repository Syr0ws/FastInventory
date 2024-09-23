package com.github.syr0ws.fastinventory.common.mapping;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.mapping.Enhancement;
import com.github.syr0ws.fastinventory.api.mapping.EnhancementManager;
import com.github.syr0ws.fastinventory.api.mapping.Mapper;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.internal.item.SimpleInventoryItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryItemMapper implements Mapper<InventoryItemConfig, InventoryItemDto, InventoryItem> {

    private final EnhancementManager enhancementManager;
    private final ItemParser parser;

    public InventoryItemMapper(EnhancementManager enhancementManager, ItemParser parser) {
        this.enhancementManager = enhancementManager;
        this.parser = parser;
    }

    @Override
    public InventoryItemDto toDto(InventoryItemConfig config, InventoryProvider provider, Context context) {

        if (config == null) {
            return null;
        }

        return new InventoryItemDto(config.getId(), config.getItemStack(), config.getActions());
    }

    @Override
    public InventoryItemDto enhance(InventoryItemDto dto, InventoryProvider provider, Context context) {

        if (dto == null) {
            return null;
        }

        List<Enhancement<InventoryItemDto>> enhancements = this.enhancementManager.getEnhancements(
                dto.getId(),
                InventoryItemDto.class
        );

        enhancements.forEach(enhancement -> enhancement.enhance(dto, context));

        return dto;
    }

    @Override
    public InventoryItem fromDto(InventoryItemDto dto, InventoryProvider provider, Context context) {

        if (dto == null) {
            return null;
        }

        ItemStack stack = this.parser.parse(provider, dto.getItem(), context);
        return new SimpleInventoryItem(dto.getId(), stack, dto.getActions());
    }
}
