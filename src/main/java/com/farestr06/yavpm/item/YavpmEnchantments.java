package com.farestr06.yavpm.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOGGER;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {

    public static final RegistryKey<Enchantment> CRITICAL_HIT = of("critical_hit");
    public static final RegistryKey<Enchantment> VOID_STRIKE = of("void_strike");
    public static final RegistryKey<Enchantment> TEMPO_THEFT = of("tempo_theft");
    public static final RegistryKey<Enchantment> LONGSTRIDER = of("longstrider");

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId(name));
    }
}
