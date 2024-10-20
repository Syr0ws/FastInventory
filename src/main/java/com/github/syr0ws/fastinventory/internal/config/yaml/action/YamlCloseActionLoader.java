package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.CloseAction;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlCloseActionLoader extends YamlCommonActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {
        Set<ClickType> clickTypes = super.loadClickTypes(section);
        return new CloseAction(clickTypes);
    }

    @Override
    public String getName() {
        return CloseAction.ACTION_NAME;
    }
}
