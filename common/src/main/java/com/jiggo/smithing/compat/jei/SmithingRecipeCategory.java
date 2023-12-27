package com.jiggo.smithing.compat.jei;

import com.jiggo.smithing.util.RecipeUtils;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.block.Blocks;

public class SmithingRecipeCategory implements IRecipeCategory<SmithingRecipe> {

	private final IDrawable background;
	private final IDrawable icon;

	public SmithingRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(JEIConstants.RECIPE_GUI_VANILLA, 0, 168, 125, 18);
		this.icon = guiHelper.createDrawableItemStack(new ItemStack(Blocks.SMITHING_TABLE));
	}

	@Override
	public RecipeType<SmithingRecipe> getRecipeType() {
		return JEIConstants.SMITHING;
	}

	@Override
	public Component getTitle() {
		return Blocks.SMITHING_TABLE.getName();
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, SmithingRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
			.addIngredients(RecipeUtils.getBase(recipe));

		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
			.addIngredients(RecipeUtils.getAddition(recipe));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
			.addItemStack(RecipeUtils.getResultItem(recipe));
	}

	@Override
	public boolean isHandled(SmithingRecipe recipe) {
		return !recipe.isSpecial();
	}

}