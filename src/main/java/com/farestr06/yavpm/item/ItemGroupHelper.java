package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class ItemGroupHelper {
    public static void modifyEntries() {
        YetAnotherVanillaPlusMod.LOGGER.info("Modifying item groups for YAVPM!");

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Building Blocks item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ItemGroupHelper::buildingBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Natural item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ItemGroupHelper::naturalBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Functional item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ItemGroupHelper::functionalBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Redstone item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ItemGroupHelper::redstone);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Tools item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ItemGroupHelper::tools);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Combat item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ItemGroupHelper::combat);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Food and Drink item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ItemGroupHelper::foodAndDrink);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Ingredients item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemGroupHelper::ingredients);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Spawn Eggs item group...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ItemGroupHelper::spawnEggs);
    }

    private static void buildingBlocks(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.APPLE_LOG);
        entries.add(YavpmBlocks.APPLE_WOOD);
        entries.add(YavpmBlocks.STRIPPED_APPLE_LOG);
        entries.add(YavpmBlocks.STRIPPED_APPLE_WOOD);
        entries.add(YavpmBlocks.APPLE_PLANKS);
        entries.add(YavpmBlocks.APPLE_STAIRS);
        entries.add(YavpmBlocks.APPLE_SLAB);
        entries.add(YavpmBlocks.APPLE_FENCE);
        entries.add(YavpmBlocks.APPLE_FENCE_GATE);
        entries.add(YavpmBlocks.APPLE_DOOR);
        entries.add(YavpmBlocks.APPLE_TRAPDOOR);
        entries.add(YavpmBlocks.APPLE_PRESSURE_PLATE);
        entries.add(YavpmBlocks.APPLE_BUTTON);

        entries.add(YavpmBlocks.PERSIMMON_LOG);
        entries.add(YavpmBlocks.PERSIMMON_WOOD);
        entries.add(YavpmBlocks.STRIPPED_PERSIMMON_LOG);
        entries.add(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);
        entries.add(YavpmBlocks.PERSIMMON_PLANKS);
        entries.add(YavpmBlocks.PERSIMMON_STAIRS);
        entries.add(YavpmBlocks.PERSIMMON_SLAB);
        entries.add(YavpmBlocks.PERSIMMON_FENCE);
        entries.add(YavpmBlocks.PERSIMMON_FENCE_GATE);
        entries.add(YavpmBlocks.PERSIMMON_DOOR);
        entries.add(YavpmBlocks.PERSIMMON_TRAPDOOR);
        entries.add(YavpmBlocks.PERSIMMON_PRESSURE_PLATE);
        entries.add(YavpmBlocks.PERSIMMON_BUTTON);

        entries.add(YavpmBlocks.PRICKLE_LOG);
        entries.add(YavpmBlocks.PRICKLE_WOOD);
        entries.add(YavpmBlocks.STRIPPED_PRICKLE_LOG);
        entries.add(YavpmBlocks.STRIPPED_PRICKLE_WOOD);
        entries.add(YavpmBlocks.PRICKLE_PLANKS);
        entries.add(YavpmBlocks.PRICKLE_STAIRS);
        entries.add(YavpmBlocks.PRICKLE_SLAB);
        entries.add(YavpmBlocks.PRICKLE_FENCE);
        entries.add(YavpmBlocks.PRICKLE_FENCE_GATE);
        entries.add(YavpmBlocks.PRICKLE_DOOR);
        entries.add(YavpmBlocks.PRICKLE_TRAPDOOR);
        entries.add(YavpmBlocks.PRICKLE_PRESSURE_PLATE);
        entries.add(YavpmBlocks.PRICKLE_BUTTON);

        entries.add(YavpmBlocks.COBBLED_GRANITE);
        entries.add(YavpmBlocks.COBBLED_GRANITE_STAIRS);
        entries.add(YavpmBlocks.COBBLED_GRANITE_SLAB);
        entries.add(YavpmBlocks.COBBLED_GRANITE_WALL);
        entries.add(YavpmBlocks.POLISHED_GRANITE_BRICKS);
        entries.add(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
        entries.add(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
        entries.add(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);

        entries.add(YavpmBlocks.COBBLED_DIORITE);
        entries.add(YavpmBlocks.COBBLED_DIORITE_STAIRS);
        entries.add(YavpmBlocks.COBBLED_DIORITE_SLAB);
        entries.add(YavpmBlocks.COBBLED_DIORITE_WALL);
        entries.add(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        entries.add(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        entries.add(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        entries.add(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);

        entries.add(YavpmBlocks.COBBLED_ANDESITE);
        entries.add(YavpmBlocks.COBBLED_ANDESITE_STAIRS);
        entries.add(YavpmBlocks.COBBLED_ANDESITE_SLAB);
        entries.add(YavpmBlocks.COBBLED_ANDESITE_WALL);
        entries.add(YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        entries.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);
        entries.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);
        entries.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);

        entries.add(YavpmBlocks.KIMBERLITE);
        entries.add(YavpmBlocks.KIMBERLITE_STAIRS);
        entries.add(YavpmBlocks.KIMBERLITE_SLAB);
        entries.add(YavpmBlocks.KIMBERLITE_WALL);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_SLAB);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_WALL);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB);
        entries.add(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);

        entries.add(YavpmBlocks.GRAPHITE_BLOCK);
        entries.add(YavpmBlocks.GRAPHENE_BLOCK);
    }

    private static void naturalBlocks(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.GLOWING_OBSIDIAN);
        entries.add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);
        entries.add(YavpmBlocks.KIMBERLITE);
        entries.add(YavpmBlocks.APPLE_LOG);
        entries.add(YavpmBlocks.PERSIMMON_LOG);
        entries.add(YavpmBlocks.PRICKLE_LOG);
        entries.add(YavpmBlocks.APPLE_LEAVES);
        entries.add(YavpmBlocks.APPLE_SAPLING);
        entries.add(YavpmBlocks.PERSIMMON_LEAVES);
        entries.add(YavpmBlocks.PRICKLE_SHOOT);
        entries.add(YavpmItems.ACORN);
        entries.add(YavpmItems.BANANA_SEEDS);
        entries.add(YavpmItems.RICE_SEEDS);
        entries.add(YavpmItems.PEANUT);
        entries.add(YavpmItems.MAGIC_BEAN);
        entries.add(YavpmItems.WARPED_WART);
    }

    private static void functionalBlocks(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.GLOWING_OBSIDIAN);
        entries.add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);
    }

    private static void redstone(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.POLARIZED_GLASS);
        entries.add(YavpmBlocks.KEYLOCK);
    }

    private static void tools(FabricItemGroupEntries entries) {
        entries.add(YavpmItems.VOID_WATER_BUCKET);
        entries.add(YavpmItems.FAKE_MILK_BUCKET);
        entries.add(YavpmItems.FORTUNE_COOKIE);
        entries.add(YavpmItems.REACTOR);
        entries.add(YavpmItems.BABY_KEY);
        entries.add(YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT);
        entries.add(YavpmItems.MUSIC_DISC_HALLAND_DALARNA);
    }

    private static void combat(FabricItemGroupEntries entries) {
        entries.add(YavpmItems.GAUNTLET);
        entries.add(YavpmItems.STUDDED_HELMET);
        entries.add(YavpmItems.STUDDED_CHESTPLATE);
        entries.add(YavpmItems.STUDDED_LEGGINGS);
        entries.add(YavpmItems.STUDDED_BOOTS);
    }

    private static void foodAndDrink(FabricItemGroupEntries entries) {
        entries.add(YavpmItems.ACORN);
        entries.add(YavpmItems.DIAMOND_ACORN);
        entries.add(YavpmItems.PERSIMMON);
        entries.add(YavpmItems.GOLDEN_PERSIMMON);
        entries.add(YavpmItems.BANANA);
        entries.add(YavpmItems.MAGIC_BEAN);
        entries.add(YavpmItems.TRUFFLE);
        entries.add(YavpmItems.PEANUT);
        entries.add(YavpmItems.COOKED_PEANUT);
        entries.add(YavpmItems.COOKED_EGG);
        entries.add(YavpmItems.FRIED_BANANA);
        entries.add(YavpmItems.FRIED_COD);
        entries.add(YavpmItems.FAKE_BEEF);
        entries.add(YavpmItems.COOKED_FAKE_BEEF);
        entries.add(YavpmItems.BEAN_TOAST);
        entries.add(YavpmItems.RICE_BAR);
        entries.add(YavpmItems.RICE_PASTRY);
        entries.add(YavpmItems.SUSHI);
        entries.add(YavpmItems.SEA_SOUP);
        entries.add(YavpmItems.CHICKEN_SOUP);
        entries.add(YavpmItems.FANCY_MUSHROOM_STEW);
        entries.add(YavpmItems.FAKE_MILK_BUCKET);
        entries.add(YavpmItems.MOLY);
        entries.add(YavpmItems.JELLY);
        entries.add(YavpmItems.SWEET_BERRY_JELLY);
        entries.add(YavpmItems.FORTUNE_COOKIE);
    }

    private static void ingredients(FabricItemGroupEntries entries) {
        entries.add(YavpmItems.CHAINMAIL);
        entries.add(YavpmItems.RAW_DIAMOND);
        entries.add(YavpmItems.GRAPHITE);
        entries.add(YavpmBlocks.GRAPHITE_BLOCK);
        entries.add(YavpmBlocks.GRAPHENE_BLOCK);
        entries.add(YavpmItems.BREADING);
        entries.add(YavpmItems.MAGIC_BEAN);
        entries.add(YavpmItems.RICE);
        entries.add(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT);
        entries.add(YavpmItems.WARPED_WART);
        entries.add(YavpmItems.BITTER_BERRIES);
        entries.add(YavpmItems.GAUNTLET_FRAGMENT);
        entries.add(YavpmItems.PHANTOM_CHORD);
        entries.add(YavpmItems.RUNE_ATTACK);
        entries.add(YavpmItems.RUNE_DURABILITY);
        entries.add(YavpmItems.RUNE_SPEED);
    }

    private static void spawnEggs(FabricItemGroupEntries entries) {
        entries.add(YavpmItems.CARBONFOWL_SPAWN_EGG);
        entries.add(YavpmItems.MOONGUS_SPAWN_EGG);
        entries.add(YavpmItems.TANUKI_SPAWN_EGG);
        entries.add(YavpmItems.VOID_PHANTOM_SPAWN_EGG);
    }
}
