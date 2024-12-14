package com.farestr06.yavpm.item.enchantment;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.enchantment.effect.value.MultiplyEnchantmentEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {
    public static final RegistryKey<Enchantment> VOID_STRIKE = RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId("void_strike"));
    public static final RegistryKey<Enchantment> ILLAGERS_BANE = RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId("illagers_bane"));
    public static final RegistryKey<Enchantment> ENDERBANE = RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId("enderbane"));
    public static final RegistryKey<Enchantment> STIFFNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId("stiffness"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        // region Void Strike
        register(
                registerable,
                VOID_STRIKE,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
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
        // endregion

        // region Illager's Bane
        register(
                registerable,
                ILLAGERS_BANE,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                        5,
                                        5,
                                        Enchantment.leveledCost(5, 8),
                                        Enchantment.leveledCost(25, 8),
                                        2,
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                        .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.5F)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE))
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new ApplyMobEffectEnchantmentEffect(
                                        RegistryEntryList.of(StatusEffects.SLOWNESS),
                                        EnchantmentLevelBasedValue.constant(1.5F),
                                        EnchantmentLevelBasedValue.linear(1.5F, 0.5F),
                                        EnchantmentLevelBasedValue.constant(3.0F),
                                        EnchantmentLevelBasedValue.constant(3.0F)
                                ),
                                EntityPropertiesLootCondition.builder(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE))
                                        )
                                        .and(DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().isDirect(true)))
                        )
        );
        // endregion

        // region Enderbane
        register(registerable,
                ENDERBANE,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                5,
                                1,
                                Enchantment.leveledCost(5, 8),
                                Enchantment.leveledCost(25, 8),
                                2,
                                AttributeModifierSlot.MAINHAND
                        )
                ).exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.constant(1.25f)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(
                                                EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_25)
                                        )
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.constant(1.5f)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(
                                                EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_50)
                                        )
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.constant(1.75f)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(
                                                EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_75)
                                        )
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.constant(2)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(
                                                EntityTypePredicate.create(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_100)
                                        )
                                )
                        )
        );
        // endregion

        // region Stiffness
        register(
                registerable,
                STIFFNESS,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_GLIDER),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_GLIDER),
                                10,
                                3,
                                Enchantment.leveledCost(1, 11),
                                Enchantment.leveledCost(12, 11),
                                1,
                                AttributeModifierSlot.CHEST
                        )
                ).addEffect(
                        EnchantmentEffectComponentTypes.DAMAGE_PROTECTION,
                        new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1f, 0.5f)),
                        DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY)))
                )
        );
        // endregion
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
