package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.TanukiEntity;
import com.farestr06.yavpm.entity.mob.client.model.TanukiEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class TanukiEntityRenderer extends MobEntityRenderer<TanukiEntity, LivingEntityRenderState, TanukiEntityModel> {
    public static final Identifier TEXTURE = makeId("textures/entity/tanuki.png");

    public TanukiEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TanukiEntityModel(context.getPart(YavpmModelLayers.TANUKI)), 0.6f);
    }

    @Override
    public void render(LivingEntityRenderState renderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(renderState.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(renderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
