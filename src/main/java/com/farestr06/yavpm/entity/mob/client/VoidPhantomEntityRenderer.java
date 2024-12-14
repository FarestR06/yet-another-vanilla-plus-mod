package com.farestr06.yavpm.entity.mob.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class VoidPhantomEntityRenderer extends PhantomEntityRenderer {
    private static final Identifier TEXTURE = makeId("textures/entity/void_phantom.png");

    public VoidPhantomEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PhantomEntity phantomEntity) {
        return TEXTURE;
    }
}
