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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import static net.minecraft.client.data.BlockStateModelGenerator.*;

public class YavpmModelProvider extends FabricModelProvider {
    public YavpmModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static TextureMap paneTextureMap(Block block) {
        return new TextureMap().put(TextureKey.PANE, TextureMap.getId(block)).put(TextureKey.EDGE, TextureMap.getSubId(block, "_top"));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(YavpmBlocks.GLOWING_OBSIDIAN);
        generator.registerSimpleCubeAll(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

        createCrops(generator);

        generator.registerSimpleCubeAll(YavpmBlocks.NAHCOLITE_ORE);
        generator.registerSimpleCubeAll(YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE);

        createKimberliteSet(generator);
        createGraniteSet(generator);
        createAndesiteSet(generator);
        createDioriteSet(generator);

        generator.registerSimpleState(YavpmBlocks.DENSITITE_BLOCK);

        generator.registerSimpleCubeAll(YavpmBlocks.GRAPHITE_BLOCK);
        generator.registerSimpleCubeAll(YavpmBlocks.GRAPHENE_BLOCK);

        createAppleSet(generator);
        createPersimmonSet(generator);
        createPrickleSet(generator);

        registerPane(generator, YavpmBlocks.SHOJI);

        registerPolarizedGlass(generator);
        registerBurner(generator);
        generator.registerDispenserLikeOrientable(YavpmBlocks.RECYCLER);
        generator.registerItemModel(YavpmBlocks.NULL_TORCH);

        generator.registerTintableCross(YavpmBlocks.APPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerTintableCross(YavpmBlocks.PERSIMMON_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerTintableCross(YavpmBlocks.PRICKLE_SHOOT, BlockStateModelGenerator.CrossType.NOT_TINTED);

        generator.registerStateWithModelReference(YavpmBlocks.VOID_WATER, Blocks.WATER);

        createConglomerate(generator);
        createNewDeepslate(generator);
    }

    private void registerBurner(BlockStateModelGenerator generator) {
        WeightedVariant variant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(YavpmBlocks.BURNER, generator.modelCollector));
        WeightedVariant variant2 = BlockStateModelGenerator.createWeightedVariant(generator.createSubModel(YavpmBlocks.BURNER, "_on", Models.CUBE_ALL, TextureMap::all));
        generator.blockStateCollector
                .accept(VariantsBlockModelDefinitionCreator.of(YavpmBlocks.BURNER).with(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, variant, variant2)));
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(YavpmItems.BAKING_SODA, Models.GENERATED);

        generator.register(YavpmItems.SPRUCE_CONE, Models.GENERATED); // TODO: Replace with block model

        generator.register(YavpmItems.REACTOR, Models.GENERATED);
        generator.register(YavpmItems.HEATED_REACTOR, Models.GENERATED);

        generator.register(YavpmItems.COPPER_HORN, Models.GENERATED);
        generator.register(YavpmItems.BABY_KEY, Models.GENERATED);

        generator.register(YavpmItems.PHANTOM_CHORD, Models.GENERATED);
        generator.register(YavpmItems.THUNDER_SHARD, Models.GENERATED);

        food(generator);

        generator.register(YavpmItems.CARBON_EGG, Models.GENERATED);
        generator.register(YavpmItems.GRAPHITE, Models.GENERATED);
        generator.register(YavpmItems.RAW_DIAMOND, Models.GENERATED);

        generator.register(YavpmItems.GAUNTLET_FRAGMENT, Models.GENERATED);

        generator.register(YavpmItems.CHAINMAIL, Models.GENERATED);
        createStuddedArmor(generator);
        createDensitite(generator);

        generator.register(YavpmItems.APPLE_BOAT, Models.GENERATED);
        generator.register(YavpmItems.APPLE_CHEST_BOAT, Models.GENERATED);

        generator.register(YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, Models.TEMPLATE_MUSIC_DISC);
        generator.register(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT, Models.GENERATED);
        generator.register(YavpmItems.MUSIC_DISC_HALLAND_DALARNA, Models.TEMPLATE_MUSIC_DISC);

        generator.register(YavpmItems.MOONGUS_SPAWN_EGG, Models.GENERATED);
        generator.register(YavpmItems.CARBONFOWL_SPAWN_EGG, Models.GENERATED);
        generator.register(YavpmItems.SUNBURN_SPAWN_EGG, Models.GENERATED);
        generator.register(YavpmItems.TANUKI_SPAWN_EGG, Models.GENERATED);
        generator.register(YavpmItems.VOID_PHANTOM_SPAWN_EGG, Models.GENERATED);

        generator.register(YavpmItems.VOID_WATER_BUCKET, Models.GENERATED);
    }

    private void createConglomerate(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(YavpmBlocks.CONGLOMERATE);

        BlockStateModelGenerator.BlockTexturePool hardenedConglomerate =
                generator.registerCubeAllModelTexturePool(YavpmBlocks.HARDENED_CONGLOMERATE);
        hardenedConglomerate.slab(YavpmBlocks.HARDENED_CONGLOMERATE_SLAB);
        hardenedConglomerate.stairs(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS);
        hardenedConglomerate.wall(YavpmBlocks.HARDENED_CONGLOMERATE_WALL);

        BlockStateModelGenerator.BlockTexturePool hardenedConglomerateBricks =
                generator.registerCubeAllModelTexturePool(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
        hardenedConglomerateBricks.slab(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB);
        hardenedConglomerateBricks.stairs(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS);
        hardenedConglomerateBricks.wall(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedConglomerate =
                generator.registerCubeAllModelTexturePool(YavpmBlocks.DULL_CONGLOMERATE);
        polishedConglomerate.slab(YavpmBlocks.DULL_CONGLOMERATE_SLAB);
    }

    private void createDensitite(ItemModelGenerator generator) {
        generator.register(YavpmItems.DENSITITE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        generator.register(YavpmItems.DENSITITE_INGOT, Models.GENERATED);

        generator.register(YavpmItems.DENSITITE_SWORD, Models.HANDHELD);
        generator.register(YavpmItems.DENSITITE_SHOVEL, Models.HANDHELD);
        generator.register(YavpmItems.DENSITITE_PICKAXE, Models.HANDHELD);
        generator.register(YavpmItems.DENSITITE_AXE, Models.HANDHELD);
        generator.register(YavpmItems.DENSITITE_HOE, Models.HANDHELD);

        generator.registerArmor(YavpmItems.DENSITITE_HELMET, DensititeMaterial.ARMOR_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        generator.registerArmor(YavpmItems.DENSITITE_CHESTPLATE, DensititeMaterial.ARMOR_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        generator.registerArmor(YavpmItems.DENSITITE_LEGGINGS, DensititeMaterial.ARMOR_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        generator.registerArmor(YavpmItems.DENSITITE_BOOTS, DensititeMaterial.ARMOR_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
    }

    private void createNewDeepslate(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool sculkyDeepslatePool
                = generator.registerCubeAllModelTexturePool(YavpmBlocks.SCULKY_DEEPSLATE_BRICKS);

        sculkyDeepslatePool.stairs(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_STAIRS);
        sculkyDeepslatePool.slab(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB);
        sculkyDeepslatePool.wall(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL);
    }

    public final void registerPane(BlockStateModelGenerator generator, Block pane) {
        TextureMap textureMap = paneTextureMap(pane);
        WeightedVariant weightedVariant = createWeightedVariant(Models.TEMPLATE_GLASS_PANE_POST.upload(pane, textureMap, generator.modelCollector));
        WeightedVariant weightedVariant2 = createWeightedVariant(Models.TEMPLATE_GLASS_PANE_SIDE.upload(pane, textureMap, generator.modelCollector));
        WeightedVariant weightedVariant3 = createWeightedVariant(Models.TEMPLATE_GLASS_PANE_SIDE_ALT.upload(pane, textureMap, generator.modelCollector));
        WeightedVariant weightedVariant4 = createWeightedVariant(Models.TEMPLATE_GLASS_PANE_NOSIDE.upload(pane, textureMap, generator.modelCollector));
        WeightedVariant weightedVariant5 = createWeightedVariant(Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT.upload(pane, textureMap, generator.modelCollector));
        Item item = pane.asItem();
        generator.registerItemModel(item, generator.uploadBlockItemModel(item, pane));
        generator.blockStateCollector
                .accept(
                        MultipartBlockModelDefinitionCreator.create(pane)
                                .with(weightedVariant)
                                .with(createMultipartConditionBuilder().put(Properties.NORTH, true), weightedVariant2)
                                .with(createMultipartConditionBuilder().put(Properties.EAST, true), weightedVariant2.apply(ROTATE_Y_90))
                                .with(createMultipartConditionBuilder().put(Properties.SOUTH, true), weightedVariant3)
                                .with(createMultipartConditionBuilder().put(Properties.WEST, true), weightedVariant3.apply(ROTATE_Y_90))
                                .with(createMultipartConditionBuilder().put(Properties.NORTH, false), weightedVariant4)
                                .with(createMultipartConditionBuilder().put(Properties.EAST, false), weightedVariant5)
                                .with(createMultipartConditionBuilder().put(Properties.SOUTH, false), weightedVariant5.apply(ROTATE_Y_90))
                                .with(createMultipartConditionBuilder().put(Properties.WEST, false), weightedVariant4.apply(ROTATE_Y_270))
                );
    }

    private void registerPolarizedGlass(BlockStateModelGenerator generator) {
        WeightedVariant variant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(YavpmBlocks.POLARIZED_GLASS, generator.modelCollector));
        WeightedVariant variant2 = BlockStateModelGenerator.createWeightedVariant(generator.createSubModel(YavpmBlocks.POLARIZED_GLASS, "_on", Models.CUBE_ALL, TextureMap::all));
        generator.blockStateCollector
                .accept(VariantsBlockModelDefinitionCreator.of(YavpmBlocks.POLARIZED_GLASS)
                        .with(BlockStateModelGenerator.createBooleanModelMap(Properties.POWERED, variant, variant2)));
    }

    private static void food(ItemModelGenerator generator) {
        generator.register(YavpmItems.MOLY, Models.GENERATED);

        generator.register(YavpmItems.PRETZEL, Models.GENERATED);

        generator.register(YavpmItems.CANTALOUPE_SLICE, Models.GENERATED);

        generator.register(YavpmItems.BREADING, Models.GENERATED);
        generator.register(YavpmItems.FRIED_BANANA, Models.GENERATED);
        generator.register(YavpmItems.FRIED_COD, Models.GENERATED);

        generator.register(YavpmItems.BANANA, Models.GENERATED);
        generator.register(YavpmItems.COOKED_PEANUT, Models.GENERATED);
        generator.register(YavpmItems.CHOCOLATE, Models.GENERATED);

        generator.register(YavpmItems.FORTUNE_COOKIE, Models.GENERATED);
        generator.register(YavpmItems.LUCKY_SLIP, Models.GENERATED);

        generator.register(YavpmItems.DIAMOND_ACORN, Models.GENERATED);

        generator.register(YavpmItems.PERSIMMON, Models.GENERATED);
        generator.register(YavpmItems.GOLDEN_PERSIMMON, Models.GENERATED);

        generator.register(YavpmItems.TRUFFLE, Models.GENERATED);
        generator.registerWithTextureSource(YavpmItems.FANCY_MUSHROOM_STEW, Items.MUSHROOM_STEW, Models.GENERATED);

        generator.register(YavpmItems.CHEESE, Models.GENERATED);

        generator.register(YavpmItems.BEAN_TOAST, Models.GENERATED);
        generator.register(YavpmItems.COOKED_EGG, Models.GENERATED);

        generator.register(YavpmItems.FAKE_BEEF, Models.GENERATED);
        generator.register(YavpmItems.COOKED_FAKE_BEEF, Models.GENERATED);
        generator.register(YavpmItems.FAKE_MILK_BUCKET, Models.GENERATED);
        generator.register(YavpmItems.TOFU, Models.GENERATED);

        generator.register(YavpmItems.JELLY, Models.GENERATED);
        generator.register(YavpmItems.SWEET_BERRY_JELLY, Models.GENERATED);

        generator.register(YavpmItems.RICE, Models.GENERATED);
        generator.register(YavpmItems.RICE_BAR, Models.GENERATED);
        generator.register(YavpmItems.RICE_PASTRY, Models.GENERATED);
        generator.register(YavpmItems.SUSHI, Models.GENERATED);
        generator.register(YavpmItems.SEA_SOUP, Models.GENERATED);

        generator.register(YavpmItems.CHICKEN_SOUP, Models.GENERATED);
    }

    private void createCrops(BlockStateModelGenerator generator) {
        generator.registerTintableCrossBlockStateWithStages(
                YavpmBlocks.BITTER_BERRY_BUSH,
                BlockStateModelGenerator.CrossType.NOT_TINTED,
                BitterBerryBushBlock.AGE, 0, 1, 2, 3
        );
        generator.registerCrop(YavpmBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        generator.registerCrop(YavpmBlocks.WARPED_WART_CROP, Properties.AGE_3, 0, 1, 1, 2);
        generator.registerCrop(YavpmBlocks.PEANUT_CROP, PeanutCropBlock.AGE, 0, 1, 2, 3);
        registerBananaCrop(generator);

        registerCrossCrop(generator, YavpmBlocks.OAK_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.BIRCH_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.CRIMSON_FUNGUS_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.WARPED_FUNGUS_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.MAGIC_BEAN_CROP, MagicBeanCropBlock.AGE, 0,1,1,2,3,4,5);

        generator.registerSingleton(YavpmBlocks.CANTALOUPE, TexturedModel.CUBE_COLUMN);
        generator.registerGourd(YavpmBlocks.CANTALOUPE_STEM, YavpmBlocks.ATTACHED_CANTALOUPE_STEM);
    }

    private static void registerBananaCrop(BlockStateModelGenerator generator) {
        Block block = YavpmBlocks.BANANA_CROP;
        generator.registerItemModel(block.asItem());
        generator.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator.of(block)
                                .with(BlockStateVariantMap.models(BananaCropBlock.AGE, Properties.DOUBLE_BLOCK_HALF).generate((age, half) -> switch (half) {
                                    case UPPER -> createWeightedVariant(ModelIds.getBlockSubModelId(block, "_top_stage_" + age));
                                    case LOWER -> createWeightedVariant(ModelIds.getBlockSubModelId(block, "_bottom_stage_" + age));
                                }))
                );
    }

    private static void createKimberliteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool kimberlitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.KIMBERLITE);
        kimberlitePool.stairs(YavpmBlocks.KIMBERLITE_STAIRS);
        kimberlitePool.slab(YavpmBlocks.KIMBERLITE_SLAB);
        kimberlitePool.wall(YavpmBlocks.KIMBERLITE_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedKimberlitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_KIMBERLITE);
        polishedKimberlitePool.stairs(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
        polishedKimberlitePool.slab(YavpmBlocks.POLISHED_KIMBERLITE_SLAB);
        polishedKimberlitePool.wall(YavpmBlocks.POLISHED_KIMBERLITE_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedKimberliteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
        polishedKimberliteBrickPool.stairs(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);
        polishedKimberliteBrickPool.slab(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB);
        polishedKimberliteBrickPool.wall(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);
    }
    private static void createAndesiteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool polishedPool = generator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE);
        polishedPool.wall(YavpmBlocks.POLISHED_ANDESITE_WALL);

        BlockStateModelGenerator.BlockTexturePool cobbledAndesitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_ANDESITE);
        cobbledAndesitePool.stairs(YavpmBlocks.COBBLED_ANDESITE_STAIRS);
        cobbledAndesitePool.slab(YavpmBlocks.COBBLED_ANDESITE_SLAB);
        cobbledAndesitePool.wall(YavpmBlocks.COBBLED_ANDESITE_WALL);

        BlockStateModelGenerator.BlockTexturePool andesiteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        andesiteBrickPool.stairs(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);
        andesiteBrickPool.slab(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);
        andesiteBrickPool.wall(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);
    }
    private static void createGraniteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool polishedPool = generator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE);
        polishedPool.wall(YavpmBlocks.POLISHED_GRANITE_WALL);

        BlockStateModelGenerator.BlockTexturePool cobbledGranitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_GRANITE);
        cobbledGranitePool.stairs(YavpmBlocks.COBBLED_GRANITE_STAIRS);
        cobbledGranitePool.slab(YavpmBlocks.COBBLED_GRANITE_SLAB);
        cobbledGranitePool.wall(YavpmBlocks.COBBLED_GRANITE_WALL);

        BlockStateModelGenerator.BlockTexturePool graniteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_GRANITE_BRICKS);
        graniteBrickPool.stairs(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
        graniteBrickPool.slab(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
        graniteBrickPool.wall(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);
    }
    private static void createDioriteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool polishedPool = generator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE);
        polishedPool.wall(YavpmBlocks.POLISHED_DIORITE_WALL);

        BlockStateModelGenerator.BlockTexturePool cobbledDioritePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_DIORITE);
        cobbledDioritePool.stairs(YavpmBlocks.COBBLED_DIORITE_STAIRS);
        cobbledDioritePool.slab(YavpmBlocks.COBBLED_DIORITE_SLAB);
        cobbledDioritePool.wall(YavpmBlocks.COBBLED_DIORITE_WALL);

