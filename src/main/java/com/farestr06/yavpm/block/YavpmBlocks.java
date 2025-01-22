package com.farestr06.yavpm.block;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.custom.KeylockBlock;
import com.farestr06.yavpm.block.custom.PolarizedGlassBlock;
import com.farestr06.yavpm.block.custom.PrickleLogBlock;
import com.farestr06.yavpm.block.custom.crop.*;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmFoods;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.world.feature.configured.YavpmTreeConfiguredFeatures;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.farestr06.api.block.BlockHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlocks {

    // region Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = makeSimpleBlockAndSimpleItem(makeId("glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 15)
    );
    public static final Block SOUL_GLOWING_OBSIDIAN = makeSimpleBlockAndSimpleItem(
            makeId("soul_glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 11)
    );
    // endregion

    // region Crops
    public static final Block WARPED_WART = makeBlockAndItem(
            makeId("warped_wart_crop"),
            WarpedWartCropBlock::new,
            AbstractBlock.Settings.copy(Blocks.NETHER_WART).mapColor(MapColor.TEAL),
            new Item.Settings().useItemPrefixedTranslationKey()
    );

    public static final Block BANANA_CROP = makeBlockAndItem(makeId("banana_crop"),
            BananaCropBlock::new, AbstractBlock.Settings.copy(Blocks.POTATOES),
            new Item.Settings().useItemPrefixedTranslationKey()
    );

    public static final Block RICE_CROP = makeBlockAndItem(makeId("rice_crop"),
            RiceCropBlock::new, AbstractBlock.Settings.copy(Blocks.WHEAT),
            new Item.Settings().useItemPrefixedTranslationKey()
    );

    public static final Block PEANUT_CROP = makeBlockAndItem(makeId("peanut_crop"),
            PeanutCropBlock::new, AbstractBlock.Settings.copy(Blocks.POTATOES),
            new Item.Settings().food(YavpmFoods.RAW_PEANUT).useItemPrefixedTranslationKey()
    );

    public static final Block OAK_SAPLING_CROP = makeBlockAndItem(
            makeId("oak_sapling_crop"),
            settings -> new SaplingCropBlock(settings) {
                @Override
                protected ItemConvertible getSeedsItem() {
                    return YavpmItems.ACORN;
                }
            },
            AbstractBlock.Settings.copy(Blocks.OAK_SAPLING),
            new Item.Settings().food(YavpmFoods.ACORN).useItemPrefixedTranslationKey()
    );

    public static final Block MAGIC_BEAN_CROP = makeBlockAndItem(
            makeId("magic_bean_crop"),
            MagicBeanCropBlock::new,
            AbstractBlock.Settings.copy(Blocks.POTATOES),
            new Item.Settings().food(YavpmFoods.MAGIC_BEAN)
    );

    public static final Block BITTER_BERRY_BUSH = makeBlockAndItem(
            makeId("bitter_berry_bush"),
            BitterBerryBushBlock::new,
            AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH),
            new Item.Settings().food(FoodComponents.SWEET_BERRIES).useItemPrefixedTranslationKey()
    );
    // endregion

    // region Igneous Stone
    public static final Block COBBLED_GRANITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_granite"), AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
    public static final Block COBBLED_DIORITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_diorite"), AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
    public static final Block COBBLED_ANDESITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_andesite"), AbstractBlock.Settings.copy(Blocks.COBBLESTONE));

    public static final Block COBBLED_GRANITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_granite_stairs"),
            settings -> new StairsBlock(COBBLED_GRANITE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.COBBLESTONE_STAIRS)
    );
    public static final Block COBBLED_DIORITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_diorite_stairs"),
            settings -> new StairsBlock(COBBLED_DIORITE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.COBBLESTONE_STAIRS)
    );
    public static final Block COBBLED_ANDESITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_andesite_stairs"),
            settings -> new StairsBlock(COBBLED_ANDESITE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.COBBLESTONE_STAIRS)
    );

    public static final Block COBBLED_GRANITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_granite_slab"), SlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLED_DIORITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_diorite_slab"), SlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLED_ANDESITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_andesite_slab"), SlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB));

    public static final Block COBBLED_GRANITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_granite_wall"), WallBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL));
    public static final Block COBBLED_DIORITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_diorite_wall"), WallBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL));
    public static final Block COBBLED_ANDESITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_andesite_wall"), WallBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL));

    public static final Block POLISHED_GRANITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_granite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_DIORITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_diorite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_ANDESITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_andesite_bricks"), AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));

    public static final Block POLISHED_GRANITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_granite_brick_stairs"),
            settings -> new StairsBlock(POLISHED_GRANITE_BRICKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    );
    public static final Block POLISHED_DIORITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_diorite_brick_stairs"),
            settings -> new StairsBlock(POLISHED_DIORITE_BRICKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    );
    public static final Block POLISHED_ANDESITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_andesite_brick_stairs"),
            settings -> new StairsBlock(POLISHED_ANDESITE_BRICKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)
    );

    public static final Block POLISHED_GRANITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_granite_brick_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB));
    public static final Block POLISHED_DIORITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_diorite_brick_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB));
    public static final Block POLISHED_ANDESITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_andesite_brick_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB));

    public static final Block POLISHED_GRANITE_WALL = makeBlockAndSimpleItem(makeId("polished_granite_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.GRANITE_WALL));
    public static final Block POLISHED_DIORITE_WALL = makeBlockAndSimpleItem(makeId("polished_diorite_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.DIORITE_WALL));
    public static final Block POLISHED_ANDESITE_WALL = makeBlockAndSimpleItem(makeId("polished_andesite_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.ANDESITE_WALL));

    public static final Block POLISHED_GRANITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_granite_brick_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL));
    public static final Block POLISHED_DIORITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_diorite_brick_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL));
    public static final Block POLISHED_ANDESITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_andesite_brick_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL));

    public static final BlockFamily COBBLED_GRANITE_FAMILY = new BlockFamily.Builder(COBBLED_GRANITE)
            .slab(COBBLED_GRANITE_SLAB).stairs(COBBLED_GRANITE_STAIRS).wall(COBBLED_GRANITE_WALL).build();
    public static final BlockFamily COBBLED_DIORITE_FAMILY = new BlockFamily.Builder(COBBLED_DIORITE)
            .slab(COBBLED_DIORITE_SLAB).stairs(COBBLED_DIORITE_STAIRS).wall(COBBLED_DIORITE_WALL).build();
    public static final BlockFamily COBBLED_ANDESITE_FAMILY = new BlockFamily.Builder(COBBLED_ANDESITE)
            .slab(COBBLED_ANDESITE_SLAB).stairs(COBBLED_ANDESITE_STAIRS).wall(COBBLED_ANDESITE_WALL).build();

    public static final BlockFamily POLISHED_GRANITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_GRANITE_BRICKS)
            .slab(POLISHED_GRANITE_BRICK_SLAB).stairs(POLISHED_GRANITE_BRICK_STAIRS).wall(POLISHED_GRANITE_BRICK_WALL).build();
    public static final BlockFamily POLISHED_DIORITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_DIORITE_BRICKS)
            .slab(POLISHED_DIORITE_BRICK_SLAB).stairs(POLISHED_DIORITE_BRICK_STAIRS).wall(POLISHED_DIORITE_BRICK_WALL).build();
    public static final BlockFamily POLISHED_ANDESITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_ANDESITE_BRICKS)
            .slab(POLISHED_ANDESITE_BRICK_SLAB).stairs(POLISHED_ANDESITE_BRICK_STAIRS).wall(POLISHED_ANDESITE_BRICK_WALL).build();
    // endregion

    // region Kimberlite
    public static final Block KIMBERLITE = makeSimpleBlockAndSimpleItem(
            makeId("kimberlite"),
            AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE).requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE = makeSimpleBlockAndSimpleItem(
            makeId("polished_kimberlite"),
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE).requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICKS = makeSimpleBlockAndSimpleItem(
            makeId("polished_kimberlite_bricks"),
            AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block KIMBERLITE_STAIRS = makeBlockAndSimpleItem(
            makeId("kimberlite_stairs"),
            settings -> new StairsBlock(KIMBERLITE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_STAIRS).requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block KIMBERLITE_SLAB = makeBlockAndSimpleItem(
            makeId("kimberlite_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_SLAB)
                    .requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block KIMBERLITE_WALL = makeBlockAndSimpleItem(
            makeId("kimberlite_wall"),
            WallBlock::new,AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block POLISHED_KIMBERLITE_STAIRS = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_stairs"),
            settings -> new StairsBlock(POLISHED_KIMBERLITE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_STAIRS).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_SLAB = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_SLAB).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_WALL = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block POLISHED_KIMBERLITE_BRICK_STAIRS = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_stairs"),
            settings -> new StairsBlock(POLISHED_KIMBERLITE_BRICKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_STAIRS).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_SLAB = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_SLAB).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_WALL = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_wall"),
            WallBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final BlockFamily KIMBERLITE_FAMILY = new BlockFamily.Builder(KIMBERLITE)
            .slab(KIMBERLITE_SLAB).stairs(KIMBERLITE_STAIRS).wall(KIMBERLITE_WALL).build();
    public static final BlockFamily POLISHED_KIMBERLITE_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE)
            .slab(POLISHED_KIMBERLITE_SLAB).stairs(POLISHED_KIMBERLITE_STAIRS).wall(POLISHED_KIMBERLITE_WALL).build();
    public static final BlockFamily POLISHED_KIMBERLITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE_BRICKS)
            .slab(POLISHED_KIMBERLITE_BRICK_SLAB).stairs(POLISHED_KIMBERLITE_BRICK_STAIRS).wall(POLISHED_KIMBERLITE_BRICK_WALL).build();
    // endregion

    public static final Block POLARIZED_GLASS = makeBlockAndSimpleItem(
            makeId("polarized_glass"),
            PolarizedGlassBlock::new,
            AbstractBlock.Settings.copy(Blocks.TINTED_GLASS).mapColor(MapColor.BRIGHT_TEAL)
    );

    public static final Block KEYLOCK = makeBlockAndSimpleItem(
            makeId("keylock"),
            KeylockBlock::new,
            AbstractBlock.Settings.copy(Blocks.OBSERVER)
    );

    // region Applewood
    public static final Block APPLE_LOG = makeBlockAndSimpleItem(
            makeId("apple_log"),
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)
    );
    public static final Block APPLE_WOOD = makeBlockAndSimpleItem(
            makeId("apple_wood"),
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.CHERRY_WOOD)
    );
    public static final Block STRIPPED_APPLE_LOG = makeBlockAndSimpleItem(
            makeId("stripped_apple_log"),
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_LOG)
    );
    public static final Block STRIPPED_APPLE_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_apple_wood"),
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_WOOD)
    );
    public static final Block APPLE_LEAVES = makeBlockAndSimpleItem(
            makeId("apple_leaves"),
            LeavesBlock::new,
            AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES)
    );

    public static final Block APPLE_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("apple_planks"),
            AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)
    );
    public static final Block APPLE_STAIRS = makeBlockAndSimpleItem(
            makeId("apple_stairs"),
            settings -> new StairsBlock(APPLE_PLANKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.CHERRY_STAIRS)
    );
    public static final Block APPLE_SLAB = makeBlockAndSimpleItem(
            makeId("apple_slab"),
            SlabBlock::new,
            AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB)
    );
    public static final Block APPLE_FENCE = makeBlockAndSimpleItem(
            makeId("apple_fence"),
            FenceBlock::new,
            AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE)
    );
    public static final Block APPLE_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("apple_fence_gate"),
            settings -> new FenceGateBlock(WoodType.CHERRY, settings),
            AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE)
    );
    public static final Block APPLE_DOOR = makeBlockAndSimpleItem(
            makeId("apple_door"),
            settings -> new DoorBlock(BlockSetType.CHERRY, settings),
            AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR)
    );
    public static final Block APPLE_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("apple_trapdoor"),
            settings -> new TrapdoorBlock(BlockSetType.CHERRY, settings),

            AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR)
    );
    public static final Block APPLE_BUTTON = makeBlockAndSimpleItem(
            makeId("apple_button"),
            settings -> new ButtonBlock(BlockSetType.CHERRY, 30, settings),
            AbstractBlock.Settings.copy(Blocks.CHERRY_BUTTON)
    );
    public static final Block APPLE_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("apple_pressure_plate"),
            settings -> new PressurePlateBlock(BlockSetType.CHERRY, settings),
            AbstractBlock.Settings.copy(Blocks.CHERRY_PRESSURE_PLATE)
    );
    public static final Block APPLE_SAPLING = makeBlockAndSimpleItem(
            makeId("apple_sapling"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.APPLEWOOD_GENERATOR, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
    );
    public static final BlockFamily APPLE_FAMILY = BlockFamilies.register(APPLE_PLANKS)
            .slab(APPLE_SLAB).stairs(APPLE_STAIRS).fence(APPLE_FENCE).fenceGate(APPLE_FENCE_GATE)
            .door(APPLE_DOOR).trapdoor(APPLE_TRAPDOOR)
            .pressurePlate(APPLE_PRESSURE_PLATE).button(APPLE_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();
    // endregion

    // region Prickle Wood
    public static final Block PRICKLE_LOG = makeBlockAndSimpleItem(
            makeId("prickle_log"),
            PrickleLogBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_STEM).burnable()
    );
    public static final Block PRICKLE_WOOD = makeBlockAndSimpleItem(
            makeId("prickle_wood"),
            PrickleLogBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_HYPHAE).burnable()
    );
    public static final Block STRIPPED_PRICKLE_LOG = makeBlockAndSimpleItem(
            makeId("stripped_prickle_log"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_STEM).burnable()
    );
    public static final Block STRIPPED_PRICKLE_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_prickle_wood"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_HYPHAE).burnable()
    );
    public static final Block PRICKLE_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("prickle_planks"),
            AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).burnable()
    );
    public static final Block PRICKLE_STAIRS = makeBlockAndSimpleItem(
            makeId("prickle_stairs"),
            settings -> new StairsBlock(PRICKLE_PLANKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_STAIRS).burnable()
    );
    public static final Block PRICKLE_SLAB = makeBlockAndSimpleItem(
            makeId("prickle_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_SLAB).burnable()
    );
    public static final Block PRICKLE_FENCE = makeBlockAndSimpleItem(
            makeId("prickle_fence"),
            FenceBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_FENCE).burnable()
    );
    public static final Block PRICKLE_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("prickle_fence_gate"),
            settings -> new FenceGateBlock(WoodType.WARPED, settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_FENCE_GATE)
    );
    public static final Block PRICKLE_DOOR = makeBlockAndSimpleItem(
            makeId("prickle_door"),
            settings -> new DoorBlock(BlockSetType.WARPED, settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_DOOR)
    );
    public static final Block PRICKLE_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("prickle_trapdoor"),
            settings -> new TrapdoorBlock(BlockSetType.WARPED, settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_TRAPDOOR)
    );
    public static final Block PRICKLE_BUTTON = makeBlockAndSimpleItem(
            makeId("prickle_button"),
            settings -> new ButtonBlock(BlockSetType.WARPED, 30, settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_BUTTON)
    );
    public static final Block PRICKLE_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("prickle_pressure_plate"),
            settings -> new PressurePlateBlock(BlockSetType.WARPED, settings),
            AbstractBlock.Settings.copy(Blocks.WARPED_PRESSURE_PLATE)
    );


    public static final BlockFamily PRICKLE_FAMILY = BlockFamilies.register(PRICKLE_PLANKS)
            .slab(PRICKLE_SLAB).stairs(PRICKLE_STAIRS).fence(PRICKLE_FENCE).fenceGate(PRICKLE_FENCE_GATE)
            .door(PRICKLE_DOOR).trapdoor(PRICKLE_TRAPDOOR)
            .pressurePlate(PRICKLE_PRESSURE_PLATE).button(PRICKLE_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block PRICKLE_SHOOT = makeBlockAndSimpleItem(
            makeId("prickle_shoot"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.PRICKLE_GENERATOR, settings) {
                @Override
                protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
                    return floor.isOf(Blocks.END_STONE);
                }
            },
            AbstractBlock.Settings.copy(Blocks.WARPED_FUNGUS)
    );
    // endregion

    // region Persimmon Wood
    public static final Block PERSIMMON_LOG = makeBlockAndSimpleItem(
            makeId("persimmon_log"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LOG)
    );
    public static final Block PERSIMMON_WOOD = makeBlockAndSimpleItem(
            makeId("persimmon_wood"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_WOOD)
    );
    public static final Block STRIPPED_PERSIMMON_LOG = makeBlockAndSimpleItem(
            makeId("stripped_persimmon_log"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)
    );
    public static final Block STRIPPED_PERSIMMON_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_persimmon_wood"),
            PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)
    );
    public static final Block PERSIMMON_LEAVES = makeBlockAndSimpleItem(
            makeId("persimmon_leaves"),
            LeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
    );

    public static final Block PERSIMMON_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("persimmon_planks"),
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
    );
    public static final Block PERSIMMON_STAIRS = makeBlockAndSimpleItem(
            makeId("persimmon_stairs"),
            settings -> new StairsBlock(PERSIMMON_PLANKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
    );
    public static final Block PERSIMMON_SLAB = makeBlockAndSimpleItem(
            makeId("persimmon_slab"),
            SlabBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_SLAB)
    );
    public static final Block PERSIMMON_FENCE = makeBlockAndSimpleItem(
            makeId("persimmon_fence"),
            FenceBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_FENCE)
    );
    public static final Block PERSIMMON_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("persimmon_fence_gate"), settings -> new FenceGateBlock(WoodType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_FENCE)
    );
    public static final Block PERSIMMON_DOOR = makeBlockAndSimpleItem(
            makeId("persimmon_door"),
            settings -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_DOOR)
    );
    public static final Block PERSIMMON_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("persimmon_trapdoor"),
            settings -> new TrapdoorBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)
    );
    public static final Block PERSIMMON_BUTTON = makeBlockAndSimpleItem(
            makeId("persimmon_button"),
            settings -> new ButtonBlock(BlockSetType.OAK, 30, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)
    );
    public static final Block PERSIMMON_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("persimmon_pressure_plate"),
            settings -> new PressurePlateBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)
    );

    public static final BlockFamily PERSIMMON_FAMILY = BlockFamilies.register(PERSIMMON_PLANKS)
            .slab(PERSIMMON_SLAB).stairs(PERSIMMON_STAIRS).fence(PERSIMMON_FENCE).fenceGate(PERSIMMON_FENCE_GATE)
            .door(PERSIMMON_DOOR).trapdoor(PERSIMMON_TRAPDOOR)
            .pressurePlate(PERSIMMON_PRESSURE_PLATE).button(PERSIMMON_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block PERSIMMON_SAPLING = makeBlockAndSimpleItem(
            makeId("persimmon_sapling"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.PERSIMMON_GENERATOR, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
    );
    // endregion

    // region Graphite and Graphene
    public static final Block GRAPHITE_BLOCK = makeSimpleBlockAndSimpleItem(makeId("graphite_block"),
            AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.BLACK).strength(2f, 2.5f).sounds(BlockSoundGroup.TUFF));
    public static final Block GRAPHENE_BLOCK = makeSimpleBlockAndSimpleItem(makeId("graphene_block"),
            AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.BLACK).strength(6f, 7.5f).sounds(BlockSoundGroup.POLISHED_TUFF));
    // endregion

    // region Fake
    public static final Block FAKE_LOG = makeBlock(
            makeId("fake_log"),
            FakeLogBlock::new,
            AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM).sounds(BlockSoundGroup.WOOD)
    );
    public static final Block FAKE_ORE = makeBlock(
            makeId("fake_ore"),
            FakeOreBlock::new,
            AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
    );
    // endregion

    public static final Block VOID_WATER = makeBlock(
            makeId("void_water"),
            settings -> new FluidBlock(YavpmFluids.STILL_VOID_WATER, settings) {
                @Override
                protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
                    if (!world.isClient) {
                        if (entity instanceof LivingEntity livingEntity) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(YavpmStatusEffects.VOID_TOUCHED, 220));
                        }
                    }
                }
            },
            AbstractBlock.Settings.copy(Blocks.WATER)
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");
        setUpRegistries();
    }

    private static void setUpRegistries() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Making logs strippable...");
        StrippableBlockRegistry.register(APPLE_LOG, STRIPPED_APPLE_LOG);
        StrippableBlockRegistry.register(APPLE_WOOD, STRIPPED_APPLE_WOOD);
        StrippableBlockRegistry.register(PERSIMMON_LOG, STRIPPED_PERSIMMON_LOG);
        StrippableBlockRegistry.register(PERSIMMON_WOOD, STRIPPED_PERSIMMON_WOOD);
        StrippableBlockRegistry.register(PRICKLE_LOG, STRIPPED_PRICKLE_LOG);
        StrippableBlockRegistry.register(PRICKLE_WOOD, STRIPPED_PRICKLE_WOOD);

        YetAnotherVanillaPlusMod.LOGGER.debug("Making blocks flammable...");
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

        flammables.add(PERSIMMON_LOG, 5, 5);
        flammables.add(STRIPPED_PERSIMMON_LOG, 5, 5);
        flammables.add(PERSIMMON_WOOD, 5, 5);
        flammables.add(STRIPPED_PERSIMMON_WOOD, 5, 5);
        flammables.add(PERSIMMON_PLANKS, 5, 20);
        flammables.add(PERSIMMON_STAIRS, 5, 20);
        flammables.add(PERSIMMON_SLAB, 5, 20);
        flammables.add(PERSIMMON_FENCE, 5, 20);
        flammables.add(PERSIMMON_FENCE_GATE, 5, 20);
        flammables.add(PERSIMMON_LEAVES, 30, 60);

        flammables.add(PRICKLE_LOG, 2, 2);
        flammables.add(STRIPPED_PRICKLE_LOG, 2, 2);
        flammables.add(PRICKLE_WOOD, 2, 2);
        flammables.add(STRIPPED_PRICKLE_WOOD, 2, 2);
        flammables.add(PRICKLE_PLANKS, 2, 10);
        flammables.add(PRICKLE_STAIRS, 2, 10);
        flammables.add(PRICKLE_SLAB, 2, 10);
        flammables.add(PRICKLE_FENCE, 2, 10);
        flammables.add(PRICKLE_FENCE_GATE, 2, 10);
    }
}
