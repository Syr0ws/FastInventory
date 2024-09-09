package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.action.ClickAction;
import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.common.action.ClickActionEnum;
import com.github.syr0ws.fastinventory.common.action.PreviousPageAction;
import org.bukkit.configuration.ConfigurationSection;

public class YamlPreviousPageActionLoader extends YamlPageActionLoader {

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        String paginationId = super.getPaginationId(section);

        return new PreviousPageAction(paginationId);
    }

    @Override
    public String getName() {
        return ClickActionEnum.PREVIOUS_PAGE.name();
    }
}
