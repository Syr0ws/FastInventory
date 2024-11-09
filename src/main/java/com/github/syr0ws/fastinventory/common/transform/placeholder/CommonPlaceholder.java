package com.github.syr0ws.fastinventory.common.transform.placeholder;

import com.github.syr0ws.fastinventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.inventory.InventorySizePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.inventory.InventoryTypePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.item.ItemSlotPlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.pagination.CurrentPagePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.pagination.NextPagePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.pagination.PreviousPagePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.pagination.TotalPagesPlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.player.PlayerNamePlaceholder;
import com.github.syr0ws.fastinventory.common.transform.placeholder.player.PlayerUUIDPlaceholder;

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
