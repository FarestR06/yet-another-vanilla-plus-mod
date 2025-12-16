package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import net.minecraft.world.entity.monster.Phantom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Phantom.class)
public class PhantomEntityMixin {
    @Redirect(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Phantom;setPhantomSize(I)V"))
    private void injected(Phantom instance, int size) {
        if (instance.getType() == YavpmEntities.VOID_PHANTOM) {
            int voidPhantomSize = instance.getRandom().nextInt(3);
            instance.setPhantomSize(voidPhantomSize);
        } else {
            instance.setPhantomSize(0);
        }
    }
}
