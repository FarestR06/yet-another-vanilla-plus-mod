package com.farestr06.yavpm.entity.mob.client;

import com.farestr06.yavpm.entity.mob.MoongusEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.MooshroomMushroomFeatureRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class MoongusEntityRenderer extends MobEntityRenderer<MoongusEntity, CowEntityModel<MoongusEntity>> {
    private static final Map<MoongusEntity.Type, Identifier> TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(MooshroomEntity.Type.BROWN, makeId("textures/entity/cow/warped_moongus.png"));
        map.put(MooshroomEntity.Type.RED, makeId("textures/entity/cow/crimson_moongus.png"));
    });

    public MoongusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7f);

        this.addFeature(new MooshroomMushroomFeatureRenderer<>(this, context.getBlockRenderManager()));
    }

    @Override
    public Identifier getTexture(MoongusEntity entity) {
        return TEXTURES.get(entity.getVariant());
    }
}
