package com.farestr06.yafm;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.entity.YavpmBoats;
import com.farestr06.yafm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.type.DyedColorComponent;

public class YetAnotherVanillaPlusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.ELECTRO_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.BANANA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.PEANUT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.OAK_SAPLING_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(YavpmBlocks.APPLE_LEAVES, RenderLayer.getCutoutMipped());

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, -6265536),
                YavpmItems.STUDDED_HELMET,
                YavpmItems.STUDDED_CHESTPLATE,
                YavpmItems.STUDDED_LEGGINGS,
                YavpmItems.STUDDED_BOOTS
        );

        TerraformBoatClientHelper.registerModelLayers(
                YavpmBoats.APPLE_BOAT_TYPE_ID, false
        );
    }
}
