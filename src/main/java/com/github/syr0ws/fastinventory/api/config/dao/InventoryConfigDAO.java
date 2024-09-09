package com.github.syr0ws.fastinventory.api.config.dao;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;

import java.nio.file.Path;
import java.util.Set;

public interface InventoryConfigDAO {

    InventoryConfig loadConfig(Path file) throws InventoryConfigException;

    Set<InventoryConfig> loadConfigs(Path folder) throws InventoryConfigException;
}
