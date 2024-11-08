package com.github.syr0ws.fastinventory.internal.config.yaml.action;

import com.github.syr0ws.fastinventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.fastinventory.common.inventory.action.SoundAction;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class YamlSoundActionLoader extends YamlCommonActionLoader {

    private static final String SOUND_KEY = "sound";
    private static final String VOLUME_KEY = "volume";
    private static final String PITCH_KEY = "pitch";

    @Override
    public ClickAction load(ConfigurationSection section) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(section);
        Sound sound = this.loadSound(section);
        float volume = this.loadVolume(section);
        float pitch = this.loadPitch(section);

        return new SoundAction(clickTypes, sound, volume, pitch);
    }

    private Sound loadSound(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isString(SOUND_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' missing or is not a string", section.getCurrentPath(), VOLUME_KEY));
        }

        String soundName = section.getString(SOUND_KEY);

        try {
            return Sound.valueOf(soundName);
        } catch (IllegalArgumentException exception) {
            throw new InventoryConfigException(String.format("Invalid sound '%s' at '%s.%s'", soundName, section.getCurrentPath(), SOUND_KEY));
        }
    }

    private float loadVolume(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isDouble(VOLUME_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' missing or is not a float", section.getCurrentPath(), VOLUME_KEY));
        }

        float volume = (float) section.getDouble(VOLUME_KEY);

        if (volume < 0f) {
            throw new InventoryConfigException(String.format("Volume at '%s.%s' cannot be negative", section.getCurrentPath(), VOLUME_KEY));
        }

        return volume;
    }

    private float loadPitch(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isDouble(PITCH_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' missing or is not a float", section.getCurrentPath(), VOLUME_KEY));
        }

        float pitch = (float) section.getDouble(PITCH_KEY);

        if (pitch < 0f || pitch > 2f) {
            throw new InventoryConfigException(String.format("Pitch at '%s.%s' cannot be negative or greater than 2", section.getCurrentPath(), VOLUME_KEY));
        }

        return pitch;
    }

    @Override
    public String getName() {
        return SoundAction.ACTION_NAME;
    }
}
