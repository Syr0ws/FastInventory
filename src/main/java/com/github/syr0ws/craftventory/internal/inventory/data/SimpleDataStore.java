package com.github.syr0ws.craftventory.internal.inventory.data;

import com.github.syr0ws.craftventory.api.inventory.data.DataStore;

import java.util.*;

public class SimpleDataStore implements DataStore {

    private final Map<String, Data<?>> data = new HashMap<>();

    @Override
    public <T> void setData(String key, Class<T> type, T value) {

        if(key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key cannot be null or empty");
        }

        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        if(value == null) {
            throw new IllegalArgumentException("null value is not allowed");
        }

        this.data.put(key, new Data<>(type, value));
    }

    @Override
    public boolean removeData(String key) {

        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        return this.data.remove(key) != null;
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public boolean hasData(String key) {

        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        return this.data.containsKey(key);
    }

    @Override
    public boolean hasData(String key, Class<?> type) {

        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        if(!this.data.containsKey(key)) {
            return false;
        }

        Data<?> data = this.data.get(key);

        return type.isInstance(data.value());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getData(String key, Class<T> type) {

        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.data.get(key);

        if(data == null) {
            return Optional.empty();
        }

        if(!type.isInstance(data.value())) {
            throw new IllegalArgumentException(String.format("type %s is not compatible with data type %s", type.getName(), data.getClass().getName()));
        }

        return Optional.of((T) data.value());
    }

    @Override
    public Set<String> getKeys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    private record Data<T>(Class<T> type, T value) {

    }
}
