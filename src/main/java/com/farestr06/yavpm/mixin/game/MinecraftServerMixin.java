package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.world.SunburnSpawner;
import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.village.VillageSiege;
import net.minecraft.world.entity.npc.CatSpawner;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @ModifyVariable(method = "createLevels", at = @At("STORE"), ordinal = 0)
    private List<CustomSpawner> modified(List<CustomSpawner> spawners, @Local ServerLevelData properties) {
        return ImmutableList.of(
                new PhantomSpawner(), new PatrolSpawner(), new CatSpawner(), new VillageSiege(),
                new WanderingTraderSpawner(properties), new SunburnSpawner()
        );
    }
}
