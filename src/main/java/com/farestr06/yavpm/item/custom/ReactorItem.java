package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ReactorItem extends Item {
    public ReactorItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (stack.is(YavpmItems.REACTOR)) {
            return super.getName(stack).copy().withStyle(ChatFormatting.DARK_GRAY);
        } else if (stack.is(YavpmItems.HEATED_REACTOR)) {
            return super.getName(stack).copy().withStyle(ChatFormatting.GOLD);
        }
        return super.getName(stack);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        if (stack.is(YavpmItems.HEATED_REACTOR)) {
            ItemStack damaged = new ItemStack(YavpmItems.REACTOR);
            int damageAmount = stack.getDamageValue() + RandomSource.create().nextIntBetweenInclusive(1, 12);
            if (damageAmount >= 1024) {
                return ItemStack.EMPTY;
            }
            damaged.setDamageValue(damageAmount);
            return damaged;
        }
        return super.getRecipeRemainder(stack);
    }
}
