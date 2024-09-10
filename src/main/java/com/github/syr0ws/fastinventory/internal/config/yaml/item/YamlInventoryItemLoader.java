package com.github.syr0ws.fastinventory.internal.config.yaml.item;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.InventoryItemConfig;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.fastinventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.internal.config.SimpleInventoryItemConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YamlInventoryItemLoader {

    private static final String ITEM_KEY = "item";
    private static final String ACTIONS_KEY = "actions";
    private static final String ACTION_TYPE_KEY = "type";

    private final ClickActionLoaderFactory<ConfigurationSection> factory;
    private final YamlItemStackLoader itemStackLoader = new YamlItemStackLoader();

    public YamlInventoryItemLoader(ClickActionLoaderFactory<ConfigurationSection> factory) {

        if(factory == null) {
            throw new IllegalArgumentException("factory cannot be null");
        }

        this.factory = factory;
    }

    public InventoryItemConfig loadItem(ConfigurationSection section) throws InventoryConfigException {

        if(section == null) {
            throw new IllegalArgumentException("section cannot be null");
        }

        String id = section.getName();
        ItemStack item = this.loadItemStack(section);
        List<ClickAction> actions = this.loadActions(section);

        return new SimpleInventoryItemConfig(id, item, actions);
    }

    private ItemStack loadItemStack(ConfigurationSection section) throws InventoryConfigException {

        ItemStack item;

        if(section.isItemStack(ITEM_KEY)) {
            item = section.getItemStack(ITEM_KEY);
        } else if(section.isConfigurationSection(ITEM_KEY)) {
            ConfigurationSection itemSection = section.getConfigurationSection(ITEM_KEY);
            item = this.itemStackLoader.loadItem(itemSection);
        } else {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid at '%s'", ITEM_KEY, section.getCurrentPath()));
        }

        return item;
    }

    private List<ClickAction> loadActions(ConfigurationSection section) throws InventoryConfigException {

        ConfigurationSection actionsSection = section.getConfigurationSection(ACTIONS_KEY);

        if(actionsSection == null) {
            return new ArrayList<>();
        }

        List<ClickAction> actions = new ArrayList<>();

        for(String key : actionsSection.getKeys(false)) {

            ConfigurationSection actionSection = actionsSection.getConfigurationSection(key);

            if(actionSection == null) {
                throw new InventoryConfigException(String.format("Key '%s.%s' is not a section", actionsSection.getCurrentPath(), key));
            }

            if(!actionSection.isString(ACTION_TYPE_KEY)) {
                throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", ACTION_TYPE_KEY, actionSection.getCurrentPath()));
            }

            String clickActionType = actionSection.getString(ACTION_TYPE_KEY);
            ClickActionLoader<ConfigurationSection> loader = this.factory.getLoader(clickActionType);

            if(loader == null) {
                throw new InventoryConfigException(String.format("Invalid action type '%s' at '%s'", clickActionType, actionSection.getCurrentPath()));
            }

            ClickAction action = loader.load(actionSection);

            actions.add(action);
        }

        return actions;
    }
}
