package com.github.syr0ws.fastinventory.common.transform.placeholder.player;

import com.github.syr0ws.fastinventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
import org.bukkit.entity.Player;

public class PlayerNamePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%player_name%";
    }

    @Override
    public String getValue(Context context) {
        Player player = context.getData(CommonContextKey.VIEWER.name(), Player.class);
        return player.getName();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.VIEWER.name());
    }
}
