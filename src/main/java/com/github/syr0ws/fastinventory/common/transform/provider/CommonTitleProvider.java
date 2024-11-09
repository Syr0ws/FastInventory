package com.github.syr0ws.fastinventory.common.transform.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;
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
