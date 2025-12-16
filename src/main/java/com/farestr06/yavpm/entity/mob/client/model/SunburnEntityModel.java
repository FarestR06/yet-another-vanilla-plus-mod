package com.farestr06.yavpm.entity.mob.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

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
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(-8, 16).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 16.0F, 0.0F));

        body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -9.0F, -4.25F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.body.xRot = state.ageInTicks / 20;
		this.body.yRot = state.ageInTicks / 20;
		this.body.zRot = state.ageInTicks / 20;
	}
}