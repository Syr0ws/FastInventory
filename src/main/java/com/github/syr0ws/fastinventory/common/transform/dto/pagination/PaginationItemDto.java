package com.github.syr0ws.fastinventory.common.transform.dto.pagination;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.common.util.IdUtil;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PaginationItemDto implements DTO {

    private final String paginationId;
    private final List<ClickAction> actions;
    private ItemStack item;

    public PaginationItemDto(String paginationId, ItemStack item, List<ClickAction> actions) {
        this.paginationId = paginationId;
        this.actions = actions;
        this.item = item;
    }

    public String getPaginationId() {
        return paginationId;
    }

    public List<ClickAction> getActions() {
        return this.actions;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public String getId() {
        return IdUtil.getPaginationItemId(this.paginationId);
    }
}
