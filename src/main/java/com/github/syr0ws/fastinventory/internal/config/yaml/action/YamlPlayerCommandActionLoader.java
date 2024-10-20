package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.PlayerCommandAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class YamlPlayerCommandActionLoader extends YamlCommandActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        List<String> commands = super.loadCommands(section);

        return new PlayerCommandAction(clickTypes, commands);
    }

    @Override
    public String getName() {
        return PlayerCommandAction.ACTION_NAME;
    }
}
