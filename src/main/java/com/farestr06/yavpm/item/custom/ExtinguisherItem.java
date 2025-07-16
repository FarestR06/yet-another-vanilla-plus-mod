package com.farestr06.yavpm.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExtinguisherItem extends Item {
    public ExtinguisherItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);
            if (user.isOnFire() && stack.getCount() >= 1) {
                user.extinguish();
                stack.decrementUnlessCreative(1, user);
                return ActionResult.SUCCESS;
            }
            return super.use(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        BlockPos pos = ctx.getBlockPos().up();
        if (ctx.getWorld() instanceof ServerWorld server) {
            BlockState state;
            BlockPos pos1;
            boolean successful = false;
            for (int x = -4; x <= 4; x++) {
                for (int y = -4; y <= 4; y++) {
                    for (int z = -4; z <= 4; z++) {
                        pos1 = pos.add(x, y, z);
                        state = server.getBlockState(pos1);
                        if (state.isIn(BlockTags.FIRE)) {
                            server.setBlockState(pos1, Blocks.AIR.getDefaultState());
                            successful = true;
                        } else if (state.isOf(Blocks.CAMPFIRE)) {
                            server.setBlockState(pos1, Blocks.CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, false));
                            successful = true;
                        } else if (state.isOf(Blocks.SOUL_CAMPFIRE)) {
                            server.setBlockState(pos1, Blocks.SOUL_CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, false));
                            successful = true;
                        }
                    }
                }
            }
            return successful ? ActionResult.SUCCESS : ActionResult.PASS;
        }
        return super.useOnBlock(ctx);
    }
}
