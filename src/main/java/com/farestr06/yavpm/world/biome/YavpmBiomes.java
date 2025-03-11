package com.farestr06.yavpm.world.biome;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.world.feature.placed.YavpmMiscPlacedFeatures;
import com.farestr06.yavpm.world.feature.placed.YavpmVegetationPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.EndPlacedFeatures;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBiomes {

    public static void bootstrap(Registerable<Biome> context) {
        Overworld.bootstrapOverworld(context);
        End.bootstrapEnd(context);
    }

    private static RegistryKey<Biome> of(String id) {
        return RegistryKey.of(RegistryKeys.BIOME, makeId(id));
    }

    public static class Overworld {
        public static final RegistryKey<Biome> ORCHARD_PEAKS = of("orchard_peaks");
        public static final RegistryKey<Biome> WITHERED_SCAR = of("withered_scar");
        public static final RegistryKey<Biome> EBONY_FOREST = of("ebony_forest");


        protected static void bootstrapOverworld(Registerable<Biome> context) {
            context.register(ORCHARD_PEAKS, makeOrchardGrove(context));
            context.register(WITHERED_SCAR, makeWitheredScar(context));
            context.register(EBONY_FOREST, makeEbonyForest(context));
        }

        public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
            DefaultBiomeFeatures.addLandCarvers(builder);
            DefaultBiomeFeatures.addAmethystGeodes(builder);
            DefaultBiomeFeatures.addDungeons(builder);
            DefaultBiomeFeatures.addMineables(builder);
            DefaultBiomeFeatures.addSprings(builder);
            DefaultBiomeFeatures.addFrozenTopLayer(builder);
        }

        private static Biome makeOrchardGrove(Registerable<Biome> context) {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

            DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
            DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

            GenerationSettings.LookupBackedBuilder biomeBuilder =
                    new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                            context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
            DefaultBiomeFeatures.addEmeraldOre(biomeBuilder);

            DefaultBiomeFeatures.addInfestedStone(biomeBuilder);

            biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.APPLE_ORCHARD_GROVE_VEGETAION_PLACED);
            DefaultBiomeFeatures.addDefaultFlowers(biomeBuilder);
            DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
            DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
            DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

            return new Biome.Builder()
                    .precipitation(true)
                    .downfall(0.2f)
                    .temperature(0.3f)
                    .generationSettings(biomeBuilder.build())
                    .spawnSettings(spawnBuilder.build())
                    .effects((new BiomeEffects.Builder())
                            .skyColor(8233727)
                            .fogColor(12638463)
                            .waterColor(4159204)
                            .waterFogColor(329011)
                            .moodSound(BiomeMoodSound.CAVE)
                            .music(MusicType.GAME).build())
                    .build();
        }

        private static Biome makeEbonyForest(Registerable<Biome> context) {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

            DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
            DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);


            GenerationSettings.LookupBackedBuilder biomeBuilder =
                    new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                            context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

            biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.PERSIMMON_VEGETAION_PLACED);
            DefaultBiomeFeatures.addDefaultFlowers(biomeBuilder);
            DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
            DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
            DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

            return new Biome.Builder()
                    .precipitation(true)
                    .downfall(0.8f)
                    .temperature(0.7f)
                    .generationSettings(biomeBuilder.build())
                    .spawnSettings(spawnBuilder.build())
                    .effects(
                            new BiomeEffects.Builder()
                                    .waterColor(4159204)
                                    .waterFogColor(329011)
                                    .fogColor(12638463)
                                    .skyColor(OverworldBiomeCreator.getSkyColor(0.7f))
                                    .grassColorModifier(BiomeEffects.GrassColorModifier.DARK_FOREST)
                                    .moodSound(BiomeMoodSound.CAVE)
                                    .music(MusicType.GAME)
                                    .build()
                    ).build();
        }

        private static Biome makeWitheredScar(Registerable<Biome> context) {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

            addWitheredScarFarmAnimals(spawnBuilder);
            addWitheredScarMonsters(spawnBuilder);

            GenerationSettings.LookupBackedBuilder biomeBuilder =
                    new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                            context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

            globalOverworldGeneration(biomeBuilder);

            DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
            DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

            biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, YavpmMiscPlacedFeatures.PATCH_WITHER_ROSE_PLACED);
            DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);


            return new Biome.Builder()
                    .precipitation(false)
                    .temperature(2.0F)
                    .downfall(0.0F)
                    .effects(
                            new BiomeEffects.Builder()
                                    .waterColor(4159204)
                                    .waterFogColor(329011)
                                    .fogColor(3344392)
                                    .skyColor(OverworldBiomeCreator.getSkyColor(2.0F))
                                    .grassColor(0x2A3114)
                                    .foliageColor(0x999999)
                                    .loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                                    .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0))
                                    .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                    .music(MusicType.createIngameMusic(YavpmSounds.MUSIC_OVERWORLD_WITHERED_SCAR))
                                    .build()
                    )
                    .spawnSettings(spawnBuilder.build())
                    .generationSettings(biomeBuilder.build())
                    .build();
        }

        private static void addWitheredScarMonsters(SpawnSettings.Builder builder) {
            DefaultBiomeFeatures.addCaveMobs(builder);
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 75, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 75, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 25, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 75, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        }

        private static void addWitheredScarFarmAnimals(SpawnSettings.Builder builder) {
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 6, 1, 1));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 5, 1, 1));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 6, 1, 1));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 4, 1, 1));
        }
    }

    public static class End {
        public static final RegistryKey<Biome> END_OASIS = of("end_oasis");

        public static void bootstrapEnd(Registerable<Biome> context) {
            context.register(END_OASIS, createEndOasis(context));
        }

        private static Biome createEndBiome(GenerationSettings.LookupBackedBuilder builder) {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
            DefaultBiomeFeatures.addEndMobs(spawnBuilder);
            return new Biome.Builder()
                    .precipitation(false)
                    .temperature(0.5F)
                    .downfall(0.5F)
                    .effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).moodSound(BiomeMoodSound.CAVE).build())
                    .spawnSettings(spawnBuilder.build())
                    .generationSettings(builder.build())
                    .build();
        }

        private static Biome createEndOasis(Registerable<Biome> context) {
            GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(
                    context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                    context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            );
            lookupBackedBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, EndPlacedFeatures.END_GATEWAY_RETURN);
            lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, YavpmVegetationPlacedFeatures.PRICKLE_VEGETAION_PLACED);
            lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, YavpmMiscPlacedFeatures.LAKE_VOID_WATER_PLACED);
            return createEndBiome(lookupBackedBuilder);
        }
    }
}
