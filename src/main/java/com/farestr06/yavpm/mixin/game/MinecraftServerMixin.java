package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.world.SunburnSpawner;
import com.google.common.collect.ImmutableList;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private <E> ImmutableList<E> redirected(E e1, E e2, E e3, E e4, E e5) {
        return (ImmutableList<E>) ImmutableList.of(
                e1, e2, e3, e4, e5, new SunburnSpawner()
        );
    }
}
