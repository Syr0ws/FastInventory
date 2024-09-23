package com.github.syr0ws.fastinventory.common.provider;

import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.item.ItemParser;
import com.github.syr0ws.fastinventory.api.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CommonItemStackParser implements ItemParser {

    @Override
    public ItemStack parse(InventoryProvider provider, ItemStack item, Context context) {

        PlaceholderManager manager = provider.getPlaceholderManager();

        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return item;
        }

        this.parseName(provider, manager, meta, context);
        this.parseLore(provider, manager, meta, context);

        item.setItemMeta(meta);

        return item;
    }

    private void parseName(InventoryProvider provider, PlaceholderManager manager, ItemMeta meta, Context context) {

        if(!meta.hasDisplayName()) {
            return;
        }

        String name = provider.getI18n()
                .map(i18n -> {
                    Player viewer = context.getData(CommonContextKey.VIEWER.name(), Player.class);
                    return i18n.getText(viewer, meta.getDisplayName());
                }).orElse(meta.getDisplayName());

        name = manager.parse(name, context);

        meta.setDisplayName(name);
    }

    private void parseLore(InventoryProvider provider, PlaceholderManager manager, ItemMeta meta, Context context) {

        if(!meta.hasLore()) {
            return;
        }

        List<String> lore = provider.getI18n()
                .map(i18n -> {
                    Player viewer = context.getData(CommonContextKey.VIEWER.name(), Player.class);
                    return meta.getLore().stream().map(line -> i18n.getText(viewer, line)).toList();
                }).orElse(meta.getLore());

        lore = lore.stream()
                .map(line -> manager.parse(line, context))
                .toList();

        meta.setLore(lore);
    }
}
