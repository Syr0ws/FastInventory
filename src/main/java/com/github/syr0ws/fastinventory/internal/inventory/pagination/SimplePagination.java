package com.github.syr0ws.fastinventory.internal.inventory.pagination;

import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryContent;
import com.github.syr0ws.fastinventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.transform.dto.pagination.PaginationItemDto;
import com.github.syr0ws.fastinventory.common.transform.provider.ProviderNameEnum;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.inventory.item.SimpleInventoryItem;

import java.util.Collections;
import java.util.List;

public class SimplePagination<T> implements Pagination<T> {

    private final String id;
    private final FastInventory inventory;
    private final PaginationModel<T> model;
    private final List<Integer> slots;

    public SimplePagination(String id, FastInventory inventory, PaginationModel<T> model, List<Integer> slots) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }

        if (slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }

        this.id = id;
        this.inventory = inventory;
        this.model = model;
        this.slots = slots;
    }

    @Override
    public void update() {
        this.model.update();
        this.updateCurrentPage();
        this.updatePaginationItems();
        this.updatePageItems();
    }

    private void updateCurrentPage() {

        int lastPage = this.model.getLastPage();

        if(this.model.getCurrentPage() > lastPage) {
            this.model.setCurrentPage(lastPage);
        }
    }

    private void updatePaginationItems() {

        InventoryProvider provider = this.inventory.getProvider();
        InventoryContent content = this.inventory.getContent();

        List<T> items = this.model.getCurrentItems();

        int i = 0;

        for (int slot : this.slots) {

            InventoryItem item = null;

            if (i < items.size()) {

                Context context = this.getPaginationContext();
                context.addData(CommonContextKey.SLOT.name(), slot, Integer.class);
                context.addData(CommonContextKey.PAGINATION_ITEM.name(), items.get(i), this.model.getDataType());

                PaginationItemDto dto = provider.getProviderManager()
                        .provide(ProviderNameEnum.PAGINATION_ITEM.name(), PaginationItemDto.class, provider, context)
                        .orElseThrow(() -> new InventoryException(String.format("Cannot provide pagination item for pagination '%s'", this.id)));

                item = new SimpleInventoryItem(dto.getId(), dto.getItem(), dto.getActions());
            }

            if (item == null) {
                content.removeItem(slot);
            } else {
                content.setItem(item, slot);
            }

            i++;
        }
    }

    private void updatePageItems() {

        InventoryProvider provider = this.inventory.getProvider();
        InventoryContent content = this.inventory.getContent();

        Context context = this.getPaginationContext();

        PaginationConfig paginationConfig = provider.getConfig()
                .getPaginationConfig(this.id)
                .orElseThrow(() -> new InventoryException(String.format("Cannot update page items: no pagination with id '%s' found in the configuration", this.id)));

        // Previous page item handling.
        if (this.model.hasPreviousPage()) {

            provider.getProviderManager()
                    .provide(ProviderNameEnum.PAGINATION_PREVIOUS_PAGE_ITEM.name(), InventoryItemDto.class, provider, context)
                    .filter(dto -> dto.getItemId() != null && dto.getItem() != null)
                    .ifPresent(dto -> {
                        InventoryItem item = new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions());
                        content.setItem(item, dto.getSlots());
                    });

        } else {
            content.removeItems(paginationConfig.getPreviousPageItemSlots());
        }

        // Next page item handling.
        if (this.model.hasNextPage()) {

            provider.getProviderManager()
                    .provide(ProviderNameEnum.PAGINATION_NEXT_PAGE_ITEM.name(), InventoryItemDto.class, provider, context)
                    .filter(dto -> dto.getItemId() != null && dto.getItem() != null)
                    .ifPresent(dto -> {
                        InventoryItem item = new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions());
                        content.setItem(item, dto.getSlots());
                    });
        } else {
            content.removeItems(paginationConfig.getNextPageItemSlots());
        }
    }

    @Override
    public void previousPage() {

        if (this.model.hasPreviousPage()) {
            this.model.previousPage();
            this.update();
        }
    }

    @Override
    public void nextPage() {

        if (this.model.hasNextPage()) {
            this.model.nextPage();
            this.update();
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public PaginationModel<T> getModel() {
        return this.model;
    }

    @Override
    public List<Integer> getSlots() {
        return Collections.unmodifiableList(this.slots);
    }

    private Context getPaginationContext() {
        Context context = this.inventory.getDefaultContext();
        context.addData(CommonContextKey.PAGINATION_ID.name(), this.id, String.class);
        return context;
    }
}
