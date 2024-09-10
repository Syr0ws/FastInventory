package com.github.syr0ws.fastinventory.common.placeholder.player;

import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import org.bukkit.entity.Player;

public class PlayerUUIDPlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%player_uuid%";
    }

    @Override
    public String getValue(Context context) {
        Player viewer = context.getData(CommonContextKey.VIEWER.name(), Player.class);
        return viewer.getUniqueId().toString();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.VIEWER.name());
    }
}
