package com.github.syr0ws.craftventory.internal;

import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.transform.InventoryDescriptor;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.internal.inventory.SimpleInventoryViewer;
import com.github.syr0ws.craftventory.internal.transform.provider.SimpleInventoryProvider;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleInventoryService implements InventoryService {

    private final Plugin plugin;
    private final Map<Player, InventoryViewer> viewers = new HashMap<>();
    private final Map<String, InventoryProvider> providers = new HashMap<>();

    public SimpleInventoryService(Plugin plugin) {

        if(plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        this.plugin = plugin;
    }

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
        viewer.getViewManager().clear(true);

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
    public void reloadInventoryConfigs() {
        this.providers.values().forEach(InventoryProvider::loadConfig);
    }

    @Override
    public void createProvider(InventoryDescriptor descriptor) {

        if(descriptor == null) {
            throw new IllegalArgumentException("descriptor cannot be null");
        }

        InventoryProvider provider = new SimpleInventoryProvider(this.plugin, descriptor);
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
