package com.github.syr0ws.craftventory.common.transform.item;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CommonItemStackParser implements ItemParser {

    @Override
    public ItemStack parse(InventoryProvider provider, ItemStack item, Context context) {

        if(provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        if(context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        if(item == null) {
            return null;
        }

        PlaceholderManager manager = provider.getPlaceholderManager();

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        this.parseName(provider, manager, meta, context);
        this.parseLore(provider, manager, meta, context);

        item.setItemMeta(meta);

        return item;
    }

    private void parseName(InventoryProvider provider, PlaceholderManager manager, ItemMeta meta, Context context) {

        if (!meta.hasDisplayName()) {
            return;
        }

        String name = provider.getI18n()
                .map(i18n -> {
                    InventoryViewer viewer = context.getData(CommonContextKey.VIEWER.name(), InventoryViewer.class);
                    return i18n.getText(viewer, meta.getDisplayName());
                }).orElse(meta.getDisplayName());

        name = manager.parse(name, context);

        meta.setDisplayName(name);
    }

    private void parseLore(InventoryProvider provider, PlaceholderManager manager, ItemMeta meta, Context context) {

        if (!meta.hasLore()) {
            return;
        }

        List<String> lore = provider.getI18n()
                .map(i18n -> {
                    InventoryViewer viewer = context.getData(CommonContextKey.VIEWER.name(), InventoryViewer.class);
                    return meta.getLore().stream().map(line -> i18n.getText(viewer, line)).toList();
                }).orElse(meta.getLore());

        lore = lore.stream()
                .map(line -> manager.parse(line, context))
                .toList();

        meta.setLore(lore);
    }
}
