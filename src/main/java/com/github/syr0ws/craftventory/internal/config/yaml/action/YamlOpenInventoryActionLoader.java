package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.OpenInventoryAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlOpenInventoryActionLoader extends YamlCommandActionLoader {

    private static final String INVENTORY_ID_KEY = "inventory-id";
    private static final String NEW_HISTORY = "new-history";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);

        if(!section.isString(INVENTORY_ID_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a string", INVENTORY_ID_KEY, section.getCurrentPath()));
        }

        String inventoryId = section.getString(INVENTORY_ID_KEY);

        if(inventoryId.isEmpty()) {
            throw new InventoryConfigException(String.format("Property '%s.%s' cannot be empty", INVENTORY_ID_KEY, section.getCurrentPath()));
        }

        boolean newHistory = section.getBoolean(NEW_HISTORY, true);

        return new OpenInventoryAction(clickTypes, inventoryId, newHistory);
    }

    @Override
    public String getName() {
        return OpenInventoryAction.ACTION_NAME;
    }
}
