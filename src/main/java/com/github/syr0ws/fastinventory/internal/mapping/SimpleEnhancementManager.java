package com.github.syr0ws.fastinventory.internal.mapping;

import com.github.syr0ws.fastinventory.api.mapping.Dto;
import com.github.syr0ws.fastinventory.api.mapping.Enhancement;
import com.github.syr0ws.fastinventory.api.mapping.EnhancementManager;

import java.util.*;

public class SimpleEnhancementManager implements EnhancementManager {

    private final Map<String, Data<?>> enhancements = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Dto> void addEnhancement(String id, Enhancement<T> enhancement, Class<T> type) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (enhancement == null) {
            throw new IllegalArgumentException("enhancement cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        if (this.enhancements.containsKey(id)) {

            Data<?> data = this.enhancements.get(id);

            if (!data.getType().equals(type)) {
                throw new IllegalArgumentException("Enhancement type mismatch");
            }

            Data<T> casted = (Data<T>) data;
            casted.getEnhancements().add(enhancement);

        } else {

            Data<T> data = new Data<>(type);
            data.getEnhancements().add(enhancement);

            this.enhancements.put(id, data);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Dto> List<Enhancement<T>> getEnhancements(String id, Class<T> type) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.enhancements.get(id);

        if (data == null) {
            return Collections.emptyList();
        }

        if (!data.getType().equals(type)) {
            throw new IllegalArgumentException("Enhancement type mismatch");
        }

        Data<T> casted = (Data<T>) data;

        return casted.getEnhancements();
    }

    private static class Data<T extends Dto> {

        private final Class<T> type;
        private final List<Enhancement<T>> enhancements;

        public Data(Class<T> type) {
            this.type = type;
            this.enhancements = new ArrayList<>();
        }

        public Class<T> getType() {
            return this.type;
        }

        public List<Enhancement<T>> getEnhancements() {
            return this.enhancements;
        }
    }
}
