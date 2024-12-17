package com.farestr06.yavpm.world.feature.configured;

import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class YavpmConfiguredFeatureBootstrapper {
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        YavpmTreeConfiguredFeatures.boostrap(context);
        YavpmVegetationConfiguredFeatures.bootstrap(context);
        YavpmMiscConfiguredFeatures.boostrap(context);
    }
}
