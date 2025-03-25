package com.farestr06.yavpm.block.custom.nullium;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class NullToggleBlock extends AbstractRedstoneGateBlock {
    public static final MapCodec<NullToggleBlock> CODEC = createCodec(NullToggleBlock::new);

    public NullToggleBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean isPowered = state.get(POWERED);
        if (this.hasPower(world, pos, state)) {
            world.setBlockState(pos, state.with(POWERED, !isPowered));
        }
        super.scheduledTick(state, world, pos, random);
    }
}
