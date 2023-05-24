package com.jiggo.smithing.mixin;

import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.inventory.LegacySmithingContainer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SmithingTransformRecipe.class)
public class SmithingTransformRecipeMixin {

    @Final
    @Shadow
    Ingredient base;

    @Final
    @Shadow
    Ingredient addition;

    @Final
    @Shadow
    ItemStack result;

    @Inject(method = "matches", at = @At(value = "HEAD"), cancellable = true)
    private void matches(Container container, Level level, CallbackInfoReturnable<Boolean> info) {
        if (LegacySmithingTable.isFeatureFlagEnabled(level) && container instanceof LegacySmithingContainer) {
            info.setReturnValue(this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1)));
        }
    }

    @Inject(method = "assemble", at = @At(value = "HEAD"), cancellable = true)
    private void assemble(Container container, RegistryAccess access, CallbackInfoReturnable<ItemStack> info) {
        if (container instanceof LegacySmithingContainer inventory &&
                inventory.getSmithing() instanceof ItemCombinerMenuAccessor accessor) {
            Optional<Level> world = accessor.getContainerLevelAccess().evaluate((level, pos) -> level);
            if (world.isPresent() && LegacySmithingTable.isFeatureFlagEnabled(world.get())) {
                ItemStack result = this.result.copy();
                CompoundTag tag = container.getItem(0).getTag();
                if (tag != null) {
                    result.setTag(tag.copy());
                }
                info.setReturnValue(result);
            }
        }
    }

}