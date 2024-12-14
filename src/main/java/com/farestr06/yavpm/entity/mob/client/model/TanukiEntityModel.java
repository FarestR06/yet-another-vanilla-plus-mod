// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.farestr06.yavpm.entity.mob.client.model;

import com.farestr06.yavpm.entity.mob.TanukiEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class TanukiEntityModel extends EntityModel<TanukiEntity> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	public TanukiEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftFrontLeg = root.getChild("left_front_leg");
		this.leftHindLeg = root.getChild("left_hind_leg");
		this.rightFrontLeg = root.getChild("right_front_leg");
		this.rightHindLeg = root.getChild("right_hind_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 17).cuboid(-4.0F, -4.5F, -3.875F, 8.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(10, 28).cuboid(-4.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(1.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -1.5F, -5.875F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.5F, -5.125F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -8.0F, -6.0F, 7.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(21, 23).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 8.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData left_front_leg = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(26, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 21.0F, -4.0F));

		ModelPartData left_hind_leg = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(26, 6).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 21.0F, 4.0F));

		ModelPartData right_front_leg = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 21.0F, -4.0F));

		ModelPartData right_hind_leg = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 21.0F, 4.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void animateModel(TanukiEntity entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
	}

	@Override
	public void setAngles(TanukiEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

		this.head.pitch = headPitch * (float) (Math.PI / 180.0);
		this.head.yaw = headYaw * (float) (Math.PI / 180.0);
		this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
		this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
		this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay);
		body.render(matrices, vertices, light, overlay);
		leftFrontLeg.render(matrices, vertices, light, overlay);
		leftHindLeg.render(matrices, vertices, light, overlay);
		rightFrontLeg.render(matrices, vertices, light, overlay);
		rightHindLeg.render(matrices, vertices, light, overlay);
	}
}