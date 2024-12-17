package com.farestr06.yavpm.world.feature.placed;

import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.PlacedFeature;

public class YavpmPlacedFeatureBootstrapper {
    public static void boostrap(Registerable<PlacedFeature> context) {
        YavpmMiscPlacedFeatures.boostrap(context);
        YavpmTreePlacedFeatures.bootstrap(context);
        YavpmVegetationPlacedFeatures.bootstrap(context);
    }
}
