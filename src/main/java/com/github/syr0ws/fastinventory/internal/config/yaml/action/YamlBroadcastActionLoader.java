package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.BroadcastAction;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class YamlBroadcastActionLoader extends YamlCommonActionLoader {

    private static final String MESSAGES_KEY = "messages";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);

        if (!section.isList(MESSAGES_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a list", MESSAGES_KEY, section.getCurrentPath()));
        }

        List<String> messages = section.getStringList(MESSAGES_KEY);

        return new BroadcastAction(clickTypes, messages);
    }

    @Override
    public String getName() {
        return BroadcastAction.ACTION_NAME;
    }
}
