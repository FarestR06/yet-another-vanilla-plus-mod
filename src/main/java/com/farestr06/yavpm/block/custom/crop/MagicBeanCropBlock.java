package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagicBeanCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 5.0, 10.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0),
            Block.box(3.0, 0.0, 3.0, 13.0, 13.0, 13.0),
            Block.box(3.0, 0.0, 3.0, 13.0, 13.0, 13.0)
    };

    public MagicBeanCropBlock(Properties settings) {
        super(settings);
    }

    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return MagicBeanCropBlock.AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return YavpmItems.MAGIC_BEAN;
    }
}
