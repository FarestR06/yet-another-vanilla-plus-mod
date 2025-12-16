package com.farestr06.yavpm.entity.effect;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ChokingStatusEffect extends MobEffect {
    protected ChokingStatusEffect() {
        super(MobEffectCategory.HARMFUL, 0x7a583d);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        if (!entity.hasInfiniteMaterials()) {
            entity.hurtServer(world, YavpmDamageTypes.choke(entity.level()), (amplifier + 1) * 2);
        }
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 20 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
