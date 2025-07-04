package com.farestr06.yavpm.entity.mob.client.moongus;

import com.farestr06.yavpm.entity.mob.MoongusEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class MoongusEntityRenderState extends LivingEntityRenderState {
    public MoongusEntity.Variant type = MoongusEntity.Variant.CRIMSON;
    public boolean sheared = false;
}
