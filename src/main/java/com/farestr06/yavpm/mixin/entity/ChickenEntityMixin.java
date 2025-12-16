package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.BiConsumer;

@Mixin(Chicken.class)
public abstract class ChickenEntityMixin {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Chicken;dropFromGiftLootTable(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/resources/ResourceKey;Ljava/util/function/BiConsumer;)Z"))
    private boolean layGraphite(Chicken instance, ServerLevel serverWorld, ResourceKey<LootTable> registryKey, BiConsumer<ServerLevel, ItemStack> biConsumer) {
        if (instance.getType() == YavpmEntities.CARBONFOWL) {
            int count = instance.getRandom().nextIntBetweenInclusive(1, 4);
            instance.spawnAtLocation(serverWorld, new ItemStack(YavpmItems.GRAPHITE, count));
            return true;
        } else {
            return instance.dropFromGiftLootTable(serverWorld, registryKey, biConsumer);
        }
    }
}
