package com.github.syr0ws.fastinventory.api.transform;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.transform.i18n.I18n;
import com.github.syr0ws.fastinventory.api.transform.mapping.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.transform.provider.ProviderManager;
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
