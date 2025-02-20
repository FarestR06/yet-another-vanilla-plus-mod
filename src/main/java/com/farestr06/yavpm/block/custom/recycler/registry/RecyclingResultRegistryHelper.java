package com.farestr06.yavpm.block.custom.recycler.registry;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.item.Items;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOGGER;
import static com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResultRegistry.INSTANCE;

public final class RecyclingResultRegistryHelper {
    private static final RecyclingResult WOODEN_TOOL_RESULT = new RecyclingResult(Items.OAK_PLANKS, 1, null);
    private static final RecyclingResult LEATHER_ARMOR_RESULT = new RecyclingResult(Items.LEATHER, 3, 5);
    private static final RecyclingResult STONE_TOOL_RESULT = new RecyclingResult(Items.COBBLESTONE, 1, null);
    private static final RecyclingResult IRON_TOOL_RESULT = new RecyclingResult(Items.IRON_NUGGET, 5, 9);
    private static final RecyclingResult IRON_ARMOR_RESULT = new RecyclingResult(Items.IRON_NUGGET, 24, 36);
    private static final RecyclingResult GOLDEN_TOOL_RESULT = new RecyclingResult(Items.GOLD_NUGGET, 5, 9);
    private static final RecyclingResult GOLDEN_ARMOR_RESULT = new RecyclingResult(Items.GOLD_NUGGET, 24, 36);
    private static final RecyclingResult DIAMOND_TOOL_RESULT = new RecyclingResult(Items.DIAMOND, 1, null);
    private static final RecyclingResult DIAMOND_ARMOR_RESULT = new RecyclingResult(Items.DIAMOND, 3, 5);
    private static final RecyclingResult NETHERITE_EQUIPMENT_RESULT = new RecyclingResult(Items.NETHERITE_SCRAP, 1, 3);

    private static final RecyclingResult COPPER_BLOCK_RESULT = new RecyclingResult(Items.COPPER_INGOT, 7, 9);
    private static final RecyclingResult CUT_COPPER_BLOCK_RESULT = new RecyclingResult(Items.COPPER_INGOT, 1, 2);

