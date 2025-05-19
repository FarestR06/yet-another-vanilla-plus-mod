package com.farestr06.yavpm.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ChoppingBlockBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0d, 0d, 0d, 16d, 6d, 16d);

    public ChoppingBlockBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
