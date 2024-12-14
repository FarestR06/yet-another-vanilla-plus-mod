package com.farestr06.yavpm.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public abstract class SaplingCropBlock extends CropBlock {
    public SaplingCropBlock(Settings settings) {
        super(settings);
    }

    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected abstract ItemConvertible getSeedsItem();
}
