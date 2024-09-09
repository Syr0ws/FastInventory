package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;
import com.github.syr0ws.fastinventory.internal.item.SimpleInventoryItem;
import org.bukkit.inventory.ItemStack;

public class CommonInventoryItemProvider implements Provider<InventoryItem> {

    private final ItemParser itemParser;

    public CommonInventoryItemProvider(ItemParser itemParser) {

        if(itemParser == null) {
            throw new IllegalArgumentException("itemParser cannot be null");
        }

        this.itemParser = itemParser;
    }

    @Override
    public InventoryItem provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        Integer slot = context.getData(CommonContextKeyEnum.SLOT.name(), Integer.class);
        InventoryItemConfig itemConfig = config.getContent().get(slot);

        if(itemConfig == null) {
            return null;
        }

        ItemStack item = this.itemParser.parse(provider, itemConfig.getItemStack(), context);

        return new SimpleInventoryItem(itemConfig.getId(), item, itemConfig.getActions());
    }

    @Override
    public String getName() {
        return CommonProviderEnum.CONTENT_ITEM.name();
    }

    @Override
    public Class<InventoryItem> getType() {
        return InventoryItem.class;
    }
}
