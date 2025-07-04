package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.component.AlwaysHatchesComponent;
import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    private AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Unique
    private final AnimalEntity thiz = (AnimalEntity) (Object) this;

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V", at = @At(value = "HEAD"), cancellable = true)
    private void injected(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        if (thiz instanceof ChickenEntity chicken && YavpmConfig.HANDLER.instance().chickenBreedingCreatesEggs) { // Is it a chicken or carbonfowl?
            if (world instanceof ServerWorld server) {
                AtomicReference<ItemStack> itemStack = new AtomicReference<>(); // Item Stack for chicken to lay, atomic reference for the lambda
                if (chicken.getType() == YavpmEntities.CARBONFOWL) {
                    itemStack.set(new ItemStack(YavpmItems.CARBON_EGG)); // If it's a carbonfowl, then we lay a carbon egg
                } else {
                    // Otherwise, lay the normal egg
                    chicken.forEachGiftedItem(server, LootTables.CHICKEN_LAY_GAMEPLAY, (serverWorld, itemStack1) -> itemStack.set(itemStack1));
                }
                itemStack.get().set(YavpmDataComponentTypes.ALWAYS_HATCHES, AlwaysHatchesComponent.INSTANCE);
                ItemEntity itemEntity = new ItemEntity(world, thiz.getPos().getX(), thiz.getPos().getY(), thiz.getPos().getZ(), itemStack.get());
                itemEntity.setToDefaultPickupDelay();
                thiz.breed(world, other, null);
                thiz.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (thiz.getRandom().nextFloat() - thiz.getRandom().nextFloat()) * 0.2F + 0.5F);
                world.spawnEntity(itemEntity);
                ci.cancel();
            }
        }
    }
}
