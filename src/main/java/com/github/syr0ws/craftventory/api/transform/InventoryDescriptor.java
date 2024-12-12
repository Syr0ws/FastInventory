package com.github.syr0ws.craftventory.api.transform;

import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.inventory.hook.HookManager;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.i18n.I18n;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.transform.provider.ProviderManager;

import java.nio.file.Path;

/**
 * Used to declare an inventory.
 */
public interface InventoryDescriptor {

    /**
     * Adds custom providers to the inventory.
     * <p>
     * This method can be overridden to register specific providers for the inventory.
     * By default, it does nothing.
     * </p>
     *
     * @param manager The {@link ProviderManager} used by the inventory to which providers will be added.
     *                Never {@code null}.
     */
    default void addProviders(ProviderManager manager) {
    }

    /**
     * Adds placeholders to the inventory.
     * <p>
     * This method can be overridden to register placeholders for the inventory.
     * By default, it does nothing.
     * </p>
     *
     * @param manager The {@link PlaceholderManager} used by the inventory to which placeholders will be added.
     *                Never {@code null}.
     */
    default void addPlaceholders(PlaceholderManager manager) {
    }

    /**
     * Adds enhancements to the transformation process of the inventory.
     * <p>
     * This method can be overridden to register enhancements for the inventory.
     * By default, it does nothing.
     * </p>
     *
     * @param manager The {@link EnhancementManager} used by the inventory to which enhancements will be added.
     *                Never {@code null}.
     */
    default void addEnhancements(EnhancementManager manager) {
    }

    /**
     * Adds hooks to the inventory.
     * <p>
     * This method can be overridden to register hooks for the inventory.
     * By default, it does nothing.
     * </p>
     *
     * @param manager The {@link HookManager} used by the inventory to which hooks will be added.
     *                Never {@code null}.
     */
    default void addHooks(HookManager manager) {
    }

    /**
     * Retrieves the {@link I18n} instance for this inventory, if available.
     * <p>
     * By default, this method returns {@code null}, indicating no internationalization is used.
     * </p>
     *
     * @return The {@link I18n} instance, or {@code null} if not defined.
     */
    default I18n getI18n() {
        return null;
    }

    /**
     * Retrieves the path to the inventory configuration resource file.
     *
     * @return The path of the inventory configuration resource file.
     * May be null {@code null} if no resource file is used.
     */
    String getInventoryResourceFile();

    /**
     * Retrieves the path to the inventory configuration file.
     *
     * @return The {@link Path} to the inventory configuration file. Must not be {@code null}.
     */
    Path getInventoryConfigFile();

    /**
     * Retrieves the unique identifier of the inventory.
     *
     * @return The inventory's unique identifier. Must not be {@code null}.
     */
    String getInventoryId();

    /**
     * Retrieves the {@link InventoryConfigDAO} instance for this inventory.
     *
     * @return The {@link InventoryConfigDAO} instance. Must not be {@code null}.
     */
    InventoryConfigDAO getInventoryConfigDAO();
}
