package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.BananaCropBlock;
import com.farestr06.yavpm.block.custom.PeanutCropBlock;
import com.farestr06.yavpm.block.custom.SaplingCropBlock;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class YavpmModelProvider extends FabricModelProvider {
    public YavpmModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(YavpmBlocks.GLOWING_OBSIDIAN);
        generator.registerSimpleCubeAll(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

        generator.registerCrop(YavpmBlocks.PEANUT_CROP, PeanutCropBlock.AGE, 0, 1, 2, 3);
        registerBananaCrop(generator);

        generator.registerCrop(YavpmBlocks.OAK_SAPLING_CROP, SaplingCropBlock.AGE, 0,1,2,3);

        createGraniteSet(generator);
        createDioriteSet(generator);
        createAndesiteSet(generator);

        createAppleSet(generator);

        generator.registerTintableCross(YavpmBlocks.APPLE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
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

        generator.register(YavpmItems.TRUFFLE, Models.GENERATED);

        generator.register(YavpmItems.RICE, Models.GENERATED);
        generator.register(YavpmItems.MAGIC_BEANS, Models.GENERATED);
        generator.register(YavpmItems.SEA_SOUP, Models.GENERATED);

        generator.register(YavpmItems.APPLE_BOAT, Models.GENERATED);
        generator.register(YavpmItems.APPLE_CHEST_BOAT, Models.GENERATED);

        createStuddedArmor(generator);
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

    private static void createAppleSet(BlockStateModelGenerator generator) {

        // Apple Logs and Woods
        generator.registerLog(YavpmBlocks.APPLE_LOG).log(YavpmBlocks.APPLE_LOG).wood(YavpmBlocks.APPLE_WOOD);
        generator.registerLog(YavpmBlocks.STRIPPED_APPLE_LOG).log(YavpmBlocks.STRIPPED_APPLE_LOG).wood(YavpmBlocks.STRIPPED_APPLE_WOOD);

        generator.registerSimpleCubeAll(YavpmBlocks.APPLE_LEAVES);

        // Apple Planks and Texture Pool
        BlockStateModelGenerator.BlockTexturePool applePool = generator.registerCubeAllModelTexturePool(YavpmBlocks.APPLE_PLANKS);

        // Plank variants
        applePool.stairs(YavpmBlocks.APPLE_STAIRS);
        applePool.slab(YavpmBlocks.APPLE_SLAB);
        applePool.fence(YavpmBlocks.APPLE_FENCE);
        applePool.fenceGate(YavpmBlocks.APPLE_FENCE_GATE);
        applePool.button(YavpmBlocks.APPLE_BUTTON);
        applePool.pressurePlate(YavpmBlocks.APPLE_PRESSURE_PLATE);

        // Signs
        applePool.family(YavpmBlocks.APPLE_FAMILY);
        generator.registerHangingSign(YavpmBlocks.STRIPPED_APPLE_LOG, YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN);

        // Door and Trapdoor
        generator.registerDoor(YavpmBlocks.APPLE_DOOR);
        generator.registerTrapdoor(YavpmBlocks.APPLE_TRAPDOOR);
    }

    private static void createStuddedArmor(ItemModelGenerator generator) {
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_HELMET);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_CHESTPLATE);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_LEGGINGS);
        generator.registerArmor((ArmorItem) YavpmItems.STUDDED_BOOTS);
        generator.register(
                YavpmItems.MOONGUS_SPAWN_EGG,
                new Model(
                        Optional.of(Identifier.ofVanilla("item/template_spawn_egg")),
                        Optional.empty()
                )
        );

    }
}