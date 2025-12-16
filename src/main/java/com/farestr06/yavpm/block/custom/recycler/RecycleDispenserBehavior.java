package com.farestr06.yavpm.block.custom.recycler;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class RecycleDispenserBehavior extends DefaultDispenseItemBehavior {

    @Override
    protected ItemStack execute(BlockSource pointer, ItemStack stack) {
        Direction direction = pointer.state().getValue(DispenserBlock.FACING);
        Position position = DispenserBlock.getDispensePosition(pointer);
        spawnItem(pointer.level(), stack, 6, direction, position);
        return stack;
    }
}
