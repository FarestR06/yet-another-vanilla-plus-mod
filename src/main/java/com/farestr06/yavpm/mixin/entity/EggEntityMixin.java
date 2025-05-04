package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin extends ThrownItemEntity {
    public EggEntityMixin(EntityType<? extends ThrownItemEntity> type, LivingEntity owner, World world, ItemStack stack) {
        super(type, owner, world, stack);
    }

    @Unique
    private final EggEntity thiz = (EggEntity) (Object) this;

    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I", ordinal = 0))
    private int injected(Random instance, int i) {
        if (thiz.getStack() != null) {
            if (thiz.getStack().get(YavpmDataComponentTypes.ALWAYS_HATCHES) != null) return 0;
        }
        return instance.nextInt(i);
    }
}
