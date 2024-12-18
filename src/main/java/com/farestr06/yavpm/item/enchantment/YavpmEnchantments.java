package com.farestr06.yavpm.item.enchantment;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.item.enchantment.effect.LapDogEnchantmentEffect;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.enchantment.effect.entity.DamageEntityEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.enchantment.effect.value.MultiplyEnchantmentEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.EnchantmentLevelLootNumberProvider;
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
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {
    public static final RegistryKey<Enchantment> VOID_STRIKE = registerKey("void_strike");
    public static final RegistryKey<Enchantment> ILLAGERS_BANE = registerKey("illagers_bane");
    public static final RegistryKey<Enchantment> ENDERBANE = registerKey("enderbane");
    // Elytra
    public static final RegistryKey<Enchantment> STIFFNESS = registerKey("stiffness");
    // Wolf Armor
    public static final RegistryKey<Enchantment> MAULING = registerKey("mauling");
    public static final RegistryKey<Enchantment> BLEED_OUT = registerKey("bleed_out");
    public static final RegistryKey<Enchantment> CRUSHING = registerKey("crushing");

    public static final RegistryKey<Enchantment> RETRIEVE = registerKey("retrieve");

    public static final RegistryKey<Enchantment> LAP_DOG = registerKey("lap_dog");
    public static final RegistryKey<Enchantment> PARRY = registerKey("parry");
    public static final RegistryKey<Enchantment> PLAGUE = registerKey("plague");
    // Horse Armor
    public static final RegistryKey<Enchantment> GALLOP = registerKey("gallop");
    public static final RegistryKey<Enchantment> BOUNDING = registerKey("bounding");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);
        var damageTypes = registerable.getRegistryLookup(RegistryKeys.DAMAGE_TYPE);

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
                        new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.5f, 0.5f)),
                        DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY)))
                )
        );
        // endregion
        // region Mauling
        register(
                registerable,
                MAULING,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        10,
                                        3,
                                        Enchantment.leveledCost(1, 11),
                                        Enchantment.leveledCost(21, 11),
                                        2,
                                        AttributeModifierSlot.BODY
                                )
                        )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.5f, 1.5f)
                                )
                        )
        );
        // endregion
        // region Bleed Out
        register(
                registerable,
                BLEED_OUT,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        7,
                                        4,
                                        Enchantment.leveledCost(5, 8),
                                        Enchantment.leveledCost(25, 8),
                                        2,
                                        AttributeModifierSlot.BODY
                                )
                        )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new ApplyMobEffectEnchantmentEffect(
                                        RegistryEntryList.of(StatusEffects.WITHER),
                                        EnchantmentLevelBasedValue.constant(2f),
                                        EnchantmentLevelBasedValue.linear(2f, 1f),
                                        EnchantmentLevelBasedValue.constant(0f),
                                        EnchantmentLevelBasedValue.constant(0f)
                                )
                        )
        );
        // endregion
        // region Crushing
        register(
                registerable,
                CRUSHING,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                2,
                                4,
                                Enchantment.leveledCost(15, 9),
                                Enchantment.leveledCost(65, 9),
                                4,
                                AttributeModifierSlot.BODY
                        )
                )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .addEffect(EnchantmentEffectComponentTypes.ARMOR_EFFECTIVENESS, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.1f)))
        );
        // endregion
        // region Retrieve
        register(
                registerable,
                RETRIEVE,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                2,
                                5,
                                Enchantment.leveledCost(15, 9),
                                Enchantment.leveledCost(65, 9),
                                4,
                                AttributeModifierSlot.BODY
                        )
                )
                        .addEffect(
                                EnchantmentEffectComponentTypes.EQUIPMENT_DROPS,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.0075F)),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.create().type(EntityTypePredicate.create(EntityType.WOLF))
                                )
                        )
        );
        // endregion
        // region Lap Dog
        register(
                registerable,
                LAP_DOG,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                10,
                                3,
                                Enchantment.leveledCost(1, 11),
                                Enchantment.leveledCost(12, 11),
                                1,
                                AttributeModifierSlot.BODY
                        )
                )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .addEffect(
                                EnchantmentEffectComponentTypes.TICK,
                                new LapDogEnchantmentEffect()
                        )
        );
        // endregion
        // region Parry
        register(
                registerable,
                PARRY,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        3,
                                        2,
                                        Enchantment.leveledCost(10, 20),
                                        Enchantment.leveledCost(60, 20),
                                        8,
                                        AttributeModifierSlot.BODY
                                )
                        )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.VICTIM,
                                EnchantmentEffectTarget.ATTACKER,
                                new DamageEntityEnchantmentEffect(
                                        EnchantmentLevelBasedValue.constant(1f),
                                        EnchantmentLevelBasedValue.constant(4f),
                                        damageTypes.getOrThrow(DamageTypes.THORNS)
                                ),
                                RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.linear(0.15f)))
                        )
        );
        // endregion
        // region Plague
        register(
                registerable,
                PLAGUE,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        7,
                                        2,
                                        Enchantment.leveledCost(10, 20),
                                        Enchantment.leveledCost(60, 20),
                                        8,
                                        AttributeModifierSlot.BODY
                                )
                        )
                        .exclusiveSet(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.VICTIM,
                                EnchantmentEffectTarget.ATTACKER,
                                new ApplyMobEffectEnchantmentEffect(
                                        RegistryEntryList.of(StatusEffects.POISON),
                                        EnchantmentLevelBasedValue.constant(2f),
                                        EnchantmentLevelBasedValue.linear(2f),
                                        EnchantmentLevelBasedValue.constant(0f),
                                        EnchantmentLevelBasedValue.linear(0f, 1f)
                                ),
                                RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.linear(0.2f)))
                        )
        );
        // endregion
        // region Gallop
        register(
                registerable,
                GALLOP,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                6,
                                3,
                                Enchantment.leveledCost(10, 10),
                                Enchantment.leveledCost(25, 10),
                                5,
                                AttributeModifierSlot.BODY
                        )
                )
                        .addEffect(
                                EnchantmentEffectComponentTypes.ATTRIBUTES,
                                new AttributeEnchantmentEffect(
                                        Identifier.ofVanilla("enchantment.gallop"),
                                        EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                        EnchantmentLevelBasedValue.linear(0.0405f, 0.0105f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                ))
        );
        // endregion
        // region Bounding
        register(
                registerable,
                BOUNDING,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                        14,
                                        2,
                                        Enchantment.leveledCost(10, 10),
                                        Enchantment.leveledCost(25, 10),
                                        5,
                                        AttributeModifierSlot.BODY
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.ATTRIBUTES,
                                new AttributeEnchantmentEffect(
                                        Identifier.ofVanilla("enchantment.bounding"),
                                        EntityAttributes.GENERIC_JUMP_STRENGTH,
                                        EnchantmentLevelBasedValue.linear(0.25f, 0.75f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                ))
        );
        // endregion
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> registerKey(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, makeId(id));
    }
}
