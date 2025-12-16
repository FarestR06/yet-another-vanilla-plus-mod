package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.projectile.DragonFireball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DragonFireball.class)
public class DragonFireballEntityMixin {

    // Replace Instant Damage II with Voided II
    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/AreaEffectCloud;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)V"))
    private void redirected(AreaEffectCloud instance, MobEffectInstance effect) {
        // Are the void-touched fireballs enabled in config?
        if (YavpmConfig.HANDLER.instance().voidTouchedDragonFireball) {
            // If so, we'll replace the Instant Damage instance with a Void Touched instance.
            instance.addEffect(new MobEffectInstance(YavpmStatusEffects.VOID_TOUCHED, 1111, 1)); // MUSIC DISC 11 REFERENCE?!?!1?!??
        } else {
            // Otherwise, the fireball will apply Instant Damage like usual.
            instance.addEffect(effect);
        }
    }
}
