package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.TanukiEntity;
import com.farestr06.yavpm.entity.mob.client.model.TanukiEntityModel;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class TanukiEntityRenderer extends AgeableMobEntityRenderer<TanukiEntity, LivingEntityRenderState, TanukiEntityModel> {
    public static final Identifier TEXTURE = makeId("textures/entity/tanuki.png");

    public TanukiEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TanukiEntityModel(context.getPart(YavpmModelLayers.TANUKI)), new TanukiEntityModel(context.getPart(YavpmModelLayers.TANUKI_BABY)), 0.6f);
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
