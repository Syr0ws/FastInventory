package com.github.syr0ws.fastinventory.api.inventory.event;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;

public class FastInventoryClickEvent extends FastInventoryEvent implements Cancellable {

    private final InventoryView view;
    private final InventoryType.SlotType slotType;
    private final int slot;
    private final ClickType clickType;
    private final InventoryAction action;
    private boolean cancelled;

    public FastInventoryClickEvent(FastInventory inventory, Player player, InventoryView view, InventoryType.SlotType slotType, int slot, ClickType clickType, InventoryAction action) {
        super(inventory, player);
        this.view = view;
        this.slotType = slotType;
        this.slot = slot;
        this.clickType = clickType;
        this.action = action;
    }

    public InventoryView getView() {
        return this.view;
    }

    public InventoryType.SlotType getSlotType() {
        return this.slotType;
    }

    public int getSlot() {
        return this.slot;
    }

    public ClickType getClickType() {
        return this.clickType;
    }

    public InventoryAction getAction() {
        return this.action;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
