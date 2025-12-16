package com.farestr06.yavpm.world.biome;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.world.feature.placed.YavpmMiscPlacedFeatures;
import com.farestr06.yavpm.world.feature.placed.YavpmVegetationPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBiomes {

    public static void bootstrap(BootstrapContext<Biome> context) {
        Overworld.bootstrapOverworld(context);
        End.bootstrapEnd(context);
    }

    private static ResourceKey<Biome> of(String id) {
        return ResourceKey.create(Registries.BIOME, makeId(id));
    }

    public static class Overworld {
        public static final ResourceKey<Biome> ORCHARD_PEAKS = of("orchard_peaks");
        public static final ResourceKey<Biome> WITHERED_SCAR = of("withered_scar");
        public static final ResourceKey<Biome> EBONY_FOREST = of("ebony_forest");


        protected static void bootstrapOverworld(BootstrapContext<Biome> context) {
            context.register(ORCHARD_PEAKS, makeOrchardGrove(context));
            context.register(WITHERED_SCAR, makeWitheredScar(context));
            context.register(EBONY_FOREST, makeEbonyForest(context));
        }

        public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
            BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
            BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
            BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
            BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
            BiomeDefaultFeatures.addDefaultSprings(builder);
            BiomeDefaultFeatures.addSurfaceFreezing(builder);
        }

        private static Biome makeOrchardGrove(BootstrapContext<Biome> context) {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

            BiomeDefaultFeatures.farmAnimals(spawnBuilder);
            BiomeDefaultFeatures.commonSpawns(spawnBuilder);

            BiomeGenerationSettings.Builder biomeBuilder =
                    new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                            context.lookup(Registries.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
            BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);

            BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.APPLE_ORCHARD_GROVE_VEGETAION_PLACED);
            BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
            BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);
            BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
            BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);

            return new Biome.BiomeBuilder()
                    .hasPrecipitation(true)
                    .downfall(0.2f)
                    .temperature(0.3f)
                    .generationSettings(biomeBuilder.build())
                    .mobSpawnSettings(spawnBuilder.build())
                    .specialEffects((new BiomeSpecialEffects.Builder())
                            .skyColor(8233727)
                            .fogColor(12638463)
                            .waterColor(4159204)
                            .waterFogColor(329011)
                            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                            .backgroundMusic(Musics.GAME).build())
                    .build();
        }

        private static Biome makeEbonyForest(BootstrapContext<Biome> context) {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

            BiomeDefaultFeatures.farmAnimals(spawnBuilder);
            BiomeDefaultFeatures.commonSpawns(spawnBuilder);


            BiomeGenerationSettings.Builder biomeBuilder =
                    new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                            context.lookup(Registries.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.PERSIMMON_VEGETAION_PLACED);
            BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
            BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);
            BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
            BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);

            return new Biome.BiomeBuilder()
                    .hasPrecipitation(true)
                    .downfall(0.8f)
                    .temperature(0.7f)
                    .generationSettings(biomeBuilder.build())
                    .mobSpawnSettings(spawnBuilder.build())
                    .specialEffects(
                            new BiomeSpecialEffects.Builder()
                                    .waterColor(4159204)
                                    .waterFogColor(329011)
                                    .fogColor(12638463)
                                    .skyColor(OverworldBiomes.calculateSkyColor(0.7f))
                                    .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                    .backgroundMusic(Musics.GAME)
                                    .build()
                    ).build();
        }

        private static Biome makeWitheredScar(BootstrapContext<Biome> context) {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

            addWitheredScarFarmAnimals(spawnBuilder);
            addWitheredScarMonsters(spawnBuilder);

            BiomeGenerationSettings.Builder biomeBuilder =
                    new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                            context.lookup(Registries.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
            BiomeDefaultFeatures.addExtraGold(biomeBuilder);

            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, YavpmMiscPlacedFeatures.PATCH_WITHER_ROSE_PLACED);
            BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);


            return new Biome.BiomeBuilder()
                    .hasPrecipitation(false)
                    .temperature(2.0F)
                    .downfall(0.0F)
                    .specialEffects(
                            new BiomeSpecialEffects.Builder()
                                    .waterColor(4159204)
                                    .waterFogColor(329011)
                                    .fogColor(3344392)
                                    .skyColor(OverworldBiomes.calculateSkyColor(2.0F))
                                    .grassColorOverride(0x2A3114)
                                    .foliageColorOverride(0x999999)
                                    .ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                                    .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0))
                                    .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                    .backgroundMusic(Musics.createGameMusic(YavpmSounds.MUSIC_OVERWORLD_WITHERED_SCAR))
                                    .build()
                    )
                    .mobSpawnSettings(spawnBuilder.build())
                    .generationSettings(biomeBuilder.build())
                    .build();
        }

        private static void addWitheredScarMonsters(MobSpawnSettings.Builder builder) {
            BiomeDefaultFeatures.caveSpawns(builder);
            builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 70, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));
            builder.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4));
            builder.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1));
            }

        private static void addWitheredScarFarmAnimals(MobSpawnSettings.Builder builder) {
            builder.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 1));
            builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 1));
            builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 1));
            builder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 1));
        }
    }

    public static class End {
        public static final ResourceKey<Biome> END_OASIS = of("end_oasis");

        public static void bootstrapEnd(BootstrapContext<Biome> context) {
            context.register(END_OASIS, createEndOasis(context));
        }

        private static Biome createEndBiome(BiomeGenerationSettings.Builder builder) {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
            BiomeDefaultFeatures.endSpawns(spawnBuilder);
            return new Biome.BiomeBuilder()
                    .hasPrecipitation(false)
                    .temperature(0.5F)
                    .downfall(0.5F)
                    .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                    .mobSpawnSettings(spawnBuilder.build())
                    .generationSettings(builder.build())
                    .build();
        }

        private static Biome createEndOasis(BootstrapContext<Biome> context) {
            BiomeGenerationSettings.Builder lookupBackedBuilder = new BiomeGenerationSettings.Builder(
                    context.lookup(Registries.PLACED_FEATURE),
                    context.lookup(Registries.CONFIGURED_CARVER)
            );
            lookupBackedBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
            lookupBackedBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.PRICKLE_VEGETAION_PLACED);
            lookupBackedBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, YavpmMiscPlacedFeatures.LAKE_VOID_WATER_PLACED);
            return createEndBiome(lookupBackedBuilder);
        }
    }
}
