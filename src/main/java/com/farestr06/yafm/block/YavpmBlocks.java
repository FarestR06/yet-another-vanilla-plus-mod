package com.farestr06.yafm.block;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import com.farestr06.yafm.block.custom.*;
import com.farestr06.yafm.item.YavpmItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;

import static com.farestr06.api.block.BlockHelper.*;
import static com.farestr06.yafm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlocks {

    public static final Block NETHER_REACTOR_CORE = makeAdvancedBlockAndItem(
            makeId("nether_reactor_core"),
            new NetherReactorCoreBlock(
                    AbstractBlock.Settings.copy(Blocks.BEACON)
            )
    );

    public static final Block GLOWING_OBSIDIAN = makeBlockAndItem(makeId("glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 15)
    );
    public static final Block SOUL_GLOWING_OBSIDIAN = makeBlockAndItem(
            makeId("soul_glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 11)
    );

    public static final Block BANANA_CROP = makeAdvancedBlock(makeId("banana_crop"),
            new BananaCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));

    public static final Block PEANUT_CROP = makeAdvancedBlock(makeId("peanut_crop"),
            new PeanutCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));

    public static final Block OAK_SAPLING_CROP = makeAdvancedBlock(
            makeId("oak_sapling_crop"),
            new SaplingCropBlock(AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)) {
                @Override
                protected ItemConvertible getSeedsItem() {
                    return YavpmItems.ACORN;
                }
            }
    );

    // region Bricks and Tiles

    public static final Block POLISHED_GRANITE_BRICKS = makeBlockAndItem(makeId("polished_granite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_DIORITE_BRICKS = makeBlockAndItem(makeId("polished_diorite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_ANDESITE_BRICKS = makeBlockAndItem(makeId("polished_andesite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));

    public static final Block POLISHED_GRANITE_BRICK_STAIRS = makeAdvancedBlockAndItem(makeId("polished_granite_brick_stairs"), new StairsBlock(
            POLISHED_GRANITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_DIORITE_BRICK_STAIRS = makeAdvancedBlockAndItem(makeId("polished_diorite_brick_stairs"), new StairsBlock(
            POLISHED_DIORITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_STAIRS = makeAdvancedBlockAndItem(makeId("polished_andesite_brick_stairs"), new StairsBlock(
            POLISHED_ANDESITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));

    public static final Block POLISHED_GRANITE_BRICK_SLAB = makeAdvancedBlockAndItem(makeId("polished_granite_brick_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_DIORITE_BRICK_SLAB = makeAdvancedBlockAndItem(makeId("polished_diorite_brick_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_SLAB = makeAdvancedBlockAndItem(makeId("polished_andesite_brick_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));

    public static final Block POLISHED_GRANITE_BRICK_WALL = makeAdvancedBlockAndItem(makeId("polished_granite_brick_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_DIORITE_BRICK_WALL = makeAdvancedBlockAndItem(makeId("polished_diorite_brick_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_WALL = makeAdvancedBlockAndItem(makeId("polished_andesite_brick_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    
    
    public static final Block POLISHED_GRANITE_TILES = makeBlockAndItem(makeId("polished_granite_tiles"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_DIORITE_TILES = makeBlockAndItem(makeId("polished_diorite_tiles"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_ANDESITE_TILES = makeBlockAndItem(makeId("polished_andesite_tiles"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));

    public static final Block POLISHED_GRANITE_TILE_STAIRS = makeAdvancedBlockAndItem(makeId("polished_granite_tile_stairs"), new StairsBlock(
            POLISHED_GRANITE_TILES.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_DIORITE_TILE_STAIRS = makeAdvancedBlockAndItem(makeId("polished_diorite_tile_stairs"), new StairsBlock(
            POLISHED_DIORITE_TILES.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_ANDESITE_TILE_STAIRS = makeAdvancedBlockAndItem(makeId("polished_andesite_tile_stairs"), new StairsBlock(
            POLISHED_ANDESITE_TILES.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));

    public static final Block POLISHED_GRANITE_TILE_SLAB = makeAdvancedBlockAndItem(makeId("polished_granite_tile_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_DIORITE_TILE_SLAB = makeAdvancedBlockAndItem(makeId("polished_diorite_tile_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_ANDESITE_TILE_SLAB = makeAdvancedBlockAndItem(makeId("polished_andesite_tile_slab"), new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));

    public static final Block POLISHED_GRANITE_TILE_WALL = makeAdvancedBlockAndItem(makeId("polished_granite_tile_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_DIORITE_TILE_WALL = makeAdvancedBlockAndItem(makeId("polished_diorite_tile_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_ANDESITE_TILE_WALL = makeAdvancedBlockAndItem(makeId("polished_andesite_tile_wall"), new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));

    // endregion

    public static final Block ELECTRO_GLASS = makeAdvancedBlockAndItem(makeId("electro_glass"),
            new ElectroGlassBlock(
                    AbstractBlock.Settings.copy(Blocks.TINTED_GLASS)
            ));

    private static void addToRedstoneBlocks(FabricItemGroupEntries entries) {
        entries.add(ELECTRO_GLASS);
    }

    private static void addToFunctionalBlocks(FabricItemGroupEntries entries) {
        entries.add(NETHER_REACTOR_CORE);
    }

    private static void addToBuildingBlocks(FabricItemGroupEntries entries) {
        entries.add(GLOWING_OBSIDIAN);
        entries.add(SOUL_GLOWING_OBSIDIAN);
        entries.add(POLISHED_GRANITE_BRICKS);
        entries.add(POLISHED_GRANITE_BRICK_STAIRS);
        entries.add(POLISHED_GRANITE_BRICK_SLAB);
        entries.add(POLISHED_GRANITE_BRICK_WALL);
        entries.add(POLISHED_DIORITE_BRICKS);
        entries.add(POLISHED_DIORITE_BRICK_STAIRS);
        entries.add(POLISHED_DIORITE_BRICK_SLAB);
        entries.add(POLISHED_DIORITE_BRICK_WALL);
        entries.add(POLISHED_ANDESITE_BRICKS);
        entries.add(POLISHED_ANDESITE_BRICK_STAIRS);
        entries.add(POLISHED_ANDESITE_BRICK_SLAB);
        entries.add(POLISHED_ANDESITE_BRICK_WALL);
        entries.add(POLISHED_GRANITE_TILES);
        entries.add(POLISHED_GRANITE_TILE_STAIRS);
        entries.add(POLISHED_GRANITE_TILE_SLAB);
        entries.add(POLISHED_GRANITE_TILE_WALL);
        entries.add(POLISHED_DIORITE_TILES);
        entries.add(POLISHED_DIORITE_TILE_STAIRS);
        entries.add(POLISHED_DIORITE_TILE_SLAB);
        entries.add(POLISHED_DIORITE_TILE_WALL);
        entries.add(POLISHED_ANDESITE_TILES);
        entries.add(POLISHED_ANDESITE_TILE_STAIRS);
        entries.add(POLISHED_ANDESITE_TILE_SLAB);
        entries.add(POLISHED_ANDESITE_TILE_WALL);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(YavpmBlocks::addToFunctionalBlocks);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(YavpmBlocks::addToBuildingBlocks);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(YavpmBlocks::addToRedstoneBlocks);
    }
}
