package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.misc.YavpmStats;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin extends Player {

    @Unique
    private final ServerPlayer self = (ServerPlayer) (Object) this;

    public ServerPlayerEntityMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "startSleeping", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;resetStat(Lnet/minecraft/stats/Stat;)V", shift = At.Shift.AFTER))
    private void injected(BlockPos pos, CallbackInfo ci) {
        self.awardStat(Stats.CUSTOM.get(YavpmStats.DAYS_SLEPT_THROUGH), 1);
    }
}
