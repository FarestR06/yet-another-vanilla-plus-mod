package com.farestr06.yavpm.world.feature.configured;

import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class YavpmConfiguredFeatureBootstrapper {
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        YavpmMiscConfiguredFeatures.bootstrap(context);
        YavpmTreeConfiguredFeatures.bootstrap(context);
        YavpmVegetationConfiguredFeatures.bootstrap(context);
    }
}
