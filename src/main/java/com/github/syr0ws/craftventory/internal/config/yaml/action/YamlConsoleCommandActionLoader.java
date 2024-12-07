package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.ConsoleCommandAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class YamlConsoleCommandActionLoader extends YamlCommandActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        List<String> commands = super.loadCommands(section);

        return new ConsoleCommandAction(clickTypes, commands);
    }

    @Override
    public String getName() {
        return ConsoleCommandAction.ACTION_NAME;
    }
}