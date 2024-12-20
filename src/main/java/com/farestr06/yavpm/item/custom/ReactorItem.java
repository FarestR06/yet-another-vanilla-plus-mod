package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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
