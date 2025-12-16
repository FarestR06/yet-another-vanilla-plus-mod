package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.DiamondChickenEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

@Environment(EnvType.CLIENT)
public class CarbonfowlEntityRenderer extends MobRenderer<DiamondChickenEntity, ChickenRenderState, ChickenModel> {
    private static final ResourceLocation TEXTURE = makeId("textures/entity/carbonfowl.png");

    public CarbonfowlEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public void submit(ChickenRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if (renderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public void extractRenderState(DiamondChickenEntity livingEntity, ChickenRenderState renderState, float f) {
        super.extractRenderState(livingEntity, renderState, f);
        renderState.flap = Mth.lerp(f, livingEntity.oFlap, livingEntity.flap);
        renderState.flapSpeed = Mth.lerp(f, livingEntity.oFlapSpeed, livingEntity.flapSpeed);
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenRenderState livingEntityRenderState) {
        return TEXTURE;
    }
}
