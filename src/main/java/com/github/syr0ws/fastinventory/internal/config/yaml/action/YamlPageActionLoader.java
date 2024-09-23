package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;

public abstract class YamlPageActionLoader implements ClickActionLoader<ConfigurationSection> {

    private static final String PAGINATION_ID_KEY = "pagination-id";

    protected String getPaginationId(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isString(PAGINATION_ID_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", PAGINATION_ID_KEY, section.getCurrentPath()));
        }

        return section.getString(PAGINATION_ID_KEY);
    }
}