        BlockStateModelGenerator.BlockTexturePool dioriteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        dioriteBrickPool.stairs(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        dioriteBrickPool.slab(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        dioriteBrickPool.wall(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);
    }

    private static void createAppleSet(BlockStateModelGenerator generator) {
        // Apple Logs and Woods
        generator.createLogTexturePool(YavpmBlocks.APPLE_LOG).log(YavpmBlocks.APPLE_LOG).wood(YavpmBlocks.APPLE_WOOD);
        generator.createLogTexturePool(YavpmBlocks.STRIPPED_APPLE_LOG).log(YavpmBlocks.STRIPPED_APPLE_LOG).wood(YavpmBlocks.STRIPPED_APPLE_WOOD);

        generator.registerSimpleCubeAll(YavpmBlocks.APPLE_LEAVES);
        generator.registerSimpleCubeAll(YavpmBlocks.FLOWERING_APPLE_LEAVES);

        // Apple Planks and Texture Pool
        BlockStateModelGenerator.BlockTexturePool applePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.APPLE_PLANKS);
        applePool.family(YavpmBlocks.APPLE_FAMILY).stairs(YavpmBlocks.APPLE_STAIRS);
        generator.registerHangingSign(YavpmBlocks.APPLE_LOG, YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN);
        generator.registerTrapdoor(YavpmBlocks.APPLE_TRAPDOOR);
    }

