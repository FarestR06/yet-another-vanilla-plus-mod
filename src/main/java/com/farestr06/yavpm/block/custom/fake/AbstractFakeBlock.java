package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.api.block.custom.MobSpawningBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.misc.criterion.YavpmCriteria;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFakeBlock extends MobSpawningBlock {
    public AbstractFakeBlock(Block regularBlock, Properties settings) {
        super(regularBlock, settings, YavpmEntities.TANUKI);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(world, player, pos, state, blockEntity, tool);
        if (!world.isClientSide()) {
            YavpmCriteria.FAKE_BLOCK_DESTROYED.trigger(((ServerPlayer) player), state, tool);
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        this.spawnMob(world, pos);
    }
}
