package com.github.syr0ws.craftventory.api.inventory.data;

import java.util.Optional;
import java.util.Set;

/**
 * Represents a data model associated with an inventory.
 * <p>
 * The {@link DataStore} provides a flexible way to store, retrieve, and manage
 * data associated with an inventory instance. It supports typed data storage
 * and retrieval based on unique keys.
 * </p>
 */
public interface DataStore {

    /**
     * Stores a data entry in the inventory model.
     *
     * @param key   The unique key that identifies the data entry. Must not be {@code null} or empty.
     * @param type  The {@link Class} type of the data. Must not be {@code null}.
     * @param value The value to store.
     * @param <T>   The type of the value to store.
     */
    <T> void setData(String key, Class<T> type, T value);

    /**
     * Removes a data entry from the inventory model by its key.
     *
     * @param key The unique key that identifies the data entry to remove. Must not be {@code null}.
     * @return {@code true} if the data entry was successfully removed, {@code false} otherwise.
     */
    boolean removeData(String key);

    /**
     * Removes all the stored data in the storage.
     */
    void clear();

    /**
     * Checks if the inventory model contains a data entry for the specified key.
     *
     * @param key The unique key that identifies the data entry. Must not be {@code null}.
     * @return {@code true} if a data entry exists for the given key, {@code false} otherwise.
     */
    boolean hasData(String key);

    /**
     * Checks if the inventory model contains a data entry for the specified key and class.
     *
     * @param key The unique key that identifies the data entry. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return {@code true} if a data entry exists for the given key, {@code false} otherwise.
     */
    boolean hasData(String key, Class<?> type);

    /**
     * Retrieves a data entry from the inventory model.
     *
     * @param key  The unique key that identifies the data entry. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @param <T>  The expected type of the data.
     * @return An {@link Optional} containing the data entry if it exists, or an empty {@link Optional}
     * if not found.
     * @throws IllegalArgumentException If a data entry has been found but its type does not match the given type.
     */
    <T> Optional<T> getData(String key, Class<T> type);

    /**
     * Retrieves all the keys currently stored in the inventory model.
     *
     * @return An immutable {@link Set} containing all the keys of the stored data entries.
     * Never {@code null}.
     */
    Set<String> getKeys();
}
