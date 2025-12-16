package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.mixin.block.LockableContainerBlockEntityAccessor;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.LockCode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class BabyKeyItem extends Item {
    public BabyKeyItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if (HANDLER.instance().babyKeyCries && entity instanceof Player player) {
            if (player.tickCount % 45 == 0 && player.getRandom().nextFloat() <= 0.15f) {
                BlockPos pos = player.blockPosition();
                level.playSound(null, pos, YavpmSounds.ITEM_BABY_KEY_SCARED, SoundSource.NEUTRAL, 1f, 1f); // player.playSound(YavpmSounds.ITEM_BABY_KEY_SCARED, 1f, 1f);
                level.gameEvent(GameEvent.ENTITY_ACTION, pos, GameEvent.Context.of(player));
            }
        }
        super.inventoryTick(stack, level, entity, slot);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final Level world = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        if (context.getPlayer() != null && world.getBlockEntity(pos) != null) {
            Player player = context.getPlayer();
            BlockEntity entity = world.getBlockEntity(pos);
            if (player.isShiftKeyDown() && world instanceof ServerLevel serverWorld) { // If the player is sneaking AND the logical side is the server...
                HolderLookup.RegistryLookup<Item> itemLookup = serverWorld.registryAccess().lookupOrThrow(Registries.ITEM); // Create an item lookup
                ItemStack stack = context.getItemInHand(); // Grab the stack
                ItemPredicate predicate = ItemPredicate.Builder.item() // Make a predicate from our stack
                        .of(itemLookup, stack.getItem())
                        .withComponents(DataComponentMatchers.Builder.components().exact(
                                DataComponentExactPredicate.allOf(stack.getComponents())
                        ).build()).build();
                if (entity instanceof BaseContainerBlockEntity lockable) { // If there's a chest
                    if (((LockableContainerBlockEntityAccessor) lockable).getLockKey() == LockCode.NO_LOCK) {
                        ((LockableContainerBlockEntityAccessor) lockable).setLockKey(new LockCode(predicate));
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return InteractionResult.SUCCESS;
                    } else if (((LockableContainerBlockEntityAccessor) lockable).getLockKey().unlocksWith(stack)) {
                        ((LockableContainerBlockEntityAccessor) lockable).setLockKey(LockCode.NO_LOCK);
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.FAIL;
                    }
                }
            }
        }
        return super.useOn(context);
    }
}
