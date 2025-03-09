package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrickleLogBlock extends PillarBlock {
    public static final BooleanProperty PRICKLY = BooleanProperty.of("prickly");

    public PrickleLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PRICKLY, true)); // Naturally spawning Prickle Logs are prickly! Ouch!
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(PRICKLY, false); // Player-placed Prickle Logs shouldn't be prickly.
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // Are we on the server side?
        if (world instanceof ServerWorld serverWorld) {
            if (
                    // If it's a horizontal Prickle Log or a Prickle Wood...
                    (state.get(Properties.AXIS).isHorizontal() || state.isOf(YavpmBlocks.PRICKLE_WOOD))
                            && state.get(PRICKLY) // And it has the needles...
            ) {
                if (entity instanceof LivingEntity livingEntity) { // Then we'll check if the entity is alive.
                    // If they are, we'll poke them!
                    livingEntity.damage(serverWorld, livingEntity.getDamageSources().cactus(), 1.5f);
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PRICKLY);
    }
}
