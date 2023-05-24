package com.jiggo.smithing.util;

import com.jiggo.smithing.LegacySmithingTable;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

public class SmithingRenderUtils {

    public static final ResourceLocation SMITHING = new ResourceLocation(LegacySmithingTable.MODID, "textures/gui/smithing.png");
    public static final ResourceLocation LEGACY_SMITHING = new ResourceLocation(LegacySmithingTable.MODID, "textures/gui/legacy_smithing.png");

    public static void smithing(GuiComponent self, PoseStack matrices, int x, int y) {
        SmithingRenderUtils.render(self, SMITHING, matrices, x, y);
    }

    public static void legacy(GuiComponent self, PoseStack matrices, int x, int y) {
        SmithingRenderUtils.render(self, LEGACY_SMITHING, matrices, x, y);
    }

    public static void render(GuiComponent self, ResourceLocation resource, PoseStack matrices, int x, int y) {
        RenderSystem.setShaderTexture(0, resource);
        self.blit(matrices, x - 20, y, 0, 0, 24, 166);
    }

}