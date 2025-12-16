package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.world.component.AlwaysHatchesComponent;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(Animal.class)
public abstract class AnimalEntityMixin extends AgeableMob {
    private AnimalEntityMixin(EntityType<? extends AgeableMob> entityType, Level world) {
        super(entityType, world);
    }
    
    @Unique
    private final Animal self = (Animal) (Object) this;

    @Inject(method = "spawnChildFromBreeding(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/animal/Animal;)V", at = @At(value = "HEAD"), cancellable = true)
    private void injected(ServerLevel world, Animal other, CallbackInfo ci) {
        if (self instanceof Chicken chicken && YavpmConfig.HANDLER.instance().chickenBreedingCreatesEggs) { // Is it a chicken or carbonfowl?
            if (world instanceof ServerLevel server) {
                AtomicReference<ItemStack> itemStack = new AtomicReference<>(); // Item Stack for chicken to lay, atomic reference for the lambda
                if (chicken.getType() == YavpmEntities.CARBONFOWL) {
                    itemStack.set(new ItemStack(YavpmItems.CARBON_EGG)); // If it's a carbonfowl, then we lay a carbon egg
                } else {
                    // Otherwise, lay the normal egg
                    chicken.dropFromGiftLootTable(server, BuiltInLootTables.CHICKEN_LAY, (serverWorld, itemStack1) -> itemStack.set(itemStack1));
                }
                itemStack.get().set(YavpmDataComponentTypes.Item.ALWAYS_HATCHES, AlwaysHatchesComponent.INSTANCE);
                ItemEntity itemEntity = new ItemEntity(world, self.position().x(), self.position().y(), self.position().z(), itemStack.get());
                itemEntity.setDefaultPickUpDelay();
                self.finalizeSpawnChildFromBreeding(world, other, null);
                self.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (self.getRandom().nextFloat() - self.getRandom().nextFloat()) * 0.2F + 0.5F);
                world.addFreshEntity(itemEntity);
                ci.cancel();
            }
        }
    }
}
