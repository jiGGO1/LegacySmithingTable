package com.jiggo.smithing.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public final class RecipeUtils {

    public static Ingredient getBase(SmithingRecipe recipe) {
        if (recipe instanceof SmithingTransformRecipe transform) {
            return transform.base;
        }
        if (recipe instanceof LegacyUpgradeRecipe upgrade) {
            return upgrade.base;
        }
        return Ingredient.EMPTY;
    }

    public static Ingredient getAddition(SmithingRecipe recipe) {
        if (recipe instanceof SmithingTransformRecipe transform) {
            return transform.addition;
        }
        if (recipe instanceof LegacyUpgradeRecipe upgrade) {
            return upgrade.addition;
        }
        return Ingredient.EMPTY;
    }

    public static ItemStack getResultItem(Recipe<?> recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registryAccess = level.registryAccess();
        return recipe.getResultItem(registryAccess);
    }

    public static boolean isUpgrade(SmithingRecipe recipe) {
        return recipe instanceof SmithingTransformRecipe || recipe instanceof LegacyUpgradeRecipe;
    }

}