package com.farestr06.yavpm.item.enchantment;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.item.enchantment.effect.LapDogEnchantmentEffect;
import com.farestr06.yavpm.item.enchantment.effect.ParryEnchantmentEffect;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AllOfEnchantmentEffects;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.*;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.enchantment.effect.value.MultiplyEnchantmentEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.EnchantmentLevelLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.*;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {
    // Damage
    public static final RegistryKey<Enchantment> CRITICAL_HIT = registerKey("critical_hit");
    public static final RegistryKey<Enchantment> VOID_STRIKE = registerKey("void_strike");
    public static final RegistryKey<Enchantment> ILLAGERS_BANE = registerKey("illagers_bane");
    public static final RegistryKey<Enchantment> ENDERBANE = registerKey("enderbane");

    // Boots
    public static final RegistryKey<Enchantment> FIGURE_EIGHT = registerKey("figure_eight");

    // Shield
    public static final RegistryKey<Enchantment> PARRY = registerKey("parry");

    // Elytra
    public static final RegistryKey<Enchantment> STIFFNESS = registerKey("stiffness");

    // Wolf Armor
    public static final RegistryKey<Enchantment> MAULING = registerKey("mauling");
    public static final RegistryKey<Enchantment> BLEED_OUT = registerKey("bleed_out");
    public static final RegistryKey<Enchantment> CRUSHING = registerKey("crushing");

    public static final RegistryKey<Enchantment> RETRIEVE = registerKey("retrieve");

    public static final RegistryKey<Enchantment> LAP_DOG = registerKey("lap_dog");
    public static final RegistryKey<Enchantment> COUNTER = registerKey("counter");
    public static final RegistryKey<Enchantment> PLAGUE = registerKey("plague");
    // Horse Armor
    public static final RegistryKey<Enchantment> GALLOP = registerKey("gallop");
    public static final RegistryKey<Enchantment> BOUNDING = registerKey("bounding");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);
        var damageTypes = registerable.getRegistryLookup(RegistryKeys.DAMAGE_TYPE);

        // region Critical Hit
        register(
                registerable,
                CRITICAL_HIT,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                2,
                                3,
                                Enchantment.leveledCost(10, 5),
                                Enchantment.leveledCost(15, 5),
                                6,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.DAMAGE,
                                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.constant(2.5f)),
                                RandomChanceLootCondition.builder(
                                        EnchantmentLevelLootNumberProvider.create(
                                                EnchantmentLevelBasedValue.linear(0.1f, 0.05f)
                                        )
                                )
                        )
        );
        // endregion
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
        // region Figure Eight
        EntityPredicate.Builder builder = EntityPredicate.Builder.create()
                .periodicTick(5)
                .flags(EntityFlagsPredicate.Builder.create().flying(false).onGround(true))
                .movement(MovementPredicate.horizontalSpeed(NumberRange.DoubleRange.atLeast(1.0E-5F)))
                .movementAffectedBy(LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().tag(BlockTags.ICE)));
        register(
                registerable,
                FIGURE_EIGHT,
                Enchantment.builder(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                        1,
                                        1,
                                        Enchantment.leveledCost(10, 10),
                                        Enchantment.leveledCost(25, 10),
                                        8,
                                        AttributeModifierSlot.FEET
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.LOCATION_CHANGED,
                                new AttributeEnchantmentEffect(
                                        Identifier.ofVanilla("enchantment.figure_eight"),
                                        EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                        EnchantmentLevelBasedValue.constant(0.06f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                ),
                                AllOfLootCondition.builder(
                                        InvertedLootCondition.builder(
                                                EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().vehicle(EntityPredicate.Builder.create()))
                                        ),
                                        AnyOfLootCondition.builder(
                                                AllOfLootCondition.builder(
                                                        EnchantmentActiveCheckLootCondition.requireActive(),
                                                        EntityPropertiesLootCondition.builder(
                                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().flying(false))
                                                        ),
                                                        AnyOfLootCondition.builder(
                                                                EntityPropertiesLootCondition.builder(
                                                                        LootContext.EntityTarget.THIS,
                                                                        EntityPredicate.Builder.create()
                                                                                .movementAffectedBy(
                                                                                        LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().tag(BlockTags.ICE))
                                                                                )
                                                                ),
                                                                EntityPropertiesLootCondition.builder(
                                                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onGround(false)).build()
                                                                )
                                                        )
                                                ),
                                                AllOfLootCondition.builder(
                                                        EnchantmentActiveCheckLootCondition.requireInactive(),
                                                        EntityPropertiesLootCondition.builder(
                                                                LootContext.EntityTarget.THIS,
                                                                EntityPredicate.Builder.create()
                                                                        .movementAffectedBy(
                                                                                LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().tag(BlockTags.ICE))
                                                                        )
                                                                        .flags(EntityFlagsPredicate.Builder.create().flying(false))
                                                        )
                                                )
                                        )
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.LOCATION_CHANGED,
                                new AttributeEnchantmentEffect(
                                        Identifier.ofVanilla("enchantment.figure_eight"),
                                        EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY,
                                        EnchantmentLevelBasedValue.constant(1.0f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                ),
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.create()
                                                .movementAffectedBy(LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().tag(BlockTags.ICE)))
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.LOCATION_CHANGED,
                                new DamageItemEnchantmentEffect(EnchantmentLevelBasedValue.constant(1f)),
                                AllOfLootCondition.builder(
                                        RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.constant(0.04F))),
                                        EntityPropertiesLootCondition.builder(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.create()
                                                        .flags(EntityFlagsPredicate.Builder.create().onGround(true))
                                                        .movementAffectedBy(LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().tag(BlockTags.ICE)))
                                        )
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.TICK,
                                new PlaySoundEnchantmentEffect(YavpmSounds.ENCHANTMENT_FIGURE_EIGHT, ConstantFloatProvider.create(0.6f), UniformFloatProvider.create(1f, 1.5f)),
                                AllOfLootCondition.builder(RandomChanceLootCondition.builder(0.9f), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, builder))
                        )
        );
        // endregion
        // region Parry
        register(
                registerable,
                PARRY,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ConventionalItemTags.SHIELD_TOOLS),
                                items.getOrThrow(ConventionalItemTags.SHIELD_TOOLS),
                                8,
                                2,
                                Enchantment.leveledCost(10, 20),
                                Enchantment.leveledCost(60, 20),
                                8,
                                AttributeModifierSlot.OFFHAND
                        )
                ).addEffect(
                        EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.VICTIM,
                        EnchantmentEffectTarget.ATTACKER,
                        AllOfEnchantmentEffects.allOf(
                                new ParryEnchantmentEffect(
                                        EnchantmentLevelBasedValue.constant(1.0F), EnchantmentLevelBasedValue.constant(5.0F), damageTypes.getOrThrow(DamageTypes.THORNS)
                                ),
                                new DamageItemEnchantmentEffect(EnchantmentLevelBasedValue.constant(2.0F))
                        ),
                        RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.linear(0.15F)))
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
                                        RegistryEntryList.of(YavpmStatusEffects.WOUNDED),
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
        // region Counter
        register(
                registerable,
                COUNTER,
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
                                        RegistryEntryList.of(StatusEffects.INFESTED),
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
                                        makeId("enchantment.gallop"),
                                        EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                        EnchantmentLevelBasedValue.linear(0.04f, 0.03f),
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
                                        makeId("enchantment.bounding.jump_strength"),
                                        EntityAttributes.GENERIC_JUMP_STRENGTH,
                                        EnchantmentLevelBasedValue.linear(0.25f, 0.75f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                ))
                        .addEffect(
                                EnchantmentEffectComponentTypes.ATTRIBUTES,
                                new AttributeEnchantmentEffect(
                                        makeId("enchantment.bounding.safe_fall_distance"),
                                        EntityAttributes.GENERIC_SAFE_FALL_DISTANCE,
                                        EnchantmentLevelBasedValue.linear(3f, 5f),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                )
                        )
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
