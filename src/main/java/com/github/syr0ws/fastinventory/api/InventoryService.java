package com.github.syr0ws.fastinventory.api;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.transform.provider.InventoryProvider;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface InventoryService {

    void addInventory(FastInventory inventory);

    void removeInventory(Player player);

    boolean hasInventory(Player player);

    Optional<FastInventory> getInventory(Player player);

    Map<Player, FastInventory> getInventories();

    void addProvider(InventoryProvider provider);

    void removeProvider(String providerId);

    boolean hasProvider(String providerId);

    Optional<InventoryProvider> getProvider(String providerId);

    Set<InventoryProvider> getProviders();
}
