package com.github.syr0ws.fastinventory.common.transform.placeholder.player;

import com.github.syr0ws.fastinventory.api.inventory.InventoryViewer;
import com.github.syr0ws.fastinventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import org.bukkit.entity.Player;

public class PlayerUUIDPlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%player_uuid%";
    }

    @Override
    public String getValue(Context context) {
        InventoryViewer viewer = context.getData(CommonContextKey.VIEWER.name(), InventoryViewer.class);
        Player player = viewer.getPlayer();
        return player.getUniqueId().toString();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.VIEWER.name());
    }
}
