package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class PrickleLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty PRICKLY = BooleanProperty.create("prickly");

    public PrickleLogBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(PRICKLY, true)); // Naturally spawning Prickle Logs are prickly! Ouch!
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(PRICKLY, false); // Player-placed Prickle Logs shouldn't be prickly.
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        // Are we on the server side?
        if (world instanceof ServerLevel serverWorld) {
            if (
                    // If it's a horizontal Prickle Log or a Prickle Wood...
                    (state.getValue(BlockStateProperties.AXIS).isHorizontal() || state.is(YavpmBlocks.PRICKLE_WOOD))
                            && state.getValue(PRICKLY) // And it has the needles...
            ) {
                if (entity instanceof LivingEntity livingEntity) { // Then we'll check if the entity is alive.
                    // If they are, we'll poke them!
                    livingEntity.hurtServer(serverWorld, livingEntity.damageSources().cactus(), 1.5f);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PRICKLY);
    }
}
