package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WarpedWartCropBlock extends NetherWartBlock {
    public WarpedWartCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(YavpmItems.WARPED_WART);
    }
}
