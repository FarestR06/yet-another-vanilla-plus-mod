package com.farestr06.yavpm.world.gen;

import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.world.YavpmPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class YavpmWorldGeneration {
    public static void generateModWorldGen() {
        generateTrees();
        modfiyMobSpawns();
    }

    private static void modfiyMobSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                SpawnGroup.MONSTER,
                YavpmMobs.CARBONFOWL,
                18,
                1,
                3
        );
    }

    private static void generateTrees() {
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
