package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmDamageTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrickleLogBlock extends PillarBlock {
    public static final BooleanProperty PRICKLY = BooleanProperty.of("prickly");

    public PrickleLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient) {
            if (state.get(Properties.AXIS).isHorizontal() || state.isOf(YavpmBlocks.PRICKLE_WOOD)) {
                if (entity instanceof ServerPlayerEntity player) {
                    player.damage(
                            new DamageSource(
                                    world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(YavpmDamageTypes.PRICKLE_DAMAGE)
                            ),
                            1.5f
                    );
                }
            }
        }
    }
}
