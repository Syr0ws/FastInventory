package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;

import java.util.Optional;

/**
 * Represents an event triggered when a {@link Player} clicks on a slot in a {@link CraftVentory}.
 */
public class FastInventoryClickEvent extends FastInventoryEvent implements Cancellable {

    private final InventoryItem item;
    private final InventoryView view;
    private final InventoryType.SlotType slotType;
    private final int slot;
    private final ClickType clickType;
    private final InventoryAction action;
    private boolean cancelled;

    /**
     * Constructs a new {@code FastInventoryClickEvent}.
     *
     * @param inventory The {@link CraftVentory} instance where the click occurred.
     * @param viewer    The {@link InventoryViewer} who clicked the inventory.
     * @param item      The {@link InventoryItem} that was clicked (can be {@code null} if no item was clicked).
     * @param view      The {@link InventoryView} representing the current view of the inventory.
     * @param slotType  The {@link InventoryType.SlotType} representing the type of slot clicked.
     * @param slot      The slot number where the click occurred.
     * @param clickType The {@link ClickType } of the click performed.
     * @param action    The {@link InventoryAction} that is being performed based on the click.
     */
    public FastInventoryClickEvent(CraftVentory inventory, InventoryViewer viewer, InventoryItem item, InventoryView view, InventoryType.SlotType slotType, int slot, ClickType clickType, InventoryAction action) {
        super(inventory, viewer);
        this.item = item;
        this.view = view;
        this.slotType = slotType;
        this.slot = slot;
        this.clickType = clickType;
        this.action = action;
    }

    /**
     * Gets the {@link InventoryView} representing the current view of the inventory.
     *
     * @return The {@link InventoryView} of the clicked inventory.
     */
    public InventoryView getView() {
        return this.view;
    }

    /**
     * Gets the {@link InventoryType.SlotType} of the clicked slot.
     *
     * @return The {@link InventoryType.SlotType} of the clicked slot.
     */
    public InventoryType.SlotType getSlotType() {
        return this.slotType;
    }

    /**
     * Gets the slot number where the click occurred.
     *
     * @return The slot number of the clicked inventory.
     */
    public int getSlot() {
        return this.slot;
    }

    /**
     * Gets the {@link ClickType} representing the type of click performed.
     *
     * @return The {@link ClickType} of the click performed.
     */
    public ClickType getClickType() {
        return this.clickType;
    }

    /**
     * Retrieves the {@link InventoryAction} representing the action performed during the click.
     *
     * @return The {@link InventoryAction} that describes the action being performed during the click.
     */
    public InventoryAction getAction() {
        return this.action;
    }

    /**
     * Gets the {@link InventoryItem} that was clicked, if any.
     *
     * @return An {@link Optional} containing the {@link InventoryItem}, or an empty {@link Optional} if no item was clicked.
     */
    public Optional<InventoryItem> getItem() {
        return Optional.ofNullable(this.item);
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
