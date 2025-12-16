package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.custom.CopperHornItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class ItemGroupHelper {
    public static void modifyEntries() {
        YetAnotherVanillaPlusMod.LOGGER.info("Modifying item groups for YAVPM!");

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Building Blocks item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(ItemGroupHelper::buildingBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Natural item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(ItemGroupHelper::naturalBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Functional item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(ItemGroupHelper::functionalBlocks);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Redstone item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(ItemGroupHelper::redstone);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Tools item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(ItemGroupHelper::tools);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Combat item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(ItemGroupHelper::combat);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Food and Drink item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(ItemGroupHelper::foodAndDrink);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Ingredients item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(ItemGroupHelper::ingredients);

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying Spawn Eggs item group...");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(ItemGroupHelper::spawnEggs);
    }

    private static void buildingBlocks(FabricItemGroupEntries entries) {
        entries.accept(YavpmBlocks.APPLE_LOG);
        entries.accept(YavpmBlocks.APPLE_WOOD);
        entries.accept(YavpmBlocks.STRIPPED_APPLE_LOG);
        entries.accept(YavpmBlocks.STRIPPED_APPLE_WOOD);
        entries.accept(YavpmBlocks.APPLE_PLANKS);
        entries.accept(YavpmBlocks.APPLE_STAIRS);
        entries.accept(YavpmBlocks.APPLE_SLAB);
        entries.accept(YavpmBlocks.APPLE_FENCE);
        entries.accept(YavpmBlocks.APPLE_FENCE_GATE);
        entries.accept(YavpmBlocks.APPLE_DOOR);
        entries.accept(YavpmBlocks.APPLE_TRAPDOOR);
        entries.accept(YavpmBlocks.APPLE_PRESSURE_PLATE);
        entries.accept(YavpmBlocks.APPLE_BUTTON);

        entries.accept(YavpmBlocks.PERSIMMON_LOG);
        entries.accept(YavpmBlocks.PERSIMMON_WOOD);
        entries.accept(YavpmBlocks.STRIPPED_PERSIMMON_LOG);
        entries.accept(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);
        entries.accept(YavpmBlocks.PERSIMMON_PLANKS);
        entries.accept(YavpmBlocks.PERSIMMON_STAIRS);
        entries.accept(YavpmBlocks.PERSIMMON_SLAB);
        entries.accept(YavpmBlocks.PERSIMMON_FENCE);
        entries.accept(YavpmBlocks.PERSIMMON_FENCE_GATE);
        entries.accept(YavpmBlocks.PERSIMMON_DOOR);
        entries.accept(YavpmBlocks.PERSIMMON_TRAPDOOR);
        entries.accept(YavpmBlocks.PERSIMMON_PRESSURE_PLATE);
        entries.accept(YavpmBlocks.PERSIMMON_BUTTON);

        entries.accept(YavpmBlocks.PRICKLE_LOG);
        entries.accept(YavpmBlocks.PRICKLE_WOOD);
        entries.accept(YavpmBlocks.STRIPPED_PRICKLE_LOG);
        entries.accept(YavpmBlocks.STRIPPED_PRICKLE_WOOD);
        entries.accept(YavpmBlocks.PRICKLE_PLANKS);
        entries.accept(YavpmBlocks.PRICKLE_STAIRS);
        entries.accept(YavpmBlocks.PRICKLE_SLAB);
        entries.accept(YavpmBlocks.PRICKLE_FENCE);
        entries.accept(YavpmBlocks.PRICKLE_FENCE_GATE);
        entries.accept(YavpmBlocks.PRICKLE_DOOR);
        entries.accept(YavpmBlocks.PRICKLE_TRAPDOOR);
        entries.accept(YavpmBlocks.PRICKLE_PRESSURE_PLATE);
        entries.accept(YavpmBlocks.PRICKLE_BUTTON);

        entries.accept(YavpmBlocks.SHOJI);

        entries.accept(YavpmBlocks.COBBLED_GRANITE);
        entries.accept(YavpmBlocks.COBBLED_GRANITE_STAIRS);
        entries.accept(YavpmBlocks.COBBLED_GRANITE_SLAB);
        entries.accept(YavpmBlocks.COBBLED_GRANITE_WALL);
        entries.accept(YavpmBlocks.POLISHED_GRANITE_BRICKS);
        entries.accept(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
        entries.accept(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);

        entries.accept(YavpmBlocks.COBBLED_DIORITE);
        entries.accept(YavpmBlocks.COBBLED_DIORITE_STAIRS);
        entries.accept(YavpmBlocks.COBBLED_DIORITE_SLAB);
        entries.accept(YavpmBlocks.COBBLED_DIORITE_WALL);
        entries.accept(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        entries.accept(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        entries.accept(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);

        entries.accept(YavpmBlocks.COBBLED_ANDESITE);
        entries.accept(YavpmBlocks.COBBLED_ANDESITE_STAIRS);
        entries.accept(YavpmBlocks.COBBLED_ANDESITE_SLAB);
        entries.accept(YavpmBlocks.COBBLED_ANDESITE_WALL);
        entries.accept(YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        entries.accept(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);
        entries.accept(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);

        entries.accept(YavpmBlocks.KIMBERLITE);
        entries.accept(YavpmBlocks.KIMBERLITE_STAIRS);
        entries.accept(YavpmBlocks.KIMBERLITE_SLAB);
        entries.accept(YavpmBlocks.KIMBERLITE_WALL);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_SLAB);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_WALL);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB);
        entries.accept(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);

        entries.accept(YavpmBlocks.SCULKY_DEEPSLATE_BRICKS);
        entries.accept(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB);
        entries.accept(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL);

        entries.accept(YavpmBlocks.SOULSTONE);
        entries.accept(YavpmBlocks.SOULSTONE_STAIRS);
        entries.accept(YavpmBlocks.SOULSTONE_SLAB);
        entries.accept(YavpmBlocks.SOULSTONE_WALL);
        entries.accept(YavpmBlocks.CHISELED_SOULSTONE);
        entries.accept(YavpmBlocks.SMOOTH_SOULSTONE);
        entries.accept(YavpmBlocks.SMOOTH_SOULSTONE_STAIRS);
        entries.accept(YavpmBlocks.SMOOTH_SOULSTONE_SLAB);
        entries.accept(YavpmBlocks.CUT_SOULSTONE);
        entries.accept(YavpmBlocks.CUT_SOULSTONE_SLAB);

        entries.accept(YavpmBlocks.CONGLOMERATE);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_SLAB);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_WALL);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB);
        entries.accept(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL);
        entries.accept(YavpmBlocks.DULL_CONGLOMERATE);
        entries.accept(YavpmBlocks.DULL_CONGLOMERATE_SLAB);

        entries.accept(YavpmBlocks.GRAPHITE_BLOCK);
        entries.accept(YavpmBlocks.GRAPHENE_BLOCK);
    }

    private static void naturalBlocks(FabricItemGroupEntries entries) {
        entries.accept(YavpmBlocks.INFESTED_COBBLED_DEEPSLATE);
        entries.accept(YavpmBlocks.INFESTED_DEEPSLATE_BRICKS);
        entries.accept(YavpmBlocks.INFESTED_SCULKY_DEEPSLATE_BRICKS);
        entries.accept(YavpmBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS);
        entries.accept(YavpmBlocks.INFESTED_CHISELED_DEEPSLATE);

        entries.accept(YavpmBlocks.GLOWING_OBSIDIAN);
        entries.accept(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);
        entries.accept(YavpmBlocks.NAHCOLITE_ORE);
        entries.accept(YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE);
        entries.accept(YavpmBlocks.KIMBERLITE);
        entries.accept(YavpmBlocks.APPLE_LOG);
        entries.accept(YavpmBlocks.PERSIMMON_LOG);
        entries.accept(YavpmBlocks.PRICKLE_LOG);
        entries.accept(YavpmBlocks.APPLE_LEAVES);
        entries.accept(YavpmBlocks.FLOWERING_APPLE_LEAVES);
        entries.accept(YavpmBlocks.APPLE_SAPLING);
        entries.accept(YavpmBlocks.PERSIMMON_LEAVES);
        entries.accept(YavpmBlocks.PRICKLE_SHOOT);
        entries.accept(YavpmItems.CANTALOUPE_SEEDS);
        entries.accept(YavpmItems.ACORN);
        entries.accept(YavpmItems.BIRCH_SEEDS);
        entries.accept(YavpmItems.SPRUCE_CONE);
        entries.accept(YavpmItems.CRIMSON_SPORE);
        entries.accept(YavpmItems.WARPED_SPORE);
        entries.accept(YavpmItems.BANANA_SEEDS);
        entries.accept(YavpmItems.RICE_SEEDS);
        entries.accept(YavpmItems.PEANUT);
        entries.accept(YavpmItems.MAGIC_BEAN);
        entries.accept(YavpmItems.WARPED_WART);
        entries.accept(YavpmBlocks.CANTALOUPE);
    }

    private static void functionalBlocks(FabricItemGroupEntries entries) {
        entries.accept(YavpmBlocks.GLOWING_OBSIDIAN);
        entries.accept(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);
        entries.accept(YavpmBlocks.CHOPPING_BLOCK);
        entries.accept(YavpmBlocks.PINATA);
    }

    private static void redstone(FabricItemGroupEntries entries) {
        entries.accept(YavpmBlocks.POLARIZED_GLASS);
        if (HANDLER.instance().nulliumExperiment) {
            entries.accept(YavpmBlocks.NULL_TORCH);
        }
        if (HANDLER.instance().recyclerExperiment) {
            entries.accept(YavpmBlocks.RECYCLER);
        }
        entries.accept(YavpmBlocks.BURNER);
        entries.accept(YavpmItems.APPLE_SIGN);
        entries.accept(YavpmItems.APPLE_HANGING_SIGN);
        entries.accept(YavpmBlocks.PINATA);
    }

    private static void tools(FabricItemGroupEntries entries) {
        entries.accept(YavpmItems.DENSITITE_SHOVEL);
        entries.accept(YavpmItems.DENSITITE_PICKAXE);
        entries.accept(YavpmItems.DENSITITE_AXE);
        entries.accept(YavpmItems.DENSITITE_HOE);
        entries.accept(YavpmItems.VOID_WATER_BUCKET);
        entries.accept(YavpmItems.FAKE_MILK_BUCKET);
        entries.accept(YavpmItems.FORTUNE_COOKIE);
        entries.accept(YavpmItems.REACTOR);
        entries.accept(YavpmItems.BABY_KEY);
        entries.accept(YavpmItems.APPLE_BOAT);
        entries.accept(YavpmItems.APPLE_CHEST_BOAT);
        entries.accept(
                CopperHornItem.getStackForId(YavpmItems.COPPER_HORN, CopperInstruments.GREAT_SKY_FALLING)
        );

        entries.accept(YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT);
        entries.accept(YavpmItems.MUSIC_DISC_HALLAND_DALARNA);
    }

    private static void combat(FabricItemGroupEntries entries) {
        entries.accept(YavpmItems.DENSITITE_SWORD);
        entries.accept(YavpmItems.DENSITITE_AXE);
        entries.accept(YavpmItems.GAUNTLET);
        entries.accept(YavpmItems.STUDDED_HELMET);
        entries.accept(YavpmItems.STUDDED_CHESTPLATE);
        entries.accept(YavpmItems.STUDDED_LEGGINGS);
        entries.accept(YavpmItems.STUDDED_BOOTS);
        entries.accept(YavpmItems.DENSITITE_HELMET);
        entries.accept(YavpmItems.DENSITITE_CHESTPLATE);
        entries.accept(YavpmItems.DENSITITE_LEGGINGS);
        entries.accept(YavpmItems.DENSITITE_BOOTS);
        entries.accept(YavpmItems.CARBON_EGG);
    }

    private static void foodAndDrink(FabricItemGroupEntries entries) {
        entries.accept(YavpmItems.ACORN);
        entries.accept(YavpmItems.DIAMOND_ACORN);
        entries.accept(YavpmItems.PERSIMMON);
        entries.accept(YavpmItems.GOLDEN_PERSIMMON);
        entries.accept(YavpmItems.BANANA);
        entries.accept(YavpmItems.CANTALOUPE_SLICE);
        entries.accept(YavpmItems.MAGIC_BEAN);
        entries.accept(YavpmItems.TRUFFLE);
        entries.accept(YavpmItems.PRETZEL);
        entries.accept(YavpmItems.CHEESE);
        entries.accept(YavpmItems.TOFU);
        entries.accept(YavpmItems.PEANUT);
        entries.accept(YavpmItems.COOKED_PEANUT);
        entries.accept(YavpmItems.COOKED_EGG);
        entries.accept(YavpmItems.FRIED_BANANA);
        entries.accept(YavpmItems.FRIED_COD);
        entries.accept(YavpmItems.FAKE_BEEF);
        entries.accept(YavpmItems.COOKED_FAKE_BEEF);
        entries.accept(YavpmItems.BEAN_TOAST);
        entries.accept(YavpmItems.RICE_BAR);
        entries.accept(YavpmItems.RICE_PASTRY);
        entries.accept(YavpmItems.SUSHI);
        entries.accept(YavpmItems.SEA_SOUP);
        entries.accept(YavpmItems.CHICKEN_SOUP);
        entries.accept(YavpmItems.FANCY_MUSHROOM_STEW);
        entries.accept(YavpmItems.FAKE_MILK_BUCKET);
        entries.accept(YavpmItems.MOLY);
        entries.accept(YavpmItems.JELLY);
        entries.accept(YavpmItems.SWEET_BERRY_JELLY);
        entries.accept(YavpmItems.FORTUNE_COOKIE);
    }

    private static void ingredients(FabricItemGroupEntries entries) {
        entries.accept(YavpmItems.RAW_DIAMOND);
        entries.accept(YavpmItems.GRAPHITE);
        entries.accept(YavpmBlocks.GRAPHITE_BLOCK);
        entries.accept(YavpmBlocks.GRAPHENE_BLOCK);
        entries.accept(YavpmItems.DENSITITE_INGOT);
        entries.accept(YavpmBlocks.DENSITITE_BLOCK);
        if (HANDLER.instance().nulliumExperiment) {
            entries.accept(YavpmItems.NULLIUM_NUGGET);
        }
        entries.accept(YavpmItems.CARBON_EGG);
        entries.accept(YavpmItems.BAKING_SODA);
        entries.accept(YavpmItems.BREADING);
        entries.accept(YavpmItems.MAGIC_BEAN);
        entries.accept(YavpmItems.RICE);
        entries.accept(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT);
        entries.accept(YavpmItems.WARPED_WART);
        entries.accept(YavpmItems.BITTER_BERRIES);
        entries.accept(YavpmItems.CHAINMAIL);
        entries.accept(YavpmItems.GAUNTLET_FRAGMENT);
        entries.accept(YavpmItems.PHANTOM_CHORD);
        entries.accept(YavpmItems.THUNDER_SHARD);
        entries.accept(YavpmItems.DENSITITE_UPGRADE_SMITHING_TEMPLATE);
    }

    private static void spawnEggs(FabricItemGroupEntries entries) {
        entries.accept(YavpmItems.CARBONFOWL_SPAWN_EGG);
        entries.accept(YavpmItems.MOONGUS_SPAWN_EGG);
        entries.accept(YavpmItems.SUNBURN_SPAWN_EGG);
        entries.accept(YavpmItems.TANUKI_SPAWN_EGG);
        entries.accept(YavpmItems.VOID_PHANTOM_SPAWN_EGG);
    }
}
