package com.github.syr0ws.craftventory.common.config.yaml;

import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class YamlCommonActionLoader implements ClickActionLoader<ConfigurationSection> {

    private static final String CLICK_TYPES_KEY = "click-types";

    protected Set<ClickType> loadClickTypes(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = new HashSet<>();

        // If the property is not set, all clicks are handled by default.
        if (!section.isSet(CLICK_TYPES_KEY)) {
            clickTypes.add(ClickType.ALL);
            return clickTypes;
        }

        // When the property is set, checking that it is a list.
        if (!section.isList(CLICK_TYPES_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' must be a list", section.getCurrentPath(), CLICK_TYPES_KEY));
        }

        List<String> clickTypeNames = section.getStringList(CLICK_TYPES_KEY);

        for (String clickTypeName : clickTypeNames) {

            String parsedName = clickTypeName.toUpperCase();

            if (!ClickType.isClickType(parsedName)) {
                throw new InventoryConfigException(String.format("Invalid click type '%s' at '%s.%s'", clickTypeName, section.getCurrentPath(), CLICK_TYPES_KEY));
            }

            clickTypes.add(ClickType.valueOf(parsedName));
        }

        return clickTypes;
    }
}
