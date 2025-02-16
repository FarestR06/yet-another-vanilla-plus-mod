package com.farestr06.yavpm.entity.effect;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class WoundedStatusEffect extends StatusEffect {
    protected WoundedStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x500500);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, YavpmDamageTypes.bleed(entity.getWorld()), 1.5f * (amplifier + 1));
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 60 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
