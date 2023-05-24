package com.jiggo.smithing.network;

import com.jiggo.smithing.block.FutureSmithingTable;
import com.jiggo.smithing.mixin.ItemCombinerMenuAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;

public class PacketOpenSmithing {

    private ResourceLocation id;

    public PacketOpenSmithing() {
    }

    public PacketOpenSmithing(ResourceLocation id) {
        this.id = id;
    }

    public PacketOpenSmithing(FriendlyByteBuf buffer) {
        this.fromBytes(buffer);
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.id = buf.readResourceLocation();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.id);
    }

    public static void packet(PacketOpenSmithing packet, Player player) {
        final Level level = player.getLevel();
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