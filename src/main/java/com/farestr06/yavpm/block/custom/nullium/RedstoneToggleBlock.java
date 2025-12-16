package com.farestr06.yavpm.block.custom.nullium;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.TickPriority;

public class RedstoneToggleBlock extends DiodeBlock {
    public static final MapCodec<RedstoneToggleBlock> CODEC = simpleCodec(RedstoneToggleBlock::new);
    public static final BooleanProperty OUTPUTTING = BooleanProperty.create("outputting");

    public RedstoneToggleBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends DiodeBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, OUTPUTTING);
    }

    @Override
    protected int getDelay(BlockState state) {
        return 2;
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        boolean bl = state.getValue(POWERED);
        boolean bl2 = this.shouldTurnOn(world, pos, state);
        if (bl && !bl2) {
            world.setBlock(pos, state.setValue(POWERED, false), Block.UPDATE_CLIENTS);
        } else if (!bl) {
            world.setBlock(pos, state.setValue(POWERED, true), Block.UPDATE_CLIENTS);
            world.setBlockAndUpdate(pos, state.setValue(OUTPUTTING, !state.getValue(OUTPUTTING)));
            if (!bl2) {
                world.scheduleTick(pos, this, this.getDelay(state), TickPriority.VERY_HIGH);
            }
        }
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        if(state.getValue(OUTPUTTING)) {
            return state.getValue(FACING) == direction ? this.getOutputSignal(world, pos, state) : 0;
        }
        return 0;
    }
}
