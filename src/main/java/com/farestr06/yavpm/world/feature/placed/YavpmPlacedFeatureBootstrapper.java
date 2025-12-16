package com.farestr06.yavpm.world.feature.placed;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class YavpmPlacedFeatureBootstrapper {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        YavpmMiscPlacedFeatures.bootstrap(context);
        YavpmTreePlacedFeatures.bootstrap(context);
        YavpmVegetationPlacedFeatures.bootstrap(context);
    }
}
