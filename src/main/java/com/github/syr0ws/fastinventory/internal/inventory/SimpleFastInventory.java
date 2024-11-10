package com.github.syr0ws.fastinventory.internal.inventory;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;
import com.github.syr0ws.fastinventory.api.inventory.InventoryContent;
import com.github.syr0ws.fastinventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationManager;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.fastinventory.common.transform.dto.InventoryTypeDto;
import com.github.syr0ws.fastinventory.common.transform.dto.TitleDto;
import com.github.syr0ws.fastinventory.common.transform.provider.ProviderNameEnum;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import com.github.syr0ws.fastinventory.internal.inventory.item.SimpleInventoryItem;
import com.github.syr0ws.fastinventory.internal.inventory.pagination.SimplePaginationManager;
import com.github.syr0ws.fastinventory.internal.util.SimpleContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SimpleFastInventory implements FastInventory {

    private final InventoryProvider provider;
    private final InventoryContent content;
    private final InventoryService service;
    private final SimplePaginationManager paginationManager;
    private final Player viewer;

    private Inventory inventory;

    public SimpleFastInventory(InventoryProvider provider, InventoryService service, Player viewer) {

        if (provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        if (service == null) {
            throw new IllegalArgumentException("service cannot be null");
        }

        if (viewer == null) {
            throw new IllegalArgumentException("viewer cannot be null");
        }

        this.provider = provider;
        this.service = service;
        this.viewer = viewer;
        this.content = new SimpleInventoryContent(this);
        this.paginationManager = new SimplePaginationManager();
    }

    @Override
    public void open() {

        this.inventory = this.createBukkitInventory();

        this.viewer.openInventory(this.inventory);
        this.service.addInventory(this);
        this.update();
    }

    @Override
    public void close() {
        this.service.removeInventory(this.viewer);
        this.viewer.closeInventory();
    }

    @Override
    public void update() {

        FastInventoryType type = this.getType();

        // Updating inventory content.
        for (int row = 0; row < type.getRows(); row++) {

            for (int col = 0; col < type.getColumns(); col++) {

                int slot = (row * type.getColumns()) + col;

                Context context = this.getDefaultContext();
                context.addData(CommonContextKey.SLOT.name(), slot, Integer.class);

                InventoryItem item = this.provider.getProviderManager()
                        .provide(ProviderNameEnum.INVENTORY_ITEM_BY_SLOT.name(), InventoryItemDto.class, provider, context)
                        .map(dto -> new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions()))
                        .orElse(null);

                if (item == null) {
                    this.content.removeItem(slot);
                } else {
                    this.content.setItem(item, slot);
                }
            }
        }

        // Updating pagination contents.
        this.paginationManager.updatePaginations();

        // Updating viewer inventory.
        this.updateBukkitInventory();
    }

    @Override
    public String getTitle() {
        return this.provider.getProviderManager()
                .provide(ProviderNameEnum.TITLE.name(), TitleDto.class, provider, this.getDefaultContext())
                .map(TitleDto::getTitle)
                .orElse("");
    }

    @Override
    public FastInventoryType getType() {
        return this.provider.getProviderManager()
                .provide(ProviderNameEnum.INVENTORY_TYPE.name(), InventoryTypeDto.class, provider, this.getDefaultContext())
                .map(InventoryTypeDto::getType)
                .orElseThrow(() -> new InventoryException("No provider found for FastInventoryType"));
    }

    @Override
    public int getSize() {
        return this.getType().getSize();
    }

    @Override
    public InventoryProvider getProvider() {
        return this.provider;
    }

    @Override
    public InventoryContent getContent() {
        return this.content;
    }

    @Override
    public InventoryService getService() {
        return this.service;
    }

    @Override
    public PaginationManager getPaginationManager() {
        return this.paginationManager;
    }

    @Override
    public Player getViewer() {
        return this.viewer;
    }

    @Override
    public Inventory getBukkitInventory() {
        return this.inventory;
    }

    @Override
    public Context getDefaultContext() {

        Context context = new SimpleContext();
        context.addData(CommonContextKey.VIEWER.name(), this.viewer, Player.class);
        context.addData(CommonContextKey.INVENTORY.name(), this, FastInventory.class);

        return context;
    }

    private Inventory createBukkitInventory() {

        FastInventoryType type = this.getType();
        InventoryType bukkitType = type.getBukkitType();

        if (bukkitType == InventoryType.CHEST) {
            return Bukkit.createInventory(null, this.getSize(), this.getTitle());
        }

        return this.inventory = Bukkit.createInventory(null, bukkitType, this.getTitle());
    }

    private void updateBukkitInventory() {

        for (int slot = 0; slot < this.inventory.getSize(); slot++) {

            ItemStack item = this.content.getItem(slot)
                    .map(InventoryItem::getItemStack)
                    .orElse(null);

            this.inventory.setItem(slot, item);
        }

        this.viewer.updateInventory();
    }
}
