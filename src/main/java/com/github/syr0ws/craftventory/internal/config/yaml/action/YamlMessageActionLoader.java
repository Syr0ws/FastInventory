package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.MessageAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class YamlMessageActionLoader extends YamlCommonActionLoader {

    private static final String MESSAGES_KEY = "messages";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);

        if (!section.isList(MESSAGES_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a list", MESSAGES_KEY, section.getCurrentPath()));
        }

        List<String> messages = section.getStringList(MESSAGES_KEY);

        return new MessageAction(clickTypes, messages);
    }

    @Override
    public String getName() {
        return MessageAction.ACTION_NAME;
    }
}
