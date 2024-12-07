package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.BackwardAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlBackActionLoader extends YamlCommandActionLoader {

    private static final String INVENTORY_ID_KEY = "inventory-id";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        String inventoryId = section.getString(INVENTORY_ID_KEY, null);

        return new BackwardAction(clickTypes, inventoryId);
    }

    @Override
    public String getName() {
        return BackwardAction.ACTION_NAME;
    }
}
