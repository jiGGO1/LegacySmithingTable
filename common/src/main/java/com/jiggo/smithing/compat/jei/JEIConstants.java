package com.jiggo.smithing.compat.jei;

import com.jiggo.smithing.LegacySmithingTable;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SmithingRecipe;

public class JEIConstants {

    public static final String TEXTURE_GUI_PATH = "textures/gui/";
    public static final String TEXTURE_GUI_VANILLA = JEIConstants.TEXTURE_GUI_PATH + "gui_vanilla.png";

    public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(LegacySmithingTable.MODID, TEXTURE_GUI_VANILLA);

    public static final RecipeType<SmithingRecipe> SMITHING =
            RecipeType.create(LegacySmithingTable.MODID, "smithing", SmithingRecipe.class);

}