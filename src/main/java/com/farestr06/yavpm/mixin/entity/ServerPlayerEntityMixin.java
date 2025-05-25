package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.misc.YavpmStats;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    private ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Unique
    private final ServerPlayerEntity thiz = (ServerPlayerEntity) (Object) this;

    @Inject(method = "sleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;resetStat(Lnet/minecraft/stat/Stat;)V", shift = At.Shift.AFTER))
    private void injected(BlockPos pos, CallbackInfo ci) {
        thiz.increaseStat(Stats.CUSTOM.getOrCreateStat(YavpmStats.DAYS_SLEPT_THROUGH), 1);
    }
}
