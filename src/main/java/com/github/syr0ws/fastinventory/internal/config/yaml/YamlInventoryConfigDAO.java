package com.github.syr0ws.fastinventory.internal.config.yaml;

import com.github.syr0ws.fastinventory.api.FastInventoryType;
import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.fastinventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.internal.config.SimpleInventoryConfig;
import com.github.syr0ws.fastinventory.internal.config.yaml.item.YamlInventoryItemLoader;
import com.github.syr0ws.fastinventory.internal.util.TextUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlInventoryConfigDAO implements InventoryConfigDAO {

    private static final String INVENTORY_ID_KEY = "id";
    private static final String INVENTORY_TITLE_KEY = "title";
    private static final String INVENTORY_TYPE_KEY = "type";
    private static final String INVENTORY_PATTERN_KEY = "pattern";

    private final YamlInventoryContentLoader contentLoader;
    private final YamlInventoryPaginationLoader paginationLoader;

    public YamlInventoryConfigDAO(ClickActionLoaderFactory<ConfigurationSection> factory) {

        YamlInventoryItemLoader itemLoader = new YamlInventoryItemLoader(factory);

        this.contentLoader = new YamlInventoryContentLoader(itemLoader);
        this.paginationLoader = new YamlInventoryPaginationLoader(itemLoader);
    }

    @Override
    public InventoryConfig loadConfig(Path file) throws InventoryConfigException {

        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }

        if (!Files.exists(file)) {
            throw new InventoryConfigException(String.format("File %s does not exist", file));
        }

        ConfigurationSection section = YamlConfiguration.loadConfiguration(file.toFile());

        return this.loadConfig(section);
    }

    @Override
    public Set<InventoryConfig> loadConfigs(Path folder) throws InventoryConfigException {

        if (folder == null) {
            throw new IllegalArgumentException("folder cannot be null");
        }

        if (!Files.isDirectory(folder)) {
            throw new InventoryConfigException(String.format("Folder %s is not a directory or does not exist", folder));
        }

        Set<InventoryConfig> configs;

        try (Stream<Path> files = Files.list(folder)) {

            configs = files.map(file -> {
                try {
                    return this.loadConfig(file);
                } catch (InventoryConfigException exception) {
                    exception.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toSet());

        } catch (IOException exception) {
            throw new InventoryConfigException(String.format("An error occurred while listing configuration files of folder %s", folder), exception);
        }

        return configs;
    }

    private InventoryConfig loadConfig(ConfigurationSection section) throws InventoryConfigException {

        // Loading inventory properties.
        String id = this.loadId(section);
        String title = TextUtil.parseColors(section.getString(INVENTORY_TITLE_KEY, ""));
        FastInventoryType type = this.loadInventoryType(section);

        // Pattern.
        List<String> pattern = this.loadPattern(section, type);

        // Retrieving all the symbols and their slots from the pattern.
        Map<Character, List<Integer>> symbolSlots = this.getSymbolSlots(pattern, type);

        // Loading content.
        Map<Integer, InventoryItemConfig> content = this.contentLoader.loadContent(section, symbolSlots);


        Set<PaginationConfig> paginations = this.paginationLoader.loadPaginations(section, symbolSlots);

        return new SimpleInventoryConfig(id, title, type, content, paginations);
    }

    private String loadId(ConfigurationSection section) throws InventoryConfigException {

        String id = section.getString(INVENTORY_ID_KEY);

        if (id == null || id.isEmpty()) {
            throw new InventoryConfigException(String.format("Property '%s' missing or empty at '%s'", INVENTORY_ID_KEY, section.getCurrentPath()));
        }

        return id;
    }

    private FastInventoryType loadInventoryType(ConfigurationSection section) throws InventoryConfigException {

        String typeAsString = section.getString(INVENTORY_TYPE_KEY);

        if (typeAsString == null) {
            throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", INVENTORY_TYPE_KEY, section.getCurrentPath()));
        }

        try {
            return FastInventoryType.valueOf(typeAsString);
        } catch (IllegalArgumentException exception) {
            throw new InventoryConfigException(String.format("Invalid type '%s' at '%s'", typeAsString, section.getCurrentPath()));
        }
    }

    private List<String> loadPattern(ConfigurationSection section, FastInventoryType type) throws InventoryConfigException {

        // Existence and type checks.
        if (!section.isList(INVENTORY_PATTERN_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' missing or not a list at '%s'", INVENTORY_PATTERN_KEY, section.getCurrentPath()));
        }

        List<String> pattern = section.getStringList(INVENTORY_PATTERN_KEY);

        // Pattern validation.
        if (pattern.size() != type.getRows()) {
            throw new InventoryConfigException(String.format("Invalid property '%s' at '%s' : number of rows must be %d", INVENTORY_PATTERN_KEY, section.getCurrentPath(), type.getRows()));
        }

        boolean hasInvalidRow = pattern.stream()
                .anyMatch(line -> line.length() != type.getColumns());

        if (hasInvalidRow) {
            throw new InventoryConfigException(String.format("Invalid property '%s' at '%s' : some lines do not have %d characters", INVENTORY_PATTERN_KEY, section.getCurrentPath(), type.getColumns()));
        }

        return pattern;
    }

    private Map<Character, List<Integer>> getSymbolSlots(List<String> pattern, FastInventoryType type) throws InventoryConfigException {

        Map<Character, List<Integer>> symbolsSlots = new HashMap<>();

        for (int row = 0; row < pattern.size(); row++) {

            String line = pattern.get(row);

            for (int column = 0; column < line.length(); column++) {

                char symbol = line.charAt(column);
                int slot = (row * type.getColumns()) + column;

                if (!symbolsSlots.containsKey(symbol)) {
                    symbolsSlots.put(symbol, new ArrayList<>());
                }

                symbolsSlots.get(symbol).add(slot);
            }
        }

        return symbolsSlots;
    }
}
