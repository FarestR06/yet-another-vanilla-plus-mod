package com.farestr06.yavpm.world.gen;

import com.farestr06.yavpm.world.YavpmPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class YavpmTreeGeneration {
    protected static void generateTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                YavpmPlacedFeatures.APPLE_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.END_BARRENS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                YavpmPlacedFeatures.PRICKLE_PLACED
        );
    }
}
