// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.farestr06.yavpm.entity.mob.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.math.MathHelper;

import java.util.Set;

public class TanukiEntityModel extends EntityModel<LivingEntityRenderState> {
	public static final BabyModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(Set.of(EntityModelPartNames.HEAD));

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	public TanukiEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild(EntityModelPartNames.HEAD);
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 17).cuboid(-4.0F, -4.5F, -3.875F, 8.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(10, 28).cuboid(-4.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(1.0F, -6.5F, -1.875F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -1.5F, -5.875F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 17.5F, -5.125F));

		ModelPartData body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -8.0F, -6.0F, 7.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        body.addChild("cube_r1", ModelPartBuilder.create().uv(21, 23).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 8.0F, 0.3927F, 0.0F, 0.0F));

        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(26, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 21.0F, -4.0F));

        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create().uv(26, 6).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 21.0F, 4.0F));

        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 21.0F, -4.0F));

        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 21.0F, 4.0F));
        return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(LivingEntityRenderState state) {
		super.setAngles(state);
		this.head.pitch = state.pitch * (float) (Math.PI / 180.0);
		this.head.yaw = state.relativeHeadYaw * (float) (Math.PI / 180.0);
		float f = state.limbSwingAnimationProgress;
		float g = state.limbSwingAmplitude;
		this.rightHindLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
		this.leftHindLeg.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
		this.rightFrontLeg.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
		this.leftFrontLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
	}
}