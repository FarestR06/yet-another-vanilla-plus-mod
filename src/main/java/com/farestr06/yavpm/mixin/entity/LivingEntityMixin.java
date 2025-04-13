package com.farestr06.yavpm.mixin.entity;

import com.farestr06.api.util.FarestsUtils;
import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    final LivingEntity thiz = (LivingEntity) (Object) this;

    // Increase damage taken with Void Touched effect
    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float voidedMultiplier(float damage) {
        // Get the entity's Void Touched effect instance (or not)
        StatusEffectInstance effect = thiz.getStatusEffect(YavpmStatusEffects.VOID_TOUCHED);
        if (effect != null) { // Do we have an effect instance?
            // If so, multiply damage...
            float multipliedDamage = damage * ((effect.getAmplifier() + 1) * YavpmConfig.HANDLER.instance().voidTouchedDamageMultiplier);
            // ...And round it to a multiple of 0.5! Oh, and inflict it. Can't forget that.
            return FarestsUtils.Math.roundToHalf(multipliedDamage);
        } else return damage; // Otherwise, we'll deal the normal amount of damage.
    }
}
