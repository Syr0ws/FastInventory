package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.fastinventory.common.inventory.action.UpdateContentAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlUpdateContentActionLoader extends YamlCommonActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {
        Set<ClickType> clickTypes = super.loadClickTypes(section);
        return new UpdateContentAction(clickTypes);
    }

    @Override
    public String getName() {
        return UpdateContentAction.ACTION_NAME;
    }
}
