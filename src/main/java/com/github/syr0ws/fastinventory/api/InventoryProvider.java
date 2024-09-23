package com.github.syr0ws.fastinventory.api;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.i18n.I18n;
import com.github.syr0ws.fastinventory.api.mapping.EnhancementManager;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.ProviderManager;
import org.bukkit.entity.Player;

import java.util.Optional;

public interface InventoryProvider {

    void loadConfig();

    FastInventory createInventory(InventoryService service, Player player);

    InventoryConfig getConfig();

    PlaceholderManager getPlaceholderManager();

    ProviderManager getProviderManager();

    EnhancementManager getEnhancementManager();

    String getId();

    Optional<I18n> getI18n();
}
