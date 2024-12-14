package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.BananaCropBlock;
import com.farestr06.yavpm.block.custom.MagicBeanCropBlock;
import com.farestr06.yavpm.block.custom.PeanutCropBlock;
import com.farestr06.yavpm.block.custom.SaplingCropBlock;
import com.farestr06.yavpm.item.YavpmItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class YavpmModelProvider extends FabricModelProvider {
    public YavpmModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static final Model TEMPLATE_SPAWN_EGG = new Model(
            Optional.of(Identifier.ofVanilla("item/template_spawn_egg")),
            Optional.empty()
    );

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(YavpmBlocks.GLOWING_OBSIDIAN);
        generator.registerSimpleCubeAll(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);


        generator.registerCrop(YavpmBlocks.WARPED_WART, Properties.AGE_3, 0, 1, 1, 2);
        generator.registerCrop(YavpmBlocks.PEANUT_CROP, PeanutCropBlock.AGE, 0, 1, 2, 3);
        registerBananaCrop(generator);

        registerCrossCrop(generator, YavpmBlocks.OAK_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);
        registerCrossCrop(generator, YavpmBlocks.MAGIC_BEAN_CROP, MagicBeanCropBlock.AGE, 0,1,1,2,3,4,5);

        createGraniteSet(generator);
        createDioriteSet(generator);
        createAndesiteSet(generator);

        generator.registerSimpleCubeAll(YavpmBlocks.KIMBERLITE);
        generator.registerSimpleCubeAll(YavpmBlocks.GRAPHITE_BLOCK);
        generator.registerSimpleCubeAll(YavpmBlocks.GRAPHENE_BLOCK);

        createAppleSet(generator);
        createPrickleSet(generator);

        generator.registerTintableCross(YavpmBlocks.APPLE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerTintableCross(YavpmBlocks.PRICKLE_SHOOT, BlockStateModelGenerator.TintType.NOT_TINTED);

        generator.registerStateWithModelReference(YavpmBlocks.VOID_WATER, Blocks.WATER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(YavpmItems.REACTOR, Models.GENERATED);
        generator.register(YavpmItems.HEATED_REACTOR, Models.GENERATED);
        generator.register(YavpmItems.MOLY, Models.GENERATED);

        generator.register(YavpmItems.PHANTOM_CHORD, Models.GENERATED);

        generator.register(YavpmItems.BANANA, Models.GENERATED);
        generator.register(YavpmItems.COOKED_PEANUT, Models.GENERATED);
        generator.register(YavpmItems.CHOCOLATE, Models.GENERATED);

        generator.register(YavpmItems.FORTUNE_COOKIE, Models.GENERATED);
        generator.register(YavpmItems.LUCKY_SLIP, Models.GENERATED);

        generator.register(YavpmItems.DIAMOND_ACORN, Models.GENERATED);

        generator.register(YavpmItems.TRUFFLE, Models.GENERATED);

        generator.register(YavpmItems.CHEESE, Models.GENERATED);

        generator.register(YavpmItems.BEAN_TOAST, Models.GENERATED);
        generator.register(YavpmItems.COOKED_EGG, Models.GENERATED);

        generator.register(YavpmItems.FAKE_BEEF, Models.GENERATED);
        generator.register(YavpmItems.COOKED_FAKE_BEEF, Models.GENERATED);
        generator.register(YavpmItems.FAKE_MILK_BUCKET, Models.GENERATED);
        generator.register(YavpmItems.TOFU, Models.GENERATED);

        generator.register(YavpmItems.RICE, Models.GENERATED);
        generator.register(YavpmItems.SUSHI, Models.GENERATED);
        generator.register(YavpmItems.SEA_SOUP, Models.GENERATED);

        generator.register(YavpmItems.GRAPHITE, Models.GENERATED);
        generator.register(YavpmItems.RAW_DIAMOND, Models.GENERATED);

        generator.register(YavpmItems.APPLE_BOAT, Models.GENERATED);
        generator.register(YavpmItems.APPLE_CHEST_BOAT, Models.GENERATED);

        createStuddedArmor(generator);

        generator.register(YavpmItems.MOONGUS_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
        generator.register(YavpmItems.CARBONFOWL_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
        generator.register(YavpmItems.TANUKI_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
        generator.register(YavpmItems.VOID_PHANTOM_SPAWN_EGG, TEMPLATE_SPAWN_EGG);

        generator.register(YavpmItems.VOID_WATER_BUCKET, Models.GENERATED);

        generator.register(YavpmItems.RUNE_ATTACK, Models.GENERATED);
        generator.register(YavpmItems.RUNE_DURABILITY, Models.GENERATED);
        generator.register(YavpmItems.RUNE_SPEED, Models.GENERATED);
    }

    private static void registerBananaCrop(BlockStateModelGenerator generator) {
        Block block = YavpmBlocks.BANANA_CROP;
        generator.registerItemModel(block.asItem());
        BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(BananaCropBlock.AGE, Properties.DOUBLE_BLOCK_HALF).register((age, half) -> switch (half) {
            case UPPER -> BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, "_top_stage_" + age));
            case LOWER -> BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, "_bottom_stage_" + age));
        });
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(blockStateVariantMap));
    }

    private static void createAndesiteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool cobbledAndesitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_ANDESITE);
        cobbledAndesitePool.stairs(YavpmBlocks.COBBLED_ANDESITE_STAIRS);
        cobbledAndesitePool.slab(YavpmBlocks.COBBLED_ANDESITE_SLAB);
        cobbledAndesitePool.wall(YavpmBlocks.COBBLED_ANDESITE_WALL);

        BlockStateModelGenerator.BlockTexturePool andesiteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        andesiteBrickPool.stairs(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);
        andesiteBrickPool.slab(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);
        andesiteBrickPool.wall(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool andesiteTilePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_ANDESITE_TILES);
        andesiteTilePool.stairs(YavpmBlocks.POLISHED_ANDESITE_TILE_STAIRS);
        andesiteTilePool.slab(YavpmBlocks.POLISHED_ANDESITE_TILE_SLAB);
        andesiteTilePool.wall(YavpmBlocks.POLISHED_ANDESITE_TILE_WALL);
    }
    private static void createGraniteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool cobbledGranitePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_GRANITE);
        cobbledGranitePool.stairs(YavpmBlocks.COBBLED_GRANITE_STAIRS);
        cobbledGranitePool.slab(YavpmBlocks.COBBLED_GRANITE_SLAB);
        cobbledGranitePool.wall(YavpmBlocks.COBBLED_GRANITE_WALL);

        BlockStateModelGenerator.BlockTexturePool graniteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_GRANITE_BRICKS);
        graniteBrickPool.stairs(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
        graniteBrickPool.slab(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
        graniteBrickPool.wall(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool graniteTilePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_GRANITE_TILES);
        graniteTilePool.stairs(YavpmBlocks.POLISHED_GRANITE_TILE_STAIRS);
        graniteTilePool.slab(YavpmBlocks.POLISHED_GRANITE_TILE_SLAB);
        graniteTilePool.wall(YavpmBlocks.POLISHED_GRANITE_TILE_WALL);
    }
    private static void createDioriteSet(BlockStateModelGenerator generator) {
        BlockStateModelGenerator.BlockTexturePool cobbledDioritePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.COBBLED_DIORITE);
        cobbledDioritePool.stairs(YavpmBlocks.COBBLED_DIORITE_STAIRS);
        cobbledDioritePool.slab(YavpmBlocks.COBBLED_DIORITE_SLAB);
        cobbledDioritePool.wall(YavpmBlocks.COBBLED_DIORITE_WALL);

        BlockStateModelGenerator.BlockTexturePool dioriteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        dioriteBrickPool.stairs(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        dioriteBrickPool.slab(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        dioriteBrickPool.wall(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool dioriteTilePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_DIORITE_TILES);
        dioriteTilePool.stairs(YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS);
        dioriteTilePool.slab(YavpmBlocks.POLISHED_DIORITE_TILE_SLAB);
        dioriteTilePool.wall(YavpmBlocks.POLISHED_DIORITE_TILE_WALL);
    }

    private static void createAppleSet(BlockStateModelGenerator generator) {

        // Apple Logs and Woods
        generator.registerLog(YavpmBlocks.APPLE_LOG).log(YavpmBlocks.APPLE_LOG).wood(YavpmBlocks.APPLE_WOOD);
        generator.registerLog(YavpmBlocks.STRIPPED_APPLE_LOG).log(YavpmBlocks.STRIPPED_APPLE_LOG).wood(YavpmBlocks.STRIPPED_APPLE_WOOD);

        generator.registerSimpleCubeAll(YavpmBlocks.APPLE_LEAVES);

        // Apple Planks and Texture Pool
        BlockStateModelGenerator.BlockTexturePool applePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.APPLE_PLANKS);
        applePool.family(YavpmBlocks.APPLE_FAMILY);
        generator.registerHangingSign(YavpmBlocks.STRIPPED_APPLE_LOG, YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN);
    }

    private static void createPrickleSet(BlockStateModelGenerator generator) {
        generator.registerLog(YavpmBlocks.PRICKLE_LOG).log(YavpmBlocks.PRICKLE_LOG).wood(YavpmBlocks.PRICKLE_WOOD);
        generator.registerLog(YavpmBlocks.STRIPPED_PRICKLE_LOG).log(YavpmBlocks.STRIPPED_PRICKLE_LOG).wood(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

        BlockStateModelGenerator.BlockTexturePool pricklePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.PRICKLE_PLANKS);

        pricklePool.family(YavpmBlocks.PRICKLE_FAMILY);
        generator.registerHangingSign(YavpmBlocks.STRIPPED_PRICKLE_LOG, YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN);
    }

    private static void createStuddedArmor(ItemModelGenerator generator) {
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_HELMET);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_CHESTPLATE);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_LEGGINGS);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_BOOTS);

    }

    public final void registerCrossCrop(BlockStateModelGenerator generator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty)
                    .register(
                            integer -> {
                                int i = ageTextureIndices[integer];
                                Identifier identifier = int2ObjectMap.computeIfAbsent(
                                        i, (Int2ObjectFunction<? extends Identifier>)(j -> generator.createSubModel(crop, "_stage" + i, Models.CROSS, TextureMap::cross))
                                );
                                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
                            }
                    );
            generator.registerItemModel(crop.asItem());
            generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
        }
    }
}
