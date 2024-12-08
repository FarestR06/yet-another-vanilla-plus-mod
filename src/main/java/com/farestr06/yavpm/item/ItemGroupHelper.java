package com.farestr06.yavpm.item;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

public class ItemGroupHelper {
    private static void BuildingBlocks(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.APPLE_LOG);
        entries.add(YavpmBlocks.APPLE_WOOD);
        entries.add(YavpmBlocks.STRIPPED_APPLE_LOG);
        entries.add(YavpmBlocks.STRIPPED_APPLE_WOOD);
        entries.add(YavpmBlocks.APPLE_PLANKS);
        entries.add(YavpmBlocks.APPLE_STAIRS);
        entries.add(YavpmBlocks.APPLE_SLAB);
        entries.add(YavpmBlocks.APPLE_FENCE);
        entries.add(YavpmBlocks.APPLE_FENCE_GATE);
        entries.add(YavpmBlocks.APPLE_DOOR);
        entries.add(YavpmBlocks.APPLE_TRAPDOOR);
        entries.add(YavpmBlocks.APPLE_PRESSURE_PLATE);
    }
}
