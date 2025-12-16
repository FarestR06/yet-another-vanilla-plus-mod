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
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.StemBlock;

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
            int i = state.getValue(StemBlock.AGE);
            return ARGB.color(i * 32, 255 - i * 8, i * 4);
        }, YavpmBlocks.CANTALOUPE_STEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT, YavpmBlocks.PERSIMMON_LEAVES);
    }

    private static void setUpBlocks() {
        BlockRenderLayerMap.putBlock(YavpmBlocks.POLARIZED_GLASS, ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.SHOJI, ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(YavpmBlocks.ATTACHED_CANTALOUPE_STEM, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.CANTALOUPE_STEM, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.WARPED_WART_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BANANA_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.RICE_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PEANUT_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.MAGIC_BEAN_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BITTER_BERRY_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.OAK_SAPLING_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.BIRCH_SAPLING_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.CRIMSON_FUNGUS_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.WARPED_FUNGUS_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_SHOOT, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_LEAVES, ChunkSectionLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.FLOWERING_APPLE_LEAVES, ChunkSectionLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.APPLE_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_LEAVES, ChunkSectionLayer.CUTOUT_MIPPED);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PERSIMMON_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PRICKLE_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(YavpmBlocks.PINATA, ChunkSectionLayer.CUTOUT);

        FluidRenderHandlerRegistry.INSTANCE.register(YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER, new SimpleFluidRenderHandler(
                ResourceLocation.withDefaultNamespace("block/water_still"),
                ResourceLocation.withDefaultNamespace("block/water_flow"),
                0x1f001f
        ));
        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER);
    }

    private static void setUpEntities() {
        EntityRenderers.register(YavpmEntities.MOONGUS, MoongusEntityRenderer::new);
        EntityRenderers.register(YavpmEntities.CARBONFOWL, CarbonfowlEntityRenderer::new);
        EntityRenderers.register(YavpmEntities.SUNBURN, SunburnEntityRenderer::new);
        EntityRenderers.register(YavpmEntities.VOID_PHANTOM, VoidPhantomEntityRenderer::new);
        EntityRenderers.register(YavpmEntities.TANUKI, TanukiEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(YavpmModelLayers.TANUKI, TanukiEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(YavpmModelLayers.SUNBURN, SunburnEntityModel::getTexturedModelData);

        TerraformBoatClientHelper.registerModelLayers(YavpmItems.APPLE_BOAT_ID);
        TerraformBoatClientHelper.registerModelLayers(YavpmItems.PERSIMMON_BOAT_ID);
    }
}
