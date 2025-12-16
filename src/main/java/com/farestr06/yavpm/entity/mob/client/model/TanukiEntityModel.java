// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.farestr06.yavpm.entity.mob.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class TanukiEntityModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	public TanukiEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftFrontLeg = root.getChild("left_front_leg");
		this.leftHindLeg = root.getChild("left_hind_leg");
		this.rightFrontLeg = root.getChild("right_front_leg");
		this.rightHindLeg = root.getChild("right_hind_leg");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -4.5F, -3.875F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(10, 28).addBox(-4.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(1.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -1.5F, -5.875F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 17.5F, -5.125F));

		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -8.0F, -6.0F, 7.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 24.0F, 0.0F));

        body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 8.0F, 0.3927F, 0.0F, 0.0F));

        modelPartData.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(26, 17).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(2.0F, 21.0F, -4.0F));

        modelPartData.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(26, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(2.0F, 21.0F, 4.0F));

        modelPartData.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(-2.0F, 21.0F, -4.0F));

        modelPartData.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(-2.0F, 21.0F, 4.0F));
        return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		float f = state.walkAnimationPos;
		float g = state.walkAnimationSpeed;
		this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
		this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
		this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
		this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
	}
}