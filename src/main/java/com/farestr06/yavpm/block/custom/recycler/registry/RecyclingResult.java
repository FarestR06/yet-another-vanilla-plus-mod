package com.farestr06.yavpm.block.custom.recycler.registry;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

/**
 * This record is used to define the results of recycling items.
 * @param item The item to be dispensed by the Recycler. Although it is nullable, a null value should not be passed in; use RecyclingResult.EMPTY instead.
 * @param min The minimum count for the dispensed item stack.
 * @param max The maximum count for the dispensed item stack. If null, the count will always match the minimum value.
 */
public record RecyclingResult(@Nullable ItemConvertible item, Integer min, @Nullable Integer max) {
    public static final RecyclingResult EMPTY = new RecyclingResult(null, 0, 0);

    public ItemStack toStack(Random rand) {
        if (item == null) {
            return ItemStack.EMPTY;
        }
        if (max == null) {
            return new ItemStack(item, min);
        }
        int count = rand.nextBetween(min, max);
        return new ItemStack(item, count);
    }
}
