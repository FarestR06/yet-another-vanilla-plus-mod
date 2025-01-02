package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class WarpedWartCropBlock extends NetherWartBlock {
    public WarpedWartCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(YavpmItems.WARPED_WART);
    }
}
