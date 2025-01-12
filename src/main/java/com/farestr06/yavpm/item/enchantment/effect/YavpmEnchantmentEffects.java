package com.farestr06.yavpm.item.enchantment.effect;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantmentEffects {
    private static void registerEntityEffect(String name,
                                             MapCodec<? extends EnchantmentEntityEffect> codec) {
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, makeId(name), codec);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering enchantment effects for YAVPM!");
        registerEntityEffect("lap_dog", LapDogEnchantmentEffect.CODEC );
        registerEntityEffect("parry", ParryEnchantmentEffect.CODEC );
    }
}
