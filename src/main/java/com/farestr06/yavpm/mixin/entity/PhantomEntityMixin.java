package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import net.minecraft.entity.mob.PhantomEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin {
    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PhantomEntity;setPhantomSize(I)V"))
    private void injected(PhantomEntity instance, int size) {
        if (instance.getType() == YavpmEntities.VOID_PHANTOM) {
            int voidPhantomSize = instance.getRandom().nextInt(3);
            instance.setPhantomSize(voidPhantomSize);
        } else {
            instance.setPhantomSize(0);
        }
    }
}
