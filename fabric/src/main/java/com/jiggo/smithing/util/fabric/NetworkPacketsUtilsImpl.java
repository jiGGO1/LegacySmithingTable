package com.jiggo.smithing.util.fabric;

import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.network.PacketOpenSmithing;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class NetworkPacketsUtilsImpl {

    public static ResourceLocation BUTTON_ID = new ResourceLocation(LegacySmithingTable.MODID, "switch");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(BUTTON_ID, (server, player, handler, buf, sender) -> {
            PacketOpenSmithing packet = new PacketOpenSmithing(buf);
            packet(packet, player);
        });
    }

    public static void send(Player player, ResourceLocation id) {
        PacketOpenSmithing packet = new PacketOpenSmithing(id);
        FriendlyByteBuf buf = PacketByteBufs.create();
        packet.toBytes(buf);
        ClientPlayNetworking.send(BUTTON_ID, buf);
    }

    public static void packet(PacketOpenSmithing packet, ServerPlayer player) {
        PacketOpenSmithing.packet(packet, player);
    }

}