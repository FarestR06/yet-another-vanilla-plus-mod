package com.farestr06.yavpm.block.custom.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;

import java.util.List;

public class PinataBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {
    private ItemStack stack = ItemStack.EMPTY;

    public PinataBlockEntity(BlockPos pos, BlockState state) {
        super(YavpmBlockEntities.PINATA, pos, state);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        if (!this.stack.isEmpty()) {
            valueOutput.store("item", ItemStack.CODEC, this.stack);
        }
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.stack = valueInput.read("item", ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(this.stack)));
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        this.stack = components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
    }

    @Override
    public ItemStack splitTheItem(int count) {
        ItemStack itemStack = this.stack.split(count);
        if (this.stack.isEmpty()) {
            this.stack = ItemStack.EMPTY;
        }

        return itemStack;
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return this.stack;
    }

    @Override
    public void setTheItem(ItemStack stack) {
        this.stack = stack;
    }
}
