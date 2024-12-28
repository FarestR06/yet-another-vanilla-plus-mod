package com.farestr06.yavpm.block;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.custom.*;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.world.feature.configured.YavpmTreeConfiguredFeatures;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.farestr06.api.block.BlockHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlocks {

    // region Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = makeBlockAndItem(makeId("glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 15)
    );
    public static final Block SOUL_GLOWING_OBSIDIAN = makeBlockAndItem(
            makeId("soul_glowing_obsidian"),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance(state -> 11)
    );
    // endregion

    // region Crops
    public static final Block WARPED_WART = makeAdvancedBlock(
            makeId("warped_wart_crop"),
            new WarpedWartCropBlock(
                    AbstractBlock.Settings.copy(Blocks.NETHER_WART).mapColor(MapColor.TEAL)
            )
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

    public static final Block MAGIC_BEAN_CROP = makeAdvancedBlock(
            makeId("magic_bean_crop"),
            new MagicBeanCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES))
    );
    // endregion

    // region Kimberlite
    public static final Block KIMBERLITE = makeBlockAndItem(
            makeId("kimberlite"),
            AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE).requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE = makeBlockAndItem(
            makeId("polished_kimberlite"),
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE).requiresTool().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICKS = makeBlockAndItem(
            makeId("polished_kimberlite_bricks"),
            AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS).requiresTool()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block KIMBERLITE_STAIRS = makeAdvancedBlockAndItem(
            makeId("kimberlite_stairs"),
            new StairsBlock(KIMBERLITE.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_STAIRS).requiresTool()
                            .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block KIMBERLITE_SLAB = makeAdvancedBlockAndItem(
            makeId("kimberlite_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_SLAB).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block KIMBERLITE_WALL = makeAdvancedBlockAndItem(
            makeId("kimberlite_wall"),
            new WallBlock(AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );

    public static final Block POLISHED_KIMBERLITE_STAIRS = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_stairs"),
            new StairsBlock(POLISHED_KIMBERLITE.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_STAIRS).requiresTool()
                            .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block POLISHED_KIMBERLITE_SLAB = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_SLAB).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block POLISHED_KIMBERLITE_WALL = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_wall"),
            new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );

    public static final Block POLISHED_KIMBERLITE_BRICK_STAIRS = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_brick_stairs"),
            new StairsBlock(POLISHED_KIMBERLITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_STAIRS).requiresTool()
                            .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_SLAB = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_brick_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_SLAB).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_WALL = makeAdvancedBlockAndItem(
            makeId("polished_kimberlite_brick_wall"),
            new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_WALL).requiresTool()
                    .instrument(NoteBlockInstrument.PLING))
    );

    public static final BlockFamily KIMBERLITE_FAMILY = new BlockFamily.Builder(KIMBERLITE)
            .slab(KIMBERLITE_SLAB).stairs(KIMBERLITE_STAIRS).wall(KIMBERLITE_WALL).build();
    public static final BlockFamily POLISHED_KIMBERLITE_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE)
            .slab(POLISHED_KIMBERLITE_SLAB).stairs(POLISHED_KIMBERLITE_STAIRS).wall(POLISHED_KIMBERLITE_WALL).build();
    public static final BlockFamily POLISHED_KIMBERLITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE_BRICKS)
            .slab(POLISHED_KIMBERLITE_BRICK_SLAB).stairs(POLISHED_KIMBERLITE_BRICK_STAIRS).wall(POLISHED_KIMBERLITE_BRICK_WALL).build();
    // endregion

    public static final Block POLARIZED_GLASS = makeAdvancedBlockAndItem(
            makeId("polarized_glass"),
            new PolarizedGlassBlock(
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
                    AbstractBlock.Settings.copy(Blocks.CHERRY_WALL_SIGN)
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

    public static final BlockFamily APPLE_FAMILY = BlockFamilies.register(APPLE_PLANKS)
            .slab(APPLE_SLAB).stairs(APPLE_STAIRS).fence(APPLE_FENCE).fenceGate(APPLE_FENCE_GATE)
            .door(APPLE_DOOR).trapdoor(APPLE_TRAPDOOR).sign(APPLE_SIGN, APPLE_WALL_SIGN)
            .pressurePlate(APPLE_PRESSURE_PLATE).button(APPLE_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block APPLE_SAPLING = makeAdvancedBlockAndItem(
            makeId("apple_sapling"),
            new SaplingBlock(YavpmTreeConfiguredFeatures.APPLEWOOD_GENERATOR, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING))
    );
    // endregion

    // region Prickle Wood
    public static final Block PRICKLE_LOG = makeAdvancedBlockAndItem(
            makeId("prickle_log"),
            new PrickleLogBlock(AbstractBlock.Settings.copy(Blocks.WARPED_STEM).burnable())
    );
    public static final Block PRICKLE_WOOD = makeAdvancedBlockAndItem(
            makeId("prickle_wood"),
            new PrickleLogBlock(AbstractBlock.Settings.copy(Blocks.WARPED_HYPHAE).burnable())
    );
    public static final Block STRIPPED_PRICKLE_LOG = makeAdvancedBlockAndItem(
            makeId("stripped_prickle_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_STEM).burnable())
    );
    public static final Block STRIPPED_PRICKLE_WOOD = makeAdvancedBlockAndItem(
            makeId("stripped_prickle_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_HYPHAE).burnable())
    );
    public static final Block PRICKLE_PLANKS = makeBlockAndItem(
            makeId("prickle_planks"),
            AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).burnable()
    );
    public static final Block PRICKLE_STAIRS = makeAdvancedBlockAndItem(
            makeId("prickle_stairs"),
            new StairsBlock(PRICKLE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WARPED_STAIRS).burnable())
    );
    public static final Block PRICKLE_SLAB = makeAdvancedBlockAndItem(
            makeId("prickle_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.WARPED_SLAB).burnable())
    );
    public static final Block PRICKLE_FENCE = makeAdvancedBlockAndItem(
            makeId("prickle_fence"),
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.WARPED_FENCE).burnable())
    );
    public static final Block PRICKLE_FENCE_GATE = makeAdvancedBlockAndItem(makeId("prickle_fence_gate"), new FenceGateBlock(
            WoodType.WARPED,
            AbstractBlock.Settings.copy(Blocks.WARPED_FENCE_GATE))
    );
    public static final Block PRICKLE_DOOR = makeAdvancedBlockAndItem(
            makeId("prickle_door"),
            new DoorBlock(
                    BlockSetType.WARPED,
                    AbstractBlock.Settings.copy(Blocks.WARPED_DOOR)
            )
    );
    public static final Block PRICKLE_TRAPDOOR = makeAdvancedBlockAndItem(
            makeId("prickle_trapdoor"),
            new TrapdoorBlock(
                    BlockSetType.WARPED,
                    AbstractBlock.Settings.copy(Blocks.WARPED_TRAPDOOR)
            )
    );
    public static final Block PRICKLE_BUTTON = makeAdvancedBlockAndItem(
            makeId("prickle_button"),
            new ButtonBlock(
                    BlockSetType.WARPED,
                    30,
                    AbstractBlock.Settings.copy(Blocks.WARPED_BUTTON)
            )
    );
    public static final Block PRICKLE_PRESSURE_PLATE = makeAdvancedBlockAndItem(
            makeId("prickle_pressure_plate"),
            new PressurePlateBlock(
                    BlockSetType.WARPED,
                    AbstractBlock.Settings.copy(Blocks.WARPED_PRESSURE_PLATE)
            )
    );

    protected static final Identifier PRICKLE_SIGN_TEXTURE = makeId("entity/signs/prickle");
    protected static final Identifier PRICKLE_HANGING_SIGN_TEXTURE = makeId("entity/signs/hanging/prickle");
    protected static final Identifier PRICKLE_HANGING_SIGN_GUI_TEXTURE = makeId("textures/gui/hanging_signs/prickle");

    public static final Block PRICKLE_SIGN = makeAdvancedBlock(
            makeId("prickle_sign"),
            new TerraformSignBlock(
                    PRICKLE_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.WARPED_SIGN)
            )
    );
    public static final Block PRICKLE_WALL_SIGN = makeAdvancedBlock(
            makeId("prickle_wall_sign"),
            new TerraformWallSignBlock(
                    PRICKLE_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.WARPED_WALL_SIGN)
            )
    );
    public static final Block PRICKLE_HANGING_SIGN = makeAdvancedBlock(
            makeId("prickle_hanging_sign"),
            new TerraformHangingSignBlock(
                    PRICKLE_HANGING_SIGN_TEXTURE,
                    PRICKLE_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.WARPED_HANGING_SIGN)
            )
    );
    public static final Block PRICKLE_WALL_HANGING_SIGN = makeAdvancedBlock(
            makeId("prickle_hanging_sign"),
            new TerraformWallHangingSignBlock(
                    PRICKLE_HANGING_SIGN_TEXTURE,
                    PRICKLE_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.WARPED_WALL_HANGING_SIGN)
            )
    );

    public static final BlockFamily PRICKLE_FAMILY = BlockFamilies.register(PRICKLE_PLANKS)
            .slab(PRICKLE_SLAB).stairs(PRICKLE_STAIRS).fence(PRICKLE_FENCE).fenceGate(PRICKLE_FENCE_GATE)
            .door(PRICKLE_DOOR).trapdoor(PRICKLE_TRAPDOOR).sign(PRICKLE_SIGN, PRICKLE_WALL_SIGN)
            .pressurePlate(PRICKLE_PRESSURE_PLATE).button(PRICKLE_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block PRICKLE_SHOOT = makeAdvancedBlockAndItem(
            makeId("prickle_shoot"),
            new SaplingBlock(YavpmTreeConfiguredFeatures.PRICKLE_GENERATOR, AbstractBlock.Settings.copy(Blocks.WARPED_FUNGUS)) {
                @Override
                protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
                    return floor.isOf(Blocks.END_STONE);
                }
            }
    );
    // endregion

    // region Persimmon Wood
    public static final Block PERSIMMON_LOG = makeAdvancedBlockAndItem(
            makeId("persimmon_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG))
    );
    public static final Block PERSIMMON_WOOD = makeAdvancedBlockAndItem(
            makeId("persimmon_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD))
    );
    public static final Block STRIPPED_PERSIMMON_LOG = makeAdvancedBlockAndItem(
            makeId("stripped_persimmon_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG))
    );
    public static final Block STRIPPED_PERSIMMON_WOOD = makeAdvancedBlockAndItem(
            makeId("stripped_persimmon_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD))
    );
    public static final Block PERSIMMON_LEAVES = makeAdvancedBlockAndItem(
            makeId("persimmon_leaves"),
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES))
    );

    public static final Block PERSIMMON_PLANKS = makeBlockAndItem(
            makeId("persimmon_planks"),
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
    );
    public static final Block PERSIMMON_STAIRS = makeAdvancedBlockAndItem(
            makeId("persimmon_stairs"),
            new StairsBlock(
                    PERSIMMON_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
            )
    );
    public static final Block PERSIMMON_SLAB = makeAdvancedBlockAndItem(
            makeId("persimmon_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB))
    );
    public static final Block PERSIMMON_FENCE = makeAdvancedBlockAndItem(
            makeId("persimmon_fence"),
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE))
    );
    public static final Block PERSIMMON_FENCE_GATE = makeAdvancedBlockAndItem(
            makeId("persimmon_fence_gate"),
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE))
    );
    public static final Block PERSIMMON_DOOR = makeAdvancedBlockAndItem(
            makeId("persimmon_door"),
            new DoorBlock(
                    BlockSetType.OAK,
                    AbstractBlock.Settings.copy(Blocks.OAK_DOOR)
            )
    );
    public static final Block PERSIMMON_TRAPDOOR = makeAdvancedBlockAndItem(
            makeId("persimmon_trapdoor"),
            new TrapdoorBlock(
                    BlockSetType.OAK,
                    AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)
            )
    );
    public static final Block PERSIMMON_BUTTON = makeAdvancedBlockAndItem(
            makeId("persimmon_button"),
            new ButtonBlock(
                    BlockSetType.OAK,
                    30,
                    AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)
            )
    );
    public static final Block PERSIMMON_PRESSURE_PLATE = makeAdvancedBlockAndItem(
            makeId("persimmon_pressure_plate"),
            new PressurePlateBlock(
                    BlockSetType.OAK,
                    AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)
            )
    );

    protected static final Identifier PERSIMMON_SIGN_TEXTURE = makeId("entity/signs/persimmon");
    protected static final Identifier PERSIMMON_HANGING_SIGN_TEXTURE = makeId("entity/signs/hanging/persimmon");
    protected static final Identifier PERSIMMON_HANGING_SIGN_GUI_TEXTURE = makeId("textures/gui/hanging_signs/persimmon");

    public static final Block PERSIMMON_SIGN = makeAdvancedBlock(
            makeId("persimmon_sign"),
            new TerraformSignBlock(
                    PERSIMMON_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.OAK_SIGN)
            )
    );
    public static final Block PERSIMMON_WALL_SIGN = makeAdvancedBlock(
            makeId("persimmon_wall_sign"),
            new TerraformWallSignBlock(
                    PERSIMMON_SIGN_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)
            )
    );
    public static final Block PERSIMMON_HANGING_SIGN = makeAdvancedBlock(
            makeId("persimmon_hanging_sign"),
            new TerraformHangingSignBlock(
                    PERSIMMON_HANGING_SIGN_TEXTURE,
                    PERSIMMON_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)
            )
    );
    public static final Block PERSIMMON_WALL_HANGING_SIGN = makeAdvancedBlock(
            makeId("persimmon_hanging_sign"),
            new TerraformWallHangingSignBlock(
                    PERSIMMON_HANGING_SIGN_TEXTURE,
                    PERSIMMON_HANGING_SIGN_GUI_TEXTURE,
                    AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)
            )
    );

    public static final BlockFamily PERSIMMON_FAMILY = BlockFamilies.register(PERSIMMON_PLANKS)
            .slab(PERSIMMON_SLAB).stairs(PERSIMMON_STAIRS).fence(PERSIMMON_FENCE).fenceGate(PERSIMMON_FENCE_GATE)
            .door(PERSIMMON_DOOR).trapdoor(PERSIMMON_TRAPDOOR).sign(PERSIMMON_SIGN, PERSIMMON_WALL_SIGN)
            .pressurePlate(PERSIMMON_PRESSURE_PLATE).button(PERSIMMON_BUTTON)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block PERSIMMON_SAPLING = makeAdvancedBlockAndItem(
            makeId("persimmon_sapling"),
            new SaplingBlock(YavpmTreeConfiguredFeatures.PERSIMMON_GENERATOR, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING))
    );
    // endregion

    // region Graphite and Graphene
    public static final Block GRAPHITE_BLOCK = makeBlockAndItem(makeId("graphite_block"),
            AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.BLACK).strength(2f, 2.5f).sounds(BlockSoundGroup.TUFF));
    public static final Block GRAPHENE_BLOCK = makeBlockAndItem(makeId("graphene_block"),
            AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.BLACK).strength(6f, 7.5f).sounds(BlockSoundGroup.POLISHED_TUFF));
    // endregion

    // region Fake
    public static final Block FAKE_LOG = makeAdvancedBlock(
            makeId("fake_log"),
            new FakeLogBlock()
    );
    public static final Block FAKE_ORE = makeAdvancedBlock(
            makeId("fake_ore"),
            new FakeOreBlock()
    );
    // endregion

    public static final Block VOID_WATER = makeAdvancedBlock(
            makeId("void_water"),
            new FluidBlock(YavpmFluids.STILL_VOID_WATER, AbstractBlock.Settings.copy(Blocks.WATER)) {
                @Override
                protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
                    if (!world.isClient) {
                        if (entity instanceof LivingEntity livingEntity) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(YavpmStatusEffects.VOID_TOUCHED, 220));
                        }
                    }
                }
            }
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
