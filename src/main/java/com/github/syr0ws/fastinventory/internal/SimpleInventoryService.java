package com.github.syr0ws.fastinventory.internal;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.internal.inventory.SimpleInventoryViewer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleInventoryService implements InventoryService {

    private final Map<Player, InventoryViewer> viewers = new HashMap<>();
    private final Map<String, InventoryProvider> providers = new HashMap<>();

    public void addInventoryViewer(Player player) {

        if(player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        this.viewers.put(player, new SimpleInventoryViewer(player));
    }

    public void removeInventoryViewer(Player player) {

        if(player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        InventoryViewer viewer = this.viewers.get(player);
        viewer.getViewManager().clear();

        this.viewers.remove(player);
    }

    @Override
    public InventoryViewer getInventoryViewer(Player player) {

        if(player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        return this.viewers.get(player);
    }

    @Override
    public Set<InventoryViewer> getInventoryViewers() {
        return this.viewers.values().stream().collect(Collectors.toUnmodifiableSet());
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
