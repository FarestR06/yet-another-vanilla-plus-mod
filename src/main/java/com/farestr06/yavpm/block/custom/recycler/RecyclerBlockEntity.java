package com.farestr06.yavpm.block.custom.recycler;

import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RecyclerBlockEntity extends DispenserBlockEntity {
    public RecyclerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(YavpmBlockEntities.RECYCLER, blockPos, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.recycler");
    }
}
