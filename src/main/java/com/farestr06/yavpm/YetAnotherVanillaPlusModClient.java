package com.farestr06.yavpm;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmBoats;
import com.farestr06.yavpm.entity.client.CarbonfowlEntityRenderer;
import com.farestr06.yavpm.entity.client.MoongusEntityRenderer;
import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.util.Identifier;

public class YetAnotherVanillaPlusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(YavpmMobs.MOONGUS, MoongusEntityRenderer::new);
        EntityRendererRegistry.register(YavpmMobs.CARBONFOWL, CarbonfowlEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.ELECTRO_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.BANANA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PEANUT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.MAGIC_BEAN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.OAK_SAPLING_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_SHOOT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PRICKLE_TRAPDOOR, RenderLayer.getCutout());

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, -6265536),
                YavpmItems.STUDDED_HELMET,
                YavpmItems.STUDDED_CHESTPLATE,
                YavpmItems.STUDDED_LEGGINGS,
                YavpmItems.STUDDED_BOOTS
        );

        FluidRenderHandlerRegistry.INSTANCE.register(YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER, new SimpleFluidRenderHandler(
                Identifier.ofVanilla("block/water_still"),
                Identifier.ofVanilla("block/water_flow"),
                0x110011
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), YavpmFluids.STILL_VOID_WATER, YavpmFluids.FLOWING_VOID_WATER);

        TerraformBoatClientHelper.registerModelLayers(
                YavpmBoats.APPLE_BOAT_TYPE_ID, false
        );
    }
}
