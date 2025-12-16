package com.farestr06.yavpm.world.feature.placed;

import com.farestr06.yavpm.world.feature.configured.YavpmMiscConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmMiscPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PATCH_WITHER_ROSE_PLACED = registerKey("patch_wither_rose_placed");
    public static final ResourceKey<PlacedFeature> ORE_NAHCOLITE_PLACED = registerKey("ore_nahcolite_placed");
    public static final ResourceKey<PlacedFeature> ORE_KIMBERLITE_UPPER = registerKey("ore_kimberlite_upper");
    public static final ResourceKey<PlacedFeature> ORE_KIMBERLITE_LOWER = registerKey("ore_kimberlite_lower");
    public static final ResourceKey<PlacedFeature> LAKE_VOID_WATER_PLACED = registerKey("lake_void_water_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> kimberlite = configuredFeatureRegistryEntryLookup.getOrThrow(YavpmMiscConfiguredFeatures.ORE_KIMBERLITE);

        Holder<ConfiguredFeature<?, ?>> nahcolite = configuredFeatureRegistryEntryLookup.getOrThrow(YavpmMiscConfiguredFeatures.ORE_NAHCOLITE);

        register(
                context, ORE_KIMBERLITE_UPPER, kimberlite, modifiersWithRarity(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128)))
        );
        register(
                context, ORE_KIMBERLITE_LOWER, kimberlite, modifiersWithCount(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60)))
        );


        PlacementUtils.register(
                context, ORE_NAHCOLITE_PLACED, nahcolite, modifiersWithCount(15, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(52), VerticalAnchor.belowTop(72))
        ));

        register(
                context,
                PATCH_WITHER_ROSE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmMiscConfiguredFeatures.PATCH_WITHER_ROSE),
                List.of(
                        NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );

        register(
                context,
                LAKE_VOID_WATER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmMiscConfiguredFeatures.LAKE_VOID_WATER),
                modifiersWithRarity(111, PlacementUtils.HEIGHTMAP)
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, makeId(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }

}
