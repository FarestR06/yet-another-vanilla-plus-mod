package com.farestr06.yavpm.world.feature.configured;

import com.farestr06.yavpm.world.feature.placed.YavpmTreePlacedFeatures;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.*;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmVegetationConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_VEGETAION = registerKey("apple_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_ORCHARD_GROVE_VEGETAION = registerKey("apple_orchard_grove_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PERSIMMON_VEGETAION = registerKey("persimmon_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PRICKLE_VEGETATION = registerKey("prickle_vegetation");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        register(
                context,
                APPLE_VEGETAION,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        List.of(
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.FANCY_APPLE_PLACED), 0.2f),
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.APPLE_BEES_0002_PLACED), 0.2f),
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.FANCY_APPLE_BEES_0002_PLACED), 0.1f)
                        ),
                        placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.APPLE_PLACED)
                )
        );

        register(
                context,
                APPLE_ORCHARD_GROVE_VEGETAION,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        List.of(
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.APPLE_BEES_002_PLACED), 0.2f),
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.FANCY_APPLE_BEES_002_PLACED), 0.1f)
                        ),
                        placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.APPLE_PLACED)
                )
        );

        register(
                context,
                PERSIMMON_VEGETAION,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        List.of(
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.FANCY_PERSIMMON_PLACED), 0.2f)
                        ),
                        placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.PERSIMMON_PLACED))
        );

        register(
                context,
                PRICKLE_VEGETATION,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        List.of(
                                new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.FANCY_PRICKLE_PLACED), 0.3f)
                        ),
                        placedFeatureRegistryEntryLookup.getOrThrow(YavpmTreePlacedFeatures.PRICKLE_PLACED)
                )
        );
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, makeId(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
