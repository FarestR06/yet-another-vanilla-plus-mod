package com.farestr06.yavpm.block;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.custom.*;
import com.farestr06.yavpm.block.custom.crop.*;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.block.custom.nullium.NullTorchBlock;
import com.farestr06.yavpm.block.custom.recycler.RecyclerBlock;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmFoods;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.world.feature.configured.YavpmTreeConfiguredFeatures;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.farestr06.api.block.BlockHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmBlocks {

    public static final Block NAHCOLITE_ORE = makeBlockAndSimpleItem(
            makeId("nahcolite_ore"),
            settings -> new DropExperienceBlock(UniformInt.of(1, 5), settings),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
    );
    public static final Block DEEPSLATE_NAHCOLITE_ORE = makeBlockAndSimpleItem(
            makeId("deepslate_nahcolite_ore"),
            settings -> new DropExperienceBlock(UniformInt.of(1, 5), settings),
            BlockBehaviour.Properties.ofFullCopy(NAHCOLITE_ORE)
                    .mapColor(MapColor.DEEPSLATE)
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)
    );

    // region Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = makeSimpleBlockAndSimpleItem(makeId("glowing_obsidian"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel(state -> HANDLER.instance().glowingObsidianLuminance)
    );
    public static final Block SOUL_GLOWING_OBSIDIAN = makeSimpleBlockAndSimpleItem(
            makeId("soul_glowing_obsidian"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel(state -> HANDLER.instance().soulGlowingObsidianLuminance)
    );
    // endregion

    // region Crops
    public static final Block WARPED_WART_CROP = makeBlockAndAliasedItem(
            makeId("warped_wart_crop"), makeId("warped_wart"),
            WarpedWartCropBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.WARPED_NYLIUM),
            new Item.Properties().useItemDescriptionPrefix()
    );

    public static final Block BANANA_CROP = makeBlockAndAliasedItem(
            makeId("banana_crop"), makeId("banana_seeds"),
            BananaCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES),
            new Item.Properties().useItemDescriptionPrefix()
    );

    public static final Block RICE_CROP = makeBlockAndAliasedItem(
            makeId("rice_crop"), makeId("rice_seeds"),
            RiceCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT),
            new Item.Properties().useItemDescriptionPrefix()
    );

    public static final Block PEANUT_CROP = makeBlockAndAliasedItem(
            makeId("peanut_crop"), makeId("peanut"),
            PeanutCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES),
            new Item.Properties().food(YavpmFoods.RAW_PEANUT, YavpmFoods.ConsumableComponents.RAW_PEANUT).useItemDescriptionPrefix()
    );

    public static final Block MAGIC_BEAN_CROP = makeBlockAndAliasedItem(
            makeId("magic_bean_crop"), makeId("magic_bean"),
            MagicBeanCropBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES),
            new Item.Properties().food(YavpmFoods.MAGIC_BEAN)
    );

    public static final Block BITTER_BERRY_BUSH = makeBlockAndAliasedItem(
            makeId("bitter_berry_bush"), makeId("bitter_berries"),
            BitterBerryBushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH),
            new Item.Properties().food(Foods.SWEET_BERRIES).useItemDescriptionPrefix()
    );

    public static final ResourceKey<Block> CANTALOUPE_KEY = key("cantaloupe");
    public static final ResourceKey<Block> CANTALOUPE_STEM_KEY = key("cantaloupe_stem");
    public static final ResourceKey<Block> ATTACHED_CANTALOUPE_STEM_KEY = key("attached_cantaloupe_stem");

    public static final Block CANTALOUPE = register(
            CANTALOUPE_KEY, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
    );
    public static final Block ATTACHED_CANTALOUPE_STEM = register(
            ATTACHED_CANTALOUPE_STEM_KEY,
            settings -> new AttachedStemBlock(CANTALOUPE_STEM_KEY, CANTALOUPE_KEY, YavpmItems.CANTALOUPE_SEEDS_KEY, settings),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final Block CANTALOUPE_STEM = register(
            CANTALOUPE_STEM_KEY,
            settings -> new StemBlock(CANTALOUPE_KEY, ATTACHED_CANTALOUPE_STEM_KEY, YavpmItems.CANTALOUPE_SEEDS_KEY, settings),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.HARD_CROP)
                    .pushReaction(PushReaction.DESTROY)
    );

    // region Sapling
    public static final Block OAK_SAPLING_CROP = makeBlockAndAliasedItem(
            makeId("oak_sapling_crop"),
            makeId("acorn"),
            SaplingCropBlock.Oak::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING),
            new Item.Properties().food(YavpmFoods.ACORN, Consumables.DRIED_KELP).useItemDescriptionPrefix()
    );
    public static final Block BIRCH_SAPLING_CROP = makeBlockAndAliasedItem(
            makeId("birch_sapling_crop"),
            makeId("birch_seeds"),
            SaplingCropBlock.Birch::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SAPLING),
            new Item.Properties().useItemDescriptionPrefix()
    );

    // region Fungus
    public static final Block CRIMSON_FUNGUS_CROP = makeBlockAndAliasedItem(
            makeId("crimson_fungus_crop"),
            makeId("crimson_spore"),
            SaplingCropBlock.Fungus.Crimson::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FUNGUS),
            new Item.Properties().useItemDescriptionPrefix()
    );
    public static final Block WARPED_FUNGUS_CROP = makeBlockAndAliasedItem(
            makeId("warped_fungus_crop"),
            makeId("warped_spore"),
            SaplingCropBlock.Fungus.Warped::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS),
            new Item.Properties().useItemDescriptionPrefix()
    );
    // endregion
    // endregion

    public static final Block CHOPPING_BLOCK = makeBlockAndSimpleItem(makeId("chopping_block"), ChoppingBlockBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));

    public static final Block SCULKY_DEEPSLATE_BRICKS = makeSimpleBlockAndSimpleItem(
            makeId("sculky_deepslate_bricks"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );
    public static final Block SCULKY_DEEPSLATE_BRICK_STAIRS = makeBlockAndSimpleItem(
            makeId("sculky_deepslate_brick_stairs"),
            settings -> new StairBlock(SCULKY_DEEPSLATE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(SCULKY_DEEPSLATE_BRICKS)
    );
    public static final Block SCULKY_DEEPSLATE_BRICK_SLAB = makeBlockAndSimpleItem(
            makeId("sculky_deepslate_brick_slab"),
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SCULKY_DEEPSLATE_BRICKS)
    );
    public static final Block SCULKY_DEEPSLATE_BRICK_WALL = makeBlockAndSimpleItem(
            makeId("sculky_deepslate_brick_wall"),
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SCULKY_DEEPSLATE_BRICKS).forceSolidOn()
    );

    public static final Block INFESTED_COBBLED_DEEPSLATE = makeBlockAndSimpleItem(
            makeId("infested_cobbled_deepslate"),
            settings -> new InfestedBlock(Blocks.COBBLED_DEEPSLATE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)
    );
    public static final Block INFESTED_DEEPSLATE_BRICKS = makeBlockAndSimpleItem(
            makeId("infested_deepslate_bricks"),
            settings -> new InfestedBlock(Blocks.DEEPSLATE_BRICKS, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );
    public static final Block INFESTED_SCULKY_DEEPSLATE_BRICKS = makeBlockAndSimpleItem(
            makeId("infested_sculky_deepslate_bricks"),
            settings -> new InfestedBlock(SCULKY_DEEPSLATE_BRICKS, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );
    public static final Block INFESTED_CRACKED_DEEPSLATE_BRICKS = makeBlockAndSimpleItem(
            makeId("infested_cracked_deepslate_bricks"),
            settings -> new InfestedBlock(Blocks.CRACKED_DEEPSLATE_BRICKS, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_DEEPSLATE_BRICKS)
    );
    public static final Block INFESTED_CHISELED_DEEPSLATE = makeBlockAndSimpleItem(
            makeId("infested_chiseled_deepslate"),
            settings -> new InfestedBlock(Blocks.CHISELED_DEEPSLATE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_DEEPSLATE)
    );

    // region Conglomerate
    public static final Block CONGLOMERATE = makeBlockAndSimpleItem(
            makeId("conglomerate"), Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE_POWDER)
    );

    public static final Block HARDENED_CONGLOMERATE = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate"), Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    public static final Block HARDENED_CONGLOMERATE_STAIRS = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_stairs"),
            settings -> new StairBlock(HARDENED_CONGLOMERATE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    public static final Block HARDENED_CONGLOMERATE_SLAB = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_slab"), SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    public static final Block HARDENED_CONGLOMERATE_WALL = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_wall"), WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );

    public static final Block HARDENED_CONGLOMERATE_BRICKS = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_bricks"), Block::new,
            BlockBehaviour.Properties.ofFullCopy(HARDENED_CONGLOMERATE)
    );
    public static final Block HARDENED_CONGLOMERATE_BRICK_STAIRS = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_brick_stairs"),
            settings -> new StairBlock(HARDENED_CONGLOMERATE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    public static final Block HARDENED_CONGLOMERATE_BRICK_SLAB = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_brick_slab"), SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    public static final Block HARDENED_CONGLOMERATE_BRICK_WALL = makeBlockAndSimpleItem(
            makeId("hardened_conglomerate_brick_wall"), WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );

    public static final Block DULL_CONGLOMERATE = makeBlockAndSimpleItem(
            makeId("dull_conglomerate"), Block::new,
            BlockBehaviour.Properties.ofFullCopy(HARDENED_CONGLOMERATE).strength(2.2f)
    );
    public static final Block DULL_CONGLOMERATE_SLAB = makeBlockAndSimpleItem(
            makeId("dull_conglomerate_slab"), SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CONCRETE)
    );
    // endregion

    // region Igneous Stone
    public static final Block COBBLED_GRANITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_granite"), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    public static final Block COBBLED_DIORITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_diorite"), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    public static final Block COBBLED_ANDESITE = makeSimpleBlockAndSimpleItem(makeId("cobbled_andesite"), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));

    public static final Block COBBLED_GRANITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_granite_stairs"),
            settings -> new StairBlock(COBBLED_GRANITE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)
    );
    public static final Block COBBLED_DIORITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_diorite_stairs"),
            settings -> new StairBlock(COBBLED_DIORITE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)
    );
    public static final Block COBBLED_ANDESITE_STAIRS = makeBlockAndSimpleItem(makeId("cobbled_andesite_stairs"),
            settings -> new StairBlock(COBBLED_ANDESITE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)
    );

    public static final Block COBBLED_GRANITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_granite_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLED_DIORITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_diorite_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLED_ANDESITE_SLAB = makeBlockAndSimpleItem(makeId("cobbled_andesite_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB));

    public static final Block COBBLED_GRANITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_granite_wall"), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL));
    public static final Block COBBLED_DIORITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_diorite_wall"), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL));
    public static final Block COBBLED_ANDESITE_WALL = makeBlockAndSimpleItem(makeId("cobbled_andesite_wall"), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL));

    public static final Block POLISHED_GRANITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_granite_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_DIORITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_diorite_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_ANDESITE_BRICKS = makeSimpleBlockAndSimpleItem(makeId("polished_andesite_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    public static final Block POLISHED_GRANITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_granite_brick_stairs"),
            settings -> new StairBlock(POLISHED_GRANITE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)
    );
    public static final Block POLISHED_DIORITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_diorite_brick_stairs"),
            settings -> new StairBlock(POLISHED_DIORITE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)
    );
    public static final Block POLISHED_ANDESITE_BRICK_STAIRS = makeBlockAndSimpleItem(makeId("polished_andesite_brick_stairs"),
            settings -> new StairBlock(POLISHED_ANDESITE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)
    );

    public static final Block POLISHED_GRANITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_granite_brick_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB));
    public static final Block POLISHED_DIORITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_diorite_brick_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB));
    public static final Block POLISHED_ANDESITE_BRICK_SLAB = makeBlockAndSimpleItem(makeId("polished_andesite_brick_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB));

    public static final Block POLISHED_GRANITE_WALL = makeBlockAndSimpleItem(makeId("polished_granite_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_WALL));
    public static final Block POLISHED_DIORITE_WALL = makeBlockAndSimpleItem(makeId("polished_diorite_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_WALL));
    public static final Block POLISHED_ANDESITE_WALL = makeBlockAndSimpleItem(makeId("polished_andesite_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL));

    public static final Block POLISHED_GRANITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_granite_brick_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final Block POLISHED_DIORITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_diorite_brick_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final Block POLISHED_ANDESITE_BRICK_WALL = makeBlockAndSimpleItem(makeId("polished_andesite_brick_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final BlockFamily COBBLED_GRANITE_FAMILY = new BlockFamily.Builder(COBBLED_GRANITE)
            .slab(COBBLED_GRANITE_SLAB).wall(COBBLED_GRANITE_WALL).getFamily();
    public static final BlockFamily COBBLED_DIORITE_FAMILY = new BlockFamily.Builder(COBBLED_DIORITE)
            .slab(COBBLED_DIORITE_SLAB).wall(COBBLED_DIORITE_WALL).getFamily();
    public static final BlockFamily COBBLED_ANDESITE_FAMILY = new BlockFamily.Builder(COBBLED_ANDESITE)
            .slab(COBBLED_ANDESITE_SLAB).wall(COBBLED_ANDESITE_WALL).getFamily();

    public static final BlockFamily POLISHED_GRANITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_GRANITE_BRICKS)
            .slab(POLISHED_GRANITE_BRICK_SLAB).wall(POLISHED_GRANITE_BRICK_WALL).getFamily();
    public static final BlockFamily POLISHED_DIORITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_DIORITE_BRICKS)
            .slab(POLISHED_DIORITE_BRICK_SLAB).wall(POLISHED_DIORITE_BRICK_WALL).getFamily();
    public static final BlockFamily POLISHED_ANDESITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_ANDESITE_BRICKS)
            .slab(POLISHED_ANDESITE_BRICK_SLAB).wall(POLISHED_ANDESITE_BRICK_WALL).getFamily();
    // endregion

    // region Kimberlite
    public static final Block KIMBERLITE = makeSimpleBlockAndSimpleItem(
            makeId("kimberlite"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE = makeSimpleBlockAndSimpleItem(
            makeId("polished_kimberlite"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICKS = makeSimpleBlockAndSimpleItem(
            makeId("polished_kimberlite_bricks"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block KIMBERLITE_STAIRS = makeBlockAndSimpleItem(
            makeId("kimberlite_stairs"),
            settings -> new StairBlock(KIMBERLITE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_STAIRS).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block KIMBERLITE_SLAB = makeBlockAndSimpleItem(
            makeId("kimberlite_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB)
                    .requiresCorrectToolForDrops().instrument(NoteBlockInstrument.PLING)
    );
    public static final Block KIMBERLITE_WALL = makeBlockAndSimpleItem(
            makeId("kimberlite_wall"),
            WallBlock::new,BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_WALL).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block POLISHED_KIMBERLITE_STAIRS = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_stairs"),
            settings -> new StairBlock(POLISHED_KIMBERLITE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_STAIRS).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_SLAB = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_SLAB).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_WALL = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_WALL).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final Block POLISHED_KIMBERLITE_BRICK_STAIRS = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_stairs"),
            settings -> new StairBlock(POLISHED_KIMBERLITE_BRICKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_SLAB = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );
    public static final Block POLISHED_KIMBERLITE_BRICK_WALL = makeBlockAndSimpleItem(
            makeId("polished_kimberlite_brick_wall"),
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.PLING)
    );

    public static final BlockFamily KIMBERLITE_FAMILY = new BlockFamily.Builder(KIMBERLITE)
            .slab(KIMBERLITE_SLAB).wall(KIMBERLITE_WALL).getFamily();
    public static final BlockFamily POLISHED_KIMBERLITE_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE)
            .slab(POLISHED_KIMBERLITE_SLAB).wall(POLISHED_KIMBERLITE_WALL).getFamily();
    public static final BlockFamily POLISHED_KIMBERLITE_BRICK_FAMILY = new BlockFamily.Builder(POLISHED_KIMBERLITE_BRICKS)
            .slab(POLISHED_KIMBERLITE_BRICK_SLAB).wall(POLISHED_KIMBERLITE_BRICK_WALL).getFamily();
    // endregion

    public static final Block DENSITITE_BLOCK = makeBlockAndItem(
            makeId("densitite_block"),
            HeavyCoreBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_CORE).sound(SoundType.COPPER),
            new Item.Properties().rarity(Rarity.RARE)
    );

    // region Soulstone
    public static final Block SOULSTONE = makeSimpleBlockAndSimpleItem(
            makeId("soulstone"), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
    );
    public static final Block CHISELED_SOULSTONE = makeSimpleBlockAndSimpleItem(
            makeId("chiseled_soulstone"), BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE)
    );
    public static final Block SMOOTH_SOULSTONE = makeSimpleBlockAndSimpleItem(
            makeId("smooth_soulstone"), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
    );
    public static final Block CUT_SOULSTONE = makeSimpleBlockAndSimpleItem(
            makeId("cut_soulstone"), BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE)
    );

    public static final Block SOULSTONE_SLAB = makeBlockAndSimpleItem(
            makeId("soulstone_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB)
    );
    public static final Block SOULSTONE_STAIRS = makeBlockAndSimpleItem(
            makeId("soulstone_stairs"), settings -> new StairBlock(SOULSTONE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS)
    );
    public static final Block SOULSTONE_WALL = makeBlockAndSimpleItem(
            makeId("soulstone_wall"), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_WALL)
    );
    public static final Block SMOOTH_SOULSTONE_SLAB = makeBlockAndSimpleItem(
            makeId("smooth_soulstone_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB)
    );
    public static final Block SMOOTH_SOULSTONE_STAIRS = makeBlockAndSimpleItem(
            makeId("smooth_soulstone_stairs"), settings -> new StairBlock(SMOOTH_SOULSTONE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS)
    );
    public static final Block CUT_SOULSTONE_SLAB = makeBlockAndSimpleItem(
            makeId("cut_soulstone_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB)
    );
    // endregion

    public static final Block POLARIZED_GLASS = makeBlockAndSimpleItem(
            makeId("polarized_glass"),
            PolarizedGlassBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.TINTED_GLASS).mapColor(MapColor.WARPED_WART_BLOCK)
    );

    public static final Block NULL_TORCH = HANDLER.instance().nulliumExperiment ?
    makeBlockAndSimpleItem(
            makeId("null_torch"),
            NullTorchBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .instabreak()
                    .lightLevel(value -> value.getValue(NullTorchBlock.COLOR) != 0 ? 13 : 0)
                    .sound(SoundType.STONE)
                    .pushReaction(PushReaction.DESTROY)
    ) : Blocks.AIR;

    public static final Block RECYCLER = HANDLER.instance().recyclerExperiment ? makeBlockAndSimpleItem(
            makeId("recycler"),
            RecyclerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DROPPER)
    ) : Blocks.AIR;

    public static final Block PINATA = makeBlockAndSimpleItem(
            makeId("pinata"),
            PinataBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS).strength(1.2f, 2.4f)
    );

    public static final Block BURNER = makeBlockAndSimpleItem(
            makeId("burner"), BurnerBlock::new,
            BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> state.getValue(BurnerBlock.LIT) ? 3 : 0)
                    .strength(0.5F)
                    .isValidSpawn((state, world, pos, entityType) -> entityType.fireImmune())
    );

    public static final Block SHOJI = makeBlockAndSimpleItem(
            makeId("shoji"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR).pushReaction(PushReaction.NORMAL)
    );

    // region Applewood
    public static final Block APPLE_LOG = makeBlockAndSimpleItem(
            makeId("apple_log"),
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LOG)
    );
    public static final Block APPLE_WOOD = makeBlockAndSimpleItem(
            makeId("apple_wood"),
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD)
    );
    public static final Block STRIPPED_APPLE_LOG = makeBlockAndSimpleItem(
            makeId("stripped_apple_log"),
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_LOG)
    );
    public static final Block STRIPPED_APPLE_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_apple_wood"),
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_WOOD)
    );
    public static final Block APPLE_LEAVES = makeBlockAndSimpleItem(
            makeId("apple_leaves"),
            settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x3f, 0x99, 0x49), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES)
    );

    public static final WoodType APPLE_TYPE = TerraformSignBlockHelper.registerDefaultWoodType(makeId("apple"));

    // 0x3f9949
    public static final Block FLOWERING_APPLE_LEAVES = makeBlockAndSimpleItem(
            makeId("flowering_apple_leaves"),
            settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x3f, 0x99, 0x49), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWERING_AZALEA_LEAVES)
    );

    public static final Block APPLE_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("apple_planks"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
    );
    public static final Block APPLE_STAIRS = makeBlockAndSimpleItem(
            makeId("apple_stairs"),
            settings -> new StairBlock(APPLE_PLANKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS)
    );
    public static final Block APPLE_SLAB = makeBlockAndSimpleItem(
            makeId("apple_slab"),
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB)
    );
    public static final Block APPLE_FENCE = makeBlockAndSimpleItem(
            makeId("apple_fence"),
            FenceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE)
    );
    public static final Block APPLE_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("apple_fence_gate"),
            settings -> new FenceGateBlock(APPLE_TYPE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE)
    );
    public static final Block APPLE_DOOR = makeBlockAndSimpleItem(
            makeId("apple_door"),
            settings -> new DoorBlock(APPLE_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR)
    );
    public static final Block APPLE_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("apple_trapdoor"),
            settings -> new TrapDoorBlock(APPLE_TYPE.setType(), settings),

            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_TRAPDOOR)
    );
    public static final Block APPLE_BUTTON = makeBlockAndSimpleItem(
            makeId("apple_button"),
            settings -> new ButtonBlock(APPLE_TYPE.setType(), 30, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_BUTTON)
    );
    public static final Block APPLE_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("apple_pressure_plate"),
            settings -> new PressurePlateBlock(APPLE_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PRESSURE_PLATE)
    );
    public static final Block APPLE_SAPLING = makeBlockAndSimpleItem(
            makeId("apple_sapling"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.APPLEWOOD_GENERATOR, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
    );

    public static final Block APPLE_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("apple_standing_sign"),
            properties -> new StandingSignBlock(APPLE_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SIGN)
    );
    public static final Block APPLE_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("apple_wall_sign"),
            properties -> new WallSignBlock(APPLE_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WALL_SIGN)
    );
    public static final Block APPLE_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock  (
            makeId("apple_ceiling_hanging_sign"),
            properties -> new CeilingHangingSignBlock(
                    APPLE_TYPE, properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_HANGING_SIGN)
    );
    public static final Block APPLE_WALL_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("apple_wall_hanging_sign"),
            properties -> new WallHangingSignBlock(
                    APPLE_TYPE, properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WALL_HANGING_SIGN)
    );

    public static final BlockFamily APPLE_FAMILY = BlockFamilies.familyBuilder(APPLE_PLANKS)
            .slab(APPLE_SLAB).fence(APPLE_FENCE).fenceGate(APPLE_FENCE_GATE)
            .door(APPLE_DOOR).sign(APPLE_SIGN, APPLE_WALL_SIGN)
            .pressurePlate(APPLE_PRESSURE_PLATE).button(APPLE_BUTTON)
            .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    // endregion

    // region Prickle Wood

    public static final WoodType PRICKLE_TYPE = TerraformSignBlockHelper.registerDefaultWoodType(makeId("prickle"));
    
    public static final Block PRICKLE_LOG = makeBlockAndSimpleItem(
            makeId("prickle_log"),
            PrickleLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM).ignitedByLava()
    );
    public static final Block PRICKLE_WOOD = makeBlockAndSimpleItem(
            makeId("prickle_wood"),
            PrickleLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HYPHAE).ignitedByLava()
    );
    public static final Block STRIPPED_PRICKLE_LOG = makeBlockAndSimpleItem(
            makeId("stripped_prickle_log"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM).ignitedByLava()
    );
    public static final Block STRIPPED_PRICKLE_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_prickle_wood"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_HYPHAE).ignitedByLava()
    );
    public static final Block PRICKLE_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("prickle_planks"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).ignitedByLava()
    );
    public static final Block PRICKLE_STAIRS = makeBlockAndSimpleItem(
            makeId("prickle_stairs"),
            settings -> new StairBlock(PRICKLE_PLANKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS).ignitedByLava()
    );
    public static final Block PRICKLE_SLAB = makeBlockAndSimpleItem(
            makeId("prickle_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB).ignitedByLava()
    );
    public static final Block PRICKLE_FENCE = makeBlockAndSimpleItem(
            makeId("prickle_fence"),
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE).ignitedByLava()
    );
    public static final Block PRICKLE_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("prickle_fence_gate"),
            settings -> new FenceGateBlock(PRICKLE_TYPE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE)
    );
    public static final Block PRICKLE_DOOR = makeBlockAndSimpleItem(
            makeId("prickle_door"),
            settings -> new DoorBlock(PRICKLE_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR)
    );
    public static final Block PRICKLE_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("prickle_trapdoor"),
            settings -> new TrapDoorBlock(PRICKLE_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR)
    );
    public static final Block PRICKLE_BUTTON = makeBlockAndSimpleItem(
            makeId("prickle_button"),
            settings -> new ButtonBlock(PRICKLE_TYPE.setType(), 30, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_BUTTON)
    );
    public static final Block PRICKLE_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("prickle_pressure_plate"),
            settings -> new PressurePlateBlock(PRICKLE_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PRESSURE_PLATE)
    );

    public static final Block PRICKLE_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("prickle_standing_sign"),
            settings -> new StandingSignBlock(PRICKLE_TYPE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SIGN)
    );
    public static final Block PRICKLE_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("prickle_wall_sign"),
            settings -> new WallSignBlock(PRICKLE_TYPE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_SIGN)
    );
    public static final Block PRICKLE_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("prickle_ceiling_hanging_sign"),
            settings -> new CeilingHangingSignBlock(
                    PRICKLE_TYPE, settings
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HANGING_SIGN)
    );
    public static final Block PRICKLE_WALL_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("prickle_wall_hanging_sign"),
            settings -> new WallHangingSignBlock(
                    PRICKLE_TYPE, settings
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_HANGING_SIGN)
    );

    public static final BlockFamily PRICKLE_FAMILY = BlockFamilies.familyBuilder(PRICKLE_PLANKS)
            .slab(PRICKLE_SLAB).fence(PRICKLE_FENCE).fenceGate(PRICKLE_FENCE_GATE)
            .door(PRICKLE_DOOR).sign(PRICKLE_SIGN, PRICKLE_WALL_SIGN)
            .pressurePlate(PRICKLE_PRESSURE_PLATE).button(PRICKLE_BUTTON)
            .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

    public static final Block PRICKLE_SHOOT = makeBlockAndSimpleItem(
            makeId("prickle_shoot"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.PRICKLE_GENERATOR, settings) {
                @Override
                protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
                    return floor.is(Blocks.END_STONE);
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)
    );
    // endregion

    // region Persimmon Wood
    public static final WoodType PERSIMMON_TYPE = TerraformSignBlockHelper.registerDefaultWoodType(makeId("persimmon"));
    
    public static final Block PERSIMMON_LOG = makeBlockAndSimpleItem(
            makeId("persimmon_log"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    );
    public static final Block PERSIMMON_WOOD = makeBlockAndSimpleItem(
            makeId("persimmon_wood"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    );
    public static final Block STRIPPED_PERSIMMON_LOG = makeBlockAndSimpleItem(
            makeId("stripped_persimmon_log"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
    );
    public static final Block STRIPPED_PERSIMMON_WOOD = makeBlockAndSimpleItem(
            makeId("stripped_persimmon_wood"),
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
    );
    public static final Block PERSIMMON_LEAVES = makeBlockAndSimpleItem(
            makeId("persimmon_leaves"),
            settings -> new TintedParticleLeavesBlock(0.01F, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    );

    public static final Block PERSIMMON_PLANKS = makeSimpleBlockAndSimpleItem(
            makeId("persimmon_planks"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );
    public static final Block PERSIMMON_STAIRS = makeBlockAndSimpleItem(
            makeId("persimmon_stairs"),
            settings -> new StairBlock(PERSIMMON_PLANKS.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
    );
    public static final Block PERSIMMON_SLAB = makeBlockAndSimpleItem(
            makeId("persimmon_slab"),
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
    );
    public static final Block PERSIMMON_FENCE = makeBlockAndSimpleItem(
            makeId("persimmon_fence"),
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
    );
    public static final Block PERSIMMON_FENCE_GATE = makeBlockAndSimpleItem(
            makeId("persimmon_fence_gate"), settings -> new FenceGateBlock(PERSIMMON_TYPE, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
    );
    public static final Block PERSIMMON_DOOR = makeBlockAndSimpleItem(
            makeId("persimmon_door"),
            settings -> new DoorBlock(PERSIMMON_TYPE.setType(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
    );
    public static final Block PERSIMMON_TRAPDOOR = makeBlockAndSimpleItem(
            makeId("persimmon_trapdoor"),
            settings -> new TrapDoorBlock(PERSIMMON_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    );
    public static final Block PERSIMMON_BUTTON = makeBlockAndSimpleItem(
            makeId("persimmon_button"),
            settings -> new ButtonBlock(PERSIMMON_TYPE.setType(), 30, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
    );
    public static final Block PERSIMMON_PRESSURE_PLATE = makeBlockAndSimpleItem(
            makeId("persimmon_pressure_plate"),
            settings -> new PressurePlateBlock(PERSIMMON_TYPE.setType(), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
    );

    public static final Block PERSIMMON_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("persimmon_standing_sign"),
            properties -> new StandingSignBlock(PERSIMMON_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)
    );
    public static final Block PERSIMMON_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("persimmon_wall_sign"),
            properties -> new WallSignBlock(PERSIMMON_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
    );
    public static final Block PERSIMMON_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("persimmon_ceiling_hanging_sign"),
            properties -> new CeilingHangingSignBlock(
                    PERSIMMON_TYPE, properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)
    );
    public static final Block PERSIMMON_WALL_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            makeId("persimmon_wall_hanging_sign"),
            properties -> new WallHangingSignBlock(PERSIMMON_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
    );

    public static final BlockFamily PERSIMMON_FAMILY = BlockFamilies.familyBuilder(PERSIMMON_PLANKS)
            .slab(PERSIMMON_SLAB).fence(PERSIMMON_FENCE).fenceGate(PERSIMMON_FENCE_GATE)
            .door(PERSIMMON_DOOR).sign(PERSIMMON_SIGN, PERSIMMON_WALL_SIGN)
            .pressurePlate(PERSIMMON_PRESSURE_PLATE).button(PERSIMMON_BUTTON)
            .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

    public static final Block PERSIMMON_SAPLING = makeBlockAndSimpleItem(
            makeId("persimmon_sapling"),
            settings -> new SaplingBlock(YavpmTreeConfiguredFeatures.PERSIMMON_GENERATOR, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
    );
    // endregion

    // region Graphite and Graphene
    public static final Block GRAPHITE_BLOCK = makeSimpleBlockAndSimpleItem(makeId("graphite_block"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BLACK).strength(2f, 2.5f).sound(SoundType.TUFF));
    public static final Block GRAPHENE_BLOCK = makeSimpleBlockAndSimpleItem(makeId("graphene_block"),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BLACK).strength(6f, 7.5f).sound(SoundType.POLISHED_TUFF));
    // endregion

    // region Fake
    public static final Block FAKE_LOG = makeBlock(
            makeId("fake_log"),
            FakeLogBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STEM).sound(SoundType.WOOD)
    );
    public static final Block FAKE_ORE = makeBlock(
            makeId("fake_ore"),
            FakeOreBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
    );
    // endregion

    public static final Block VOID_WATER = makeBlock(
            makeId("void_water"),
            settings -> new LiquidBlock(YavpmFluids.STILL_VOID_WATER, settings) {
                @Override
                protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
                    if (!level.isClientSide()) {
                        if (entity instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(YavpmStatusEffects.VOID_TOUCHED, 220));
                        }
                    }
                    super.entityInside(blockState, level, blockPos, entity, insideBlockEffectApplier, bl);
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
    );

    private static ResourceKey<Block> key(String id) {
        return ResourceKey.create(Registries.BLOCK, makeId(id));
    }

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
        flammables.add(FLOWERING_APPLE_LEAVES, 30, 60);

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
