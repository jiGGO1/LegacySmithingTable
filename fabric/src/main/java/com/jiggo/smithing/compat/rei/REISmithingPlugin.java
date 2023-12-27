package com.jiggo.smithing.compat.rei;

import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.compat.rei.displays.DefaultSmithingDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoRegistry;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleMenuInfoProvider;
import net.minecraft.world.inventory.LegacySmithingMenu;

public class REISmithingPlugin implements REIServerPlugin {

    public static final CategoryIdentifier<DefaultSmithingDisplay> SMITHING = CategoryIdentifier.of(LegacySmithingTable.MODID, "minecraft");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(SMITHING, DefaultSmithingDisplay.serializer());
    }

    @Override
    public void registerMenuInfo(MenuInfoRegistry registry) {
        registry.register(SMITHING, LegacySmithingMenu.class, SimpleMenuInfoProvider.of(SmithingTableMenuInfo::new));
    }

}