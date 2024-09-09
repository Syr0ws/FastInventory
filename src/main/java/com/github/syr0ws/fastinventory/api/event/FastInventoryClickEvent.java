package com.github.syr0ws.fastinventory.api.event;

import com.github.syr0ws.fastinventory.api.FastInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;

public class FastInventoryClickEvent extends InventoryClickEvent {

    private static final HandlerList handlers = new HandlerList();

    private final FastInventory fastInventory;

    public FastInventoryClickEvent(FastInventory fastInventory, InventoryView view, InventoryType.SlotType type, int slot, ClickType click, InventoryAction action) {
        super(view, type, slot, click, action);

        if(fastInventory == null) {
            throw new IllegalArgumentException("fastInventory cannot be null");
        }

        this.fastInventory = fastInventory;
    }

    public FastInventory getFastInventory() {
        return this.fastInventory;
    }

    public Player getPlayer() {
        return (Player) super.getWhoClicked();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
