package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.api.block.custom.MobSpawningBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.random.Random;

public abstract class AbstractFakeBlock extends MobSpawningBlock {
    public AbstractFakeBlock(Block regularBlock, Settings settings) {
        super(regularBlock, settings, YavpmEntities.TANUKI);
    }

    public abstract BlockState makeFakeBlockState(Random rand);
}
