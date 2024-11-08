package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.inventory.action.NextPageAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlNextPageActionLoader extends YamlPageActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        String paginationId = super.getPaginationId(section);

        return new NextPageAction(clickTypes, paginationId);
    }

    @Override
    public String getName() {
        return NextPageAction.ACTION_NAME;
    }
}
