package com.farestr06.yavpm.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOCATIONS;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTags {
    public static class Items {
        public static final TagKey<Item> CARBONFOWL_FOODS = LOCATIONS.itemTag("carbonfowl_foods");
        public static final TagKey<Item> TANUKI_FOOD = LOCATIONS.itemTag("tanuki_foods");

        public static final TagKey<Item> CRIMSON_MOONGUS_FOOD = LOCATIONS.itemTag("crimson_moongus_food");
        public static final TagKey<Item> CRIMSON_MOONGUS_FOOD_CORRUPTED = LOCATIONS.itemTag("crimson_moongus_food_corrupted");
        public static final TagKey<Item> WARPED_MOONGUS_FOOD = LOCATIONS.itemTag("warped_moongus_food");

        public static final TagKey<Item> ENCHANTABLE_GLIDER = LOCATIONS.itemTag("enchantable/glider");
        public static final TagKey<Item> ENCHANTABLE_WOLF_ARMOR = LOCATIONS.itemTag("enchantable/wolf_armor");
        public static final TagKey<Item> ENCHANTABLE_HORSE_ARMOR = LOCATIONS.itemTag("enchantable/horse_armor");

        public static final TagKey<Item> REPAIRS_STUDDED_ARMOR = LOCATIONS.itemTag("repairs_studded_armor");
        public static final TagKey<Item> REPAIRS_DENSITITE_ARMOR = LOCATIONS.itemTag("repairs_densitite_armor");
        public static final TagKey<Item> DENSITITE_TOOL_MATERIALS = LOCATIONS.itemTag("densitite_tool_materials");

        public static final TagKey<Item> REACTOR_RECHARGERS = LOCATIONS.itemTag("reactor_rechargers");

        public static final TagKey<Item> APPLE_LOGS = LOCATIONS.itemTag("apple_logs");
        public static final TagKey<Item> PERSIMMON_LOGS = LOCATIONS.itemTag("persimmon_logs");
        public static final TagKey<Item> PRICKLE_LOGS = LOCATIONS.itemTag("spiral_stalks");
    }
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ILLAGERS_BANE = of("sensitive_to_illagers_bane");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_25 = of("sensitive_to_enderbane_25");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_50 = of("sensitive_to_enderbane_50");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_75 = of("sensitive_to_enderbane_75");
        public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDERBANE_100 = of("sensitive_to_enderbane_100");

        private static TagKey<EntityType<?>> of(String path) {
            return TagKey.create(Registries.ENTITY_TYPE, makeId(path));
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
            return TagKey.create(Registries.BIOME, makeId(path));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> APPLE_LOGS = of("apple_logs");
        public static final TagKey<Block> PERSIMMON_LOGS = of("persimmon_logs");
        public static final TagKey<Block> PRICKLE_LOGS = of("prickle_logs");
        public static final TagKey<Block> RICE_GROWABLE_ON = of("rice_growable_on");

        private static TagKey<Block> of(String path) {
            return TagKey.create(Registries.BLOCK, makeId(path));
        }
    }
    public static class Fluids {
        public static final TagKey<Fluid> VOID_WATER = LOCATIONS.fluidTag("void_water"); // of("void_water");

    }
    public static class Enchantments {
        public static final TagKey<Enchantment> END_ENCHANTMENTS = of("end_enchantments");
        public static final TagKey<Enchantment> EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE = of("exclusive_set/wolf_armor/offense");
        public static final TagKey<Enchantment> EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE = of("exclusive_set/wolf_armor/defense");

        private static TagKey<Enchantment> of(String path) {
            return TagKey.create(Registries.ENCHANTMENT, makeId(path));
        }
    }
}
