package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.SunburnEntity;
import com.farestr06.yavpm.entity.mob.client.model.SunburnEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class SunburnEntityRenderer extends MobEntityRenderer<SunburnEntity, LivingEntityRenderState, SunburnEntityModel> {
    public static final Identifier TEXTURE = makeId("textures/entity/sunburn.png");

    public SunburnEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SunburnEntityModel(context.getPart(YavpmModelLayers.SUNBURN)), 0.7f);
    }

    @Override
    public void render(LivingEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);

        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
