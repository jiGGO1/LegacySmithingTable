package com.jiggo.smithing.fabric;

import com.jiggo.smithing.LegacySmithingTable;
import net.fabricmc.api.ModInitializer;

public class LegacySmithingTableFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        LegacySmithingTable.init();
    }

}