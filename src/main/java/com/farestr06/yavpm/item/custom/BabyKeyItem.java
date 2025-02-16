package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.block.custom.entity.KeylockBlockEntity;
import com.farestr06.yavpm.mixin.block.LockableContainerBlockEntityAccessor;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.predicate.ComponentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class BabyKeyItem extends Item {
    public BabyKeyItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (HANDLER.instance().babyKeyCries && entity instanceof PlayerEntity player) {
            if (player.age % 45 == 0 && player.getRandom().nextFloat() <= 0.40f && selected) {
                player.playSound(YavpmSounds.ITEM_BABY_KEY_SCARED, 1f, 1f);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final World world = context.getWorld();
        final BlockPos pos = context.getBlockPos();
        if (context.getPlayer() != null && world.getBlockEntity(pos) != null) {
            PlayerEntity player = context.getPlayer();
            BlockEntity entity = world.getBlockEntity(pos);
            if (player.isSneaking() && world instanceof ServerWorld serverWorld) { // If the player is sneaking AND the logical side is the server...
                RegistryWrapper.Impl<Item> itemLookup = serverWorld.getRegistryManager().getOrThrow(RegistryKeys.ITEM); // Create an item lookup
                ItemStack stack = context.getStack(); // Grab the stack
                ItemPredicate predicate = ItemPredicate.Builder.create() // Make a predicate from our stack
                        .items(itemLookup, stack.getItem())
                        .component(ComponentPredicate.of(stack.getComponents())).build();
                if (entity instanceof LockableContainerBlockEntity lockable) { // If there's a chest
                    if (((LockableContainerBlockEntityAccessor) lockable).getLock() == ContainerLock.EMPTY) {
                        ((LockableContainerBlockEntityAccessor) lockable).setLock(new ContainerLock(predicate));
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.SUCCESS;
                    } else if (((LockableContainerBlockEntityAccessor) lockable).getLock().canOpen(stack)) {
                        ((LockableContainerBlockEntityAccessor) lockable).setLock(ContainerLock.EMPTY);
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.SUCCESS;
                    } else {
                        return ActionResult.FAIL;
                    }
                } else if (entity instanceof KeylockBlockEntity keylock) {
                    if (keylock.getLock() == ContainerLock.EMPTY) {
                        keylock.setLock(new ContainerLock(predicate));
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.SUCCESS;
                    } else if (keylock.getLock().canOpen(stack)) {
                        keylock.setLock(ContainerLock.EMPTY);
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.SUCCESS;
                    } else {
                        return ActionResult.FAIL;
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }
}