    private static void createPersimmonSet(BlockStateModelGenerator generator) {
        // Persimmon Logs and Woods
        generator.createLogTexturePool(YavpmBlocks.PERSIMMON_LOG).log(YavpmBlocks.PERSIMMON_LOG).wood(YavpmBlocks.PERSIMMON_WOOD);
        generator.createLogTexturePool(YavpmBlocks.STRIPPED_PERSIMMON_LOG).log(YavpmBlocks.STRIPPED_PERSIMMON_LOG).wood(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);

        generator.registerSingleton(YavpmBlocks.PERSIMMON_LEAVES, TexturedModel.LEAVES);

        // Persimmon Planks and Texture Pool
        BlockStateModelGenerator.BlockTexturePool persimmonPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.PERSIMMON_PLANKS);
        persimmonPool.family(YavpmBlocks.PERSIMMON_FAMILY).stairs(YavpmBlocks.PERSIMMON_STAIRS);
        generator.registerHangingSign(YavpmBlocks.PERSIMMON_LOG, YavpmBlocks.PERSIMMON_HANGING_SIGN, YavpmBlocks.PERSIMMON_WALL_HANGING_SIGN);
        generator.registerTrapdoor(YavpmBlocks.PERSIMMON_TRAPDOOR);
    }

    private static void createPrickleSet(BlockStateModelGenerator generator) {
        generator.createLogTexturePool(YavpmBlocks.PRICKLE_LOG).log(YavpmBlocks.PRICKLE_LOG).wood(YavpmBlocks.PRICKLE_WOOD);
        generator.createLogTexturePool(YavpmBlocks.STRIPPED_PRICKLE_LOG).log(YavpmBlocks.STRIPPED_PRICKLE_LOG).wood(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

        BlockStateModelGenerator.BlockTexturePool pricklePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.PRICKLE_PLANKS);

        pricklePool.family(YavpmBlocks.PRICKLE_FAMILY).stairs(YavpmBlocks.PRICKLE_STAIRS);
        generator.registerHangingSign(YavpmBlocks.PRICKLE_LOG, YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN);
        generator.registerTrapdoor(YavpmBlocks.PRICKLE_TRAPDOOR);
    }

    private static void createStuddedArmor(ItemModelGenerator generator) {
        generator.registerArmor(YavpmItems.STUDDED_HELMET, StuddedMaterial.ARMOR_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, true);
        generator.registerArmor(YavpmItems.STUDDED_CHESTPLATE, StuddedMaterial.ARMOR_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, true);
        generator.registerArmor(YavpmItems.STUDDED_LEGGINGS, StuddedMaterial.ARMOR_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, true);
        generator.registerArmor(YavpmItems.STUDDED_BOOTS, StuddedMaterial.ARMOR_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, true);

    }

    public final void registerCrossCrop(BlockStateModelGenerator generator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        generator.registerItemModel(crop.asItem());
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            generator.blockStateCollector
                    .accept(
                            VariantsBlockModelDefinitionCreator.of(crop)
                                    .with(
                                            BlockStateVariantMap.models(ageProperty)
                                                    .generate(
                                                            age -> {
                                                                int i = ageTextureIndices[age];
                                                                return createWeightedVariant(
                                                                        int2ObjectMap.computeIfAbsent(
                                                                                i, (Int2ObjectFunction<? extends Identifier>) (stage -> generator.createSubModel(crop, "_stage" + stage, Models.CROSS, TextureMap::cross))
                                                                        )
                                                                );
                                                            }
                                                    )
                                    )
                    );
        }
    }
}
