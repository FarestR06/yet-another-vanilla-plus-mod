package com.farestr06.yavpm.world.feature.configured;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class YavpmConfiguredFeatureBootstrapper {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        YavpmMiscConfiguredFeatures.bootstrap(context);
        YavpmTreeConfiguredFeatures.bootstrap(context);
        YavpmVegetationConfiguredFeatures.bootstrap(context);
    }
}
