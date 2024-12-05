package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.common.inventory.action.ForwardAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlForwardActionLoader extends YamlCommandActionLoader {

    private static final String INVENTORY_ID_KEY = "inventory-id";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        String inventoryId = section.getString(INVENTORY_ID_KEY, null);

        return new ForwardAction(clickTypes, inventoryId);
    }

    @Override
    public String getName() {
        return ForwardAction.ACTION_NAME;
    }
}