    public static void init() {
        LOGGER.info("Registering recycling results for YAVPM!!");

        INSTANCE.add(Items.CHEST, new RecyclingResult(Items.OAK_PLANKS, 3, 7));
        INSTANCE.add(Items.BARREL, new RecyclingResult(Items.OAK_PLANKS, 1, 3));

        INSTANCE.add(Items.COBBLESTONE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(YavpmBlocks.COBBLED_GRANITE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(YavpmBlocks.COBBLED_ANDESITE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(YavpmBlocks.COBBLED_DIORITE, new RecyclingResult(Items.GRAVEL, 1, null));

        INSTANCE.add(Items.GLASS, new RecyclingResult(Items.SAND, 1, null));

        INSTANCE.add(Items.STONE, new RecyclingResult(Items.COBBLESTONE, 1, null));
        INSTANCE.add(Items.STONE_BRICKS, new RecyclingResult(Items.COBBLESTONE, 1, null));
        INSTANCE.add(Items.SMOOTH_STONE, new RecyclingResult(Items.COBBLESTONE, 1, null));

        INSTANCE.add(Items.GRANITE, new RecyclingResult(YavpmBlocks.COBBLED_GRANITE, 1, null));
        INSTANCE.add(Items.POLISHED_GRANITE, new RecyclingResult(YavpmBlocks.COBBLED_GRANITE, 1, null));
        INSTANCE.add(YavpmBlocks.POLISHED_GRANITE_BRICKS, new RecyclingResult(YavpmBlocks.COBBLED_GRANITE, 1, null));
        INSTANCE.add(Items.DIORITE, new RecyclingResult(YavpmBlocks.COBBLED_DIORITE, 1, null));
        INSTANCE.add(Items.POLISHED_DIORITE, new RecyclingResult(YavpmBlocks.COBBLED_DIORITE, 1, null));
        INSTANCE.add(YavpmBlocks.POLISHED_DIORITE_BRICKS, new RecyclingResult(YavpmBlocks.COBBLED_DIORITE, 1, null));
        INSTANCE.add(Items.ANDESITE, new RecyclingResult(YavpmBlocks.COBBLED_ANDESITE, 1, null));
        INSTANCE.add(Items.POLISHED_ANDESITE, new RecyclingResult(YavpmBlocks.COBBLED_ANDESITE, 1, null));
        INSTANCE.add(YavpmBlocks.POLISHED_ANDESITE_BRICKS, new RecyclingResult(YavpmBlocks.COBBLED_ANDESITE, 1, null));

        INSTANCE.add(Items.POLISHED_TUFF, new RecyclingResult(Items.TUFF, 1, null));
        INSTANCE.add(Items.CHISELED_TUFF, new RecyclingResult(Items.TUFF, 1, null));
        INSTANCE.add(Items.TUFF_BRICKS, new RecyclingResult(Items.TUFF, 1, null));
        INSTANCE.add(Items.CHISELED_TUFF_BRICKS, new RecyclingResult(Items.TUFF, 1, null));

        INSTANCE.add(Items.PACKED_MUD, new RecyclingResult(Items.DIRT, 1, null));
        INSTANCE.add(Items.MUD_BRICKS, new RecyclingResult(Items.DIRT, 1, null));
        INSTANCE.add(Items.GRAVEL, new RecyclingResult(Items.FLINT, 1, 2));

        equipment();

        INSTANCE.add(Items.BLUE_ICE, new RecyclingResult(Items.PACKED_ICE, 4, 9));
        INSTANCE.add(Items.PACKED_ICE, new RecyclingResult(Items.ICE, 4, 9));

        INSTANCE.add(Items.QUARTZ_BLOCK, new RecyclingResult(Items.QUARTZ, 3, 4));
        INSTANCE.add(Items.QUARTZ_SLAB, new RecyclingResult(Items.QUARTZ, 1, 2));
        INSTANCE.add(Items.QUARTZ_STAIRS, new RecyclingResult(Items.QUARTZ, 2, 3));
        INSTANCE.add(Items.SMOOTH_QUARTZ, new RecyclingResult(Items.QUARTZ, 3, 4));
        INSTANCE.add(Items.QUARTZ_BRICKS, new RecyclingResult(Items.QUARTZ, 3, 4));
        INSTANCE.add(Items.QUARTZ_PILLAR, new RecyclingResult(Items.QUARTZ, 3, 4));

        INSTANCE.add(Items.AMETHYST_BLOCK, new RecyclingResult(Items.AMETHYST_SHARD, 2, 4));

        INSTANCE.add(Items.RED_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.ORANGE_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.YELLOW_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.LIME_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.GREEN_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.CYAN_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.LIGHT_BLUE_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.BLUE_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.PURPLE_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.MAGENTA_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.PINK_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.BROWN_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.BLACK_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.GRAY_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.LIGHT_GRAY_WOOL, new RecyclingResult(Items.STRING, 2, 4));
        INSTANCE.add(Items.WHITE_WOOL, new RecyclingResult(Items.STRING, 2, 4));

        INSTANCE.add(Items.NETHER_WART_BLOCK, new RecyclingResult(Items.NETHER_WART, 3, 7));
        INSTANCE.add(Items.WARPED_WART_BLOCK, new RecyclingResult(YavpmItems.WARPED_WART, 3, 7));

        INSTANCE.add(Items.COPPER_BLOCK, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.COPPER_GRATE, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_COPPER_BLOCK, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_COPPER_GRATE, COPPER_BLOCK_RESULT);

        INSTANCE.add(Items.EXPOSED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.EXPOSED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.EXPOSED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.EXPOSED_COPPER_GRATE, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_EXPOSED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_EXPOSED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_EXPOSED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_EXPOSED_COPPER_GRATE, COPPER_BLOCK_RESULT);

        INSTANCE.add(Items.WEATHERED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WEATHERED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WEATHERED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WEATHERED_COPPER_GRATE, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_WEATHERED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_WEATHERED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_WEATHERED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_WEATHERED_COPPER_GRATE, COPPER_BLOCK_RESULT);

        INSTANCE.add(Items.OXIDIZED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.OXIDIZED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.OXIDIZED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.OXIDIZED_COPPER_GRATE, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_OXIDIZED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_OXIDIZED_CUT_COPPER, CUT_COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_OXIDIZED_CHISELED_COPPER, COPPER_BLOCK_RESULT);
        INSTANCE.add(Items.WAXED_OXIDIZED_COPPER_GRATE, COPPER_BLOCK_RESULT);

        INSTANCE.add(Items.RED_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.ORANGE_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.YELLOW_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.LIME_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.GREEN_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.CYAN_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.LIGHT_BLUE_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.BLUE_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.PURPLE_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.MAGENTA_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.PINK_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.BROWN_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.BLACK_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.GRAY_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.LIGHT_GRAY_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));
        INSTANCE.add(Items.WHITE_CONCRETE, new RecyclingResult(Items.GRAVEL, 1, null));

        INSTANCE.add(Items.BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.RED_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.ORANGE_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.YELLOW_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.LIME_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.GREEN_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.CYAN_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.LIGHT_BLUE_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.BLUE_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.PURPLE_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.MAGENTA_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.PINK_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.BROWN_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.BLACK_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.GRAY_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.LIGHT_GRAY_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));
        INSTANCE.add(Items.WHITE_BUNDLE, new RecyclingResult(Items.RABBIT_HIDE, 1, 3));

        INSTANCE.add(Items.LODESTONE, new RecyclingResult(Items.NETHERITE_SCRAP, 3, 4));

        INSTANCE.add(Items.RECOVERY_COMPASS, new RecyclingResult(Items.ECHO_SHARD, 3, 7));

        INSTANCE.add(Items.MUSIC_DISC_5, new RecyclingResult(Items.DISC_FRAGMENT_5, 5, 8));
        INSTANCE.add(YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, new RecyclingResult(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT, 5, 8));

        INSTANCE.add(Items.ITEM_FRAME, new RecyclingResult(Items.RABBIT_HIDE, 1, 2));
        INSTANCE.add(Items.BOOK, new RecyclingResult(Items.PAPER, 1, null));
        INSTANCE.add(Items.BUCKET, new RecyclingResult(Items.IRON_NUGGET, 5, 20));
        INSTANCE.add(Items.GLASS_BOTTLE, new RecyclingResult(Items.GLASS, 1, null));

        INSTANCE.add(Items.OAK_DOOR, new RecyclingResult(Items.OAK_PLANKS, 1, 2));
        INSTANCE.add(Items.SPRUCE_DOOR, new RecyclingResult(Items.SPRUCE_PLANKS, 1, 2));
        INSTANCE.add(Items.BIRCH_DOOR, new RecyclingResult(Items.BIRCH_PLANKS, 1, 2));
        INSTANCE.add(Items.JUNGLE_DOOR, new RecyclingResult(Items.JUNGLE_PLANKS, 1, 2));
        INSTANCE.add(Items.ACACIA_DOOR, new RecyclingResult(Items.ACACIA_PLANKS, 1, 2));
        INSTANCE.add(Items.DARK_OAK_DOOR, new RecyclingResult(Items.DARK_OAK_PLANKS, 1, 2));
        INSTANCE.add(Items.MANGROVE_DOOR, new RecyclingResult(Items.MANGROVE_PLANKS, 1, 2));
        INSTANCE.add(Items.CHERRY_DOOR, new RecyclingResult(Items.CHERRY_PLANKS, 1, 2));
        INSTANCE.add(Items.PALE_OAK_DOOR, new RecyclingResult(Items.PALE_OAK_PLANKS, 1, 2));
        INSTANCE.add(Items.BAMBOO_DOOR, new RecyclingResult(Items.BAMBOO_PLANKS, 1, 2));
        INSTANCE.add(Items.CRIMSON_DOOR, new RecyclingResult(Items.CRIMSON_PLANKS, 1, 2));
        INSTANCE.add(Items.WARPED_DOOR, new RecyclingResult(Items.WARPED_PLANKS, 1, 2));

        INSTANCE.add(Items.OAK_TRAPDOOR, new RecyclingResult(Items.OAK_PLANKS, 2, 3));
        INSTANCE.add(Items.SPRUCE_TRAPDOOR, new RecyclingResult(Items.SPRUCE_PLANKS, 2, 3));
        INSTANCE.add(Items.BIRCH_TRAPDOOR, new RecyclingResult(Items.BIRCH_PLANKS, 2, 3));
        INSTANCE.add(Items.JUNGLE_TRAPDOOR, new RecyclingResult(Items.JUNGLE_PLANKS, 2, 3));
        INSTANCE.add(Items.ACACIA_TRAPDOOR, new RecyclingResult(Items.ACACIA_PLANKS, 2, 3));
        INSTANCE.add(Items.DARK_OAK_TRAPDOOR, new RecyclingResult(Items.DARK_OAK_PLANKS, 2, 3));
        INSTANCE.add(Items.MANGROVE_TRAPDOOR, new RecyclingResult(Items.MANGROVE_PLANKS, 2, 3));
        INSTANCE.add(Items.CHERRY_TRAPDOOR, new RecyclingResult(Items.CHERRY_PLANKS, 2, 3));
        INSTANCE.add(Items.PALE_OAK_TRAPDOOR, new RecyclingResult(Items.PALE_OAK_PLANKS, 2, 3));
        INSTANCE.add(Items.BAMBOO_TRAPDOOR, new RecyclingResult(Items.BAMBOO_PLANKS, 2, 3));
        INSTANCE.add(Items.CRIMSON_TRAPDOOR, new RecyclingResult(Items.CRIMSON_PLANKS, 2, 3));
        INSTANCE.add(Items.WARPED_TRAPDOOR, new RecyclingResult(Items.WARPED_PLANKS, 2, 3));

        INSTANCE.add(Items.DROPPER, new RecyclingResult(Items.COBBLESTONE, 2, 5));
        INSTANCE.add(Items.DISPENSER, new RecyclingResult(Items.DROPPER, 1, null));

        INSTANCE.add(Items.PISTON, new RecyclingResult(Items.COBBLESTONE, 1, 2));
        INSTANCE.add(Items.STICKY_PISTON, new RecyclingResult(Items.PISTON, 1, null));
    }

    private static void equipment() {
        INSTANCE.add(Items.WOODEN_SWORD, WOODEN_TOOL_RESULT);
        INSTANCE.add(Items.WOODEN_SHOVEL, WOODEN_TOOL_RESULT);
        INSTANCE.add(Items.WOODEN_PICKAXE, WOODEN_TOOL_RESULT);
        INSTANCE.add(Items.WOODEN_AXE, WOODEN_TOOL_RESULT);
        INSTANCE.add(Items.WOODEN_HOE, WOODEN_TOOL_RESULT);
        INSTANCE.add(Items.LEATHER_HELMET, LEATHER_ARMOR_RESULT);
        INSTANCE.add(Items.LEATHER_CHESTPLATE, LEATHER_ARMOR_RESULT);
        INSTANCE.add(Items.LEATHER_LEGGINGS, LEATHER_ARMOR_RESULT);
        INSTANCE.add(Items.LEATHER_BOOTS, LEATHER_ARMOR_RESULT);
        INSTANCE.add(Items.STONE_SWORD, STONE_TOOL_RESULT);
        INSTANCE.add(Items.STONE_SHOVEL, STONE_TOOL_RESULT);
        INSTANCE.add(Items.STONE_PICKAXE, STONE_TOOL_RESULT);
        INSTANCE.add(Items.STONE_AXE, STONE_TOOL_RESULT);
        INSTANCE.add(Items.STONE_HOE, STONE_TOOL_RESULT);
        INSTANCE.add(Items.IRON_SWORD, IRON_TOOL_RESULT);
        INSTANCE.add(Items.IRON_SHOVEL, IRON_TOOL_RESULT);
        INSTANCE.add(Items.IRON_PICKAXE, IRON_TOOL_RESULT);
        INSTANCE.add(Items.IRON_AXE, IRON_TOOL_RESULT);
        INSTANCE.add(Items.IRON_HOE, IRON_TOOL_RESULT);
        INSTANCE.add(Items.IRON_HELMET, IRON_ARMOR_RESULT);
        INSTANCE.add(Items.IRON_CHESTPLATE, IRON_ARMOR_RESULT);
        INSTANCE.add(Items.IRON_LEGGINGS, IRON_ARMOR_RESULT);
        INSTANCE.add(Items.IRON_BOOTS, IRON_ARMOR_RESULT);
        INSTANCE.add(Items.GOLDEN_SWORD, GOLDEN_TOOL_RESULT);
        INSTANCE.add(Items.GOLDEN_SHOVEL, GOLDEN_TOOL_RESULT);
        INSTANCE.add(Items.GOLDEN_PICKAXE, GOLDEN_TOOL_RESULT);
        INSTANCE.add(Items.GOLDEN_AXE, GOLDEN_TOOL_RESULT);
        INSTANCE.add(Items.GOLDEN_HOE, GOLDEN_TOOL_RESULT);
        INSTANCE.add(Items.GOLDEN_HELMET, GOLDEN_ARMOR_RESULT);
        INSTANCE.add(Items.GOLDEN_CHESTPLATE, GOLDEN_ARMOR_RESULT);
        INSTANCE.add(Items.GOLDEN_LEGGINGS, GOLDEN_ARMOR_RESULT);
        INSTANCE.add(Items.GOLDEN_BOOTS, GOLDEN_ARMOR_RESULT);
        INSTANCE.add(Items.DIAMOND_SWORD, DIAMOND_TOOL_RESULT);
        INSTANCE.add(Items.DIAMOND_SHOVEL, DIAMOND_TOOL_RESULT);
        INSTANCE.add(Items.DIAMOND_PICKAXE, DIAMOND_TOOL_RESULT);
        INSTANCE.add(Items.DIAMOND_AXE, DIAMOND_TOOL_RESULT);
        INSTANCE.add(Items.DIAMOND_HOE, DIAMOND_TOOL_RESULT);
        INSTANCE.add(Items.DIAMOND_HELMET, DIAMOND_ARMOR_RESULT);
        INSTANCE.add(Items.DIAMOND_CHESTPLATE, DIAMOND_ARMOR_RESULT);
        INSTANCE.add(Items.DIAMOND_LEGGINGS, DIAMOND_ARMOR_RESULT);
        INSTANCE.add(Items.DIAMOND_BOOTS, DIAMOND_ARMOR_RESULT);
        INSTANCE.add(Items.NETHERITE_SWORD, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_SHOVEL, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_PICKAXE, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_AXE, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_HOE, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_HELMET, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_CHESTPLATE, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_LEGGINGS, NETHERITE_EQUIPMENT_RESULT);
        INSTANCE.add(Items.NETHERITE_BLOCK, NETHERITE_EQUIPMENT_RESULT);

        INSTANCE.add(Items.MACE, new RecyclingResult(Items.HEAVY_CORE, 1, null));
        INSTANCE.add(Items.TRIDENT, new RecyclingResult(YavpmItems.THUNDER_SHARD, 1, 2));
        INSTANCE.add(Items.ELYTRA, new RecyclingResult(YavpmItems.PHANTOM_CHORD, 1, 2));
        INSTANCE.add(YavpmItems.GAUNTLET, new RecyclingResult(YavpmItems.GAUNTLET_FRAGMENT, 2, 4));

        INSTANCE.add(Items.TURTLE_HELMET, new RecyclingResult(Items.TURTLE_SCUTE, 1, 4));
        INSTANCE.add(Items.WOLF_ARMOR, new RecyclingResult(Items.ARMADILLO_SCUTE, 2, 5));
        INSTANCE.add(Items.LEATHER_HORSE_ARMOR, new RecyclingResult(Items.LEATHER, 3, 7));
        INSTANCE.add(Items.IRON_HORSE_ARMOR, new RecyclingResult(Items.IRON_INGOT, 3, 7));
        INSTANCE.add(Items.GOLDEN_HORSE_ARMOR, new RecyclingResult(Items.GOLD_INGOT, 3, 7));
        INSTANCE.add(Items.DIAMOND_HORSE_ARMOR, new RecyclingResult(Items.DIAMOND, 3, 7));
    }
}
