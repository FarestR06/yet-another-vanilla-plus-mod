package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MagicBeanCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 5.0, 10.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 13.0, 12.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 13.0, 13.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 13.0, 13.0)
    };

    public MagicBeanCropBlock(Settings settings) {
        super(settings);
    }

    public static final int MAX_AGE = 6;
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return MagicBeanCropBlock.AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return YavpmItems.MAGIC_BEAN;
    }
}
