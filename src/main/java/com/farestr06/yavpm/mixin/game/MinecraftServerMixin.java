package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.world.SunburnSpawner;
import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.ZombieSiegeManager;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.spawner.CatSpawner;
import net.minecraft.world.spawner.PatrolSpawner;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @SuppressWarnings("unchecked")
    @Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private <E> ImmutableList<E> injected(E e1, E e2, E e3, E e4, E e5, @Local ServerWorldProperties serverWorldProperties) {
        // Make Sunburns spawn
        return (ImmutableList<E>) ImmutableList.of(
                new PhantomSpawner(), new SunburnSpawner(), new PatrolSpawner(), new CatSpawner(), new ZombieSiegeManager(), new WanderingTraderManager(serverWorldProperties)
        );
    }
}
