package com.farestr06.yavpm.entity;

import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmDamageTypes {
    public static final RegistryKey<DamageType> CUT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, makeId("cut"));
    public static final RegistryKey<DamageType> BLEED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, makeId("bleed"));
    public static final RegistryKey<DamageType> CHOKE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, makeId("choke"));

    public static DamageSource cut(World world) {
        return new DamageSource(
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(CUT));
    }

    public static DamageSource bleed(World world) {
        return new DamageSource(
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(BLEED));
    }

    public static DamageSource choke(World world) {
        return new DamageSource(
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(CHOKE));
    }

    public static void bootstrap(Registerable<DamageType> context) {
        context.register(CUT, new DamageType("cut", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
        context.register(BLEED, new DamageType("bleed", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
        context.register(CHOKE, new DamageType("choke", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f, DamageEffects.DROWNING));
    }
}
