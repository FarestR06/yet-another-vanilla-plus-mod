package com.farestr06.yavpm.mixin.block;

import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.inventory.ContainerLock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LockableContainerBlockEntity.class)
public interface LockableContainerBlockEntityAccessor {
    @Accessor
    ContainerLock getLock();

    @Accessor
    void setLock(ContainerLock lock);
}
