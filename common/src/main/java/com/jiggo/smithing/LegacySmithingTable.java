package com.jiggo.smithing;

import com.jiggo.smithing.util.NetworkPacketsUtils;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.LevelAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LegacySmithingTable {

    public static final String MODID = "legacy_smithing_table";

    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public static void init() {
        NetworkPacketsUtils.init();
    }

    public static boolean isFeatureFlagEnabled(LevelAccessor level) {
        return level.enabledFeatures().contains(FeatureFlags.UPDATE_1_20);
    }

    public static Logger getLogger() {
        return LegacySmithingTable.LOGGER;
    }

}