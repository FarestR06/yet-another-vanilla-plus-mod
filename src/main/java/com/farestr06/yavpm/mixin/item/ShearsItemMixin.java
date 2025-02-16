package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.PrickleLogBlock;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "TAIL"), cancellable = true)
    private void injected(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof PrickleLogBlock) {
            if (state.get(PrickleLogBlock.PRICKLY)) {
                PlayerEntity player = context.getPlayer();
                ItemStack stack = context.getStack();
                if (player instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
                }
                world.playSound(player, pos, YavpmSounds.BLOCK_PRICKLE_LOG_PLUCK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                BlockState pluckedState = state.with(PrickleLogBlock.PRICKLY, false);
                world.setBlockState(pos, pluckedState);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(context.getPlayer(), pluckedState));
                if (player != null) {
                    int shootCount = world.getRandom().nextBetween(1, 4);
                    stack.damage(shootCount, player, LivingEntity.getSlotForHand(context.getHand()));
                    player.giveOrDropStack(new ItemStack(YavpmBlocks.PRICKLE_SHOOT.asItem(), shootCount));
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
