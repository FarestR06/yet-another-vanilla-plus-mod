package com.farestr06.yavpm.entity.effect;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class YavpmStatusEffects {
    public static final RegistryEntry<StatusEffect> VOIDED = register("voided", new VoidedStatusEffect());

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, YetAnotherVanillaPlusMod.makeId(id), statusEffect);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering status effects for YAVPM!");
    }
}
