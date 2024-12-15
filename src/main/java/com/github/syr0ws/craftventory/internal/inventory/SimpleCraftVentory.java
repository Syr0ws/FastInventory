package com.github.syr0ws.craftventory.internal.inventory;

import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;
import com.github.syr0ws.craftventory.api.inventory.InventoryContent;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryAfterOpenEvent;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryBeforeOpenEvent;
import com.github.syr0ws.craftventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.craftventory.api.inventory.hook.HookManager;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.craftventory.api.inventory.data.DataStore;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationManager;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryTypeDto;
import com.github.syr0ws.craftventory.common.transform.dto.TitleDto;
import com.github.syr0ws.craftventory.common.transform.provider.ProviderNameEnum;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import com.github.syr0ws.craftventory.internal.inventory.data.SimpleDataStore;
import com.github.syr0ws.craftventory.internal.inventory.hook.SimpleHookManager;
import com.github.syr0ws.craftventory.internal.inventory.item.SimpleInventoryItem;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePaginationManager;
import com.github.syr0ws.craftventory.internal.util.SimpleContext;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class SimpleCraftVentory implements CraftVentory {

    private final InventoryProvider provider;
    private final InventoryContent content;
    private final InventoryService service;
    private final DataStore dataStore;
    private final SimplePaginationManager paginationManager;
    private final HookManager hookManager;
    private final InventoryViewer viewer;

    private Inventory inventory;

    public SimpleCraftVentory(InventoryProvider provider, InventoryService service, InventoryViewer viewer) {

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
        this.dataStore = new SimpleDataStore();
        this.paginationManager = new SimplePaginationManager();
        this.hookManager = new SimpleHookManager();
    }

    @Override
    public void open() {

        this.inventory = this.createBukkitInventory();

        // Hook
        CraftVentoryBeforeOpenEvent event = new CraftVentoryBeforeOpenEvent(this, this.viewer);
        this.hookManager.executeHooks(event, CraftVentoryBeforeOpenEvent.class);

        if(event.isCancelled()) {
            return; // Cancel inventory opening
        }

        // Opening the inventory
        this.updateContent();
        this.viewer.getPlayer().openInventory(this.inventory);

        // Hook
        this.hookManager.executeHooks(new CraftVentoryAfterOpenEvent(this, this.viewer), CraftVentoryAfterOpenEvent.class);
    }

    @Override
    public void close() {
        this.viewer.getPlayer().closeInventory();
    }

    @Override
    public void updateContent() {
        this.updateInventoryContent();
        this.paginationManager.updatePaginations();
        this.updateView();
    }

    @Override
    public void updatePagination(String paginationId) {

        if(paginationId == null) {
            throw new IllegalArgumentException("paginationId cannot be null");
        }

        this.paginationManager.updatePagination(paginationId);
        this.updateView();
    }

    @Override
    public void updateView() {
        this.viewer.getPlayer().updateInventory();
    }

    @Override
    public String getId() {
        return this.provider.getId();
    }

    @Override
    public String getTitle() {
        return this.provider.getProviderManager()
                .provide(ProviderNameEnum.TITLE.name(), TitleDto.class, provider, this.getDefaultContext())
                .map(TitleDto::getTitle)
                .orElse("");
    }

    @Override
    public CraftVentoryType getType() {
        return this.provider.getProviderManager()
                .provide(ProviderNameEnum.INVENTORY_TYPE.name(), InventoryTypeDto.class, provider, this.getDefaultContext())
                .map(InventoryTypeDto::getType)
                .orElseThrow(() -> new InventoryException("No provider found for CraftVentoryType"));
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
    public DataStore getLocalStore() {
        return this.dataStore;
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
    public HookManager getHookManager() {
        return this.hookManager;
    }

    @Override
    public InventoryViewer getViewer() {
        return this.viewer;
    }

    @Override
    public Inventory getBukkitInventory() {
        return this.inventory;
    }

    @Override
    public Context getDefaultContext() {

        Context context = new SimpleContext();
        context.addData(CommonContextKey.VIEWER.name(), this.viewer, InventoryViewer.class);
        context.addData(CommonContextKey.INVENTORY.name(), this, CraftVentory.class);

        return context;
    }

    private Inventory createBukkitInventory() {

        CraftVentoryType type = this.getType();
        InventoryType bukkitType = type.getBukkitType();

        if (bukkitType == InventoryType.CHEST) {
            return Bukkit.createInventory(null, this.getSize(), this.getTitle());
        }

        return this.inventory = Bukkit.createInventory(null, bukkitType, this.getTitle());
    }

    private void updateInventoryContent() {

        CraftVentoryType type = this.getType();

        for (int row = 0; row < type.getRows(); row++) {

            for (int col = 0; col < type.getColumns(); col++) {

                int slot = (row * type.getColumns()) + col;

                Context context = this.getDefaultContext();
                context.addData(CommonContextKey.SLOT.name(), slot, Integer.class);

                InventoryItem item = this.provider.getProviderManager()
                        .provide(ProviderNameEnum.INVENTORY_ITEM_BY_SLOT.name(), InventoryItemDto.class, this.provider, context)
                        // A non-existing item can be enhanced.
                        // This is to prevent a non-existing item to be considered as an InventoryItem.
                        .filter(dto -> dto.getItemId() != null && dto.getItem() != null)
                        .map(dto -> new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions()))
                        .orElse(null);

                if (item == null) {
                    this.content.removeItem(slot);
                } else {
                    this.content.setItem(item, slot);
                }
            }
        }
    }
}
