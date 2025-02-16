package com.farestr06.yavpm.entity.mob.client.moongus;

import com.farestr06.yavpm.entity.mob.MoongusEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class MoongusEntityRenderState extends LivingEntityRenderState {
    public MoongusEntity.Type type = MoongusEntity.Type.CRIMSON;
    public boolean sheared = false;
}
