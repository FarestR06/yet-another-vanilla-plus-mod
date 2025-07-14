package com.farestr06.yavpm.block.custom.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.inventory.SingleStackInventory.SingleStackBlockEntityInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class PinataBlockEntity extends BlockEntity implements SingleStackBlockEntityInventory {
    private ItemStack stack = ItemStack.EMPTY;

    public PinataBlockEntity(BlockPos pos, BlockState state) {
        super(YavpmBlockEntities.PINATA, pos, state);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        if (!this.stack.isEmpty()) {
            view.put("item", ItemStack.CODEC, stack);
        }
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        this.stack = view.read("item", ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    protected void addComponents(ComponentMap.Builder builder) {
        super.addComponents(builder);
        builder.add(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(List.of(this.stack)));
    }

    @Override
    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
        this.stack = components.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).copyFirstStack();
    }

    @Override
    public ItemStack decreaseStack(int count) {
        ItemStack itemStack = this.stack.split(count);
        if (this.stack.isEmpty()) {
            this.stack = ItemStack.EMPTY;
        }

        return itemStack;
    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public void setStack(ItemStack stack) {
        this.stack = stack;
    }
}
