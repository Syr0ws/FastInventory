package com.github.syr0ws.craftventory.api.transform.i18n;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * Provides internationalization (i18n) support by translating text keys into localized strings.
 */
public interface I18n {

    /**
     * Translates a text identified by a key into the language of the specified player.
     *
     * @param viewer The player who will view the text. Must not be {@code null}.
     * @param key    The key identifying the text to translate. Must not be {@code null}.
     * @return A {@link String} containing the translated text for the player's language.
     *         If the key does not exist, implementations may return a default text or the key itself.
     * @throws IllegalArgumentException If {@code player} or {@code key} is {@code null}.
     */
    String getText(InventoryViewer viewer, String key);
}
