package com.github.syr0ws.fastinventory.api.util;

public interface Context {

    /**
     * Add a new data to the context.
     * @param key The key that uniquely identify the data in the context.
     * @param data The data value.
     * @param type The Java class type of the data.
     */
    <T> void addData(String key, T data, Class<T> type);

    /**
     * Check that the context contains a data.
     * @param key The key that uniquely identify the data in the context.
     * @return true if there is a data for the given key or else false.
     */
    boolean hasData(String key);

    /**
     * Get a data stored in the context by key/.
     * @param key The key that uniquely identify the data in the context.
     * @param type The Java class type of the data.
     * @return The value of the data at the given key.
     * @throws NullPointerException If there is no data for the given key.
     * @throws IllegalArgumentException If the given type does not match the data type.
     */
    <T> T getData(String key, Class<T> type);
}
