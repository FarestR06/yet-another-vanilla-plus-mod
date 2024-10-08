package com.farestr06.yafm.block;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import com.farestr06.yafm.block.custom.*;
import com.farestr06.yafm.item.YavpmItems;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;

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

    public static final Block ELECTRO_GLASS = makeAdvancedBlockAndItem(
            makeId("electro_glass"),
            new ElectroGlassBlock(
                    AbstractBlock.Settings.copy(Blocks.TINTED_GLASS)
            )
    );

    // region Applewood
    public static final Block APPLE_LOG = makeAdvancedBlockAndItem(
            makeId("apple_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG))
    );
    public static final Block APPLE_WOOD = makeAdvancedBlockAndItem(
            makeId("apple_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_WOOD))
    );
    public static final Block STRIPPED_APPLE_LOG = makeAdvancedBlockAndItem(
            makeId("stripped_apple_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_LOG))
    );
    public static final Block STRIPPED_APPLE_WOOD = makeAdvancedBlockAndItem(
            makeId("stripped_apple_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_WOOD))
    );
    public static final Block APPLE_LEAVES = makeAdvancedBlockAndItem(
            makeId("apple_leaves"),
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES))
    );

    public static final Block APPLE_PLANKS = makeBlockAndItem(
            makeId("apple_planks"),
            AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)
    );
    public static final Block APPLE_STAIRS = makeAdvancedBlockAndItem(
            makeId("apple_stairs"),
            new StairsBlock(
                    APPLE_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.CHERRY_STAIRS)
            )
    );
    public static final Block APPLE_SLAB = makeAdvancedBlockAndItem(
            makeId("apple_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB))
    );
    public static final Block APPLE_FENCE = makeAdvancedBlockAndItem(
            makeId("apple_fence"),
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE))
    );
    public static final Block APPLE_FENCE_GATE = makeAdvancedBlockAndItem(
            makeId("apple_fence_gate"),
            new FenceGateBlock(WoodType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE))
    );
    public static final Block APPLE_DOOR = makeAdvancedBlockAndItem(
            makeId("apple_door"),
            new DoorBlock(
                    BlockSetType.CHERRY,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR)
            )
    );
    public static final Block APPLE_TRAPDOOR = makeAdvancedBlockAndItem(
            makeId("apple_trapdoor"),
            new TrapdoorBlock(
                    BlockSetType.CHERRY,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR)
            )
    );
    public static final Block APPLE_BUTTON = makeAdvancedBlockAndItem(
            makeId("apple_button"),
            new ButtonBlock(
                    BlockSetType.CHERRY,
                    30,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_BUTTON)
            )
    );
    public static final Block APPLE_PRESSURE_PLATE = makeAdvancedBlockAndItem(
            makeId("apple_pressure_plate"),
            new PressurePlateBlock(
                    BlockSetType.CHERRY,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_PRESSURE_PLATE)
            )
    );

    protected static final Identifier APPLE_SIGN_TEXTURE = makeId("entity/signs/apple");
    protected static final Identifier APPLE_HANGING_SIGN_TEXTURE = makeId("entity/signs/hanging/apple");
    protected static final Identifier APPLE_HANGING_SIGN_GUI_TEXTURE = makeId("textures/gui/hanging_signs/apple");

    public static final Block APPLE_SIGN = makeAdvancedBlock(
            makeId("apple_sign"),
            new TerraformSignBlock(
                    APPLE_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_SIGN)
            )
    );
    public static final Block APPLE_WALL_SIGN = makeAdvancedBlock(
            makeId("apple_wall_sign"),
            new TerraformWallSignBlock(
                    APPLE_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_SIGN)
            )
    );
    public static final Block APPLE_HANGING_SIGN = makeAdvancedBlock(
            makeId("apple_hanging_sign"),
            new TerraformHangingSignBlock(
                    APPLE_HANGING_SIGN_TEXTURE,
                    APPLE_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_HANGING_SIGN)
            )
    );
    public static final Block APPLE_WALL_HANGING_SIGN = makeAdvancedBlock(
            makeId("apple_hanging_sign"),
            new TerraformWallHangingSignBlock(
                    APPLE_HANGING_SIGN_TEXTURE,
                    APPLE_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.CHERRY_WALL_HANGING_SIGN)
            )
    );
    // endregion

    private static void addToRedstoneBlocks(FabricItemGroupEntries entries) {
        entries.add(ELECTRO_GLASS);
    }

    private static void addToFunctionalBlocks(FabricItemGroupEntries entries) {
        entries.add(NETHER_REACTOR_CORE);
    }

    private static void addToBuildingBlocks(FabricItemGroupEntries entries) {

        entries.add(APPLE_LOG);
        entries.add(APPLE_WOOD);
        entries.add(STRIPPED_APPLE_LOG);
        entries.add(STRIPPED_APPLE_WOOD);
        entries.add(APPLE_PLANKS);
        entries.add(APPLE_STAIRS);
        entries.add(APPLE_SLAB);
        entries.add(APPLE_FENCE);
        entries.add(APPLE_FENCE_GATE);
        entries.add(APPLE_DOOR);
        entries.add(APPLE_TRAPDOOR);
        entries.add(APPLE_PRESSURE_PLATE);
        entries.add(APPLE_BUTTON);
        
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

        setUpRegistries();
    }

    private static void setUpRegistries() {
        StrippableBlockRegistry.register(APPLE_LOG, STRIPPED_APPLE_LOG);
        StrippableBlockRegistry.register(APPLE_WOOD, STRIPPED_APPLE_WOOD);

        FlammableBlockRegistry flammables = FlammableBlockRegistry.getDefaultInstance();
        flammables.add(APPLE_LOG, 5, 5);
        flammables.add(STRIPPED_APPLE_LOG, 5, 5);
        flammables.add(APPLE_WOOD, 5, 5);
        flammables.add(STRIPPED_APPLE_WOOD, 5, 5);
        flammables.add(APPLE_PLANKS, 5, 20);
        flammables.add(APPLE_STAIRS, 5, 20);
        flammables.add(APPLE_SLAB, 5, 20);
        flammables.add(APPLE_FENCE, 5, 20);
        flammables.add(APPLE_FENCE_GATE, 5, 20);
        flammables.add(APPLE_LEAVES, 30, 60);
    }
}
