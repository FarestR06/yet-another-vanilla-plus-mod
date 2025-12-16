package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.TanukiEntity;
import com.farestr06.yavpm.entity.mob.client.model.TanukiEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class TanukiEntityRenderer extends MobRenderer<TanukiEntity, LivingEntityRenderState, TanukiEntityModel> {
    public static final ResourceLocation TEXTURE = makeId("textures/entity/tanuki.png");

    public TanukiEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new TanukiEntityModel(context.bakeLayer(YavpmModelLayers.TANUKI)), 0.6f);
    }

    @Override
    public void submit(LivingEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if (renderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
