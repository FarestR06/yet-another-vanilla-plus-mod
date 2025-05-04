package com.farestr06.yavpm.block.custom.nullium;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class NullTorchBlock extends AbstractTorchBlock {
    public static final MapCodec<NullTorchBlock> CODEC = createCodec(NullTorchBlock::new);
    /*
     * Black (off): 0 / 0000
     * Dark Red: 1 / 0001
     * Dark Green: 2 / 0010
     * Dark Yellow: 3 / 0011
     * Dark Blue: 4 / 0100
     * Dark Magenta: 5 / 0101
     * Dark Cyan: 6 / 0110
     * Gray: 7 / 0111
     * 8 through 15 are the same, but lighter
     */
    public static final IntProperty COLOR = IntProperty.of("color", 0, 15);

    public NullTorchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(COLOR, 0));
    }

    @Override
    protected MapCodec<? extends AbstractTorchBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(COLOR);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean bl = this.shouldLightUp(world, pos);
        
        if (bl) {
            world.setBlockState(pos, getColor(state, world, pos), NOTIFY_ALL);
        } else {
            world.setBlockState(pos, state.with(COLOR, 0), NOTIFY_ALL);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if ((state.get(COLOR) == 0) == shouldLightUp(world, pos) && !world.getBlockTickScheduler().isTicking(pos, this)) {
            world.scheduleBlockTick(pos, this, 2);
        }
    }

    protected boolean shouldLightUp(World world, BlockPos pos) {
        return world.isEmittingRedstonePower(pos.down(), Direction.DOWN);
    }

    protected static BlockState getColor(BlockState state, World world, BlockPos pos) {
        int power = world.getEmittedRedstonePower(pos.down(), Direction.DOWN);

        return state.with(COLOR, power);
    }
}
