package com.github.syr0ws.fastinventory.api.transform.i18n;

import org.bukkit.entity.Player;

public interface I18n {

    /**
     * Translate a text given a key.
     *
     * @param player The player which will view the text.
     * @param key    The key of the text.
     * @return The text identified by the specified key in the lang of the player.
     */
    String getText(Player player, String key);
}
