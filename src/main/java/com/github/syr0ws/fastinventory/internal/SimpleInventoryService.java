package com.github.syr0ws.fastinventory.internal;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.transform.provider.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleInventoryService implements InventoryService {

    private final Map<Player, FastInventory> inventories = new HashMap<>();
    private final Map<String, InventoryProvider> providers = new HashMap<>();

    @Override
    public void addInventory(FastInventory inventory) {

        if (inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        this.inventories.put(inventory.getViewer(), inventory);
    }

    @Override
    public void removeInventory(Player player) {

        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        this.inventories.remove(player);
    }

    @Override
    public boolean hasInventory(Player player) {

        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        return this.inventories.containsKey(player);
    }

    @Override
    public Optional<FastInventory> getInventory(Player player) {

        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        return Optional.ofNullable(this.inventories.get(player));
    }

    @Override
    public Map<Player, FastInventory> getInventories() {
        return Collections.unmodifiableMap(this.inventories);
    }

    @Override
    public void addProvider(InventoryProvider provider) {

        if (provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        this.providers.put(provider.getId(), provider);
    }

    @Override
    public void removeProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        this.providers.remove(providerId);
    }

    @Override
    public boolean hasProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        return this.providers.containsKey(providerId);
    }

    @Override
    public Optional<InventoryProvider> getProvider(String providerId) {

        if (providerId == null) {
            throw new IllegalArgumentException("providerId cannot be null");
        }

        return Optional.ofNullable(this.providers.get(providerId));
    }

    @Override
    public Set<InventoryProvider> getProviders() {
        return new HashSet<>(this.providers.values());
    }
}
