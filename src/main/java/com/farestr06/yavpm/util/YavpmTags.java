package com.farestr06.yavpm.util;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTags {
    public static class Items {
        public static final TagKey<Item> CARBONFOWL_FOODS = of("carbonfowl_foods");
        public static final TagKey<Item> TANUKI_FOODS = of("tanuki_foods");

        public static final TagKey<Item> CRIMSON_MOONGUS_FOOD = of("crimson_moongus_food");
        public static final TagKey<Item> WARPED_MOONGUS_FOOD = of("warped_moongus_food");

        public static final TagKey<Item> RUNES = of("runes");
        public static final TagKey<Item> RUNE_ATTACK_APPLICABLE = of("rune_attack_applicable");
        public static final TagKey<Item> RUNE_DURABILITY_APPLICABLE = of("rune_durability_applicable");
        public static final TagKey<Item> RUNE_SPEED_APPLICABLE = of("rune_speed_applicable");

        public static final TagKey<Item> ENCHANTABLE_GLIDER = of("enchantable/glider");
        public static final TagKey<Item> ENCHANTABLE_WOLF_ARMOR = of("enchantable/wolf_armor");
        public static final TagKey<Item> ENCHANTABLE_HORSE_ARMOR = of("enchantable/horse_armor");

        public static final TagKey<Item> REACTOR_RECHARGERS = of("reactor_rechargers");

        public static final TagKey<Item> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Item> PERSIMMON_LOGS = of("persimmon_logs");
        public static final TagKey<Item> PRICKLE_LOGS = of("spiral_stalks");

        private static TagKey<Item> of(String path) {
            return TagKey.of(RegistryKeys.ITEM, makeId(path));
        }
    }
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> HUMANOID_ZOMBIES = of("humanoid_zombies");
        public static final TagKey<EntityType<?>> HUMANOID_SKELETONS = of("humanoid_skeletons");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ILLAGERS_BANE = of("sensitive_to_illagers_bane");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_25 = of("sensitive_to_enderbane_25");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_50 = of("sensitive_to_enderbane_50");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_75 = of("sensitive_to_enderbane_75");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_100 = of("sensitive_to_enderbane_100");

        private static TagKey<EntityType<?>> of(String path) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, makeId(path));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_CRIMSON_MOONGUS = of("spawns_crimson_moongus");
        public static final TagKey<Biome> SPAWNS_WARPED_MOONGUS = of("spawns_warped_moongus");

        public static final TagKey<Biome> FAKE_LOG_IS_SPRUCE = of("fake_log_is_spruce");
        public static final TagKey<Biome> FAKE_LOG_IS_BIRCH = of("fake_log_is_birch");
        public static final TagKey<Biome> FAKE_LOG_IS_JUNGLE = of("fake_log_is_jungle");
        public static final TagKey<Biome> FAKE_LOG_IS_ACACIA = of("fake_log_is_acacia");
        public static final TagKey<Biome> FAKE_LOG_IS_CHERRY = of("fake_log_is_cherry");
        public static final TagKey<Biome> FAKE_LOG_IS_DARK_OAK = of("fake_log_is_dark_oak");
        public static final TagKey<Biome> FAKE_LOG_IS_MANGROVE = of("fake_log_is_mangrove");

        private static TagKey<Biome> of(String path) {
            return TagKey.of(RegistryKeys.BIOME, makeId(path));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Block> PERSIMMON_LOGS = of("persimmon_logs");
        public static final TagKey<Block> PRICKLE_LOGS = of("prickle_logs");

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
    public static class Enchantments {
        public static final TagKey<Enchantment> END_ENCHANTMENTS = of("end_enchantments");
        public static final TagKey<Enchantment> EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE = of("exclusive_set/wolf_armor/offense");
        public static final TagKey<Enchantment> EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE = of("exclusive_set/wolf_armor/defense");

        private static TagKey<Enchantment> of(String path) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, makeId(path));
        }
    }
}
