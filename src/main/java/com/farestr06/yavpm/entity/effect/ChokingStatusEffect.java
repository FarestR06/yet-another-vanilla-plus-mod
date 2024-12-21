package com.farestr06.yavpm.entity.effect;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ChokingStatusEffect extends StatusEffect {
    protected ChokingStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x7a583d);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.isInCreativeMode()) {
            entity.damage(YavpmDamageTypes.choke(entity.getWorld()), (amplifier + 1) * 2);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
