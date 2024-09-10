package com.github.syr0ws.fastinventory.common.placeholder;

import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.common.placeholder.inventory.InventorySizePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.inventory.InventoryTypePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.item.ItemSlotPlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.pagination.CurrentPagePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.pagination.NextPagePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.pagination.PreviousPagePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.pagination.TotalPagesPlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.player.PlayerNamePlaceholder;
import com.github.syr0ws.fastinventory.common.placeholder.player.PlayerUUIDPlaceholder;

public enum CommonPlaceholder {

    INVENTORY_SIZE(new InventorySizePlaceholder()),
    INVENTORY_TYPE(new InventoryTypePlaceholder()),
    ITEM_SLOT(new ItemSlotPlaceholder()),
    PLAYER_NAME(new PlayerNamePlaceholder()),
    PLAYER_UUID(new PlayerUUIDPlaceholder()),
    CURRENT_PAGE(new CurrentPagePlaceholder()),
    TOTAL_PAGES(new TotalPagesPlaceholder()),
    PREVIOUS_PAGE(new PreviousPagePlaceholder()),
    NEXT_PAGE(new NextPagePlaceholder());

    private final Placeholder placeholder;

    CommonPlaceholder(Placeholder placeholder) {
        this.placeholder = placeholder;
    }

    public Placeholder getPlaceholder() {
        return this.placeholder;
    }
}
