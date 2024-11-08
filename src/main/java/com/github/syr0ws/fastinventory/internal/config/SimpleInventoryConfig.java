package com.github.syr0ws.fastinventory.internal.config;

import com.github.syr0ws.fastinventory.api.inventory.FastInventoryType;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SimpleInventoryConfig implements InventoryConfig {

    private final String id;
    private final String title;
    private final FastInventoryType type;
    private final Map<Integer, InventoryItemConfig> content;
    private final Set<PaginationConfig> paginations;

    public SimpleInventoryConfig(String id, String title, FastInventoryType type,
                                 Map<Integer, InventoryItemConfig> content,
                                 Set<PaginationConfig> paginations) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }

        if (paginations == null) {
            throw new IllegalArgumentException("paginations cannot be null");
        }

        this.id = id;
        this.title = title;
        this.type = type;
        this.content = Collections.unmodifiableMap(content);
        this.paginations = Collections.unmodifiableSet(paginations);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public FastInventoryType getType() {
        return this.type;
    }

    @Override
    public Map<Integer, InventoryItemConfig> getContent() {
        return this.content;
    }

    @Override
    public Optional<PaginationConfig> getPaginationConfig(String paginationId) {

        if (paginationId == null) {
            throw new IllegalArgumentException("paginationId cannot be null");
        }

        return this.paginations.stream()
                .filter(pagination -> pagination.getId().equals(paginationId))
                .findFirst();
    }

    @Override
    public Set<PaginationConfig> getPaginationConfigs() {
        return this.paginations;
    }
}
