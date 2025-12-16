package com.farestr06.yavpm.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmDamageTypes {
    public static final ResourceKey<DamageType> CUT = ResourceKey.create(Registries.DAMAGE_TYPE, makeId("cut"));
    public static final ResourceKey<DamageType> BLEED = ResourceKey.create(Registries.DAMAGE_TYPE, makeId("bleed"));
    public static final ResourceKey<DamageType> CHOKE = ResourceKey.create(Registries.DAMAGE_TYPE, makeId("choke"));

    public static DamageSource cut(Level world) {
        return new DamageSource(
                world.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(CUT));
    }

    public static DamageSource bleed(Level world) {
        return new DamageSource(
                world.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(BLEED));
    }

    public static DamageSource choke(Level world) {
        return new DamageSource(
                world.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(CHOKE));
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(CUT, new DamageType("cut", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
        context.register(BLEED, new DamageType("bleed", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
        context.register(CHOKE, new DamageType("choke", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f, DamageEffects.DROWNING));
    }
}
