package com.farestr06.yavpm.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTags {
    public static class Items {
        public static final TagKey<Item> RUNES = of("runes");
        public static final TagKey<Item> RUNE_ATTACK_APPLICABLE = of("rune_attack_applicable");
        public static final TagKey<Item> RUNE_DURABILITY_APPLICABLE = of("rune_durability_applicable");
        public static final TagKey<Item> RUNE_SPEED_APPLICABLE = of("rune_speed_applicable");
        public static final TagKey<Item> RUNE_TOUGHNESS_APPLICABLE = of("rune_toughness_applicable");
        public static final TagKey<Item> REACTOR_RECHARGERS = of("reactor_rechargers");
        public static final TagKey<Item> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Item> PRICKLE_LOGS = of("spiral_stalks");
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

    public static class Blocks {

        public static final TagKey<Block> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Block> PRICKLE_LOGS = of("spiral_stalks");

        private static TagKey<Block> of(String path) {
            return TagKey.of(RegistryKeys.BLOCK, makeId(path));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> VOID_WATER = of("void_water");

        private static TagKey<Fluid> of(String path) {
            return TagKey.of(RegistryKeys.FLUID, makeId(path));
        }
    }
}
