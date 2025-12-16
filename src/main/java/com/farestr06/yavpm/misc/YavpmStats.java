package com.farestr06.yavpm.misc;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class YavpmStats {
    public static final ResourceLocation DAYS_SLEPT_THROUGH = YetAnotherVanillaPlusMod.makeId("days_slept_through");

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering statistics for YAVPM!");
        Registry.register(BuiltInRegistries.CUSTOM_STAT, DAYS_SLEPT_THROUGH, DAYS_SLEPT_THROUGH);
        Stats.CUSTOM.get(DAYS_SLEPT_THROUGH, StatFormatter.DEFAULT);
    }
}
