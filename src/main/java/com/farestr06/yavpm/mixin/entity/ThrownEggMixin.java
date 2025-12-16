package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThrownEgg.class)
public abstract class ThrownEggMixin extends ThrowableItemProjectile {
    public ThrownEggMixin(EntityType<? extends ThrowableItemProjectile> type, LivingEntity owner, Level world, ItemStack stack) {
        super(type, owner, world, stack);
    }

    @Unique
    private final ThrownEgg self = (ThrownEgg) (Object) this;

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I", ordinal = 0))
    private int alwaysHatchesRedirect(RandomSource instance, int i) {
        if (self.getItem().get(YavpmDataComponentTypes.Item.ALWAYS_HATCHES) != null) return 0;
        return instance.nextInt(i);
    }

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnReason;)Lnet/minecraft/world/entity/Entity;"))
    private Entity carbonfowlRedirect(EntityType<Entity> instance, Level world, EntitySpawnReason reason) {
        if (self.getItem().get(YavpmDataComponentTypes.Item.HATCHES_CARBONFOWL) != null) {
            return YavpmEntities.CARBONFOWL.create(world, reason);
        }
        return instance.create(world, reason);
    }
}
