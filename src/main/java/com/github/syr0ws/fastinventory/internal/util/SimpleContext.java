package com.github.syr0ws.fastinventory.internal.util;

import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.HashMap;
import java.util.Map;

public class SimpleContext implements Context {

    private final Map<String, Data<?>> storage = new HashMap<>();

    @Override
    public <T> void addData(String key, T data, Class<T> type) {

        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key cannot be null or empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        this.storage.put(key, new Data<>(data, type));
    }

    @Override
    public boolean hasData(String key) {

        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        return this.storage.containsKey(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getData(String key, Class<T> type) {

        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.storage.get(key);

        if (data == null) {
            throw new NullPointerException(String.format("No data found for key %s", key));
        }

        if (!data.type().equals(type)) {
            throw new IllegalArgumentException(String.format("Type mismatch ; %s stored abd %s provided", data.type(), type));
        }

        return (T) data.value();
    }

    private record Data<T>(T value, Class<T> type) {

    }
}
