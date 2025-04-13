package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "emitGameEvent", at = @At(value = "HEAD"), cancellable = true)
    private void injected(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter, CallbackInfo ci) {
        // Does the emitter have an entity?
        if (emitter.sourceEntity() != null) {
            // Is it alive, and does it have the Silence effect?
            if (emitter.sourceEntity() instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(YavpmStatusEffects.SILENCE)) {
                // If so, stop the game event.
                ci.cancel();
            }
        }
    }
}
