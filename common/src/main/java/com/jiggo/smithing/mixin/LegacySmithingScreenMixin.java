package com.jiggo.smithing.mixin;

import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.util.NetworkPacketsUtils;
import com.jiggo.smithing.util.SmithingRenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.LegacySmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LegacySmithingMenu;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LegacySmithingScreen.class)
public abstract class LegacySmithingScreenMixin extends ItemCombinerScreen<LegacySmithingMenu> {

    public LegacySmithingScreenMixin(LegacySmithingMenu menu, Inventory inventory, Component component, ResourceLocation background) {
        super(menu, inventory, component, background);
    }

    private boolean isFeatureFlagEnabled() {
        return LegacySmithingTable.isFeatureFlagEnabled(this.minecraft.level);
    }

    @Override
    protected void init() {
        super.init();
        if (!this.isFeatureFlagEnabled()) return;
        this.addRenderableWidget(new ImageButton(this.leftPos - 15, this.topPos + 4, 15, 18, 5, 4, 0, SmithingRenderUtils.LEGACY_SMITHING, button -> {
            NetworkPacketsUtils.send(this.minecraft.player, new ResourceLocation("smithing"));
        }));
    }

    @Override
    protected void renderBg(PoseStack matrices, float partialTick, int mouseX, int mouseY) {
        super.renderBg(matrices, partialTick, mouseX, mouseY);
        if (!this.isFeatureFlagEnabled()) return;
        SmithingRenderUtils.legacy(this, matrices, this.leftPos, this.topPos);
    }

}