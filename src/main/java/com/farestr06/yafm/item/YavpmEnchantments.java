package com.farestr06.yafm.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static com.farestr06.yafm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {

    public static final RegistryKey<Enchantment> VOID_STRIKE = of("void_strike");

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId(name));
    }
}
