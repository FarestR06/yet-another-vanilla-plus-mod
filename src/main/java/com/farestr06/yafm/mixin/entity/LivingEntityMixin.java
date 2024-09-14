package com.farestr06.yafm.mixin.entity;

import com.farestr06.yafm.util.StatusEffectClearable;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, StatusEffectClearable {

    @Shadow protected abstract void onStatusEffectRemoved(StatusEffectInstance effect);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    LivingEntity thiz = (LivingEntity) (Object) this;

    @Override
    public boolean yAVPM$clearStatusEffectsWithMilk() {
        if (!thiz.getWorld().isClient) {
            boolean bl = false;
            for (StatusEffectInstance effect : thiz.getStatusEffects()) {
                if (StatusEffectClearable.isClearableByMilk(effect)) {
                    onStatusEffectRemoved(effect);
                    bl = true;
                }
            }
            return bl;
        } else return false;
    }
}
