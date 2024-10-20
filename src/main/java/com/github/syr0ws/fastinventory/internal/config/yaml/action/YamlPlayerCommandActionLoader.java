package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.PlayerCommandAction;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlPlayerCommandActionLoader extends YamlCommonActionLoader {

    private static final String COMMAND_KEY = "command";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);

        if (!section.isString(COMMAND_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' not found or not a string at '%s'", COMMAND_KEY, section.getCurrentPath()));
        }

        String command = section.getString(COMMAND_KEY);

        if (command.startsWith("/")) {
            command = command.substring(1);
        }

        return new PlayerCommandAction(clickTypes, command);
    }

    @Override
    public String getName() {
        return PlayerCommandAction.ACTION_NAME;
    }
}
