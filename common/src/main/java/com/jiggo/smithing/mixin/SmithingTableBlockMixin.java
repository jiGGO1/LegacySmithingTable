package com.jiggo.smithing.mixin;

import com.jiggo.smithing.block.FutureSmithingTable;
import net.minecraft.world.level.block.SmithingTableBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SmithingTableBlock.class)
public class SmithingTableBlockMixin implements FutureSmithingTable {

}