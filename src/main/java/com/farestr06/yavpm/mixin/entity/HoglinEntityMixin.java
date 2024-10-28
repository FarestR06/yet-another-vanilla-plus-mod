package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.NetherReactorCoreBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.farestr06.yavpm.block.custom.NetherReactorCoreBlock.PHASE;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity implements Monster, Hoglin {

    private HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    final HoglinEntity thiz = (HoglinEntity) (Object) this;

    @Inject(method = "canConvert", at = @At(value = "HEAD"), cancellable = true)
    private void injected(CallbackInfoReturnable<Boolean> cir) {
        int x = thiz.getBlockX();
        int y = thiz.getBlockY();
        int z = thiz.getBlockZ();

        for (int i = x - 8; i < x + 8; i++) {
            for (int j = y - 3; j < x + 3; j++) {
                for (int k = z - 8; k < x + 8; k++) {
                    if (thiz.getWorld().getBlockState(new BlockPos(i, j, k)).equals(YavpmBlocks.NETHER_REACTOR_CORE.getDefaultState().with(PHASE, NetherReactorCoreBlock.Phase.ACTIVE))) {
                        cir.setReturnValue(false);
                        break;
                    }
                }
            }
        }
    }
}
