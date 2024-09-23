package com.github.syr0ws.fastinventory.internal.config.yaml;

import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.internal.config.yaml.item.YamlInventoryItemLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlInventoryContentLoader {

    private static final String INVENTORY_CONTENT_KEY = "content";
    private static final String SYMBOL_KEY = "symbol";

    private final YamlInventoryItemLoader itemLoader;

    public YamlInventoryContentLoader(YamlInventoryItemLoader itemLoader) {

        if (itemLoader == null) {
            throw new IllegalArgumentException("itemLoader cannot be null");
        }

        this.itemLoader = itemLoader;
    }

    public Map<Integer, InventoryItemConfig> loadContent(ConfigurationSection section, Map<Character, List<Integer>> symbolSlots) throws InventoryConfigException {

        ConfigurationSection contentSection = section.getConfigurationSection(INVENTORY_CONTENT_KEY);

        if (contentSection == null) {
            throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", INVENTORY_CONTENT_KEY, section.getCurrentPath()));
        }

        Map<Integer, InventoryItemConfig> content = new HashMap<>();

        // Loading all the items. If one fails, it doesn't stop the loading process.
        for (String key : contentSection.getKeys(false)) {

            try {

                // Checking that the key is a section.
                ConfigurationSection itemSection = contentSection.getConfigurationSection(key);

                if (itemSection == null) {
                    throw new InventoryConfigException(String.format("Property '%s.%s' must be a section", contentSection.getCurrentPath(), key));
                }

                // Loading the symbol used in the pattern.
                String symbol = itemSection.getString(SYMBOL_KEY);

                if (symbol == null || symbol.length() != 1) {
                    throw new InventoryConfigException(String.format("Property '%s' missing or invalid at '%s'", SYMBOL_KEY, itemSection.getCurrentPath()));
                }

                // Retrieving the slots to set the item to from the pattern.
                List<Integer> slots = symbolSlots.getOrDefault(symbol.charAt(0), Collections.emptyList());

                if (slots.isEmpty()) {
                    continue;
                }

                InventoryItemConfig itemConfig = this.itemLoader.loadItem(itemSection);

                slots.forEach(slot -> content.put(slot, itemConfig));

            } catch (Exception exception) {
                throw new InventoryConfigException("An error occurred while loading the inventory content", exception);
            }
        }

        return content;
    }
}
