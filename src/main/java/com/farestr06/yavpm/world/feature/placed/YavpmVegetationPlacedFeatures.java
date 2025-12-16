package com.farestr06.yavpm.world.feature.placed;

import com.farestr06.yavpm.world.feature.configured.YavpmVegetationConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmVegetationPlacedFeatures {
    public static final ResourceKey<PlacedFeature> APPLE_VEGETAION_PLACED = registerKey("apple_vegetation_placed");
    public static final ResourceKey<PlacedFeature> APPLE_ORCHARD_GROVE_VEGETAION_PLACED = registerKey("apple_orchard_grove_vegetation_placed");
    public static final ResourceKey<PlacedFeature> PERSIMMON_VEGETAION_PLACED = registerKey("persimmon_vegetation_placed");
    public static final ResourceKey<PlacedFeature> PRICKLE_VEGETAION_PLACED = registerKey("prickle_vegetation_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(
                context,
                APPLE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.APPLE_VEGETAION),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1))
        );
        register(
                context,
                APPLE_ORCHARD_GROVE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.APPLE_ORCHARD_GROVE_VEGETAION),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))
        );
        register(
                context,
                PERSIMMON_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.PERSIMMON_VEGETAION),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))
        );
        register(
                context,
                PRICKLE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.PRICKLE_VEGETATION),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.005f, 1))
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, makeId(name));
    }
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
