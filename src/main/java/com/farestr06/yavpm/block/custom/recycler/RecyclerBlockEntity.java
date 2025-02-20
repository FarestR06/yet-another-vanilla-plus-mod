package com.farestr06.yavpm.block.custom.recycler;

import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class RecyclerBlockEntity extends DispenserBlockEntity {
    public RecyclerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(YavpmBlockEntities.RECYCLER, blockPos, blockState);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.recycler");
    }
}
