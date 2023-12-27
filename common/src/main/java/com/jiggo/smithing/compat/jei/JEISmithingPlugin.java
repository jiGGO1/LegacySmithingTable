package com.jiggo.smithing.compat.jei;

import com.google.common.collect.Lists;
import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.util.RecipeUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.LegacySmithingScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.LegacySmithingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEISmithingPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(LegacySmithingTable.MODID, "minecraft");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(new SmithingRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JEIConstants.SMITHING, this.getSmithingRecipes());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(LegacySmithingScreen.class, 68, 49, 22, 15, JEIConstants.SMITHING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(LegacySmithingMenu.class, MenuType.LEGACY_SMITHING, JEIConstants.SMITHING, 0, 3, 3, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks.SMITHING_TABLE), JEIConstants.SMITHING);
    }

    public List<SmithingRecipe> getSmithingRecipes() {
        ClientLevel world = Minecraft.getInstance().level;
        if (LegacySmithingTable.isFeatureFlagEnabled(world)) return Lists.newArrayList();
        return world.getRecipeManager().getAllRecipesFor(RecipeType.SMITHING)
                .stream()
                .filter(RecipeUtils::isUpgrade)
                .collect(Collectors.toList());
    }

}