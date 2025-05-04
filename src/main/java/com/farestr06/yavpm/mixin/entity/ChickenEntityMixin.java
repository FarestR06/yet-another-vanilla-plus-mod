package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.BiConsumer;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin {

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/ChickenEntity;forEachGiftedItem(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/registry/RegistryKey;Ljava/util/function/BiConsumer;)Z"))
    private boolean layGraphite(ChickenEntity instance, ServerWorld serverWorld, RegistryKey<LootTable> registryKey, BiConsumer<ServerWorld, ItemStack> biConsumer) {
        if (instance.getType() == YavpmEntities.CARBONFOWL) {
            int count = instance.getRandom().nextBetween(1, 4);
            instance.dropStack(serverWorld, new ItemStack(YavpmItems.GRAPHITE, count));
            return true;
        } else {
            return instance.forEachGiftedItem(serverWorld, registryKey, biConsumer);
        }
    }
}
