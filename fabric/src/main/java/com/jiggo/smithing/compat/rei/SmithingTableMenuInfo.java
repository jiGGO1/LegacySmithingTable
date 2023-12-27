package com.jiggo.smithing.compat.rei;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import net.minecraft.world.inventory.LegacySmithingMenu;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmithingTableMenuInfo <DefaultSmithingDisplay extends Display> implements SimplePlayerInventoryMenuInfo<LegacySmithingMenu, DefaultSmithingDisplay> {

    private final DefaultSmithingDisplay display;

    public SmithingTableMenuInfo(DefaultSmithingDisplay display) {
        this.display = display;
    }

    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<LegacySmithingMenu, ?, DefaultSmithingDisplay> context) {
        return IntStream.range(0, 2)
                .mapToObj(value -> SlotAccessor.fromSlot(context.getMenu().getSlot(value)))
                .collect(Collectors.toList());
    }

    @Override
    public DefaultSmithingDisplay getDisplay() {
        return this.display;
    }

}