package com.farestr06.yafm.mixin.entity;

import com.farestr06.yafm.util.YavpmSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin extends AnimalEntity {
    private ChickenEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/ChickenEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), cancellable = true)
    private void injected(CallbackInfo ci) {
        if (!isEggLaySuccessful()) {
            thiz.playSound(YavpmSounds.ENTITY_CHICKEN_EGG_BREAK, 1.0F, (thiz.getRandom().nextFloat() - thiz.getRandom().nextFloat()) * 0.2F + 1.0F);
            ci.cancel();
        }
    }

    @Unique
    final ChickenEntity thiz = (ChickenEntity) (Object) this;

    @Unique
    private boolean isEggLaySuccessful() {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int k = (int)thiz.getX() - 4; k < (int)thiz.getX() + 4; k++) {
            for (int l = (int)thiz.getY() - 4; l < (int)thiz.getY() + 4; l++) {
                for (int m = (int)thiz.getZ() - 4; m < (int)thiz.getZ() + 4; m++) {
                    BlockState blockState = thiz.getWorld().getBlockState(mutable.set(k, l, m));
                    if (!blockState.isOf(Blocks.GRASS_BLOCK)) {
                        return thiz.getRandom().nextFloat() > 0.35F;
                    }
                }
            }
        }

        return true;
    }
}
