package com.farestr06.yavpm.world.gen;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.world.feature.placed.YavpmMiscPlacedFeatures;
import com.farestr06.yavpm.world.feature.placed.YavpmVegetationPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class YavpmWorldGeneration {
    public static void generateModWorldGen() {
        YetAnotherVanillaPlusMod.LOGGER.info("Modifying worldgen for YAVPM!");
        generateOres();
        generateTrees();
        modfiyMobSpawns();
    }

    private static void generateOres() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Placing ores...");
        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_HOT_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                YavpmMiscPlacedFeatures.ORE_KIMBERLITE_LOWER
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_HOT_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                YavpmMiscPlacedFeatures.ORE_KIMBERLITE_UPPER
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                YavpmMiscPlacedFeatures.ORE_NAHCOLITE_PLACED
        );
    }

    private static void modfiyMobSpawns() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Spawning mobs...");
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_MOUNTAIN),
                MobCategory.CREATURE,
                YavpmEntities.TANUKI,
                8,
                2,
                4
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_CAVE),
                MobCategory.MONSTER,
                YavpmEntities.CARBONFOWL,
                18,
                1,
                3
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_NETHER_FOREST),
                MobCategory.CREATURE,
                YavpmEntities.MOONGUS,
                15,
                2,
                3
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_END),
                MobCategory.MONSTER,
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
                GenerationStep.Decoration.VEGETAL_DECORATION,
                YavpmVegetationPlacedFeatures.APPLE_VEGETAION_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.END_BARRENS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                YavpmVegetationPlacedFeatures.PRICKLE_VEGETAION_PLACED
        );
    }
}
