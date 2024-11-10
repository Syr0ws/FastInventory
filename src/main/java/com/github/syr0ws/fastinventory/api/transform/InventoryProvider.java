package com.github.syr0ws.fastinventory.api.transform;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.fastinventory.api.transform.i18n.I18n;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.transform.provider.ProviderManager;
import org.bukkit.entity.Player;

import java.util.Optional;

public interface InventoryProvider {

    /**
     * Load the configuration of the inventory.
     */
    void loadConfig();

    /**
     * Create the inventory.
     *
     * @param service The service in which the inventory will be stored.
     * @param player  The player that will view the inventory.
     * @return The created inventory.
     */
    FastInventory createInventory(InventoryService service, Player player);

    /**
     * @return The configuration of the inventory.
     */
    InventoryConfig getConfig();

    /**
     * @return The PlaceholderManager instance of the inventory.
     */
    PlaceholderManager getPlaceholderManager();

    /**
     * @return The ProviderManager instance of the inventory.
     */
    ProviderManager getProviderManager();

    /**
     * @return The EnhancementManager instance of the inventory.
     */
    EnhancementManager getEnhancementManager();

    /**
     * Get the id of the InventoryProvider. This id should be the same as the one of the inventory
     * to be able to link them.
     *
     * @return The id of the InventoryProvider.
     */
    String getId();

    /**
     * Get the I18n instance of the inventory.
     *
     * @return An optional that contains the I18n instance is it has been provided or else an empty optional.
     */
    Optional<I18n> getI18n();
}
