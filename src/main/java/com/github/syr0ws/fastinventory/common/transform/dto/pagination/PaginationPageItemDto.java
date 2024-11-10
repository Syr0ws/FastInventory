package com.github.syr0ws.fastinventory.common.transform.dto.pagination;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.util.IdUtil;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class PaginationPageItemDto extends InventoryItemDto {

    private final String dtoId;
    
    public PaginationPageItemDto(String itemId, ItemStack item, List<ClickAction> actions, Set<Integer> slots,
                                 String paginationId, PageItemType pageItemType) {
        super(itemId, item, actions, slots);

        this.dtoId = pageItemType == PageItemType.PREVIOUS_PAGE ?
                IdUtil.getPaginationPreviousPageItemId(paginationId) :
                IdUtil.getPaginationNextPageItemId(paginationId);

    }

    @Override
    public String getId() {
        return this.dtoId;
    }

    public enum PageItemType {
         PREVIOUS_PAGE, NEXT_PAGE
    }
}
