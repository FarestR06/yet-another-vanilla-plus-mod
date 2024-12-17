package com.farestr06.yavpm.world.feature.placed;

import com.farestr06.yavpm.world.feature.configured.YavpmVegetationConfiguredFeatures;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmVegetationPlacedFeatures {
    public static final RegistryKey<PlacedFeature> APPLE_VEGETAION_PLACED = registerKey("apple_vegetation_placed");
    public static final RegistryKey<PlacedFeature> APPLE_ORCHARD_GROVE_VEGETAION_PLACED = registerKey("apple_orchard_grove_vegetation_placed");
    public static final RegistryKey<PlacedFeature> PERSIMMON_VEGETAION_PLACED = registerKey("persimmon_vegetation_placed");
    public static final RegistryKey<PlacedFeature> PRICKLE_VEGETAION_PLACED = registerKey("prickle_vegetation_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(
                context,
                APPLE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.APPLE_VEGETAION),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 1))
        );
        register(
                context,
                APPLE_ORCHARD_GROVE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.APPLE_ORCHARD_GROVE_VEGETAION),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(2, 0.1F, 1))
        );
        register(
                context,
                PERSIMMON_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.PERSIMMON_VEGETAION),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(5, 0.1F, 1))
        );
        register(
                context,
                PRICKLE_VEGETAION_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmVegetationConfiguredFeatures.PRICKLE_VEGETATION),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 1))
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, makeId(name));
    }
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
