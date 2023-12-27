package com.jiggo.smithing.compat.rei;

import com.jiggo.smithing.compat.rei.categories.DefaultSmithingCategory;
import com.jiggo.smithing.compat.rei.displays.DefaultSmithingDisplay;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.LegacyUpgradeRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

public class REISmithingClientPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<DefaultSmithingDisplay> SMITHING = REISmithingPlugin.SMITHING;

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new DefaultSmithingCategory());
        registry.addWorkstations(SMITHING, EntryStacks.of(Items.SMITHING_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SmithingTransformRecipe.class, RecipeType.SMITHING, DefaultSmithingDisplay::new);
        registry.registerRecipeFiller(LegacyUpgradeRecipe.class, RecipeType.SMITHING, DefaultSmithingDisplay::new);
    }

}