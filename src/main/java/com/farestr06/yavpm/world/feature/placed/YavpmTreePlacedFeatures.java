package com.farestr06.yavpm.world.feature.placed;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.world.feature.configured.YavpmTreeConfiguredFeatures;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTreePlacedFeatures {

    public static final RegistryKey<PlacedFeature> APPLE_PLACED = registerKey("apple_placed");
    public static final RegistryKey<PlacedFeature> APPLE_BEES_0002_PLACED = registerKey("apple_bees_0002_placed");
    public static final RegistryKey<PlacedFeature> APPLE_BEES_002_PLACED = registerKey("apple_bees_002_placed");
    public static final RegistryKey<PlacedFeature> APPLE_BEES_005_PLACED = registerKey("apple_bees_005_placed");
    public static final RegistryKey<PlacedFeature> FANCY_APPLE_PLACED = registerKey("fancy_apple_placed");
    public static final RegistryKey<PlacedFeature> FANCY_APPLE_BEES_0002_PLACED = registerKey("fancy_apple_bees_0002_placed");
    public static final RegistryKey<PlacedFeature> FANCY_APPLE_BEES_002_PLACED = registerKey("fancy_apple_bees_002_placed");
    public static final RegistryKey<PlacedFeature> FANCY_APPLE_BEES_005_PLACED = registerKey("fancy_apple_bees_005_placed");

    public static final RegistryKey<PlacedFeature> FANCY_PERSIMMON_PLACED = registerKey("fancy_persimmon_placed");
    public static final RegistryKey<PlacedFeature> PERSIMMON_PLACED = registerKey("persimmon_placed");

    public static final RegistryKey<PlacedFeature> PRICKLE_PLACED = registerKey("prickle_placed");
    public static final RegistryKey<PlacedFeature> FANCY_PRICKLE_PLACED = registerKey("fancy_prickle_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, APPLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_0002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_0002),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_002),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_005_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_005),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_0002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_0002),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_002),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_005_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_005),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.APPLE_SAPLING)));

        register(context, PRICKLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.PRICKLE),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.PRICKLE_SHOOT))
        );
        register(context, FANCY_PRICKLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_PRICKLE),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.PRICKLE_SHOOT))
        );

        register(context, PERSIMMON_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.PERSIMMON),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.PERSIMMON_SAPLING))
        );
        register(context, FANCY_PERSIMMON_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_PERSIMMON),
                List.of(PlacedFeatures.wouldSurvive(YavpmBlocks.PERSIMMON_SAPLING))
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
