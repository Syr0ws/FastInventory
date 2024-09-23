package com.github.syr0ws.fastinventory.internal;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.FastInventoryType;
import com.github.syr0ws.fastinventory.api.InventoryContent;
import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.exception.InventoryException;
import com.github.syr0ws.fastinventory.api.item.InventoryItem;
import com.github.syr0ws.fastinventory.api.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import com.github.syr0ws.fastinventory.common.provider.CommonProviderType;
import com.github.syr0ws.fastinventory.internal.util.SimpleContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class SimpleFastInventory implements FastInventory {

    private final InventoryProvider provider;
    private final InventoryContent content;
    private final InventoryService service;
    private final Player viewer;
    private final Map<String, Pagination<?>> paginations = new HashMap<>();

    private Inventory inventory;

    public SimpleFastInventory(InventoryProvider provider, InventoryService service, Player viewer) {

        if(provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        if(service == null) {
            throw new IllegalArgumentException("service cannot be null");
        }

        if(viewer == null) {
            throw new IllegalArgumentException("viewer cannot be null");
        }

        this.provider = provider;
        this.service = service;
        this.viewer = viewer;
        this.content = new SimpleInventoryContent(this);
    }

    @Override
    public void open() {

        InventoryConfig config = this.provider.getConfig();
        config.getPaginationConfigs().forEach(this::addPagination);

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
        for(int row = 0; row < type.getRows(); row++) {

            for(int col = 0; col < type.getColumns(); col++) {

                int slot = (row * type.getColumns()) + col;

                Context context = this.getDefaultContext();
                context.addData(CommonContextKey.SLOT.name(), slot, Integer.class);

                InventoryItem item = this.provider.getProviderManager().provide(CommonProviderType.CONTENT_ITEM.name(), InventoryItem.class, provider, context)
                        .orElse(null);

                if(item == null) {
                    this.content.removeItem(slot);
                } else {
                    this.content.setItem(item, slot);
                }
            }
        }

        // Updating pagination contents.
        this.paginations.values().forEach(Pagination::update);

        // Updating viewer inventory.
        this.updateBukkitInventory();
    }

    @Override
    public String getTitle() {
        return this.provider.getProviderManager().provide(CommonProviderType.TITLE.name(), String.class, provider, this.getDefaultContext())
                .orElse("");
    }

    @Override
    public FastInventoryType getType() {
        return this.provider.getProviderManager().provide(CommonProviderType.INVENTORY_TYPE.name(), FastInventoryType.class, provider, this.getDefaultContext())
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
        return null;
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
    public Optional<Pagination<?>> getPagination(String id) {
        return Optional.ofNullable(this.paginations.get(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<Pagination<T>> getPagination(String id, Class<T> type) {

        Pagination<?> pagination = this.paginations.get(id);

        if(pagination == null) {
            return Optional.empty();
        }

        Class<?> dataType = pagination.getModel().getDataType();

        return dataType.equals(type) ? Optional.of((Pagination<T>) pagination) : Optional.empty();
    }

    @Override
    public List<Pagination<?>> getPaginations() {
        return new ArrayList<>(this.paginations.values());
    }

    @Override
    public Context getDefaultContext() {

        Context context = new SimpleContext();
        context.addData(CommonContextKey.VIEWER.name(), this.viewer, Player.class);
        context.addData(CommonContextKey.INVENTORY.name(), this, FastInventory.class);

        return context;
    }

    private void addPagination(PaginationConfig config) {

        Context context = this.getDefaultContext();
        context.addData(CommonContextKey.PAGINATION_ID.name(), config.getId(), String.class);

        Pagination<?> pagination = this.provider.getProviderManager().provide(config.getId(), Pagination.class, provider, context)
                .orElseThrow(() -> new NullPointerException("No provider found for Pagination"));

        this.paginations.put(pagination.getId(), pagination);
    }

    private Inventory createBukkitInventory() {

        FastInventoryType type = this.getType();
        InventoryType bukkitType = type.getBukkitType();

        if(bukkitType == InventoryType.CHEST) {
            return Bukkit.createInventory(null, this.getSize(), this.getTitle());
        }

        return this.inventory = Bukkit.createInventory(null, bukkitType, this.getTitle());
    }

    private void updateBukkitInventory() {

        for(int slot = 0; slot < this.inventory.getSize(); slot++) {

            ItemStack item = this.content.getItem(slot)
                    .map(InventoryItem::getItemStack)
                    .orElse(null);

            this.inventory.setItem(slot, item);
        }

        this.viewer.updateInventory();
    }
}
