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
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class BabyKeyItem extends Item {
    public BabyKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            if (player.age % 45 == 0 && player.getRandom().nextFloat() <= 0.40f && selected) {
                player.playSound(YavpmSounds.ITEM_BABY_KEY_SCARED, 1f, 1f);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (context.getPlayer() != null) {
            PlayerEntity player = context.getPlayer();
            BlockEntity entity = world.getBlockEntity(context.getBlockPos());
            if (player.isSneaking()) {
                String key = context.getStack().getName().getString();
                if (entity instanceof LockableContainerBlockEntity lockable) {
                    if (((LockableContainerBlockEntityAccessor) lockable).getLock() == ContainerLock.EMPTY) {
                        if (!world.isClient()) {
                            ((LockableContainerBlockEntityAccessor) lockable).setLock(new ContainerLock(key));
                        }
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.success(world.isClient);
                    } else if (Objects.equals(((LockableContainerBlockEntityAccessor) lockable).getLock().key(), key)) {
                        if (!world.isClient()) {
                            ((LockableContainerBlockEntityAccessor) lockable).setLock(ContainerLock.EMPTY);
                        }
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.success(world.isClient);
                    } else {
                        return ActionResult.FAIL;
                    }
                } else if (entity instanceof KeylockBlockEntity keylock) {
                    if (keylock.getLock() == ContainerLock.EMPTY) {
                        if (!world.isClient()) {
                            keylock.setLock(new ContainerLock(key));
                        }
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.success(world.isClient);
                    } else if (Objects.equals(keylock.getLock().key(), key)) {
                        if (!world.isClient()) {
                            keylock.setLock(ContainerLock.EMPTY);
                        }
                        player.playSound(YavpmSounds.ITEM_BABY_KEY_TURN, 1f, 1f);
                        return ActionResult.success(world.isClient);
                    } else {
                        return ActionResult.FAIL;
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }
}
