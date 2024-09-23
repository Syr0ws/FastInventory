package com.github.syr0ws.fastinventory.api.config;

import com.github.syr0ws.fastinventory.api.FastInventoryType;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface InventoryConfig extends Configuration {

    String getId();

    String getTitle();

    FastInventoryType getType();

    Map<Integer, InventoryItemConfig> getContent();

    Optional<PaginationConfig> getPaginationConfig(String paginationId);

    Set<PaginationConfig> getPaginationConfigs();
}
