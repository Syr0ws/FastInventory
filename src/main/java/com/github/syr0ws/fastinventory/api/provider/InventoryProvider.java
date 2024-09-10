package com.github.syr0ws.fastinventory.api.provider;

import com.github.syr0ws.fastinventory.api.FastInventory;
import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.i18n.I18n;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public interface InventoryProvider {

    void loadConfig();

    FastInventory createInventory(InventoryService service, Player player);

    <T> Optional<T> provide(String name, Class<T> type, Context context);

    <T> Optional<Provider<T>> getProvider(String name, Class<T> type);

    Set<Provider<?>> getProviders();

    InventoryConfig getConfig();

    PlaceholderManager getPlaceholderManager();

    String getId();

    Optional<I18n> getI18n();
}
