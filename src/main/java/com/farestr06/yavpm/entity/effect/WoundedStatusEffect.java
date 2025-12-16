package com.farestr06.yavpm.entity.effect;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class WoundedStatusEffect extends MobEffect {
    protected WoundedStatusEffect() {
        super(MobEffectCategory.HARMFUL, 0x500500);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        entity.hurtServer(world, YavpmDamageTypes.bleed(entity.level()), 1.5f * (amplifier + 1));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 60 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
