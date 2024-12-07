package com.github.syr0ws.craftventory.common.transform.placeholder.player;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import org.bukkit.entity.Player;

public class PlayerNamePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%player_name%";
    }

    @Override
    public String getValue(Context context) {
        InventoryViewer viewer = context.getData(CommonContextKey.VIEWER.name(), InventoryViewer.class);
        Player player = viewer.getPlayer();
        return player.getName();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.VIEWER.name());
    }
}
