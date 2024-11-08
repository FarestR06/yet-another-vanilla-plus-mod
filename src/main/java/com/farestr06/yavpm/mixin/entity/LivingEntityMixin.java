package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.util.StatusEffectClearable;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, StatusEffectClearable {

    @Shadow protected abstract void onStatusEffectRemoved(StatusEffectInstance effect);

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    final LivingEntity thiz = (LivingEntity) (Object) this;

    // Increase damage taken with Voided effect
    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float voidedMultiplier(float damage) {
        StatusEffectInstance effect = thiz.getStatusEffect(YavpmStatusEffects.VOID_TOUCHED);
        if (effect != null) {
            return damage * ((effect.getAmplifier() + 1) * 1.5f);
        } else return damage;
    }

    // Nerf milk bucket
    @Override
    public boolean yAVPM$clearStatusEffectsWithMilk() {
        if (!thiz.getWorld().isClient) {
            boolean bl = false;
            for (StatusEffectInstance effect : thiz.getStatusEffects()) {
                if (StatusEffectClearable.isClearableByMilk(effect)) {
                    onStatusEffectRemoved(effect);
                    bl = true;
                }
            }
            return bl;
        } else return false;
    }
}
