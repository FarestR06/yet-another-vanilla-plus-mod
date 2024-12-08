package com.farestr06.yavpm.item.enchantment;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {
    public static final RegistryKey<Enchantment> VOID_STRIKE = RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId("void_strike"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(
                registerable,
                VOID_STRIKE,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                3,
                                4,
                                Enchantment.leveledCost(10, 5),
                                Enchantment.leveledCost(15, 5),
                                6,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new ApplyMobEffectEnchantmentEffect(
                                        RegistryEntryList.of(YavpmStatusEffects.VOID_TOUCHED),
                                        EnchantmentLevelBasedValue.constant(1f),
                                        EnchantmentLevelBasedValue.linear(1.5f, 0.5F),
                                        EnchantmentLevelBasedValue.constant(0.0F),
                                        EnchantmentLevelBasedValue.constant(1.0F)
                                )
                        )
        );
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
