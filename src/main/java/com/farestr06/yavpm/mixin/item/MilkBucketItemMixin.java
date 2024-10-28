package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.util.StatusEffectClearable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {

    // Redirect to nerfed method
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z"))
    private boolean redirected(LivingEntity instance) {
        return ((StatusEffectClearable) instance).yAVPM$clearStatusEffectsWithMilk();
    }
}
