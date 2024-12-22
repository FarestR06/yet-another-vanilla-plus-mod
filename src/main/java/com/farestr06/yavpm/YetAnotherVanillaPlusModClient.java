package com.farestr06.yavpm;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmBoats;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.client.*;
import com.farestr06.yavpm.entity.mob.client.model.TanukiEntityModel;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.FoliageColors;

public class YetAnotherVanillaPlusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        setUpBlocks();
        setUpEntities();
        setUpColors();

        TerraformBoatClientHelper.registerModelLayers(
                YavpmBoats.APPLE_BOAT_TYPE_ID, false
        );
        TerraformBoatClientHelper.registerModelLayers(
                YavpmBoats.PERSIMMON_BOAT_TYPE_ID, false
        );
    }

    private static void setUpColors() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, -6265536),
                YavpmItems.STUDDED_HELMET,
                YavpmItems.STUDDED_CHESTPLATE,
                YavpmItems.STUDDED_LEGGINGS,
                YavpmItems.STUDDED_BOOTS
        );

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> FoliageColors.getDefaultColor(),
                YavpmBlocks.PERSIMMON_LEAVES.asItem()
        );

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), YavpmBlocks.PERSIMMON_LEAVES);
    }

    private static void setUpBlocks() {
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.POLARIZED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.BANANA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PEANUT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.MAGIC_BEAN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.OAK_SAPLING_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PERSIMMON_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_SHOOT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PERSIMMON_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PERSIMMON_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PERSIMMON_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_TRAPDOOR, RenderLayer.getCutout());

        FluidRenderHandlerRegistry.INSTANCE.register(YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER, new SimpleFluidRenderHandler(
                Identifier.ofVanilla("block/water_still"),
                Identifier.ofVanilla("block/water_flow"),
                0x1f001f
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER);
    }

    private static void setUpEntities() {
        EntityRendererRegistry.register(YavpmEntities.MOONGUS, MoongusEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.CARBONFOWL, CarbonfowlEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.VOID_PHANTOM, VoidPhantomEntityRenderer::new);
        EntityRendererRegistry.register(YavpmEntities.TANUKI, TanukiEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(YavpmModelLayers.TANUKI, TanukiEntityModel::getTexturedModelData);
    }
}
