package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.crop.*;
import com.farestr06.yavpm.item.DensititeMaterial;
import com.farestr06.yavpm.item.StuddedMaterial;
import com.farestr06.yavpm.item.YavpmItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class YavpmModelProvider extends FabricModelProvider {
    public YavpmModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static TextureMapping paneTextureMap(Block block) {
        return new TextureMapping().put(TextureSlot.PANE, TextureMapping.getBlockTexture(block)).put(TextureSlot.EDGE, TextureMapping.getBlockTexture(block, "_top"));
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(YavpmBlocks.GLOWING_OBSIDIAN);
        generator.createTrivialCube(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

        createCrops(generator);

        generator.createTrivialCube(YavpmBlocks.NAHCOLITE_ORE);
        generator.createTrivialCube(YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE);

        createKimberliteSet(generator);
        createGraniteSet(generator);
        createAndesiteSet(generator);
        createDioriteSet(generator);

        generator.createNonTemplateModelBlock(YavpmBlocks.DENSITITE_BLOCK);

        generator.createTrivialCube(YavpmBlocks.GRAPHITE_BLOCK);
        generator.createTrivialCube(YavpmBlocks.GRAPHENE_BLOCK);

        createAppleSet(generator);
        createPersimmonSet(generator);
        createPrickleSet(generator);

        registerPane(generator, YavpmBlocks.SHOJI);

        registerPolarizedGlass(generator);
        registerBurner(generator);
        generator.createDispenserBlock(YavpmBlocks.RECYCLER);
        generator.registerSimpleFlatItemModel(YavpmBlocks.NULL_TORCH);

        generator.createCrossBlockWithDefaultItem(YavpmBlocks.APPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createCrossBlockWithDefaultItem(YavpmBlocks.PERSIMMON_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createCrossBlockWithDefaultItem(YavpmBlocks.PRICKLE_SHOOT, BlockModelGenerators.PlantType.NOT_TINTED);

        generator.createNonTemplateModelBlock(YavpmBlocks.VOID_WATER, Blocks.WATER);

        createConglomerate(generator);
        createNewDeepslate(generator);
    }

    private void registerBurner(BlockModelGenerators generator) {
        MultiVariant variant = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(YavpmBlocks.BURNER, generator.modelOutput));
        MultiVariant variant2 = BlockModelGenerators.plainVariant(generator.createSuffixedVariant(YavpmBlocks.BURNER, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        generator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(YavpmBlocks.BURNER).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, variant, variant2)));
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(YavpmItems.BAKING_SODA, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.SPRUCE_CONE, ModelTemplates.FLAT_ITEM); // TODO: Replace with block model

        generator.generateFlatItem(YavpmItems.REACTOR, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.HEATED_REACTOR, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.COPPER_HORN, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.BABY_KEY, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.PHANTOM_CHORD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.THUNDER_SHARD, ModelTemplates.FLAT_ITEM);

        food(generator);

        generator.generateFlatItem(YavpmItems.CARBON_EGG, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.GRAPHITE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.RAW_DIAMOND, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.GAUNTLET_FRAGMENT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.CHAINMAIL, ModelTemplates.FLAT_ITEM);
        createStuddedArmor(generator);
        createDensitite(generator);

        generator.generateFlatItem(YavpmItems.APPLE_BOAT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.APPLE_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, ModelTemplates.MUSIC_DISC);
        generator.generateFlatItem(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.MUSIC_DISC_HALLAND_DALARNA, ModelTemplates.MUSIC_DISC);

        generator.generateFlatItem(YavpmItems.MOONGUS_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.CARBONFOWL_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.SUNBURN_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.TANUKI_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.VOID_PHANTOM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.VOID_WATER_BUCKET, ModelTemplates.FLAT_ITEM);
    }

    private void createConglomerate(BlockModelGenerators generator) {
        generator.createTrivialCube(YavpmBlocks.CONGLOMERATE);

        BlockModelGenerators.BlockFamilyProvider hardenedConglomerate =
                generator.family(YavpmBlocks.HARDENED_CONGLOMERATE);
        hardenedConglomerate.slab(YavpmBlocks.HARDENED_CONGLOMERATE_SLAB);
        hardenedConglomerate.stairs(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS);
        hardenedConglomerate.wall(YavpmBlocks.HARDENED_CONGLOMERATE_WALL);

        BlockModelGenerators.BlockFamilyProvider hardenedConglomerateBricks =
                generator.family(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
        hardenedConglomerateBricks.slab(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB);
        hardenedConglomerateBricks.stairs(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS);
        hardenedConglomerateBricks.wall(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL);

        BlockModelGenerators.BlockFamilyProvider polishedConglomerate =
                generator.family(YavpmBlocks.DULL_CONGLOMERATE);
        polishedConglomerate.slab(YavpmBlocks.DULL_CONGLOMERATE_SLAB);
    }

    private void createDensitite(ItemModelGenerators generator) {
        generator.generateFlatItem(YavpmItems.DENSITITE_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.DENSITITE_INGOT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.DENSITITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(YavpmItems.DENSITITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(YavpmItems.DENSITITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(YavpmItems.DENSITITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(YavpmItems.DENSITITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        generator.generateTrimmableItem(YavpmItems.DENSITITE_HELMET, DensititeMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generator.generateTrimmableItem(YavpmItems.DENSITITE_CHESTPLATE, DensititeMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generator.generateTrimmableItem(YavpmItems.DENSITITE_LEGGINGS, DensititeMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generator.generateTrimmableItem(YavpmItems.DENSITITE_BOOTS, DensititeMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
    }

    private void createNewDeepslate(BlockModelGenerators generator) {
        BlockModelGenerators.BlockFamilyProvider sculkyDeepslatePool
                = generator.family(YavpmBlocks.SCULKY_DEEPSLATE_BRICKS);

        sculkyDeepslatePool.stairs(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_STAIRS);
        sculkyDeepslatePool.slab(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB);
        sculkyDeepslatePool.wall(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL);
    }

    public final void registerPane(BlockModelGenerators generator, Block pane) {
        TextureMapping textureMap = paneTextureMap(pane);
        MultiVariant weightedVariant = plainVariant(ModelTemplates.STAINED_GLASS_PANE_POST.create(pane, textureMap, generator.modelOutput));
        MultiVariant weightedVariant2 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE.create(pane, textureMap, generator.modelOutput));
        MultiVariant weightedVariant3 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(pane, textureMap, generator.modelOutput));
        MultiVariant weightedVariant4 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(pane, textureMap, generator.modelOutput));
        MultiVariant weightedVariant5 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(pane, textureMap, generator.modelOutput));
        Item item = pane.asItem();
        generator.registerSimpleItemModel(item, generator.createFlatItemModelWithBlockTexture(item, pane));
        generator.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(pane)
                                .with(weightedVariant)
                                .with(condition().term(BlockStateProperties.NORTH, true), weightedVariant2)
                                .with(condition().term(BlockStateProperties.EAST, true), weightedVariant2.with(Y_ROT_90))
                                .with(condition().term(BlockStateProperties.SOUTH, true), weightedVariant3)
                                .with(condition().term(BlockStateProperties.WEST, true), weightedVariant3.with(Y_ROT_90))
                                .with(condition().term(BlockStateProperties.NORTH, false), weightedVariant4)
                                .with(condition().term(BlockStateProperties.EAST, false), weightedVariant5)
                                .with(condition().term(BlockStateProperties.SOUTH, false), weightedVariant5.with(Y_ROT_90))
                                .with(condition().term(BlockStateProperties.WEST, false), weightedVariant4.with(Y_ROT_270))
                );
    }

    private void registerPolarizedGlass(BlockModelGenerators generator) {
        MultiVariant variant = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(YavpmBlocks.POLARIZED_GLASS, generator.modelOutput));
        MultiVariant variant2 = BlockModelGenerators.plainVariant(generator.createSuffixedVariant(YavpmBlocks.POLARIZED_GLASS, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        generator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(YavpmBlocks.POLARIZED_GLASS)
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, variant, variant2)));
    }

    private static void food(ItemModelGenerators generator) {
        generator.generateFlatItem(YavpmItems.MOLY, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.PRETZEL, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.CANTALOUPE_SLICE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.BREADING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.FRIED_BANANA, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.FRIED_COD, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.BANANA, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.COOKED_PEANUT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.CHOCOLATE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.FORTUNE_COOKIE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.LUCKY_SLIP, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.DIAMOND_ACORN, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.PERSIMMON, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.GOLDEN_PERSIMMON, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.TRUFFLE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.FANCY_MUSHROOM_STEW, Items.MUSHROOM_STEW, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.CHEESE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.BEAN_TOAST, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.COOKED_EGG, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.FAKE_BEEF, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.COOKED_FAKE_BEEF, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.FAKE_MILK_BUCKET, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.TOFU, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.JELLY, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.SWEET_BERRY_JELLY, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.RICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.RICE_BAR, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.RICE_PASTRY, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.SUSHI, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(YavpmItems.SEA_SOUP, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(YavpmItems.CHICKEN_SOUP, ModelTemplates.FLAT_ITEM);
    }

    private void createCrops(BlockModelGenerators generator) {
        generator.createCrossBlock(
                YavpmBlocks.BITTER_BERRY_BUSH,
                BlockModelGenerators.PlantType.NOT_TINTED,
                BitterBerryBushBlock.AGE, 0, 1, 2, 3
        );
        generator.createCropBlock(YavpmBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        generator.createCropBlock(YavpmBlocks.WARPED_WART_CROP, BlockStateProperties.AGE_3, 0, 1, 1, 2);
        generator.createCropBlock(YavpmBlocks.PEANUT_CROP, PeanutCropBlock.AGE, 0, 1, 2, 3);
        registerBananaCrop(generator);

        registerCrossCrop(generator, YavpmBlocks.OAK_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.BIRCH_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.CRIMSON_FUNGUS_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.WARPED_FUNGUS_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.MAGIC_BEAN_CROP, MagicBeanCropBlock.AGE, 0,1,1,2,3,4,5);

        generator.createTrivialBlock(YavpmBlocks.CANTALOUPE, TexturedModel.COLUMN);
        generator.createStems(YavpmBlocks.CANTALOUPE_STEM, YavpmBlocks.ATTACHED_CANTALOUPE_STEM);
    }

    private static void registerBananaCrop(BlockModelGenerators generator) {
        Block block = YavpmBlocks.BANANA_CROP;
        generator.registerSimpleFlatItemModel(block.asItem());
        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block)
                                .with(PropertyDispatch.initial(BananaCropBlock.AGE, BlockStateProperties.DOUBLE_BLOCK_HALF).generate((age, half) -> switch (half) {
                                    case UPPER -> plainVariant(ModelLocationUtils.getModelLocation(block, "_top_stage_" + age));
                                    case LOWER -> plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom_stage_" + age));
                                }))
                );
    }

    private static void createKimberliteSet(BlockModelGenerators generator) {
        BlockModelGenerators.BlockFamilyProvider kimberlitePool = generator.family(YavpmBlocks.KIMBERLITE);
        kimberlitePool.stairs(YavpmBlocks.KIMBERLITE_STAIRS);
        kimberlitePool.slab(YavpmBlocks.KIMBERLITE_SLAB);
        kimberlitePool.wall(YavpmBlocks.KIMBERLITE_WALL);

        BlockModelGenerators.BlockFamilyProvider polishedKimberlitePool = generator.family(YavpmBlocks.POLISHED_KIMBERLITE);
        polishedKimberlitePool.stairs(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
        polishedKimberlitePool.slab(YavpmBlocks.POLISHED_KIMBERLITE_SLAB);
        polishedKimberlitePool.wall(YavpmBlocks.POLISHED_KIMBERLITE_WALL);

        BlockModelGenerators.BlockFamilyProvider polishedKimberliteBrickPool = generator.family(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
        polishedKimberliteBrickPool.stairs(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);
        polishedKimberliteBrickPool.slab(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB);
        polishedKimberliteBrickPool.wall(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);
    }
    private static void createAndesiteSet(BlockModelGenerators generator) {
        BlockModelGenerators.BlockFamilyProvider polishedPool = generator.family(Blocks.POLISHED_ANDESITE);
        polishedPool.wall(YavpmBlocks.POLISHED_ANDESITE_WALL);

        BlockModelGenerators.BlockFamilyProvider cobbledAndesitePool = generator.family(YavpmBlocks.COBBLED_ANDESITE);
        cobbledAndesitePool.stairs(YavpmBlocks.COBBLED_ANDESITE_STAIRS);
        cobbledAndesitePool.slab(YavpmBlocks.COBBLED_ANDESITE_SLAB);
        cobbledAndesitePool.wall(YavpmBlocks.COBBLED_ANDESITE_WALL);

        BlockModelGenerators.BlockFamilyProvider andesiteBrickPool = generator.family(YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        andesiteBrickPool.stairs(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);
        andesiteBrickPool.slab(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);
        andesiteBrickPool.wall(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);
    }
    private static void createGraniteSet(BlockModelGenerators generator) {
        BlockModelGenerators.BlockFamilyProvider polishedPool = generator.family(Blocks.POLISHED_GRANITE);
        polishedPool.wall(YavpmBlocks.POLISHED_GRANITE_WALL);

        BlockModelGenerators.BlockFamilyProvider cobbledGranitePool = generator.family(YavpmBlocks.COBBLED_GRANITE);
        cobbledGranitePool.stairs(YavpmBlocks.COBBLED_GRANITE_STAIRS);
        cobbledGranitePool.slab(YavpmBlocks.COBBLED_GRANITE_SLAB);
        cobbledGranitePool.wall(YavpmBlocks.COBBLED_GRANITE_WALL);

        BlockModelGenerators.BlockFamilyProvider graniteBrickPool = generator.family(YavpmBlocks.POLISHED_GRANITE_BRICKS);
        graniteBrickPool.stairs(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
        graniteBrickPool.slab(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
        graniteBrickPool.wall(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);
    }
    private static void createDioriteSet(BlockModelGenerators generator) {
        BlockModelGenerators.BlockFamilyProvider polishedPool = generator.family(Blocks.POLISHED_DIORITE);
        polishedPool.wall(YavpmBlocks.POLISHED_DIORITE_WALL);

        BlockModelGenerators.BlockFamilyProvider cobbledDioritePool = generator.family(YavpmBlocks.COBBLED_DIORITE);
        cobbledDioritePool.stairs(YavpmBlocks.COBBLED_DIORITE_STAIRS);
        cobbledDioritePool.slab(YavpmBlocks.COBBLED_DIORITE_SLAB);
        cobbledDioritePool.wall(YavpmBlocks.COBBLED_DIORITE_WALL);

        BlockModelGenerators.BlockFamilyProvider dioriteBrickPool = generator.family(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        dioriteBrickPool.stairs(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        dioriteBrickPool.slab(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        dioriteBrickPool.wall(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);
    }

    private static void createAppleSet(BlockModelGenerators generator) {
        // Apple Logs and Woods
        generator.woodProvider(YavpmBlocks.APPLE_LOG).logWithHorizontal(YavpmBlocks.APPLE_LOG).wood(YavpmBlocks.APPLE_WOOD);
        generator.woodProvider(YavpmBlocks.STRIPPED_APPLE_LOG).logWithHorizontal(YavpmBlocks.STRIPPED_APPLE_LOG).wood(YavpmBlocks.STRIPPED_APPLE_WOOD);

        generator.createTrivialCube(YavpmBlocks.APPLE_LEAVES);
        generator.createTrivialCube(YavpmBlocks.FLOWERING_APPLE_LEAVES);

        // Apple Planks and Texture Pool
        BlockModelGenerators.BlockFamilyProvider applePool = generator.family(YavpmBlocks.APPLE_PLANKS);
        applePool.generateFor(YavpmBlocks.APPLE_FAMILY).stairs(YavpmBlocks.APPLE_STAIRS);
        generator.createHangingSign(YavpmBlocks.APPLE_LOG, YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN);
        generator.createTrapdoor(YavpmBlocks.APPLE_TRAPDOOR);
    }

    private static void createPersimmonSet(BlockModelGenerators generator) {
        // Persimmon Logs and Woods
        generator.woodProvider(YavpmBlocks.PERSIMMON_LOG).logWithHorizontal(YavpmBlocks.PERSIMMON_LOG).wood(YavpmBlocks.PERSIMMON_WOOD);
        generator.woodProvider(YavpmBlocks.STRIPPED_PERSIMMON_LOG).logWithHorizontal(YavpmBlocks.STRIPPED_PERSIMMON_LOG).wood(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);

        generator.createTrivialBlock(YavpmBlocks.PERSIMMON_LEAVES, TexturedModel.LEAVES);

        // Persimmon Planks and Texture Pool
        BlockModelGenerators.BlockFamilyProvider persimmonPool = generator.family(YavpmBlocks.PERSIMMON_PLANKS);
        persimmonPool.generateFor(YavpmBlocks.PERSIMMON_FAMILY).stairs(YavpmBlocks.PERSIMMON_STAIRS);
        generator.createHangingSign(YavpmBlocks.PERSIMMON_LOG, YavpmBlocks.PERSIMMON_HANGING_SIGN, YavpmBlocks.PERSIMMON_WALL_HANGING_SIGN);
        generator.createTrapdoor(YavpmBlocks.PERSIMMON_TRAPDOOR);
    }

    private static void createPrickleSet(BlockModelGenerators generator) {
        generator.woodProvider(YavpmBlocks.PRICKLE_LOG).logWithHorizontal(YavpmBlocks.PRICKLE_LOG).wood(YavpmBlocks.PRICKLE_WOOD);
        generator.woodProvider(YavpmBlocks.STRIPPED_PRICKLE_LOG).logWithHorizontal(YavpmBlocks.STRIPPED_PRICKLE_LOG).wood(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

        BlockModelGenerators.BlockFamilyProvider pricklePool = generator.family(YavpmBlocks.PRICKLE_PLANKS);

        pricklePool.generateFor(YavpmBlocks.PRICKLE_FAMILY).stairs(YavpmBlocks.PRICKLE_STAIRS);
        generator.createHangingSign(YavpmBlocks.PRICKLE_LOG, YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN);
        generator.createTrapdoor(YavpmBlocks.PRICKLE_TRAPDOOR);
    }

    private static void createStuddedArmor(ItemModelGenerators generator) {
        generator.generateTrimmableItem(YavpmItems.STUDDED_HELMET, StuddedMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, true);
        generator.generateTrimmableItem(YavpmItems.STUDDED_CHESTPLATE, StuddedMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, true);
        generator.generateTrimmableItem(YavpmItems.STUDDED_LEGGINGS, StuddedMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, true);
        generator.generateTrimmableItem(YavpmItems.STUDDED_BOOTS, StuddedMaterial.ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, true);

    }

    public final void registerCrossCrop(BlockModelGenerators generator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        generator.registerSimpleFlatItemModel(crop.asItem());
        if (ageProperty.getPossibleValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<ResourceLocation> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            generator.blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(crop)
                                    .with(
                                            PropertyDispatch.initial(ageProperty)
                                                    .generate(
                                                            age -> {
                                                                int i = ageTextureIndices[age];
                                                                return plainVariant(
                                                                        int2ObjectMap.computeIfAbsent(
                                                                                i, (Int2ObjectFunction<? extends ResourceLocation>) (stage -> generator.createSuffixedVariant(crop, "_stage" + stage, ModelTemplates.CROSS, TextureMapping::cross))
                                                                        )
                                                                );
                                                            }
                                                    )
                                    )
                    );
        }
    }
}
