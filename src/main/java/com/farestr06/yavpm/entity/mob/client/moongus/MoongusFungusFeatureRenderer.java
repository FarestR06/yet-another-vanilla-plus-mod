package com.farestr06.yavpm.entity.mob.client.moongus;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.state.BlockState;

public class MoongusFungusFeatureRenderer extends RenderLayer<MoongusEntityRenderState, CowModel> {
    private final BlockRenderDispatcher blockRenderManager;

    public MoongusFungusFeatureRenderer(RenderLayerParent<MoongusEntityRenderState, CowModel> context, BlockRenderDispatcher blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    private void submitFungusBlock(
            PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, boolean bl, int j, BlockState blockState, int k, BlockStateModel blockStateModel
    ) {
        if (bl) {
            //noinspection deprecation
            submitNodeCollector.submitBlockModel(poseStack, RenderType.outline(TextureAtlas.LOCATION_BLOCKS), blockStateModel, 0.0F, 0.0F, 0.0F, i, k, j);
        } else {
            submitNodeCollector.submitBlock(poseStack, blockState, i, k, j);
        }
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, MoongusEntityRenderState renderState, float f, float g) {

        if (!renderState.isBaby && !renderState.sheared) {
            boolean bl = renderState.appearsGlowing() && renderState.isInvisible;
            if (!renderState.isInvisible || bl) {
                BlockState blockState = renderState.type.getFungusState();
                int j = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
                BlockStateModel model = this.blockRenderManager.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitFungusBlock(poseStack, submitNodeCollector, i, bl, renderState.outlineColor, blockState, j, model);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitFungusBlock(poseStack, submitNodeCollector, i, bl, renderState.outlineColor, blockState, j, model);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitFungusBlock(poseStack, submitNodeCollector, i, bl, renderState.outlineColor, blockState, j, model);
                poseStack.popPose();
            }
        }
    }
}
