package com.farestr06.yavpm.misc;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class YavpmStats {
    public static final Identifier DAYS_SLEPT_THROUGH = YetAnotherVanillaPlusMod.makeId("days_slept_through");

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering statistics for YAVPM!");
        Registry.register(Registries.CUSTOM_STAT, DAYS_SLEPT_THROUGH, DAYS_SLEPT_THROUGH);
        Stats.CUSTOM.getOrCreateStat(DAYS_SLEPT_THROUGH, StatFormatter.DEFAULT);
    }
}
