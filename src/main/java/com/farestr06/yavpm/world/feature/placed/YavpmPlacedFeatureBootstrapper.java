package com.farestr06.yavpm.world.feature.placed;

import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.PlacedFeature;

public class YavpmPlacedFeatureBootstrapper {
    public static void bootstrap(Registerable<PlacedFeature> context) {
        YavpmMiscPlacedFeatures.bootstrap(context);
        YavpmTreePlacedFeatures.bootstrap(context);
        YavpmVegetationPlacedFeatures.bootstrap(context);
    }
}
