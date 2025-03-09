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
    // Allow shears to pluck needles from Prickle Logs.
    @Inject(method = "useOnBlock", at = @At(value = "TAIL"), cancellable = true)
    private void injected(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld(); // Get the world...
        BlockPos pos = context.getBlockPos(); // ...And the position,
        BlockState state = world.getBlockState(pos); // which we'll use to get the block state.
        if (state.getBlock() instanceof PrickleLogBlock) { // Is the state a Prickle Log block?
            if (state.get(PrickleLogBlock.PRICKLY)) { // Is that Prickle Log prickly?
                PlayerEntity player = context.getPlayer(); // Get our player...
                ItemStack stack = context.getStack(); // And the stack!
                if (player instanceof ServerPlayerEntity) { // Is our player on the server side?
                    // If so, we'll trigger the advancement criterion.
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
                }
                // BOINK!
                world.playSound(player, pos, YavpmSounds.BLOCK_PRICKLE_LOG_PLUCK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                BlockState pluckedState = state.with(PrickleLogBlock.PRICKLY, false); // Remove the needles from the block state...
                world.setBlockState(pos, pluckedState); // ...And place it!
                // Go go gadget Sculk Sensor!
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(context.getPlayer(), pluckedState));
                if (player != null) { // Check if our player exists for good measure
                    int shootCount = world.getRandom().nextBetween(1, 4); // How many shoots should we give?
                    // Wear down the shears. It's not ideal, I know.
                    stack.damage(shootCount, player, LivingEntity.getSlotForHand(context.getHand()));
                    // Give the player the shoots
                    player.giveOrDropStack(new ItemStack(YavpmBlocks.PRICKLE_SHOOT.asItem(), shootCount));
                }
                // Success!
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
