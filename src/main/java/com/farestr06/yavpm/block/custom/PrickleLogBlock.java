package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PrickleLogBlock extends PillarBlock {
    public static final BooleanProperty PRICKLY = BooleanProperty.of("prickly");

    public PrickleLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PRICKLY, true));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(PRICKLY, false);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient) {
            if (
                    (state.get(Properties.AXIS).isHorizontal()
                    || state.isOf(YavpmBlocks.PRICKLE_WOOD)) && state.get(PRICKLY)
            ) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.damage(livingEntity.getDamageSources().cactus(), 1.5f);
                }
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(PRICKLY) && stack.isOf(Items.SHEARS) && hand == Hand.MAIN_HAND) {
            Random rand = world.getRandom();
            int count = rand.nextBetween(2, 10);
            stack.damage(count, player, EquipmentSlot.MAINHAND);
            player.giveItemStack(new ItemStack(YavpmBlocks.PRICKLE_SHOOT, rand.nextBetween(1,5)));
            player.playSound(YavpmSounds.BLOCK_PRICKLE_LOG_PLUCK);
            world.setBlockState(pos, state.with(PRICKLY, false), Block.NOTIFY_NEIGHBORS);
            return ItemActionResult.success(true);
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PRICKLY);
    }
}
