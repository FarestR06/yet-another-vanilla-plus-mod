package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {

    // Replace Instant Damage II with Voided II
    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/AreaEffectCloudEntity;addEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"))
    private void redirected(AreaEffectCloudEntity instance, StatusEffectInstance effect) {
        // Are the void-touched fireballs enabled in config?
        if (YavpmConfig.HANDLER.instance().voidTouchedDragonFireball) {
            // If so, we'll replace the Instant Damage instance with a Void Touched instance.
            instance.addEffect(new StatusEffectInstance(YavpmStatusEffects.VOID_TOUCHED, 1111, 1)); // MUSIC DISC 11 REFERENCE?!?!1?!??
        } else {
            // Otherwise, the fireball will apply Instant Damage like usual.
            instance.addEffect(effect);
        }
    }
}
