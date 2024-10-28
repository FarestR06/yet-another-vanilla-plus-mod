package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.mob.MoongusEntity;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.feature.MooshroomMushroomFeatureRenderer;
import net.minecraft.entity.passive.MooshroomEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(MooshroomMushroomFeatureRenderer.class)
public class MooshroomMushroomFeatureRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/MooshroomEntity$Type;getMushroomState()Lnet/minecraft/block/BlockState;"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/MooshroomEntity;FFFFFF)V")
    private BlockState mushroom(MooshroomEntity.Type instance, @Local(argsOnly = true) MooshroomEntity entity) {
        if (entity instanceof MoongusEntity) {
            if (instance == MooshroomEntity.Type.RED) {
                return Blocks.CRIMSON_FUNGUS.getDefaultState();
            }
            if (instance == MooshroomEntity.Type.BROWN) {
                return Blocks.WARPED_FUNGUS.getDefaultState();
            }
        }
        return instance.getMushroomState();
    }
}
