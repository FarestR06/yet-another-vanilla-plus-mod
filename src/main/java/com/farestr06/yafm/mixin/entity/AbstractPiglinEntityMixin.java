package com.farestr06.yafm.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Prevents piglins from zombifying when near a Nether Reactor
 */
@Mixin(AbstractPiglinEntity.class)
public abstract class AbstractPiglinEntityMixin extends HostileEntity {

    @Unique
    private final AbstractPiglinEntity thiz = (AbstractPiglinEntity)(Object) this;

    private AbstractPiglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // TODO: Make Piglins safe near Nether Reactors
    @Inject(method = "shouldZombify", at = @At(value = "HEAD"))
    private void injected(CallbackInfoReturnable<Boolean> cir) {

    }
}
