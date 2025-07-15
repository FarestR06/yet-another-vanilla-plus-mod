package com.farestr06.yavpm.entity.mob.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmModelLayers {
    public static final EntityModelLayer TANUKI = new EntityModelLayer(
            makeId("tanuki"),
            "main"
    );

    public static final EntityModelLayer TANUKI_BABY = new EntityModelLayer(
            makeId("tanuki_baby"),
            "main"
    );

    public static final EntityModelLayer SUNBURN = new EntityModelLayer(
            makeId("sunburn"),
            "main"
    );
}
