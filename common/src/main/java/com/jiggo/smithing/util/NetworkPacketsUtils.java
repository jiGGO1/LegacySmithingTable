package com.jiggo.smithing.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class NetworkPacketsUtils {

    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void send(Player player, ResourceLocation id) {
        throw new AssertionError();
    }

}