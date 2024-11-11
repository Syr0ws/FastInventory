package com.github.syr0ws.fastinventory.api.inventory.model;

import java.util.Optional;
import java.util.Set;

public interface InventoryModel {

    <T> void setData(String key, Class<T> type, T value);

    boolean removeData(String key);

    boolean hasData(String key);

    <T> Optional<T> getData(String key, Class<T> type);

    Set<String> getKeys();
}
