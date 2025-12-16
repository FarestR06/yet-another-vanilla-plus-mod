package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BananaCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 2;
    public static final int SECOND_STAGE_MAX_AGE = 3;

    // Outline VoxelShapes
    private static final VoxelShape TOP_OUTLINE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final VoxelShape GROWN_BOTTOM_OUTLINE_SHAPE = Block.box(1.0, -1.0, 1.0, 15.0, 16.0, 15.0);
    private static final VoxelShape AGE_0_SHAPE = Block.box(7.0, -1.0, 7.0, 9.0, 4.0, 9.0);
    private static final VoxelShape[] BOTTOM_OUTLINE_SHAPES = new VoxelShape[]{
            AGE_0_SHAPE,
            Block.box(3.0, -1.0, 3.0, 13.0, 9.0, 13.0),
            TOP_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE,
            GROWN_BOTTOM_OUTLINE_SHAPE
    };

    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public BananaCropBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER
                ? TOP_OUTLINE_SHAPE
                : BOTTOM_OUTLINE_SHAPES[state.getValue(AGE)];
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return false;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        float moisture = CropBlock.getGrowthSpeed(this, world, pos);
        if (random.nextInt((int)(25.0F / moisture) + 1) == 0) {
            this.attemptToGrow(world, state, pos);
        }
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BottomContext ctx = this.getLowerHalfContext(world, pos, state);
        if (ctx != null) {
            this.attemptToGrow(world, ctx.state, ctx.pos);
        }
    }

    private void attemptToGrow(ServerLevel world, BlockState state, BlockPos pos) {
        int i = Math.min(state.getValue(AGE) + 1, 5);
        if (this.canGrow(world, pos, state, i)) {
            BlockState blockState = state.setValue(AGE, i);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            if (isDoubleTallAtAge(i)) {
                world.setBlock(pos.above(), blockState.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {

        if (isDoubleTallAtAge(state.getValue(AGE))) {
            return tallGetStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return state.canSurvive(world, pos) ? state : Blocks.AIR.defaultBlockState();
        }
    }

    protected BlockState tallGetStateForNeighborUpdate(
            BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random
    ) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y
                || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP)
                || neighborState.is(this) && neighborState.getValue(HALF) != doubleBlockHalf) {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(world, pos)
                    ? Blocks.AIR.defaultBlockState()
                    : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return (!isBottom(state) || canPlaceAt(world, pos)) && tallCanPlaceAt(state, world, pos);
    }


    protected boolean tallCanPlaceAt(BlockState state, LevelReader world, BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, world, pos);
        } else {
            BlockState blockState = world.getBlockState(pos.below());
            return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return YavpmItems.BANANA_SEEDS;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && this.isNotYetFullyGrown(state);
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
        if (entity instanceof Ravager && level instanceof ServerLevel server && server.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            level.destroyBlock(blockPos, true, entity);
        }
        super.entityInside(blockState, level, blockPos, entity, insideBlockEffectApplier, bl);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        BottomContext ctx = this.getLowerHalfContext(world, pos, state);
        return ctx != null && this.canGrow(world, ctx.pos, ctx.state, ctx.state.getValue(AGE) + 1);
    }

    private static boolean canGrowAt(LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.is(YavpmBlocks.BANANA_CROP);
    }

    private static boolean canPlaceAt(LevelReader world, BlockPos pos) {
        return CropBlock.hasSufficientLight(world, pos);
    }

    private static boolean isBottom(BlockState state) {
        return state.is(YavpmBlocks.BANANA_CROP) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private boolean isNotYetFullyGrown(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    private boolean canGrow(LevelReader world, BlockPos pos, BlockState state, int age) {
        return this.isNotYetFullyGrown(state) && canPlaceAt(world, pos) && (!isDoubleTallAtAge(age) || canGrowAt(world, pos.above()));
    }

    private static boolean isDoubleTallAtAge(int age) {
        return age >= 3;
    }

    @Nullable
    private BananaCropBlock.BottomContext getLowerHalfContext(LevelReader world, BlockPos pos, BlockState state) {
        if (isBottom(state)) {
            return new BottomContext(pos, state);
        } else {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            return isBottom(blockState) ? new BottomContext(blockPos, blockState) : null;
        }
    }

    record BottomContext(BlockPos pos, BlockState state) {
    }
}
