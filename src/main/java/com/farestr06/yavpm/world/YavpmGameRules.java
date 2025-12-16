package com.farestr06.yavpm.world;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class YavpmGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> VOID_WATER_SOURCE_CONVERSION =
            GameRuleRegistry.register("voidWaterSourceConversion", GameRules.Category.UPDATES,
                    GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanValue> DO_SUNBURN =
            GameRuleRegistry.register("doSunburn", GameRules.Category.SPAWNING,
                    GameRuleFactory.createBooleanRule(true));

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering game rules for YAVPM!");
    }
}
