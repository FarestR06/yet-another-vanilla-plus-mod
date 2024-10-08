package com.farestr06.yafm.datagen;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.block.custom.BananaCropBlock;
import com.farestr06.yafm.block.custom.PeanutCropBlock;
import com.farestr06.yafm.block.custom.SaplingCropBlock;
import com.farestr06.yafm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class YavpmModelProvider extends FabricModelProvider {
    public YavpmModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(YavpmBlocks.GLOWING_OBSIDIAN);
        generator.registerSimpleCubeAll(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

        generator.registerCrop(YavpmBlocks.PEANUT_CROP, PeanutCropBlock.AGE, 0, 1, 2, 3);
        generator.registerCrop(YavpmBlocks.BANANA_CROP, BananaCropBlock.AGE, 0, 1, 2, 3, 4, 5);

        generator.registerCrop(YavpmBlocks.OAK_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);

        createGraniteSet(generator);
        createDioriteSet(generator);
        createAndesiteSet(generator);

        createAppleBlockSet(generator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(YavpmItems.SOUL_POWDER, Models.GENERATED);
        generator.register(YavpmItems.REACTOR, Models.GENERATED);
        generator.register(YavpmItems.HEATED_REACTOR, Models.GENERATED);
        generator.register(YavpmItems.MOLY, Models.GENERATED);

        generator.register(YavpmItems.BANANA, Models.GENERATED);
        generator.register(YavpmItems.COOKED_PEANUT, Models.GENERATED);
        generator.register(YavpmItems.CHOCOLATE, Models.GENERATED);

        generator.register(YavpmItems.DIAMOND_ACORN, Models.GENERATED);

        createStuddedArmor(generator);

        generator.register(YavpmItems.APPLE_BOAT, Models.GENERATED);
        generator.register(YavpmItems.APPLE_CHEST_BOAT, Models.GENERATED);
    }

    private static void createAndesiteSet(BlockStateModelGenerator generator) {
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
        BlockStateModelGenerator.BlockTexturePool dioriteBrickPool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_DIORITE_BRICKS);
        dioriteBrickPool.stairs(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
        dioriteBrickPool.slab(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
        dioriteBrickPool.wall(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool dioriteTilePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.POLISHED_DIORITE_TILES);
        dioriteTilePool.stairs(YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS);
        dioriteTilePool.slab(YavpmBlocks.POLISHED_DIORITE_TILE_SLAB);
        dioriteTilePool.wall(YavpmBlocks.POLISHED_DIORITE_TILE_WALL);
    }

    private void createAppleBlockSet(BlockStateModelGenerator generator) {
        generator.registerLog(YavpmBlocks.APPLE_LOG).log(YavpmBlocks.APPLE_LOG).wood(YavpmBlocks.APPLE_WOOD);
        generator.registerLog(YavpmBlocks.STRIPPED_APPLE_LOG).log(YavpmBlocks.STRIPPED_APPLE_LOG).wood(YavpmBlocks.STRIPPED_APPLE_WOOD);
        generator.registerSimpleCubeAll(YavpmBlocks.APPLE_LEAVES);
        BlockStateModelGenerator.BlockTexturePool applePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.APPLE_PLANKS);
        applePool.family(YavpmBlocks.APPLE_FAMILY);
        generator.registerHangingSign(YavpmBlocks.STRIPPED_APPLE_LOG, YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN);
    }

    private static void createStuddedArmor(ItemModelGenerator generator) {
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_HELMET);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_CHESTPLATE);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_LEGGINGS);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_BOOTS);
    }
}
