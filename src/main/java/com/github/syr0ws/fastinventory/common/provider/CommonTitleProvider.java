package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import org.bukkit.entity.Player;

public class CommonTitleProvider implements Provider<String> {

    @Override
    public String provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Player viewer = context.getData(CommonContextKey.VIEWER.name(), Player.class);

        String title = provider.getI18n()
                .map(i18n -> i18n.getText(viewer, config.getTitle()))
                .orElse(config.getTitle());

        return placeholderManager.parse(title, context);
    }

    @Override
    public String getName() {
        return CommonProviderType.TITLE.name();
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
