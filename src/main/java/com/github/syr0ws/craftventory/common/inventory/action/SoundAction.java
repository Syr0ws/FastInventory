package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Set;

public class SoundAction extends CommonAction {

    public static final String ACTION_NAME = "SOUND";

    private final Sound sound;
    private final float volume;
    private final float pitch;

    public SoundAction(Set<ClickType> clickTypes, Sound sound, float volume, float pitch) {
        super(clickTypes);

        if (sound == null) {
            throw new IllegalArgumentException("Sound cannot be null");
        }

        if (volume < 0f) {
            throw new IllegalArgumentException("Volume cannot be negative");
        }

        if (pitch < 0f || pitch > 2f) {
            throw new IllegalArgumentException("Pitch cannot be negative or greater than 2");
        }

        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {
        InventoryViewer viewer = event.getViewer();
        Player player = viewer.getPlayer();
        player.playSound(player.getLocation(), this.sound, this.volume, this.pitch);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
