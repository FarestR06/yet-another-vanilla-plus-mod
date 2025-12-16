package com.farestr06.yavpm.block.custom.recycler.registry;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

/**
 * This record is used to define the results of recycling items.
 * @param item The item to be dispensed by the Recycler. Although it is nullable, a null value should not be passed in; use RecyclingResult.EMPTY instead.
 * @param min The minimum count for the dispensed item stack.
 * @param max The maximum count for the dispensed item stack. If null, the count will always match the minimum value.
 */
public record RecyclingResult(@Nullable ItemLike item, Integer min, @Nullable Integer max) {
    public static final RecyclingResult EMPTY = new RecyclingResult(null, 0, 0);

    public ItemStack toStack(RandomSource rand) {
        if (item == null) {
            return ItemStack.EMPTY;
        }
        if (max == null) {
            return new ItemStack(item, min);
        }
        int count = rand.nextIntBetweenInclusive(min, max);
        return new ItemStack(item, count);
    }
}
