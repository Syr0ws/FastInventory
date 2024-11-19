package com.github.syr0ws.fastinventory.internal.inventory.listener;

import com.github.syr0ws.fastinventory.api.InventoryService;
import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryContent;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickAction;
import com.github.syr0ws.fastinventory.api.inventory.action.ClickType;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryClickEvent;
import com.github.syr0ws.fastinventory.api.inventory.event.FastInventoryCloseEvent;
import com.github.syr0ws.fastinventory.api.inventory.item.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FastInventoryListener implements Listener {

    private final Plugin plugin;
    private final InventoryService service;

    public FastInventoryListener(Plugin plugin, InventoryService service) {

        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        if (service == null) {
            throw new IllegalArgumentException("service cannot be null");
        }

        this.plugin = plugin;
        this.service = service;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFastInventoryClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        FastInventory inventory = this.getFastInventory(player, event.getInventory());

        // Player doesn't have a FastInventory open.
        if (inventory == null) {
            return;
        }

        // This hook must be executed here to always ensure that it is called when an
        // inventory is closed.
        inventory.getHookManager().executeHooks(new FastInventoryCloseEvent(inventory, player), FastInventoryCloseEvent.class);

        // Removing inventory data.
        this.service.removeInventory(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFastInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        FastInventory inventory = this.getFastInventory(player, event.getClickedInventory());

        if (inventory == null) {
            return;
        }

        // If the event is already cancelled, do not go further.
        if(event.isCancelled()) {
            return;
        }

        // Cancelling the event by default.
        event.setCancelled(true);

        InventoryContent content = inventory.getContent();
        InventoryItem item = content.getItem(event.getSlot()).orElse(null);

        FastInventoryClickEvent fastInventoryClickEvent = new FastInventoryClickEvent(
                inventory,
                player,
                item,
                event.getView(),
                event.getSlotType(),
                event.getSlot(),
                event.getClick(),
                event.getAction()
        );

        // Calling event globally.
        inventory.getHookManager().executeHooks(fastInventoryClickEvent, FastInventoryClickEvent.class);

        // Do not go further if the event has been cancelled.
        if(fastInventoryClickEvent.isCancelled()) {
            return;
        }

        // If an item has been clicked, executing its actions.
        if(item != null) {

            item.getActions().stream()
                    .filter(action -> this.hasClickType(action, event.getClick()))
                    .forEach(action -> action.execute(fastInventoryClickEvent));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFastInventoryDrag(InventoryDragEvent event) {

        Player player = (Player) event.getWhoClicked();
        FastInventory inventory = this.getFastInventory(player, event.getInventory());

        if (inventory != null) {

            // Cancelling the event by default.
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInventoryMove(InventoryClickEvent event) {

        if (event.isCancelled()) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        Inventory topInventory = player.getOpenInventory().getTopInventory();
        Inventory clickedInventory = event.getClickedInventory();

        // Checking that the clicked inventory is the player's inventory.
        if (!player.getInventory().equals(clickedInventory)) {
            return;
        }

        FastInventory inventory = this.getFastInventory(player, topInventory);

        // Top inventory is not a FastInventory.
        if (inventory == null) {
            return;
        }

        InventoryAction action = event.getAction();

        // Prevent shift move from another inventory.
        if (action == InventoryAction.COLLECT_TO_CURSOR || action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPluginDisable(PluginDisableEvent event) {

        Plugin disabled = event.getPlugin();

        // Checking that the disabled plugin is the same as the one
        // that registered this listener.
        if (disabled.equals(this.plugin)) {
            return;
        }

        // Closing all the inventories.
        Map<Player, FastInventory> players = this.service.getInventories();
        players.forEach((player, inventory) -> inventory.close());
    }

    private FastInventory getFastInventory(Player player, Inventory inventory) {

        Optional<FastInventory> inventoryOptional = this.service.getInventory(player);

        // Inventory is not a FastInventory.
        if (inventoryOptional.isEmpty()) {
            return null;
        }

        FastInventory fastInventory = inventoryOptional.get();
        Inventory bukkitInventory = fastInventory.getBukkitInventory();

        return bukkitInventory.equals(inventory) ? fastInventory : null;
    }

    private boolean hasClickType(ClickAction action, org.bukkit.event.inventory.ClickType type) {

        Set<ClickType> clickTypes = action.getClickTypes();

        if (clickTypes.contains(ClickType.ALL)) {
            return true;
        }

        return switch (type) {
            case LEFT -> clickTypes.contains(ClickType.LEFT);
            case RIGHT -> clickTypes.contains(ClickType.RIGHT);
            case MIDDLE -> clickTypes.contains(ClickType.MIDDLE);
            default -> false;
        };
    }
}
