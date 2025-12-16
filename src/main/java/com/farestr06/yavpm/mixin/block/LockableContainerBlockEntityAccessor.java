package com.farestr06.yavpm.mixin.block;

import net.minecraft.world.LockCode;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BaseContainerBlockEntity.class)
public interface LockableContainerBlockEntityAccessor {
    @Accessor
    LockCode getLockKey();

    @Accessor
    void setLockKey(LockCode lock);
}
