package com.farestr06.yavpm.fluid;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class VoidWaterFluid extends FlowableFluid {
    @Override
    public Fluid getFlowing() {
        return YavpmFluids.FLOWING_VOID_WATER;
    }

    @Override
    public Fluid getStill() {
        return YavpmFluids.STILL_VOID_WATER;
    }

    @Override
    protected boolean isInfinite(World world) {
        return YavpmConfig.HANDLER.instance().voidWaterSourceConversion;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return YavpmItems.VOID_WATER_BUCKET;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(YavpmTags.Fluids.VOID_WATER);
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100f;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return YavpmBlocks.VOID_WATER.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == YavpmFluids.STILL_VOID_WATER || fluid == YavpmFluids.FLOWING_VOID_WATER;
    }

    public static class Flowing extends VoidWaterFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends VoidWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
