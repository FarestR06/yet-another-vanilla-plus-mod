package com.farestr06.yavpm.block.custom.recycler;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;

public class RecycleDispenserBehavior extends ItemDispenserBehavior {

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        Direction direction = pointer.state().get(DispenserBlock.FACING);
        Position position = DispenserBlock.getOutputLocation(pointer);
        spawnItem(pointer.world(), stack, 6, direction, position);
        return stack;
    }
}
