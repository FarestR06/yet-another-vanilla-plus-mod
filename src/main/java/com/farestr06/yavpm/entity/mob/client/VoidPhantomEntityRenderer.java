package com.farestr06.yavpm.entity.mob.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.client.renderer.entity.state.PhantomRenderState;
import net.minecraft.resources.ResourceLocation;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class VoidPhantomEntityRenderer extends PhantomRenderer {
    private static final ResourceLocation TEXTURE = makeId("textures/entity/void_phantom.png");

    public VoidPhantomEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(PhantomRenderState phantomEntityRenderState) {
        return TEXTURE;
    }
}
