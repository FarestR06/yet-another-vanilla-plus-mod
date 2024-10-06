package com.farestr06.yafm.util;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public interface StatusEffectClearable {
    boolean yAVPM$clearStatusEffectsWithMilk();

    // These cannot be cured with milk; you'll need Magic Herbs instead
    static boolean isClearableByMilk(StatusEffectInstance effect) {
        return !(
                effect.equals(StatusEffects.HASTE)
                || effect.equals(StatusEffects.MINING_FATIGUE)
                || effect.equals(StatusEffects.WITHER)
                || effect.equals(StatusEffects.BAD_OMEN)
                || effect.equals(StatusEffects.RAID_OMEN)
                || effect.equals(StatusEffects.TRIAL_OMEN)
                || effect.equals(StatusEffects.CONDUIT_POWER)
                || effect.equals(StatusEffects.WIND_CHARGED)
                || effect.equals(StatusEffects.WEAVING)
                || effect.equals(StatusEffects.OOZING)
                || effect.equals(StatusEffects.INFESTED)
        );
    }
}
