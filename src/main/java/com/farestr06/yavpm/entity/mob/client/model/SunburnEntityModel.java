package com.farestr06.yavpm.entity.mob.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SunburnEntityModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart body;
	private final ModelPart head;
	public SunburnEntityModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
		this.head = root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 8).cuboid(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(-8, 16).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 16.0F, 0.0F));

        body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 8).cuboid(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 25).cuboid(-3.0F, -9.0F, -4.25F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(LivingEntityRenderState state) {
		super.setAngles(state);
		this.head.pitch = state.pitch * (float) (Math.PI / 180.0);
		this.head.yaw = state.relativeHeadYaw * (float) (Math.PI / 180.0);
		this.body.pitch = state.age / 20;
		this.body.yaw = state.age / 20;
		this.body.roll = state.age / 20;
	}

}