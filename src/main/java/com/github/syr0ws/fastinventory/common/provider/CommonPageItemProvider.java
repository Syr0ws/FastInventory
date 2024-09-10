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

public abstract class CommonPageItemProvider implements Provider<InventoryItem> {

    private final ItemParser parser;

    public CommonPageItemProvider(ItemParser parser) {

        if(parser == null) {
            throw new IllegalArgumentException("parser cannot be null");
        }

        this.parser = parser;
    }

    protected abstract InventoryItemConfig getPageItemConfig(PaginationConfig config);

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        String paginationId = context.getData(CommonContextKey.PAGINATION_ID.name(), String.class);

        PaginationConfig paginationConfig = config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id %s", paginationId)));

        InventoryItemConfig itemConfig = this.getPageItemConfig(paginationConfig);

        ItemStack item = this.parser.parse(provider, itemConfig.getItemStack(), context);

        return new SimpleInventoryItem(itemConfig.getId(), item, itemConfig.getActions());
    }

    @Override
    public Class<InventoryItem> getType() {
        return InventoryItem.class;
    }
}
