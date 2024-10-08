package com.farestr06.yafm.mixin.entity;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.block.custom.NetherReactorCoreBlock;
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

import static com.farestr06.yafm.block.custom.NetherReactorCoreBlock.PHASE;

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

    // TODO: Make Piglins safe near Nether Reactors
    @Inject(method = "shouldZombify", at = @At(value = "HEAD"), cancellable = true)
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
