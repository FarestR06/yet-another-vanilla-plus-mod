package com.farestr06.yavpm.mixin.entity;

import com.farestr06.api.util.FarestsUtils;
import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    private LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Unique
    final LivingEntity self = (LivingEntity) (Object) this;

    // Increase damage taken with Void Touched effect
    @ModifyVariable(method = "hurtServer", at = @At(value = "HEAD"), argsOnly = true)
    private float voidedMultiplier(float damage) {
        // Get the entity's Void Touched effect instance (or not)
        MobEffectInstance effect = self.getEffect(YavpmStatusEffects.VOID_TOUCHED);
        if (effect != null) { // Do we have an effect instance?
            // If so, multiply damage...
            float multipliedDamage = damage * ((effect.getAmplifier() + 1) * YavpmConfig.HANDLER.instance().voidTouchedDamageMultiplier);
            // ...And round it to a multiple of 0.5! Oh, and inflict it. Can't forget that.
            return FarestsUtils.Math.roundToHalf(multipliedDamage);
        } else return damage; // Otherwise, we'll deal the normal amount of damage.
    }
}
