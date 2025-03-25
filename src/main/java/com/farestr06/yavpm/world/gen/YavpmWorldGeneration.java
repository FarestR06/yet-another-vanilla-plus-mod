package com.farestr06.yavpm.world.gen;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.world.feature.placed.YavpmVegetationPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class YavpmWorldGeneration {
    public static void generateModWorldGen() {
        YetAnotherVanillaPlusMod.LOGGER.info("Modifying worldgen for YAVPM!");
        generateTrees();
        modfiyMobSpawns();
    }

    private static void modfiyMobSpawns() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Spawning mobs...");
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
                15,
                2,
                3
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_END),
                SpawnGroup.MONSTER,
                YavpmEntities.VOID_PHANTOM,
                1,
                1,
                1
        );
    }

    private static void generateTrees() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Growing trees...");
        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                YavpmVegetationPlacedFeatures.APPLE_VEGETAION_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.END_BARRENS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                YavpmVegetationPlacedFeatures.PRICKLE_VEGETAION_PLACED
        );
    }
}
