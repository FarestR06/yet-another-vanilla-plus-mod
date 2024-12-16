package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
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

    private AbstractPiglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    final AbstractPiglinEntity thiz = (AbstractPiglinEntity) (Object) this;

    @Inject(method = "shouldZombify", at = @At(value = "HEAD"), cancellable = true)
    private void injected(CallbackInfoReturnable<Boolean> cir) {
        if (thiz.hasStatusEffect(YavpmStatusEffects.NETHER_POWER)) {
            cir.setReturnValue(false);
        }
        BlockPos pos = thiz.getBlockPos();
        if (thiz.getWorld().getBiome(pos).matchesKey(YavpmBiomes.Overworld.WITHERED_SCAR)) {
            cir.setReturnValue(false);
        }
    }
}
