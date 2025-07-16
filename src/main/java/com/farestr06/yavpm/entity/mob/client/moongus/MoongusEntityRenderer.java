package com.farestr06.yavpm.entity.mob.client.moongus;

import com.farestr06.yavpm.entity.mob.MoongusEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class MoongusEntityRenderer extends AgeableMobEntityRenderer<MoongusEntity, MoongusEntityRenderState, CowEntityModel> {
    private static final Map<MoongusEntity.Variant, Identifier> TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(MoongusEntity.Variant.WARPED, makeId("textures/entity/cow/warped_moongus.png"));
        map.put(MoongusEntity.Variant.CRIMSON, makeId("textures/entity/cow/crimson_moongus.png"));
    });

    public MoongusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM_BABY)), 0.7f);
        this.addFeature(new MoongusFungusFeatureRenderer(this, context.getBlockRenderManager()));
    }

    @Override
    public MoongusEntityRenderState createRenderState() {
        return new MoongusEntityRenderState();
    }

    @Override
    public void updateRenderState(MoongusEntity entity, MoongusEntityRenderState state, float f) {
        super.updateRenderState(entity, state, f);
        state.type = entity.getMoongusVariant();
        state.sheared = entity.isSheared();
    }

    @Override
    public Identifier getTexture(MoongusEntityRenderState state) {
        return TEXTURES.get(state.type);
    }
}
