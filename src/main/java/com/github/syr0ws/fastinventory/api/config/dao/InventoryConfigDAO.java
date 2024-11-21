package com.github.syr0ws.fastinventory.api.config.dao;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;

import java.nio.file.Path;
import java.util.Set;

/**
 * Data Access Object (DAO) interface for loading inventory configurations.
 */
public interface InventoryConfigDAO {

    /**
     * Loads an {@link InventoryConfig} from a specified file.
     *
     * @param file The path to the configuration file. Must not be {@code null}.
     * @return The loaded {@link InventoryConfig}.
     * @throws IllegalArgumentException If file is {@code null}
     * @throws InventoryConfigException If an error occurs while loading or parsing the configuration file.
     */
    InventoryConfig loadConfig(Path file) throws InventoryConfigException;

    /**
     * Loads all {@link InventoryConfig} instances from a specified folder.
     * <p>
     * This method scans the folder for valid configuration files, attempts to load them,
     * and returns a set of all successfully parsed {@link InventoryConfig} instances.
     * <br>
     * If an error occurs while loading a specific configuration file, that file will be skipped,
     * and a stack trace will be generated.
     * </p>
     *
     * @param folder The path to the folder containing configuration files. Must not be {@code null}.
     * @return A set of successfully loaded {@link InventoryConfig} instances. If no valid configuration files
     *         are found, the set will be empty.
     * @throws IllegalArgumentException If the {@code folder} parameter is {@code null}.
     * @throws InventoryConfigException If the given path does not point to a folder or if an error occurs
     *                                   while accessing the folder.
     */
    Set<InventoryConfig> loadConfigs(Path folder) throws InventoryConfigException;
}
