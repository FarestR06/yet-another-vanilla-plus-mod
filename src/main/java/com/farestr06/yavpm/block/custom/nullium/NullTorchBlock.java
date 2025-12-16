package com.farestr06.yavpm.block.custom.nullium;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

public class NullTorchBlock extends BaseTorchBlock {
    public static final MapCodec<NullTorchBlock> CODEC = simpleCodec(NullTorchBlock::new);
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
    public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 15);

    public NullTorchBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(COLOR, 0));
    }

    @Override
    protected MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR);
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        boolean bl = this.shouldLightUp(world, pos);
        
        if (bl) {
            world.setBlock(pos, getColor(state, world, pos), UPDATE_ALL);
        } else {
            world.setBlock(pos, state.setValue(COLOR, 0), UPDATE_ALL);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, @Nullable Orientation wireOrientation, boolean notify) {
        if ((state.getValue(COLOR) == 0) == shouldLightUp(world, pos) && !world.getBlockTicks().willTickThisTick(pos, this)) {
            world.scheduleTick(pos, this, 2);
        }
    }

    protected boolean shouldLightUp(Level world, BlockPos pos) {
        return world.hasSignal(pos.below(), Direction.DOWN);
    }

    protected static BlockState getColor(BlockState state, Level world, BlockPos pos) {
        int power = world.getSignal(pos.below(), Direction.DOWN);

        return state.setValue(COLOR, power);
    }
}
