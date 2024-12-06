package com.farestr06.yavpm.world;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmPlacedFeatures {

    public static final RegistryKey<PlacedFeature> PRICKLE_PLACED = registerKey("prickle_placed");
    public static final RegistryKey<PlacedFeature> APPLE_PLACED = registerKey("apple_placed");
    public static final RegistryKey<PlacedFeature> APPLE_PLACED_IN_ORCHARD_GROVE = registerKey("apple_placed_in_orchard_grove");
    public static final RegistryKey<PlacedFeature> PATCH_WITHER_ROSE_PLACED = registerKey("patch_wither_rose_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);


        register(
                context,
                PATCH_WITHER_ROSE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmConfiguredFeatures.PATCH_WITHER_ROSE),
                List.of(
                        NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                        BiomePlacementModifier.of()
                )
        );

        register(context, PRICKLE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(YavpmConfiguredFeatures.PRICKLE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1),
                        YavpmBlocks.PRICKLE_SHOOT
                )
        );

        register(
                context,
                APPLE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmConfiguredFeatures.APPLE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1),
                        YavpmBlocks.APPLE_SAPLING
                )
        );
        register(
                context,
                APPLE_PLACED_IN_ORCHARD_GROVE,
                configuredFeatureRegistryEntryLookup.getOrThrow(YavpmConfiguredFeatures.APPLE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(3, 0.1f, 1),
                        YavpmBlocks.APPLE_SAPLING
                )
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
