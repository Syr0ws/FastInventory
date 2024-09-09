package com.github.syr0ws.fastinventory.common.placeholder;

import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;
import org.bukkit.entity.Player;

public class PlayerUUIDPlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%player_uuid%";
    }

    @Override
    public String getValue(Context context) {
        Player viewer = context.getData(CommonContextKeyEnum.VIEWER.name(), Player.class);
        return viewer.getUniqueId().toString();
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKeyEnum.VIEWER.name());
    }
}
