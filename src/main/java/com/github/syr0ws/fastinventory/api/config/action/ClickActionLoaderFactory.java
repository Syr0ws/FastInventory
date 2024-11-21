package com.github.syr0ws.fastinventory.api.config.action;

import java.util.Set;

/**
 * Factory interface for managing and providing {@link ClickActionLoader} instances.
 *
 * @param <T> The type of the data source handled by the {@link ClickActionLoader}.
 */
public interface ClickActionLoaderFactory<T> {

    /**
     * Registers a new {@link ClickActionLoader}.
     *
     * @param loader The {@link ClickActionLoader} to register. Must not be {@code null}.
     */
    void addLoader(ClickActionLoader<T> loader);

    /**
     * Retrieves a {@link ClickActionLoader} by its name.
     *
     * @param name The name of the loader to retrieve. Must not be {@code null}.
     * @return The corresponding {@link ClickActionLoader}, or {@code null} if no loader
     *         with the specified name is registered.
     */
    ClickActionLoader<T> getLoader(String name);

    /**
     * Retrieves all registered {@link ClickActionLoader} instances.
     *
     * @return A set of all registered {@link ClickActionLoader} instances. If no loaders
     *         are registered, the set will be empty.
     */
    Set<ClickActionLoader<T>> getLoaders();
}
