package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.MessageAction;
import org.bukkit.configuration.ConfigurationSection;

public class YamlMessageActionLoader implements ClickActionLoader<ConfigurationSection> {

    private static final String MESSAGE_KEY = "message";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        if(!section.isString(MESSAGE_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", MESSAGE_KEY, section.getCurrentPath()));
        }

        String message = section.getString(MESSAGE_KEY);

        return new MessageAction(message);
    }

    @Override
    public String getName() {
        return MessageAction.ACTION_NAME;
    }
}
