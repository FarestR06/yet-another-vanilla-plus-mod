package com.farestr06.yavpm.entity.effect;

import com.farestr06.api.effect.SimpleMobEffect;
import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class YavpmStatusEffects {
    /**
     * Silence: This effect prevents inflicted mobs from setting off Sculk Sensors.
     */
    public static final Holder<MobEffect> SILENCE = register("silence", new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 0x00ffff));
    public static final Holder<MobEffect> VOID_TOUCHED = register("void_touched", new SimpleMobEffect(MobEffectCategory.HARMFUL, 0xe079fa));
    public static final Holder<MobEffect> WOUNDED = register("wounded", new WoundedStatusEffect());
    public static final Holder<MobEffect> CHOKING = register("choking", new ChokingStatusEffect());

    private static Holder<MobEffect> register(String id, MobEffect statusEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, YetAnotherVanillaPlusMod.makeId(id), statusEffect);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering status effects for YAVPM!");
    }
}
