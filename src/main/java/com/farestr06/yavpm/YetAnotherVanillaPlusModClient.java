package com.farestr06.yavpm;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.client.*;
import com.farestr06.yavpm.entity.mob.client.model.SunburnEntityModel;
import com.farestr06.yavpm.entity.mob.client.model.TanukiEntityModel;
import com.farestr06.yavpm.entity.mob.client.moongus.MoongusEntityRenderer;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.StemBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.FoliageColors;

public class YetAnotherVanillaPlusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        setUpBlocks();
        setUpEntities();
        setUpColors();
    }

    private static void setUpColors() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> -2046180, YavpmBlocks.ATTACHED_CANTALOUPE_STEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int i = state.get(StemBlock.AGE);
            return ColorHelper.getArgb(i * 32, 255 - i * 8, i * 4);
        }, YavpmBlocks.CANTALOUPE_STEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.DEFAULT, YavpmBlocks.PERSIMMON_LEAVES);
    }

    private static void setUpBlocks() {
        BlockRenderLayerMap.putBlock(YavpmBlocks.POLARIZED_GLASS, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.SHOJI, BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(YavpmBlocks.ATTACHED_CANTALOUPE_STEM, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.CANTALOUPE_STEM, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.WARPED_WART_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BANANA_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.RICE_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PEANUT_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.MAGIC_BEAN_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BITTER_BERRY_BUSH, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.OAK_SAPLING_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BIRCH_SAPLING_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.CRIMSON_FUNGUS_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.WARPED_FUNGUS_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_SHOOT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_LEAVES, BlockRenderLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.FLOWERING_APPLE_LEAVES, BlockRenderLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_LEAVES, BlockRenderLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PINATA, BlockRenderLayer.CUTOUT);

        FluidRenderHandlerRegistry.INSTANCE.register(YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER, new SimpleFluidRenderHandler(
                Identifier.ofVanilla("block/water_still"),
                Identifier.ofVanilla("block/water_flow"),
                0x1f001f
        ));
        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT, YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER);
    }

    private static void setUpEntities() {
        EntityRendererRegistry.register(YavpmEntities.MOONGUS, MoongusEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.CARBONFOWL, CarbonfowlEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.SUNBURN, SunburnEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.VOID_PHANTOM, VoidPhantomEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.TANUKI, TanukiEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(YavpmModelLayers.TANUKI, TanukiEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(YavpmModelLayers.SUNBURN, SunburnEntityModel::getTexturedModelData);

        TerraformBoatClientHelper.registerModelLayers(YavpmItems.APPLE_BOAT_ID);
        TerraformBoatClientHelper.registerModelLayers(YavpmItems.PERSIMMON_BOAT_ID);
    }
}
