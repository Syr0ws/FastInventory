package com.github.syr0ws.fastinventory.internal.config.yaml;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.PaginationConfig;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.NextPageAction;
import com.github.syr0ws.fastinventory.common.action.PreviousPageAction;
import com.github.syr0ws.fastinventory.internal.config.SimplePaginationConfig;
import com.github.syr0ws.fastinventory.internal.config.yaml.item.YamlInventoryItemLoader;
import com.github.syr0ws.fastinventory.internal.util.Pair;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class YamlInventoryPaginationLoader {

    private static final String PAGINATIONS_KEY = "paginations";
    private static final String PAGINATION_ID_KEY = "id";
    private static final String PAGINATION_SYMBOL_KEY = "symbol";
    private static final String PAGINATION_ITEM_KEY = "pagination-item";
    private static final String PAGE_ITEM_SYMBOL_KEY = "symbol";
    private static final String PREVIOUS_PAGE_ITEM_KEY = "previous-page-item";
    private static final String NEXT_PAGE_ITEM_KEY = "next-page-item";

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

        String paginationId = this.loadPaginationId(section);
        char paginationSymbol = this.loadPaginationSymbol(section);
        List<Integer> slots = this.loadPaginationSlots(section, paginationSymbol, symbolSlots);
        InventoryItemConfig item = this.loadPaginationItem(section);

        Pair<InventoryItemConfig, List<Integer>> previousPageItem = this.loadPageItem(section, PREVIOUS_PAGE_ITEM_KEY, symbolSlots);
        Pair<InventoryItemConfig, List<Integer>> nextPageItem = this.loadPageItem(section, NEXT_PAGE_ITEM_KEY, symbolSlots);

        previousPageItem.key().getActions().add(new PreviousPageAction(paginationId));
        nextPageItem.key().getActions().add(new NextPageAction(paginationId));

        return new SimplePaginationConfig(
                paginationId,
                new HashSet<>(slots),
                item,
                previousPageItem.key(),
                nextPageItem.key(),
                previousPageItem.value(),
                nextPageItem.value()
        );
    }

    private String loadPaginationId(ConfigurationSection section) throws InventoryConfigException {

        String paginationId = section.getString(PAGINATION_ID_KEY);

        if(paginationId == null || paginationId.isEmpty()) {
            throw new InventoryConfigException(String.format("Property '%s' missing or empty in pagination at '%s'", PAGINATION_ID_KEY, section.getCurrentPath()));
        }

        return paginationId;
    }

    private char loadPaginationSymbol(ConfigurationSection section) throws InventoryConfigException {

        String symbol = section.getString(PAGINATION_SYMBOL_KEY);

        if (symbol == null || symbol.length() != 1) {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid in pagination at '%s'", PAGINATION_SYMBOL_KEY, section.getCurrentPath()));
        }

        return symbol.charAt(0);
    }

    private InventoryItemConfig loadPaginationItem(ConfigurationSection section) throws InventoryConfigException {

        ConfigurationSection paginationItemSection = section.getConfigurationSection(PAGINATION_ITEM_KEY);

        if(paginationItemSection == null) {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid in pagination at '%s'", PAGINATION_ITEM_KEY, section.getCurrentPath()));
        }

        return this.itemLoader.loadItem(paginationItemSection);
    }

    private List<Integer> loadPaginationSlots(ConfigurationSection section, char paginationSymbol, Map<Character, List<Integer>> symbolSlots) throws InventoryConfigException {

        List<Integer> slots = symbolSlots.getOrDefault(paginationSymbol, Collections.emptyList());

        if(slots.isEmpty()) {
            throw new InventoryConfigException(String.format("Symbol '%s' not found in pattern for pagination at '%s'", paginationSymbol, section.getCurrentPath()));
        }

        return slots;
    }

    private Pair<InventoryItemConfig, List<Integer>> loadPageItem(ConfigurationSection section, String key, Map<Character, List<Integer>> symbolSlots) throws InventoryConfigException {

        ConfigurationSection pageItemSection = section.getConfigurationSection(key);

        if(pageItemSection == null) {
            throw new InventoryConfigException(String.format("Property '%s' missing or not a section at '%s'", key, section.getCurrentPath()));
        }

        InventoryItemConfig item = this.itemLoader.loadItem(pageItemSection);

        String symbol = pageItemSection.getString(PAGE_ITEM_SYMBOL_KEY);

        if (symbol == null || symbol.length() != 1) {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid at '%s'", PAGE_ITEM_SYMBOL_KEY, section.getCurrentPath()));
        }

        List<Integer> slots = symbolSlots.getOrDefault(symbol.charAt(0), Collections.emptyList());

        if(slots.isEmpty()) {
            throw new InventoryConfigException(String.format("Symbol '%s' not found in pattern for pagination at '%s'", symbol, section.getCurrentPath()));
        }

        return new Pair<>(item, slots);
    }
}
