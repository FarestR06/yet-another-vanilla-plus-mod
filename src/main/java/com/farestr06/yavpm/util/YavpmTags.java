package com.farestr06.yavpm.util;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTags {
    public static class Items {
        public static final TagKey<Item> REACTOR_RECHARGERS = of("reactor_rechargers");
        public static final TagKey<Item> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Item> CRIMSON_MOONGUS_FOOD = of("crimson_moongus_food");
        public static final TagKey<Item> WARPED_MOONGUS_FOOD = of("warped_moongus_food");

        private static TagKey<Item> of(String path) {
            return TagKey.of(RegistryKeys.ITEM, makeId(path));
        }
    }
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> HUMANOID_ZOMBIES = of("humanoid_zombies");
        public static final TagKey<EntityType<?>> HUMANOID_SKELETONS = of("humanoid_skeletons");

        private static TagKey<EntityType<?>> of(String path) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, makeId(path));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_CRIMSON_MOONGUS = of("spawns_crimson_moongus");
        public static final TagKey<Biome> SPAWNS_WARPED_MOONGUS = of("spawns_warped_moongus");

        private static TagKey<Biome> of(String path) {
            return TagKey.of(RegistryKeys.BIOME, makeId(path));
        }
    }
}
