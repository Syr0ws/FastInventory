package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.ConsoleCommandAction;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class YamlConsoleCommandActionLoader extends YamlCommonActionLoader {

    private static final String COMMANDS_KEY = "commands";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);

        if (!section.isList(COMMANDS_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a list", COMMANDS_KEY, section.getCurrentPath()));
        }

        List<String> commands = section.getStringList(COMMANDS_KEY).stream()
                .map(command -> command.startsWith("/") ? command.substring(1) : command)
                .toList();

        return new ConsoleCommandAction(clickTypes, commands);
    }

    @Override
    public String getName() {
        return ConsoleCommandAction.ACTION_NAME;
    }
}