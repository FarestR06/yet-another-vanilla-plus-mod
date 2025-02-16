package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.CarbonfowlEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.ChickenEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

@Environment(EnvType.CLIENT)
public class CarbonfowlEntityRenderer extends MobEntityRenderer<CarbonfowlEntity, ChickenEntityRenderState, ChickenEntityModel> {
    private static final Identifier TEXTURE = makeId("textures/entity/carbonfowl.png");

    public CarbonfowlEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public ChickenEntityRenderState createRenderState() {
        return new ChickenEntityRenderState();
    }

    @Override
    public Identifier getTexture(ChickenEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public void updateRenderState(CarbonfowlEntity livingEntity, ChickenEntityRenderState renderState, float f) {
        super.updateRenderState(livingEntity, renderState, f);
        renderState.flapProgress = MathHelper.lerp(f, livingEntity.prevFlapProgress, livingEntity.flapProgress);
        renderState.maxWingDeviation = MathHelper.lerp(f, livingEntity.prevMaxWingDeviation, livingEntity.maxWingDeviation);
    }
}
