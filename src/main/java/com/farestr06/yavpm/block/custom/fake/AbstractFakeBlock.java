package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.api.block.custom.MobSpawningBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.misc.criterion.YavpmCriteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFakeBlock extends MobSpawningBlock {
    public AbstractFakeBlock(Block regularBlock, Settings settings) {
        super(regularBlock, settings, YavpmEntities.TANUKI);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (!world.isClient()) {
            YavpmCriteria.FAKE_BLOCK_DESTROYED.trigger(((ServerPlayerEntity) player), state, tool);
        }
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        this.spawnMob(world, pos);
    }
}
