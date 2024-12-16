package com.farestr06.yavpm.world.gen;

import com.farestr06.yavpm.entity.YavpmEntities;
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
                BiomeSelectors.includeByKey(
                        BiomeKeys.WINDSWEPT_HILLS,
                        BiomeKeys.WINDSWEPT_FOREST,
                        BiomeKeys.JAGGED_PEAKS,
                        BiomeKeys.GROVE,
                        BiomeKeys.CHERRY_GROVE
                ),
                SpawnGroup.CREATURE,
                YavpmEntities.TANUKI,
                8,
                2,
                4
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_CAVE),
                SpawnGroup.MONSTER,
                YavpmEntities.CARBONFOWL,
                18,
                1,
                3
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_NETHER_FOREST),
                SpawnGroup.CREATURE,
                YavpmEntities.MOONGUS,
                30,
                4,
                4
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_END),
                SpawnGroup.MONSTER,
                YavpmEntities.VOID_PHANTOM,
                8,
                1,
                1
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
