package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.fastinventory.common.action.CloseAction;
import org.bukkit.configuration.ConfigurationSection;

public class YamlCloseActionLoader implements ClickActionLoader<ConfigurationSection> {

    @Override
    public ClickAction load(ConfigurationSection section) {
        return new CloseAction();
    }

    @Override
    public String getName() {
        return CloseAction.ACTION_NAME;
    }
}
