package com.farestr06.yavpm.entity;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmDamageTypes {
    public static final RegistryKey<DamageType> PRICKLE_DAMAGE = of("prickle");

    private static RegistryKey<DamageType> of(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, makeId(id));
    }
}
