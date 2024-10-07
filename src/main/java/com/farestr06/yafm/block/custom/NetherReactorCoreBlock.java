package com.farestr06.yafm.block.custom;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class NetherReactorCoreBlock extends Block {
    public static final EnumProperty<Phase> PHASE = EnumProperty.of("phase", Phase.class);

    public NetherReactorCoreBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PHASE, Phase.INACTIVE));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PHASE);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;
        if (state.get(PHASE) == Phase.DEAD) {
            player.sendMessage(Text.translatable("block.yavpm.nether_reactor_core.reactor_dead"));
            return ActionResult.FAIL;
        }
        if (isStructureCorrect(world, pos)) {
            if (state.get(PHASE) != Phase.INACTIVE) {
                player.sendMessage(Text.translatable("block.yavpm.nether_reactor_core.already_running"));
                return ActionResult.FAIL;
            }
            world.setBlockState(pos, state.with(PHASE, Phase.ACTIVE));
            world.scheduleBlockTick(pos, this, 6000);
            player.sendMessage(Text.translatable("block.yavpm.nether_reactor_core.success").formatted(Formatting.GOLD));
            return ActionResult.SUCCESS;
        }
        player.sendMessage(Text.translatable("block.yavpm.nether_reactor_core.bad_structure"));
        return ActionResult.FAIL;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(PHASE) == Phase.ACTIVE) {
            world.setBlockState(pos, state.with(PHASE, Phase.DEAD));
        }
    }

    private boolean isStructureCorrect(World world, BlockPos pos) {
        if (!world.isClient) {
            boolean bl = true;
            BlockPos pos1 = pos.up();
            BlockPos pos2 = pos.down();
            for (BlockPos blockPos : Arrays.asList(pos.north(), pos.south(), pos.east(), pos.west())) {
                bl = world.getBlockState(blockPos).isIn(ConventionalBlockTags.COBBLESTONES);
            }
            for (BlockPos blockPos : Arrays.asList(pos1.north().east(), pos1.south().east(), pos1.north().west(), pos1.south().west())) {
                bl = world.getBlockState(blockPos).isIn(ConventionalBlockTags.COBBLESTONES);
            }
            for (BlockPos blockPos : Arrays.asList(pos2.north(), pos2.south(), pos2.east(), pos2.west())) {
                bl = world.getBlockState(blockPos).isIn(ConventionalBlockTags.COBBLESTONES);
            }
            for (BlockPos blockPos : Arrays.asList(pos2.north().east(), pos2.south().east(), pos2.north().west(), pos2.south().west())) {
                bl = world.getBlockState(blockPos).isOf(Blocks.GOLD_BLOCK);
            }

            return bl;
        }
        return false;
    }

    public enum Phase implements StringIdentifiable {
        INACTIVE("inactive"),
        ACTIVE("active"),
        DEAD("dead");

        private final String id;

        Phase(String id) {
            this.id = id;
        }

        @Override
        public String asString() {
            return id;
        }
    }
}
