package com.jiggo.smithing.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.LegacySmithingMenu;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.level.Level;

import java.util.OptionalInt;

public interface FutureSmithingTable {

    public static final Component CONTAINER_TITLE = Component.translatable("container.upgrade");

    public default AbstractContainerMenu getSmithingMenu(int id, Inventory inventory, ContainerLevelAccess access) {
        return new SmithingMenu(id, inventory, access);
    }

    public default AbstractContainerMenu getLegacySmithingMenu(int id, Inventory inventory, ContainerLevelAccess access) {
        return new LegacySmithingMenu(id, inventory, access);
    }

    public default MenuProvider getMenuProvider(Level level, BlockPos pos, ResourceLocation location) {
        return new SimpleMenuProvider((id, inventory, player) -> {
            ContainerLevelAccess access = ContainerLevelAccess.create(level, pos);
            switch (location.getPath()) {
                case "legacy_smithing" -> {
                    return this.getLegacySmithingMenu(id, inventory, access);
                }
                case "smithing" -> {
                    return this.getSmithingMenu(id, inventory, access);
                }
                default -> {
                    return null;
                }
            }
        }, CONTAINER_TITLE);
    }

    public default OptionalInt openMenu(Player player, BlockPos pos, ResourceLocation id) {
        final Level level = player.getLevel();
        return player.openMenu(this.getMenuProvider(level, pos, id));
    }

}