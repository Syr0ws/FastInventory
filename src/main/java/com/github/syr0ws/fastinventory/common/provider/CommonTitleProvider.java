package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.config.InventoryConfig;
import com.github.syr0ws.fastinventory.api.i18n.I18n;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.provider.Provider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;
import org.bukkit.entity.Player;

public class CommonTitleProvider implements Provider<String> {

    private final I18n i18n;

    public CommonTitleProvider(I18n i18n) {
        this.i18n = i18n;
    }

    @Override
    public String provide(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();
        PlaceholderManager placeholderManager = provider.getPlaceholderManager();

        Player viewer = context.getData(CommonContextKeyEnum.VIEWER.name(), Player.class);

        String title = this.i18n == null ? config.getTitle() : this.i18n.getText(viewer, config.getTitle());

        return placeholderManager.parse(title, context);
    }

    @Override
    public String getName() {
        return CommonProviderEnum.TITLE.name();
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
