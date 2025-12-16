package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.PrickleLogBlock;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    // Allow shears to pluck needles from Prickle Logs.
    @Inject(method = "useOn", at = @At(value = "TAIL"), cancellable = true)
    private void injected(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level world = context.getLevel(); // Get the world...
        BlockPos pos = context.getClickedPos(); // ...And the position,
        BlockState state = world.getBlockState(pos); // which we'll use to get the block state.
        if (state.getBlock() instanceof PrickleLogBlock) { // Is the state a Prickle Log block?
            if (state.getValue(PrickleLogBlock.PRICKLY)) { // Is that Prickle Log prickly?
                Player player = context.getPlayer(); // Get our player...
                ItemStack stack = context.getItemInHand(); // And the stack!
                if (player instanceof ServerPlayer) { // Is our player on the server side?
                    // If so, we'll trigger the advancement criterion.
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                }
                // BOINK!
                world.playSound(player, pos, YavpmSounds.BLOCK_PRICKLE_LOG_PLUCK, SoundSource.BLOCKS, 1.0F, 1.0F);
                BlockState pluckedState = state.setValue(PrickleLogBlock.PRICKLY, false); // Remove the needles from the block state...
                world.setBlockAndUpdate(pos, pluckedState); // ...And place it!
                // Go go gadget Sculk Sensor!
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), pluckedState));
                if (player != null) { // Check if our player exists for good measure
                    int shootCount = world.getRandom().nextIntBetweenInclusive(1, 4); // How many shoots should we give?
                    // Wear down the shears. It's not ideal, I know.
                    stack.hurtAndBreak(shootCount, player, context.getHand().asEquipmentSlot());
                    // Give the player the shoots
                    player.handleExtraItemsCreatedOnUse(new ItemStack(YavpmBlocks.PRICKLE_SHOOT.asItem(), shootCount));
                }
                // Success!
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
