package com.github.syr0ws.craftventory.api.transform;

import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.inventory.hook.HookManager;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.i18n.I18n;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.transform.provider.ProviderManager;

import java.nio.file.Path;

public interface InventoryDescriptor {

    default void addProviders(ProviderManager manager) {}

    default void addPlaceholders(PlaceholderManager manager) {}

    default void addEnhancements(EnhancementManager manager) {}

    default void addHooks(HookManager manager) {}

    default I18n getI18n() { return null; }

    String getInventoryResourceFile();

    Path getInventoryConfigFile();

    String getInventoryId();

    InventoryConfigDAO getInventoryConfigDAO();
}
