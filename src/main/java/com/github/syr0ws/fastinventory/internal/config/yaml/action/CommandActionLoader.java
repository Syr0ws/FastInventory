package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.CommandAction;
import org.bukkit.configuration.ConfigurationSection;

public class CommandActionLoader implements ClickActionLoader<ConfigurationSection> {

    private static final String COMMAND_KEY = "command";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        if(!section.isString(COMMAND_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' not found or not a string at '%s'", COMMAND_KEY, section.getCurrentPath()));
        }

        String command = section.getString(COMMAND_KEY);

        if(command.startsWith("/")) {
            command = command.substring(1);
        }

        return new CommandAction(command);
    }

    @Override
    public String getName() {
        return CommandAction.ACTION_NAME;
    }
}
