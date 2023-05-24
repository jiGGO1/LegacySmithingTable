package com.jiggo.smithing.mixin;

import com.jiggo.smithing.util.NetworkPacketsUtils;
import com.jiggo.smithing.util.SmithingRenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SmithingMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ItemCombinerScreen<SmithingMenu> {

    public SmithingScreenMixin(SmithingMenu menu, Inventory inventory, Component component, ResourceLocation background) {
        super(menu, inventory, component, background);
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new ImageButton(this.leftPos - 15, this.topPos + 22, 15, 18, 5, 22, 0, SmithingRenderUtils.SMITHING, button -> {
            NetworkPacketsUtils.send(this.minecraft.player, new ResourceLocation("legacy_smithing"));
        }));
    }

    @Inject(method = "renderBg", at = @At(value = "TAIL"))
    private void render(PoseStack matrices, float partialTick, int mouseX, int mouseY, CallbackInfo info) {
        SmithingRenderUtils.smithing(this, matrices, this.leftPos, this.topPos);
    }

}