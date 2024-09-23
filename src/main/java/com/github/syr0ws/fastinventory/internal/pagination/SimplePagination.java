package com.github.syr0ws.fastinventory.internal.pagination;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.InventoryContent;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import com.github.syr0ws.fastinventory.common.provider.CommonProviderType;

import java.util.Collections;
import java.util.List;

public class SimplePagination<T> implements Pagination<T> {

    private final String id;
    private final FastInventory inventory;
    private final PaginationModel<T> model;
    private final List<Integer> slots;

    public SimplePagination(String id, FastInventory inventory, PaginationModel<T> model, List<Integer> slots) {

        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if(inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        if(model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }

        if(slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }

        this.id = id;
        this.inventory = inventory;
        this.model = model;
        this.slots = slots;
    }

    @Override
    public void update() {
        this.updatePaginationItems();
        this.updatePageItems();
    }

    private void updatePaginationItems() {

        InventoryProvider provider = this.inventory.getProvider();
        InventoryContent content = this.inventory.getContent();

        List<T> items = this.model.getCurrentItems();

        int i = 0;

        for(int slot : this.slots) {

            InventoryItem item = null;

            if(i < items.size()) {

                Context context = this.getPaginationContext();
                context.addData(CommonContextKey.SLOT.name(), slot, Integer.class);
                context.addData(CommonContextKey.PAGINATION_ITEM.name(), items.get(i), this.model.getDataType());

                item = provider.getProviderManager().provide(CommonProviderType.PAGINATION_ITEM.name(), InventoryItem.class, provider, context).orElse(null);
            }

            if(item == null) {
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
                .orElseThrow(() -> new NullPointerException("Pagination not found"));

        if(this.model.hasPreviousPage()) {

            provider.getProviderManager().provide(CommonProviderType.PAGINATION_PREVIOUS_PAGE_ITEM.name(), InventoryItem.class, provider, context)
                    .ifPresent(item -> content.setItem(item, paginationConfig.getPreviousPageItemSlots()));

        } else {
            content.removeItems(paginationConfig.getPreviousPageItemSlots());
        }

        if(this.model.hasNextPage()) {

            provider.getProviderManager().provide(CommonProviderType.PAGINATION_NEXT_PAGE_ITEM.name(), InventoryItem.class, provider, context)
                    .ifPresent(item -> content.setItem(item, paginationConfig.getNextPageItemSlots()));
        } else {
            content.removeItems(paginationConfig.getNextPageItemSlots());
        }
    }

    @Override
    public void previousPage() {

        if(this.model.hasPreviousPage()) {
            this.model.previousPage();
            this.update();
        }
    }

    @Override
    public void nextPage() {

        if(this.model.hasNextPage()) {
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
