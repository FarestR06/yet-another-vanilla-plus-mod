package com.farestr06.yavpm.entity.effect;

import com.farestr06.api.effect.SimpleStatusEffect;
import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class YavpmStatusEffects {
    public static final RegistryEntry<StatusEffect> VOID_TOUCHED = register("void_touched", new SimpleStatusEffect(StatusEffectCategory.HARMFUL, 0xe079fa));
    public static final RegistryEntry<StatusEffect> NETHER_POWER = register("nether_power", new SimpleStatusEffect(StatusEffectCategory.BENEFICIAL, 0xe079fa));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, YetAnotherVanillaPlusMod.makeId(id), statusEffect);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering status effects for YAVPM!");
    }
}
