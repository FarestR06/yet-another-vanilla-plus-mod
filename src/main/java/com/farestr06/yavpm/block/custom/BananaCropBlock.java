package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class BananaCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 2;
    public static final int SECOND_STAGE_MAX_AGE = 3;

    // Outline VoxelShapes
    private static final VoxelShape TOP_OUTLINE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final VoxelShape GROWN_BOTTOM_OUTLINE_SHAPE = Block.createCuboidShape(1.0, -1.0, 1.0, 15.0, 16.0, 15.0);
    private static final VoxelShape AGE_0_SHAPE = Block.createCuboidShape(7.0, -1.0, 7.0, 9.0, 4.0, 9.0);
    private static final VoxelShape[] BOTTOM_OUTLINE_SHAPES = new VoxelShape[]{
            AGE_0_SHAPE,
            Block.createCuboidShape(3.0, -1.0, 3.0, 13.0, 9.0, 13.0),
            TOP_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE
    };

    public static final IntProperty AGE = Properties.AGE_5;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public BananaCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER
                ? TOP_OUTLINE_SHAPE
                : BOTTOM_OUTLINE_SHAPES[state.get(AGE)];
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float moisture = CropBlock.getAvailableMoisture(this, world, pos);
        if (random.nextInt((int)(25.0F / moisture) + 1) == 0) {
            this.attemptToGrow(world, state, pos);
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BottomContext ctx = this.getLowerHalfContext(world, pos, state);
        if (ctx != null) {
            this.attemptToGrow(world, ctx.state, ctx.pos);
        }
    }

    private void attemptToGrow(ServerWorld world, BlockState state, BlockPos pos) {
        int i = Math.min(state.get(AGE) + 1, 5);
        if (this.canGrow(world, pos, state, i)) {
            BlockState blockState = state.with(AGE, i);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            if (isDoubleTallAtAge(i)) {
                world.setBlockState(pos.up(), blockState.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
            }
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (isDoubleTallAtAge(state.get(AGE))) {
            return tallGetStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        } else {
            return state.canPlaceAt(world, pos) ? state : Blocks.AIR.getDefaultState();
        }
    }

    protected BlockState tallGetStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (direction.getAxis() != Direction.Axis.Y
                || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP)
                || neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf) {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                    ? Blocks.AIR.getDefaultState()
                    : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (!isBottom(state) || canPlaceAt(world, pos)) && tallCanPlaceAt(state, world, pos);
    }


    protected boolean tallCanPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            return super.canPlaceAt(state, world, pos);
        } else {
            BlockState blockState = world.getBlockState(pos.down());
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return YavpmItems.BANANA_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(HALF) == DoubleBlockHalf.LOWER && this.isNotYetFullyGrown(state);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            world.breakBlock(pos, true, entity);
        }

        super.onEntityCollision(state, world, pos, entity);
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        BottomContext ctx = this.getLowerHalfContext(world, pos, state);
        return ctx != null && this.canGrow(world, ctx.pos, ctx.state, ctx.state.get(AGE) + 1);
    }

    private static boolean canGrowAt(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.isOf(YavpmBlocks.BANANA_CROP);
    }

    private static boolean canPlaceAt(WorldView world, BlockPos pos) {
        return CropBlock.hasEnoughLightAt(world, pos);
    }

    private static boolean isBottom(BlockState state) {
        return state.isOf(YavpmBlocks.BANANA_CROP) && state.get(HALF) == DoubleBlockHalf.LOWER;
    }

    private boolean isNotYetFullyGrown(BlockState state) {
        return state.get(AGE) < 5;
    }

    private boolean canGrow(WorldView world, BlockPos pos, BlockState state, int age) {
        return this.isNotYetFullyGrown(state) && canPlaceAt(world, pos) && (!isDoubleTallAtAge(age) || canGrowAt(world, pos.up()));
    }

    private static boolean isDoubleTallAtAge(int age) {
        return age >= 3;
    }

    @Nullable
    private BananaCropBlock.BottomContext getLowerHalfContext(WorldView world, BlockPos pos, BlockState state) {
        if (isBottom(state)) {
            return new BottomContext(pos, state);
        } else {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            return isBottom(blockState) ? new BottomContext(blockPos, blockState) : null;
        }
    }

    record BottomContext(BlockPos pos, BlockState state) {
    }
}
