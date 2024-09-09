package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.i18n.I18n;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CommonItemStackParser implements ItemParser {

    private final I18n i18n;

    public CommonItemStackParser(I18n i18n) {
        this.i18n = i18n;
    }

    @Override
    public ItemStack parse(InventoryProvider provider, ItemStack item, Context context) {

        if(i18n != null && !context.hasData(CommonContextKeyEnum.VIEWER.name())) {
            throw new IllegalStateException("I18n set but no viewer found in the context");
        }

        PlaceholderManager manager = provider.getPlaceholderManager();

        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return item;
        }

        this.parseName(manager, meta, context);
        this.parseLore(manager, meta, context);

        item.setItemMeta(meta);

        return item;
    }

    private void parseName(PlaceholderManager manager, ItemMeta meta, Context context) {

        if(!meta.hasDisplayName()) {
            return;
        }

        String name = meta.getDisplayName();

        if(this.i18n != null) {
            Player viewer = context.getData(CommonContextKeyEnum.VIEWER.name(), Player.class);
            name = this.i18n.getText(viewer, name);
        }

        name = manager.parse(name, context);
        meta.setDisplayName(name);
    }

    private void parseLore(PlaceholderManager manager, ItemMeta meta, Context context) {

        if(!meta.hasLore()) {
            return;
        }

        List<String> lore = meta.getLore();

        if(this.i18n != null) {
            Player viewer = context.getData(CommonContextKeyEnum.VIEWER.name(), Player.class);
            lore = lore.stream().map(line -> i18n.getText(viewer, line)).toList();
        }

        lore = lore.stream()
                .map(line -> manager.parse(line, context))
                .toList();

        meta.setLore(lore);
    }
}
