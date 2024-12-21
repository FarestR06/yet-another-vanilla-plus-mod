package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.api.block.custom.MobSpawningBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import net.minecraft.block.Block;

public abstract class AbstractFakeBlock extends MobSpawningBlock {
    public AbstractFakeBlock(Block regularBlock, Settings settings) {
        super(regularBlock, settings, YavpmEntities.TANUKI);
    }
}
