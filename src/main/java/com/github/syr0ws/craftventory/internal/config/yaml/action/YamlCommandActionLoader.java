package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public abstract class YamlCommandActionLoader extends YamlCommonActionLoader {

    private static final String COMMANDS_KEY = "commands";

    protected List<String> loadCommands(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isList(COMMANDS_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a list", COMMANDS_KEY, section.getCurrentPath()));
        }

        return section.getStringList(COMMANDS_KEY).stream()
                .map(command -> command.startsWith("/") ? command.substring(1) : command)
                .toList();
    }
}
