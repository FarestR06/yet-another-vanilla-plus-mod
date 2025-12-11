package com.farestr06.yavpm.block.custom.nullium;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.tick.TickPriority;

public class RedstoneToggleBlock extends AbstractRedstoneGateBlock {
    public static final MapCodec<RedstoneToggleBlock> CODEC = createCodec(RedstoneToggleBlock::new);
    public static final BooleanProperty OUTPUTTING = BooleanProperty.of("outputting");

    public RedstoneToggleBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, OUTPUTTING);
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean bl = state.get(POWERED);
        boolean bl2 = this.hasPower(world, pos, state);
        if (bl && !bl2) {
            world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_LISTENERS);
        } else if (!bl) {
            world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_LISTENERS);
            world.setBlockState(pos, state.with(OUTPUTTING, !state.get(OUTPUTTING)));
            if (!bl2) {
                world.scheduleBlockTick(pos, this, this.getUpdateDelayInternal(state), TickPriority.VERY_HIGH);
            }
        }
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if(state.get(OUTPUTTING)) {
            return state.get(FACING) == direction ? this.getOutputLevel(world, pos, state) : 0;
        }
        return 0;
    }
}
