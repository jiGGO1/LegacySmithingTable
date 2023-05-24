package com.jiggo.smithing.forge;

import com.jiggo.smithing.LegacySmithingTable;
import net.minecraftforge.fml.common.Mod;

@Mod(LegacySmithingTable.MODID)
public class LegacySmithingTableForge {

    public LegacySmithingTableForge() {
        LegacySmithingTable.init();
    }

}