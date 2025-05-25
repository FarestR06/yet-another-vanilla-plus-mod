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
import net.minecraft.world.spawner.SpecialSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @ModifyVariable(method = "createWorlds", at = @At("STORE"), ordinal = 0)
    private List<SpecialSpawner> modified(List<SpecialSpawner> spawners, @Local ServerWorldProperties properties) {
        return ImmutableList.of(
                new PhantomSpawner(), new PatrolSpawner(), new CatSpawner(), new ZombieSiegeManager(),
                new WanderingTraderManager(properties), new SunburnSpawner()
        );
    }
}
