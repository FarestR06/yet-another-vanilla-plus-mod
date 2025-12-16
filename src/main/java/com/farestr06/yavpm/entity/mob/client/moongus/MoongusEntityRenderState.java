package com.farestr06.yavpm.entity.mob.client.moongus;

import com.farestr06.yavpm.entity.mob.FungusCowEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MoongusEntityRenderState extends LivingEntityRenderState {
    public FungusCowEntity.Variant type = FungusCowEntity.Variant.CRIMSON;
    public boolean sheared = false;
}
