package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.CarbonfowlEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

@Environment(EnvType.CLIENT)
public class CarbonfowlEntityRenderer extends MobEntityRenderer<CarbonfowlEntity, ChickenEntityModel<CarbonfowlEntity>> {
    private static final Identifier TEXTURE = makeId("textures/entity/carbonfowl.png");

    public CarbonfowlEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public Identifier getTexture(CarbonfowlEntity entity) {
        return TEXTURE;
    }

    protected float getAnimationProgress(CarbonfowlEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}
