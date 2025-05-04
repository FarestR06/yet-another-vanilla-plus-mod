package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    private AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }
    
    private final AnimalEntity thiz = (AnimalEntity) (Object) this;

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V", at = @At(value = "HEAD"), cancellable = true)
    private void injected(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        if (thiz instanceof ChickenEntity && YavpmConfig.HANDLER.instance().chickenBreedingCreatesEggs) {
            ItemStack itemStack = new ItemStack(Items.EGG);
            itemStack.set(YavpmDataComponentTypes.ALWAYS_HATCHES, Unit.INSTANCE);
            ItemEntity itemEntity = new ItemEntity(world, thiz.getPos().getX(), thiz.getPos().getY(), thiz.getPos().getZ(), itemStack);
            itemEntity.setToDefaultPickupDelay();
            thiz.breed(world, other, null);
            thiz.playSound(SoundEvents.BLOCK_SNIFFER_EGG_PLOP, 1.0F, (thiz.getRandom().nextFloat() - thiz.getRandom().nextFloat()) * 0.2F + 0.5F);
            world.spawnEntity(itemEntity);
            ci.cancel();
        }
    }
}
