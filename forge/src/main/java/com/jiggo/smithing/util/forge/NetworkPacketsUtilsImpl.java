package com.jiggo.smithing.util.forge;

import com.jiggo.smithing.LegacySmithingTable;
import com.jiggo.smithing.network.PacketOpenSmithing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkPacketsUtilsImpl {

    private static final String VERSION = "1.0";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(LegacySmithingTable.MODID, "network"),
            () -> VERSION, VERSION::equals, VERSION::equals);

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(id++, PacketOpenSmithing.class, PacketOpenSmithing::toBytes, PacketOpenSmithing::new, (packet, supplier) -> {
            supplier.get().enqueueWork(() -> packet(packet, supplier.get().getSender()));
        });
    }

    public static void send(Player player, ResourceLocation id) {
        INSTANCE.sendToServer(new PacketOpenSmithing(id, player.getUUID()));
    }

    public static void packet(PacketOpenSmithing packet, ServerPlayer player) {
        PacketOpenSmithing.packet(packet, player);
    }

}