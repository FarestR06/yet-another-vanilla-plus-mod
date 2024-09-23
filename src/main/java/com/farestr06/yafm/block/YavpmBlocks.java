package com.farestr06.yafm.block;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import com.farestr06.yafm.block.custom.BananaCropBlock;
import com.farestr06.yafm.block.custom.PeanutCropBlock;
import com.farestr06.yafm.block.custom.ElectroGlassBlock;
import com.farestr06.yafm.block.custom.SaplingCropBlock;
import com.farestr06.yafm.item.YavpmItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class YavpmBlocks {

    public static final Block GLOWING_OBSIDIAN = registerBlockAndItem("glowing_obsidian", new Block(
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
                    .luminance(state -> 15)
            )
    );
    public static final Block SOUL_GLOWING_OBSIDIAN = registerBlockAndItem("soul_glowing_obsidian", new Block(
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
                    .luminance(state -> 11)
            )
    );

    public static final Block BANANA_CROP = registerBlock("banana_crop",
            new BananaCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));

    public static final Block PEANUT_CROP = registerBlock("peanut_crop",
            new PeanutCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));

    public static final Block OAK_SAPLING_CROP = registerBlock(
            "oak_sapling_crop",
            new SaplingCropBlock(AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)) {
                @Override
                protected ItemConvertible getSeedsItem() {
                    return YavpmItems.ACORN;
                }
            }
    );

    public static final Block POLISHED_GRANITE_BRICKS = registerBlockAndItem("polished_granite_bricks", new Block(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)
    ));
    public static final Block POLISHED_DIORITE_BRICKS = registerBlockAndItem("polished_diorite_bricks", new Block(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)
    ));
    public static final Block POLISHED_ANDESITE_BRICKS = registerBlockAndItem("polished_andesite_bricks", new Block(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)
    ));

    public static final Block POLISHED_GRANITE_BRICK_STAIRS = registerBlockAndItem("polished_granite_brick_stairs", new StairsBlock(
            POLISHED_GRANITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_DIORITE_BRICK_STAIRS = registerBlockAndItem("polished_diorite_brick_stairs", new StairsBlock(
            POLISHED_DIORITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_STAIRS = registerBlockAndItem("polished_andesite_brick_stairs", new StairsBlock(
            POLISHED_ANDESITE_BRICKS.getDefaultState(),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    ));

    public static final Block POLISHED_GRANITE_BRICK_SLAB = registerBlockAndItem("polished_granite_brick_slab", new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_DIORITE_BRICK_SLAB = registerBlockAndItem("polished_diorite_brick_slab", new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_SLAB = registerBlockAndItem("polished_andesite_brick_slab", new SlabBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)
    ));

    public static final Block POLISHED_GRANITE_BRICK_WALL = registerBlockAndItem("polished_granite_brick_wall", new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_DIORITE_BRICK_WALL = registerBlockAndItem("polished_diorite_brick_wall", new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));
    public static final Block POLISHED_ANDESITE_BRICK_WALL = registerBlockAndItem("polished_andesite_brick_wall", new WallBlock(
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)
    ));

    public static final Block ELECTRO_GLASS = registerBlockAndItem("electro_glass",
            new ElectroGlassBlock(
                    AbstractBlock.Settings.copy(Blocks.TINTED_GLASS)
            ));

    private static Block registerBlock(String id, Block block) {
        return Registry.register(Registries.BLOCK, YetAnotherVanillaPlusMod.makeId(id), block);
    }

    private static Block registerBlockAndItem(String id, Block block) {
        registerBlockItem(id, block);
        return registerBlock(id, block);
    }
    private static Item registerBlockItem(String id, Block block) {
        return Registry.register(Registries.ITEM, YetAnotherVanillaPlusMod.makeId(id), new BlockItem(block, new Item.Settings()));
    }

    private static void addToRedstoneBlocks(FabricItemGroupEntries entries) {
        entries.add(ELECTRO_GLASS);
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
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(YavpmBlocks::addToBuildingBlocks);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(YavpmBlocks::addToRedstoneBlocks);
    }
}
