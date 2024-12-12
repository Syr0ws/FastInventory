package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;
import org.bukkit.configuration.ConfigurationSection;

public enum YamlActionLoaderEnum {

    CLOSE(new YamlCloseActionLoader()),
    MESSAGE(new YamlMessageActionLoader()),
    PLAYER_COMMAND(new YamlPlayerCommandActionLoader()),
    CONSOLE_COMMAND(new YamlConsoleCommandActionLoader()),
    PREVIOUS_PAGE(new YamlPreviousPageActionLoader()),
    NEXT_PAGE(new YamlNextPageActionLoader()),
    BROADCAST(new YamlBroadcastActionLoader()),
    SOUND(new YamlSoundActionLoader()),
    UPDATE_CONTENT(new YamlUpdateContentActionLoader()),
    UPDATE_PAGINATION(new YamlUpdatePaginationActionLoader()),
    OPEN_INVENTORY(new YamlOpenInventoryActionLoader()),
    HOME(new YamlHomeActionLoader()),
    BACK(new YamlBackActionLoader()),
    FORWARD(new YamlForwardActionLoader());

    private final ClickActionLoader<ConfigurationSection> loader;

    YamlActionLoaderEnum(ClickActionLoader<ConfigurationSection> loader) {
        this.loader = loader;
    }

    public ClickActionLoader<ConfigurationSection> getLoader() {
        return this.loader;
    }
}
