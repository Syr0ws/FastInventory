package com.github.syr0ws.fastinventory.internal.inventory;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryViewManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleInventoryViewManager implements InventoryViewManager {

    private final List<FastInventory> history = new ArrayList<>();
    private int index = -1;
    private boolean actionInProgress;

    @Override
    public void openView(FastInventory inventory) {
        this.openView(inventory, true);
    }

    @Override
    public void openView(FastInventory inventory, boolean newHistory) {

        if(inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        // If the new inventory must be opened in a new history, clearing the existing one.
        // Otherwise, closing the currently opened inventory if it exists and opening the new one.
        if(newHistory) {
            this.clear(true);
        } else {
            this.closeCurrentInventory();
        }

        // When an inventory is opened, going forward on the previous inventory must
        // open this inventory. Thus, we must clear the forward history and add the
        // inventory at the end of the list.
        this.history.subList(this.index + 1, this.history.size()).clear();

        this.history.add(inventory);
        this.index = this.history.size() - 1;

        inventory.open();
    }

    @Override
    public void clear(boolean closeView) {

        // If the player has no opened inventory, then the history is already empty.
        if(!this.hasOpenedInventory()) {
            return;
        }

        // Closing the currently opened inventory.
        // Here, this.closeCurrentInventory() must not be used because we will not reopen another inventory.
        if(closeView) {
            this.history.get(this.index).close();
        }

        // Reset the history.
        this.index = -1;
        this.history.clear();
    }

    @Override
    public void home() {

        // Check that the home inventory is not already opened.
        if(this.index == 0) {
            return;
        }

        this.closeCurrentInventory();

        // Open the home inventory.
        this.index = 0;
        this.history.get(this.index).open();
    }

    @Override
    public void backward() {

        if(this.hasBackward()) {

            this.closeCurrentInventory();

            FastInventory inventory = this.history.get(--this.index);
            inventory.open();
        }
    }

    @Override
    public void backward(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        for(int i = 0; i < this.index; i++) {

            FastInventory inventory = this.history.get(i);

            if(!inventoryId.equals(inventory.getId())) {
                continue;
            }

            this.closeCurrentInventory();

            inventory.open();
            this.index = i;

            break;
        }
    }

    @Override
    public boolean hasBackward() {
        return this.index > 0;
    }

    @Override
    public void forward() {

        if(this.hasForward()) {

            this.closeCurrentInventory();

            FastInventory inventory = this.history.get(++this.index);
            inventory.open();
        }
    }

    @Override
    public void forward(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        for(int i = this.index + 1; i < this.history.size(); i++) {

            FastInventory inventory = this.history.get(i);

            if(!inventoryId.equals(inventory.getId())) {
                continue;
            }

            this.closeCurrentInventory();

            inventory.open();
            this.index = i;

            break;
        }
    }

    @Override
    public boolean hasForward() {
        return this.index < this.history.size() - 1;
    }

    @Override
    public boolean hasActionInProgress() {
        return this.actionInProgress;
    }

    @Override
    public boolean contains(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        return this.history.stream().anyMatch(inventory -> inventory.getId().equals(inventoryId));
    }

    @Override
    public boolean isEmpty() {
        return this.history.isEmpty();
    }

    @Override
    public boolean hasOpenedInventory() {
        return !this.hasActionInProgress() && this.index >= 0;
    }

    @Override
    public Optional<FastInventory> getOpenedInventory() {
        return this.hasOpenedInventory() ? Optional.of(this.history.get(this.index)) : Optional.empty();
    }

    private void closeCurrentInventory() {
        this.actionInProgress = true;
        this.history.get(this.index).close();
        this.actionInProgress = false;
    }
}
