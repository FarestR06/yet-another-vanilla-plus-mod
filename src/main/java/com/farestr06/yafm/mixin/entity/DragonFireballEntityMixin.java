package com.farestr06.yafm.mixin.entity;

import com.farestr06.yafm.entity.effect.YavpmStatusEffects;
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
        instance.addEffect(new StatusEffectInstance(YavpmStatusEffects.VOIDED, 1111, 1));
    }
}
