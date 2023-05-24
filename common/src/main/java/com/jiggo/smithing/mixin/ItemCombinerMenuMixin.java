package com.jiggo.smithing.mixin;

import com.jiggo.smithing.inventory.LegacySmithingContainer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.LegacySmithingMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCombinerMenu.class)
public class ItemCombinerMenuMixin {

    @Inject(method = "createContainer", at = @At(value = "HEAD"), cancellable = true)
    private void createContainer(int max, CallbackInfoReturnable<SimpleContainer> info) {
        if ((Object)this instanceof LegacySmithingMenu self) {
            SimpleContainer container = new LegacySmithingContainer(max, self);
            info.setReturnValue(container);
        }
    }

}