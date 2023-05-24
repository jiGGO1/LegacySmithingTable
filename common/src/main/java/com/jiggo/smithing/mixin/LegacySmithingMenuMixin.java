package com.jiggo.smithing.mixin;

import com.jiggo.smithing.LegacySmithingTable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.LegacySmithingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LegacySmithingMenu.class)
public abstract class LegacySmithingMenuMixin extends ItemCombinerMenu {

    @Final
    @Shadow
    private Level level;

    private SmithingRecipe selected;

    public LegacySmithingMenuMixin(@Nullable MenuType<?> type, int id, Inventory inventory, ContainerLevelAccess access) {
        super(type, id, inventory, access);
    }

    @Inject(method = "mayPickup", at = @At(value = "HEAD"), cancellable = true)
    private void mayPickup(Player player, boolean flag, CallbackInfoReturnable<Boolean> info) {
        if (LegacySmithingTable.isFeatureFlagEnabled(this.level)) {
            info.setReturnValue(this.selected != null && this.selected.matches(this.inputSlots, this.level));
        }
    }

    @Inject(method = "createResult", at = @At(value = "HEAD"), cancellable = true)
    public void result(CallbackInfo info) {
        if (LegacySmithingTable.isFeatureFlagEnabled(this.level)) {
            List<SmithingRecipe> list = this.level.getRecipeManager().getRecipesFor(RecipeType.SMITHING, this.inputSlots, this.level);
            if (list.isEmpty()) {
                this.resultSlots.setItem(0, ItemStack.EMPTY);
            } else {
                SmithingRecipe recipe = list.get(0);
                ItemStack result = recipe.assemble(this.inputSlots, this.level.registryAccess());
                this.selected = recipe;
                this.resultSlots.setRecipeUsed(recipe);
                this.resultSlots.setItem(0, result);
            }
            info.cancel();
        }
    }

}