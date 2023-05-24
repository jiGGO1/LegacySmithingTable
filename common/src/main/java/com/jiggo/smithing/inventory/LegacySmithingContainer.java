package com.jiggo.smithing.inventory;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ItemCombinerMenu;

public class LegacySmithingContainer extends SimpleContainer {

    final ItemCombinerMenu smithing;

    public LegacySmithingContainer(int max, ItemCombinerMenu smithing) {
        super(max);
        this.smithing = smithing;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.smithing.slotsChanged(this);
    }

    public ItemCombinerMenu getSmithing() {
        return this.smithing;
    }

}