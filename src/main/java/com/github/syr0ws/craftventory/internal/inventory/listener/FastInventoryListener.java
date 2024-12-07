package com.github.syr0ws.craftventory.internal.inventory.listener;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryContent;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewManager;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryCloseEvent;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.craftventory.internal.SimpleInventoryService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

public class FastInventoryListener implements Listener {

    private final Plugin plugin;
    private final SimpleInventoryService service;

    public FastInventoryListener(Plugin plugin, SimpleInventoryService service) {

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
        CraftVentory inventory = this.getFastInventory(player, event.getInventory());

        // Player doesn't have a FastInventory open.
        if (inventory == null) {
            return;
        }

        InventoryViewer viewer = inventory.getViewer();
        InventoryViewManager history = viewer.getViewManager();

        // This hook must be executed here to always ensure that it is called when an
        // inventory is closed.
        inventory.getHookManager().executeHooks(
                new CraftVentoryCloseEvent(inventory, viewer), CraftVentoryCloseEvent.class);

        // If no action is in progress, that means that no inventory is intended to be opened.
        // Thus, the history must be cleared.
        if(!history.hasActionInProgress()) {
            history.clear(false);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFastInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        CraftVentory inventory = this.getFastInventory(player, event.getClickedInventory());

        if (inventory == null) {
            return;
        }

        // If the event is already cancelled, do not go further.
        if(event.isCancelled()) {
            return;
        }

        // Cancelling the event by default.
        event.setCancelled(true);

        InventoryViewer viewer = inventory.getViewer();
        InventoryContent content = inventory.getContent();
        InventoryItem item = content.getItem(event.getSlot()).orElse(null);

        CraftVentoryClickEvent fastInventoryClickEvent = new CraftVentoryClickEvent(
                inventory,
                viewer,
                item,
                event.getView(),
                event.getSlotType(),
                event.getSlot(),
                event.getClick(),
                event.getAction()
        );

        // Calling event globally.
        inventory.getHookManager().executeHooks(fastInventoryClickEvent, CraftVentoryClickEvent.class);

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
        CraftVentory inventory = this.getFastInventory(player, event.getInventory());

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

        CraftVentory inventory = this.getFastInventory(player, topInventory);

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
    public void onPluginEnable(PluginEnableEvent event) {

        Plugin enabled = event.getPlugin();

        // Checking that the enabled plugin is the same as the one that registered this listener.
        if (!enabled.equals(this.plugin)) {
            return;
        }

        Bukkit.getOnlinePlayers().forEach(this.service::addInventoryViewer);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPluginDisable(PluginDisableEvent event) {

        Plugin disabled = event.getPlugin();

        // Checking that the disabled plugin is the same as the one that registered this listener.
        if (!disabled.equals(this.plugin)) {
            return;
        }

        // Closing all the inventories.
        Set<InventoryViewer> viewers = this.service.getInventoryViewers();
        viewers.forEach(viewer -> viewer.getViewManager().clear(true));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.service.addInventoryViewer(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.service.removeInventoryViewer(player);
    }

    private CraftVentory getFastInventory(Player player, Inventory inventory) {

        InventoryViewer viewer = this.service.getInventoryViewer(player);

        if(viewer == null) {
            return null;
        }

        InventoryViewManager history = viewer.getViewManager();

        Optional<CraftVentory> optional = history.getOpenedInventory();

        if(optional.isEmpty()) {
            return null;
        }

        CraftVentory craftVentory = optional.get();
        Inventory bukkitInventory = craftVentory.getBukkitInventory();

        return bukkitInventory.equals(inventory) ? craftVentory : null;
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
