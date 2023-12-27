package com.jiggo.smithing.network;

import com.jiggo.smithing.block.FutureSmithingTable;
import com.jiggo.smithing.mixin.ItemCombinerMenuAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class PacketOpenSmithing {

    private ResourceLocation id;

    private UUID uuid;

    public PacketOpenSmithing() {
    }

    public PacketOpenSmithing(ResourceLocation id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public PacketOpenSmithing(FriendlyByteBuf buffer) {
        this.fromBytes(buffer);
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.id = buf.readResourceLocation();
        this.uuid = buf.readUUID();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.id);
        buf.writeUUID(this.uuid);
    }

    public static void packet(PacketOpenSmithing packet, Player player) {
        if (!player.getUUID().equals(packet.uuid)) return;
        final Level level = player.level;
        final BlockPos pos = PacketOpenSmithing.getBlockPos(player.containerMenu);
        if (pos != null && level.getBlockState(pos).getBlock() instanceof FutureSmithingTable table) {
            table.openMenu(player, pos, packet.id);
        }
    }

    private static BlockPos getBlockPos(AbstractContainerMenu container) {
        if (container instanceof ItemCombinerMenuAccessor smithing) {
            return smithing.getContainerLevelAccess().evaluate(((world, position) -> position)).orElse(null);
        } else {
            return BlockPos.ZERO;
        }
    }

}