package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.SunburnEntity;
import com.farestr06.yavpm.entity.mob.client.model.SunburnEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class SunburnEntityRenderer extends MobRenderer<SunburnEntity, LivingEntityRenderState, SunburnEntityModel> {
    public static final ResourceLocation TEXTURE = makeId("textures/entity/sunburn.png");

    public SunburnEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SunburnEntityModel(context.bakeLayer(YavpmModelLayers.SUNBURN)), 0.7f);
    }

    @Override
    public void submit(LivingEntityRenderState livingEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.scale(1f, 1f, 1f);

        super.submit(livingEntityRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
