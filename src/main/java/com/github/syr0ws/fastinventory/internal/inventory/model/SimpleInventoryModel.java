package com.github.syr0ws.fastinventory.internal.inventory.model;

import com.github.syr0ws.fastinventory.api.inventory.model.InventoryModel;

import java.util.*;

public class SimpleInventoryModel implements InventoryModel {

    private final Map<String, Data<?>> data = new HashMap<>();

    @Override
    public <T> void setData(String key, Class<T> type, T value) {

        if(key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key cannot be null or empty");
        }

        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
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
    public boolean hasData(String key) {

        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        return this.data.containsKey(key);
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

        if(data.value() != null && !type.isInstance(data.value())) {
            throw new IllegalArgumentException(String.format("type %s is not compatible with data type %s", type.getName(), data.getClass().getName()));
        }

        return Optional.ofNullable((T) data.value());
    }

    @Override
    public Set<String> getKeys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    private record Data<T>(Class<T> type, T value) {

    }
}
