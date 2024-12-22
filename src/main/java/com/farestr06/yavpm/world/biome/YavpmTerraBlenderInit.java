package com.farestr06.yavpm.world.biome;

import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTerraBlenderInit implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new YavpmOverworldRegion(makeId("overworld"), 1));
    }
}
