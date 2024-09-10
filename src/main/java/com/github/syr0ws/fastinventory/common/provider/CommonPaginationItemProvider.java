package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.item.SimpleInventoryItem;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CommonPaginationItemProvider implements Provider<InventoryItem> {

    private final ItemParser itemParser;

    public CommonPaginationItemProvider(ItemParser itemParser) {

        if(itemParser == null) {
            throw new IllegalArgumentException("itemParser cannot be null");
        }

        this.itemParser = itemParser;
    }

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        InventoryConfig inventoryConfig = provider.getConfig();

        PaginationConfig paginationConfig = inventoryConfig.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination with id '%s' found", paginationId)));

        InventoryItemConfig itemConfig = paginationConfig.getItem();

        String itemId = UUID.randomUUID().toString();
        ItemStack item = this.itemParser.parse(provider, itemConfig.getItemStack(), context);

        return new SimpleInventoryItem(itemId, item, itemConfig.getActions());
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
