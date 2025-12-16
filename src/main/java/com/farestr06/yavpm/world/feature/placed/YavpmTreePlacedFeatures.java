package com.farestr06.yavpm.world.feature.placed;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.world.feature.configured.YavpmTreeConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> APPLE_PLACED = registerKey("apple_placed");
    public static final ResourceKey<PlacedFeature> APPLE_BEES_0002_PLACED = registerKey("apple_bees_0002_placed");
    public static final ResourceKey<PlacedFeature> APPLE_BEES_002_PLACED = registerKey("apple_bees_002_placed");
    public static final ResourceKey<PlacedFeature> APPLE_BEES_005_PLACED = registerKey("apple_bees_005_placed");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_PLACED = registerKey("fancy_apple_placed");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_BEES_0002_PLACED = registerKey("fancy_apple_bees_0002_placed");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_BEES_002_PLACED = registerKey("fancy_apple_bees_002_placed");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_BEES_005_PLACED = registerKey("fancy_apple_bees_005_placed");

    public static final ResourceKey<PlacedFeature> FANCY_PERSIMMON_PLACED = registerKey("fancy_persimmon_placed");
    public static final ResourceKey<PlacedFeature> PERSIMMON_PLACED = registerKey("persimmon_placed");

    public static final ResourceKey<PlacedFeature> PRICKLE_PLACED = registerKey("prickle_placed");
    public static final ResourceKey<PlacedFeature> FANCY_PRICKLE_PLACED = registerKey("fancy_prickle_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, APPLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_0002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_0002),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_002),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, APPLE_BEES_005_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.APPLE_BEES_005),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_0002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_0002),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_002_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_002),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));
        register(context, FANCY_APPLE_BEES_005_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_APPLE_BEES_005),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.APPLE_SAPLING)));

        register(context, PRICKLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.PRICKLE),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.PRICKLE_SHOOT))
        );
        register(context, FANCY_PRICKLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_PRICKLE),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.PRICKLE_SHOOT))
        );

        register(context, PERSIMMON_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.PERSIMMON),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.PERSIMMON_SAPLING))
        );
        register(context, FANCY_PERSIMMON_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmTreeConfiguredFeatures.FANCY_PERSIMMON),
                List.of(PlacementUtils.filteredByBlockSurvival(YavpmBlocks.PERSIMMON_SAPLING))
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
