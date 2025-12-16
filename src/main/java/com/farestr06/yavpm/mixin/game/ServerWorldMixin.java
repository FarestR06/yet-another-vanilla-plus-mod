package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerWorldMixin {
    @Inject(method = "gameEvent", at = @At(value = "HEAD"), cancellable = true)
    private void injected(Holder<GameEvent> event, Vec3 emitterPos, GameEvent.Context emitter, CallbackInfo ci) {
        // Does the emitter have an entity?
        if (emitter.sourceEntity() != null) {
            // Is it alive, and does it have the Silence effect?
            if (emitter.sourceEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(YavpmStatusEffects.SILENCE)) {
                // If so, stop the game event.
                ci.cancel();
            }
        }
    }
}
