package com.farestr06.yavpm.fluid;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import com.farestr06.yavpm.world.YavpmGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class VoidWaterFluid extends FlowingFluid {
    @Override
    public Fluid getFlowing() {
        return YavpmFluids.FLOWING_VOID_WATER;
    }

    @Override
    public Fluid getSource() {
        return YavpmFluids.STILL_VOID_WATER;
    }

    @Override
    protected boolean canConvertToSource(ServerLevel world) {
        return world.getGameRules().getBoolean(YavpmGameRules.VOID_WATER_SOURCE_CONVERSION);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropResources(state, world, pos, blockEntity);
    }

    @Override
    protected int getSlopeFindDistance(LevelReader world) {
        return 4;
    }

    @Override
    protected int getDropOff(LevelReader world) {
        return 1;
    }

    @Override
    public Item getBucket() {
        return YavpmItems.VOID_WATER_BUCKET;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected boolean canBeReplacedWith(FluidState state, BlockGetter world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(YavpmTags.Fluids.VOID_WATER);
    }

    @Override
    public int getTickDelay(LevelReader world) {
        return 5;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return YavpmBlocks.VOID_WATER.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == YavpmFluids.STILL_VOID_WATER || fluid == YavpmFluids.FLOWING_VOID_WATER;
    }

    public static class Flowing extends VoidWaterFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Still extends VoidWaterFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
