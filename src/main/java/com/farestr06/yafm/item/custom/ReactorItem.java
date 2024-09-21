package com.farestr06.yafm.item.custom;

import com.farestr06.yafm.item.YavpmItems;
import com.farestr06.yafm.util.YavpmSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.random.Random;

public class ReactorItem extends Item {
    public ReactorItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.isOf(YavpmItems.REACTOR)) {
            return super.getName(stack).copy().formatted(Formatting.DARK_GRAY);
        } else if (stack.isOf(YavpmItems.HEATED_REACTOR)) {
            return super.getName(stack).copy().formatted(Formatting.GOLD);
        }
        return super.getName(stack);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (stack.isOf(YavpmItems.REACTOR) && otherStack.isOf(Items.BLAZE_POWDER)) {
            player.playSound(YavpmSounds.ITEM_REACTOR_CHARGE);
            if (otherStack.isOf(Items.LAVA_BUCKET) && !player.isCreative()) {
                otherStack.setCount(0);
                player.giveItemStack(new ItemStack(Items.BUCKET));
            } else {
                otherStack.decrementUnlessCreative(1, player);
            }

            ItemStack newStack = new ItemStack(YavpmItems.HEATED_REACTOR);
            newStack.setDamage(stack.getDamage());

            stack.setCount(0);
            player.giveItemStack(newStack);
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        if (stack.isOf(YavpmItems.HEATED_REACTOR)) {
            ItemStack damaged = new ItemStack(YavpmItems.REACTOR);
            int damageAmount = stack.getDamage() + Random.create().nextBetween(1, 8);
            if (damageAmount >= 1024) {
                return ItemStack.EMPTY;
            }
            damaged.setDamage(damageAmount);
            return damaged;
        }
        return super.getRecipeRemainder(stack);
    }
}
