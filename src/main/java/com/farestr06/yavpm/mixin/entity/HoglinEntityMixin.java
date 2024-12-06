package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity implements Monster, Hoglin {

    private HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    final HoglinEntity thiz = (HoglinEntity) (Object) this;

    @Inject(method = "canConvert", at = @At(value = "HEAD"), cancellable = true)
    private void injected(CallbackInfoReturnable<Boolean> cir) {
        if (thiz.hasStatusEffect(YavpmStatusEffects.NETHER_POWER)) {
            cir.setReturnValue(false);
        }
        BlockPos pos = thiz.getBlockPos();
        if (thiz.getWorld().getBiome(pos).matchesKey(YavpmBiomes.Overworld.WITHERED_SCAR)) {
            cir.setReturnValue(false);
        }
    }
}
