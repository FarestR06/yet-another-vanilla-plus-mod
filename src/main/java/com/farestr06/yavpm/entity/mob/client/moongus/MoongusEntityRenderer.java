package com.farestr06.yavpm.entity.mob.client.moongus;

import com.farestr06.yavpm.entity.mob.FungusCowEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class MoongusEntityRenderer extends MobRenderer<FungusCowEntity, MoongusEntityRenderState, CowModel> {
    private static final Map<FungusCowEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(FungusCowEntity.Variant.WARPED, makeId("textures/entity/cow/warped_moongus.png"));
        map.put(FungusCowEntity.Variant.CRIMSON, makeId("textures/entity/cow/crimson_moongus.png"));
    });

    public MoongusEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7f);
        this.addLayer(new MoongusFungusFeatureRenderer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public MoongusEntityRenderState createRenderState() {
        return new MoongusEntityRenderState();
    }

    @Override
    public void extractRenderState(FungusCowEntity livingEntity, MoongusEntityRenderState state, float f) {
        super.extractRenderState(livingEntity, state, f);
        state.type = livingEntity.getMoongusVariant();
        state.sheared = livingEntity.isSheared();
    }

    @Override
    public ResourceLocation getTextureLocation(MoongusEntityRenderState state) {
        return TEXTURES.get(state.type);
    }
}
