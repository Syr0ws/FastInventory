package com.github.syr0ws.craftventory.api.config.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

/**
 * Responsible for loading a specific {@link ClickAction} instance from a given data source.
 *
 * @param <T> The type of the data source from which the {@link ClickAction} is loaded.
 */
public interface ClickActionLoader<T> {

    /**
     * Loads a {@link ClickAction} from the specified data source.
     *
     * @param dataSource The data source from which to load the {@link ClickAction}. Must not be {@code null}.
     * @return The created {@link ClickAction}.
     * @throws InventoryConfigException If an error occurs while parsing or loading the {@link ClickAction} from the data source.
     */
    ClickAction load(T dataSource) throws InventoryConfigException;

    /**
     * Retrieves the name of this loader.
     *
     * <p>
     * The name if used to uniquely identify the loader. It will be used to find the right loader
     * to load a specific action.
     * <br/>
     * To avoid confusion, the name of the loader should be the same as the name of the action it loads.
     * </p>
     *
     * @return The name of the loader. Must not be {@code null}.
     */
    String getName();
}
