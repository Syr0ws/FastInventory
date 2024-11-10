package com.github.syr0ws.fastinventory.common.transform.dto.pagination;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.common.transform.dto.DtoNameEnum;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.util.IdUtil;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class PaginationPageItemDto extends InventoryItemDto {

    private final String dtoId;
    private final String paginationId;

    public PaginationPageItemDto(String itemId, ItemStack item, List<ClickAction> actions, Set<Integer> slots,
                                 String paginationId, PageItemType pageItemType) {
        super(itemId, item, actions, slots);

        if(paginationId == null) {
            throw new IllegalArgumentException("paginationId cannot be null");
        }

        if(pageItemType == null) {
            throw new IllegalArgumentException("pageItemType cannot be null");
        }

        this.paginationId = paginationId;
        this.dtoId = pageItemType == PageItemType.PREVIOUS_PAGE ?
                DtoNameEnum.PAGINATION_PREVIOUS_PAGE_ITEM.name() :
                DtoNameEnum.PAGINATION_NEXT_PAGE_ITEM.name();
    }

    @Override
    public String getId() {
        return this.dtoId;
    }

    public String getPaginationId() {
        return this.paginationId;
    }

    public enum PageItemType {
        PREVIOUS_PAGE, NEXT_PAGE
    }
}
