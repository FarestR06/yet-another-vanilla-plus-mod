package com.farestr06.yavpm.entity.mob.client.moongus;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class MoongusFungusFeatureRenderer extends FeatureRenderer<MoongusEntityRenderState, CowEntityModel> {
    private final BlockRenderManager blockRenderManager;

    public MoongusFungusFeatureRenderer(FeatureRendererContext<MoongusEntityRenderState, CowEntityModel> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, MoongusEntityRenderState renderState, float f, float g
    ) {
        if (!renderState.baby && !renderState.sheared) {
            boolean bl = renderState.hasOutline && renderState.invisible;
            if (!renderState.invisible || bl) {
                BlockState blockState = renderState.type.getFungusState();
                int j = LivingEntityRenderer.getOverlay(renderState, 0.0F);
                BlockStateModel bakedModel = this.blockRenderManager.getModel(blockState);
                matrixStack.push();
                matrixStack.translate(0.2F, -0.35F, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockState, j, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                matrixStack.translate(0.2F, -0.35F, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0F));
                matrixStack.translate(0.1F, 0.0F, -0.6F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockState, j, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                this.getContextModel().getHead().applyTransform(matrixStack);
                matrixStack.translate(0.0F, -0.7F, -0.2F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockState, j, bakedModel);
                matrixStack.pop();
            }
        }
    }

    private void renderMushroom(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            boolean renderAsModel,
            BlockState mushroomState,
            int overlay,
            BlockStateModel mushroomModel
    ) {
        if (renderAsModel) {
            BlockModelRenderer.render(
                    matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), mushroomModel, 0.0F, 0.0F, 0.0F, light, overlay
            );
        } else {
            this.blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }
    }
}
