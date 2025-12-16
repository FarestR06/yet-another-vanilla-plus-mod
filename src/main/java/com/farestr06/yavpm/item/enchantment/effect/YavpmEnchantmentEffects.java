package com.farestr06.yavpm.item.enchantment.effect;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantmentEffects {
    private static void registerEntityEffect(String name,
                                             MapCodec<? extends EnchantmentEntityEffect> codec) {
        Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, makeId(name), codec);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering enchantment effects for YAVPM!");
        registerEntityEffect("lap_dog", LapDogEnchantmentEffect.CODEC );
        registerEntityEffect("parry", ParryEnchantmentEffect.CODEC );
    }
}
