package com.github.syr0ws.fastinventory.internal.config.yaml;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.internal.config.SimplePaginationConfig;
import com.github.syr0ws.fastinventory.internal.config.yaml.item.YamlInventoryItemLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class YamlInventoryPaginationLoader {

    private static final String PAGINATIONS_KEY = "paginations";
    private static final String PAGINATION_ID_KEY = "id";
    private static final String PAGINATION_SYMBOL_KEY = "symbol";
    private static final String PAGINATION_ITEM_KEY = "pagination-item";

    private final YamlInventoryItemLoader itemLoader;

    public YamlInventoryPaginationLoader(YamlInventoryItemLoader itemLoader) {

        if(itemLoader == null) {
            throw new IllegalArgumentException("itemLoader cannot be null");
        }

        this.itemLoader = itemLoader;
    }

    public Set<PaginationConfig> loadPaginations(ConfigurationSection section, Map<Character, List<Integer>> symbolSlots) throws InventoryConfigException {

        ConfigurationSection paginationsSection = section.getConfigurationSection(PAGINATIONS_KEY);

        // No paginations section defined.
        if(paginationsSection == null) {
            return new HashSet<>();
        }

        Set<PaginationConfig> paginations = new HashSet<>();

        // Loading all the paginations. If one fails, an exception is thrown.
        for(String key : paginationsSection.getKeys(false)) {

            ConfigurationSection paginationSection = paginationsSection.getConfigurationSection(key);

            if(paginationSection == null) {
                throw new InventoryConfigException(String.format("Key '%s.%s' is not a section", paginationsSection.getCurrentPath(), key));
            }

            PaginationConfig pagination = this.loadPagination(paginationSection, symbolSlots);

            paginations.add(pagination);
        }

        return paginations;
    }

    private PaginationConfig loadPagination(ConfigurationSection section, Map<Character, List<Integer>> symbolSlots) throws InventoryConfigException {

        // Loading the pagination id.
        String paginationId = section.getString(PAGINATION_ID_KEY);

        if(paginationId == null || paginationId.isEmpty()) {
            throw new InventoryConfigException(String.format("Property '%s' missing or empty in pagination at '%s'", PAGINATION_ID_KEY, section.getCurrentPath()));
        }

        // Loading the symbol used in the pattern.
        String symbol = section.getString(PAGINATION_SYMBOL_KEY);

        if (symbol == null || symbol.length() != 1) {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid in pagination at '%s'", PAGINATION_SYMBOL_KEY, section.getCurrentPath()));
        }

        // Loading the pagination item.
        ConfigurationSection paginationItemSection = section.getConfigurationSection(PAGINATION_ITEM_KEY);

        if(paginationItemSection == null) {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid in pagination at '%s'", PAGINATION_ITEM_KEY, section.getCurrentPath()));
        }

        InventoryItemConfig item = this.itemLoader.loadItem(paginationItemSection);

        // Retrieving pagination slots.
        List<Integer> slots = symbolSlots.getOrDefault(symbol.charAt(0), Collections.emptyList());

        if(slots.isEmpty()) {
            throw new InventoryConfigException(String.format("Symbol '%s' not found in pattern for pagination at '%s'", symbol, section.getCurrentPath()));
        }

        return new SimplePaginationConfig(paginationId, new HashSet<>(slots), item);
    }
}
