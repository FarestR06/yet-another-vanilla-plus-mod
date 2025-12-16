package com.farestr06.yavpm.item.enchantment;

import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.item.enchantment.effect.LapDogEnchantmentEffect;
import com.farestr06.yavpm.item.enchantment.effect.ParryEnchantmentEffect;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.*;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEnchantments {
    // Damage
    public static final ResourceKey<Enchantment> CRITICAL_HIT = registerKey("critical_hit");
    public static final ResourceKey<Enchantment> VOID_STRIKE = registerKey("void_strike");
    public static final ResourceKey<Enchantment> ILLAGERS_BANE = registerKey("illagers_bane");
    public static final ResourceKey<Enchantment> ENDERBANE = registerKey("enderbane");

    // Boots
    public static final ResourceKey<Enchantment> FIGURE_EIGHT = registerKey("figure_eight");

    // Shield
    public static final ResourceKey<Enchantment> PARRY = registerKey("parry");

    // Elytra
    public static final ResourceKey<Enchantment> STIFFNESS = registerKey("stiffness");

    // Ranged
    public static final ResourceKey<Enchantment> TEMPO_THEFT = registerKey("tempo_theft");

    // Wolf Armor
    public static final ResourceKey<Enchantment> MAULING = registerKey("mauling");
    public static final ResourceKey<Enchantment> BLEED_OUT = registerKey("bleed_out");
    public static final ResourceKey<Enchantment> CRUSHING = registerKey("crushing");

    public static final ResourceKey<Enchantment> RETRIEVE = registerKey("retrieve");

    public static final ResourceKey<Enchantment> LAP_DOG = registerKey("lap_dog");
    public static final ResourceKey<Enchantment> COUNTER = registerKey("counter");
    public static final ResourceKey<Enchantment> PLAGUE = registerKey("plague");
    // Horse Armor
    public static final ResourceKey<Enchantment> GALLOP = registerKey("gallop");
    public static final ResourceKey<Enchantment> BOUNDING = registerKey("bounding");

    public static void bootstrap(BootstrapContext<Enchantment> registerable) {
        var enchantments = registerable.lookup(Registries.ENCHANTMENT);
        var blocks = registerable.lookup(Registries.BLOCK);
        var items = registerable.lookup(Registries.ITEM);
        var entities = registerable.lookup(Registries.ENTITY_TYPE);
        var damageTypes = registerable.lookup(Registries.DAMAGE_TYPE);

        // region Critical Hit
        register(
                registerable,
                CRITICAL_HIT,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                2,
                                3,
                                Enchantment.dynamicCost(10, 5),
                                Enchantment.dynamicCost(15, 5),
                                6,
                                EquipmentSlotGroup.MAINHAND
                        ))
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new MultiplyValue(LevelBasedValue.constant(2.5f)),
                                LootItemRandomChanceCondition.randomChance(
                                        EnchantmentLevelProvider.forEnchantmentLevel(
                                                LevelBasedValue.perLevel(0.1f, 0.05f)
                                        )
                                )
                        )
        );
        // endregion
        // region Void Strike
        register(
                registerable,
                VOID_STRIKE,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                3,
                                4,
                                Enchantment.dynamicCost(10, 5),
                                Enchantment.dynamicCost(15, 5),
                                6,
                                EquipmentSlotGroup.MAINHAND
                        ))
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new ApplyMobEffect(
                                        HolderSet.direct(YavpmStatusEffects.VOID_TOUCHED),
                                        LevelBasedValue.constant(1f),
                                        LevelBasedValue.perLevel(1.5f, 0.5F),
                                        LevelBasedValue.constant(0.0F),
                                        LevelBasedValue.constant(1.0F)
                                )
                        )
        );
        // endregion
        // region Illager's Bane
        register(
                registerable,
                ILLAGERS_BANE,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                        5,
                                        5,
                                        Enchantment.dynamicCost(5, 8),
                                        Enchantment.dynamicCost(25, 8),
                                        2,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new AddValue(LevelBasedValue.perLevel(2.5F)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE))
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new ApplyMobEffect(
                                        HolderSet.direct(MobEffects.SLOWNESS),
                                        LevelBasedValue.constant(1.5F),
                                        LevelBasedValue.perLevel(1.5F, 0.5F),
                                        LevelBasedValue.constant(3.0F),
                                        LevelBasedValue.constant(3.0F)
                                ),
                                LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE))
                                        )
                                        .and(DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true)))
                        )
        );
        // endregion
        // region Enderbane
        register(registerable,
                ENDERBANE,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                5,
                                1,
                                Enchantment.dynamicCost(5, 8),
                                Enchantment.dynamicCost(25, 8),
                                2,
                                EquipmentSlotGroup.MAINHAND
                        )
                ).exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new MultiplyValue(LevelBasedValue.constant(1.25f)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(
                                                EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_25)
                                        )
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new MultiplyValue(LevelBasedValue.constant(1.5f)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(
                                                EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_50)
                                        )
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new MultiplyValue(LevelBasedValue.constant(1.75f)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(
                                                EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_75)
                                        )
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new MultiplyValue(LevelBasedValue.constant(2)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(
                                                EntityTypePredicate.of(entities, YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_100)
                                        )
                                )
                        )
        );
        // endregion
        // region Figure Eight
        EntityPredicate.Builder builder = EntityPredicate.Builder.entity()
                .periodicTick(5)
                .flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false).setOnGround(true))
                .moving(MovementPredicate.horizontalSpeed(MinMaxBounds.Doubles.atLeast(1.0E-5F)))
                .movementAffectedBy(LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(blocks, BlockTags.ICE)));
        register(
                registerable,
                FIGURE_EIGHT,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                        1,
                                        1,
                                        Enchantment.dynamicCost(10, 10),
                                        Enchantment.dynamicCost(25, 10),
                                        8,
                                        EquipmentSlotGroup.FEET
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.LOCATION_CHANGED,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.withDefaultNamespace("enchantment.figure_eight"),
                                        Attributes.MOVEMENT_SPEED,
                                        LevelBasedValue.constant(0.06f),
                                        AttributeModifier.Operation.ADD_VALUE
                                ),
                                AllOfCondition.allOf(
                                        InvertedLootItemCondition.invert(
                                                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity()))
                                        ),
                                        AnyOfCondition.anyOf(
                                                AllOfCondition.allOf(
                                                        EnchantmentActiveCheck.enchantmentActiveCheck(),
                                                        LootItemEntityPropertyCondition.hasProperties(
                                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
                                                        ),
                                                        AnyOfCondition.anyOf(
                                                                LootItemEntityPropertyCondition.hasProperties(
                                                                        LootContext.EntityTarget.THIS,
                                                                        EntityPredicate.Builder.entity()
                                                                                .movementAffectedBy(
                                                                                        LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(blocks, BlockTags.ICE))
                                                                                )
                                                                ),
                                                                LootItemEntityPropertyCondition.hasProperties(
                                                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(false)).build()
                                                                )
                                                        )
                                                ),
                                                AllOfCondition.allOf(
                                                        EnchantmentActiveCheck.enchantmentInactiveCheck(),
                                                        LootItemEntityPropertyCondition.hasProperties(
                                                                LootContext.EntityTarget.THIS,
                                                                EntityPredicate.Builder.entity()
                                                                        .movementAffectedBy(
                                                                                LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(blocks, BlockTags.ICE))
                                                                        )
                                                                        .flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
                                                        )
                                                )
                                        )
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.LOCATION_CHANGED,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.withDefaultNamespace("enchantment.figure_eight"),
                                        Attributes.MOVEMENT_EFFICIENCY,
                                        LevelBasedValue.constant(1.0f),
                                        AttributeModifier.Operation.ADD_VALUE
                                ),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                                .movementAffectedBy(LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(blocks, BlockTags.ICE)))
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.LOCATION_CHANGED,
                                new ChangeItemDamage(LevelBasedValue.constant(1f)),
                                AllOfCondition.allOf(
                                        LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.constant(0.04F))),
                                        LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                        .flags(EntityFlagsPredicate.Builder.flags().setOnGround(true))
                                                        .movementAffectedBy(LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(blocks, BlockTags.ICE)))
                                        )
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.TICK,
                                new PlaySoundEffect(YavpmSounds.ENCHANTMENT_FIGURE_EIGHT, ConstantFloat.of(0.6f), UniformFloat.of(1f, 1.5f)),
                                AllOfCondition.allOf(LootItemRandomChanceCondition.randomChance(0.9f), LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, builder))
                        )
        );
        // endregion
        // region Parry
        register(
                registerable,
                PARRY,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ConventionalItemTags.SHIELD_TOOLS),
                                items.getOrThrow(ConventionalItemTags.SHIELD_TOOLS),
                                8,
                                2,
                                Enchantment.dynamicCost(10, 20),
                                Enchantment.dynamicCost(60, 20),
                                8,
                                EquipmentSlotGroup.OFFHAND
                        )
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.VICTIM,
                        EnchantmentTarget.ATTACKER,
                        AllOf.entityEffects(
                                new ParryEnchantmentEffect(
                                        LevelBasedValue.constant(1.0F), LevelBasedValue.constant(5.0F), damageTypes.getOrThrow(DamageTypes.THORNS)
                                ),
                                new ChangeItemDamage(LevelBasedValue.constant(2.0F))
                        ),
                        LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.15F)))
                )
        );
        // endregion
        // region Stiffness
        register(
                registerable,
                STIFFNESS,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_GLIDER),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_GLIDER),
                                10,
                                3,
                                Enchantment.dynamicCost(1, 11),
                                Enchantment.dynamicCost(12, 11),
                                1,
                                EquipmentSlotGroup.CHEST
                        )
                ).withEffect(
                        EnchantmentEffectComponents.DAMAGE_PROTECTION,
                        new AddValue(LevelBasedValue.perLevel(1.5f, 0.5f)),
                        DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY)))
                )
        );
        // endregion
        // region Tempo Theft
        register(
                registerable,
                TEMPO_THEFT,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                                items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                                2,
                                4,
                                Enchantment.dynamicCost(15, 10),
                                Enchantment.dynamicCost(30, 5),
                                1,
                                EquipmentSlotGroup.MAINHAND
                        )
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ApplyMobEffect(
                                HolderSet.direct(MobEffects.SLOWNESS),
                                LevelBasedValue.perLevel(3f, 3f),
                                LevelBasedValue.perLevel(3f, 3f),
                                LevelBasedValue.constant(0f),
                                LevelBasedValue.constant(0f)
                        )
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.ATTACKER,
                        new ApplyMobEffect(
                                HolderSet.direct(MobEffects.SPEED),
                                LevelBasedValue.perLevel(4f, 4f),
                                LevelBasedValue.perLevel(4f, 4f),
                                LevelBasedValue.constant(0f),
                                LevelBasedValue.constant(0f)
                        )
                )
        );
        // endregion
        // region Mauling
        register(
                registerable,
                MAULING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        10,
                                        3,
                                        Enchantment.dynamicCost(1, 11),
                                        Enchantment.dynamicCost(21, 11),
                                        2,
                                        EquipmentSlotGroup.BODY
                                )
                        )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE, new AddValue(LevelBasedValue.perLevel(1.5f, 1.5f)
                                )
                        )
        );
        // endregion
        // region Bleed Out
        register(
                registerable,
                BLEED_OUT,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        7,
                                        4,
                                        Enchantment.dynamicCost(5, 8),
                                        Enchantment.dynamicCost(25, 8),
                                        2,
                                        EquipmentSlotGroup.BODY
                                )
                        )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new ApplyMobEffect(
                                        HolderSet.direct(YavpmStatusEffects.WOUNDED),
                                        LevelBasedValue.constant(2f),
                                        LevelBasedValue.perLevel(2f, 1f),
                                        LevelBasedValue.constant(0f),
                                        LevelBasedValue.constant(0f)
                                )
                        )
        );
        // endregion
        // region Crushing
        register(
                registerable,
                CRUSHING,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                2,
                                4,
                                Enchantment.dynamicCost(15, 9),
                                Enchantment.dynamicCost(65, 9),
                                4,
                                EquipmentSlotGroup.BODY
                        )
                )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE))
                        .withEffect(EnchantmentEffectComponents.ARMOR_EFFECTIVENESS, new AddValue(LevelBasedValue.perLevel(-0.1f)))
        );
        // endregion
        // region Retrieve
        register(
                registerable,
                RETRIEVE,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                2,
                                5,
                                Enchantment.dynamicCost(15, 9),
                                Enchantment.dynamicCost(65, 9),
                                4,
                                EquipmentSlotGroup.BODY
                        )
                )
                        .withEffect(
                                EnchantmentEffectComponents.EQUIPMENT_DROPS,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new AddValue(LevelBasedValue.perLevel(0.0075F)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entities, EntityType.WOLF))
                                )
                        )
        );
        // endregion
        // region Lap Dog
        register(
                registerable,
                LAP_DOG,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                10,
                                3,
                                Enchantment.dynamicCost(1, 11),
                                Enchantment.dynamicCost(12, 11),
                                1,
                                EquipmentSlotGroup.BODY
                        )
                )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .withEffect(
                                EnchantmentEffectComponents.TICK,
                                new LapDogEnchantmentEffect()
                        )
        );
        // endregion
        // region Counter
        register(
                registerable,
                COUNTER,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        3,
                                        2,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        8,
                                        EquipmentSlotGroup.BODY
                                )
                        )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.VICTIM,
                                EnchantmentTarget.ATTACKER,
                                new DamageEntity(
                                        LevelBasedValue.constant(1f),
                                        LevelBasedValue.constant(4f),
                                        damageTypes.getOrThrow(DamageTypes.THORNS)
                                ),
                                LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.15f)))
                        )
        );
        // endregion
        // region Plague
        register(
                registerable,
                PLAGUE,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR),
                                        7,
                                        2,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        8,
                                        EquipmentSlotGroup.BODY
                                )
                        )
                        .exclusiveWith(enchantments.getOrThrow(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.VICTIM,
                                EnchantmentTarget.ATTACKER,
                                new ApplyMobEffect(
                                        HolderSet.direct(MobEffects.INFESTED),
                                        LevelBasedValue.constant(2f),
                                        LevelBasedValue.perLevel(2f),
                                        LevelBasedValue.constant(0f),
                                        LevelBasedValue.perLevel(0f, 1f)
                                ),
                                LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.2f)))
                        )
        );
        // endregion
        // region Gallop
        register(
                registerable,
                GALLOP,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                6,
                                3,
                                Enchantment.dynamicCost(10, 10),
                                Enchantment.dynamicCost(25, 10),
                                5,
                                EquipmentSlotGroup.BODY
                        )
                )
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        makeId("enchantment.gallop"),
                                        Attributes.MOVEMENT_SPEED,
                                        LevelBasedValue.perLevel(0.04f, 0.03f),
                                        AttributeModifier.Operation.ADD_VALUE
                                ))
        );
        // endregion
        // region Bounding
        register(
                registerable,
                BOUNDING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                        items.getOrThrow(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR),
                                        14,
                                        2,
                                        Enchantment.dynamicCost(10, 10),
                                        Enchantment.dynamicCost(25, 10),
                                        5,
                                        EquipmentSlotGroup.BODY
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        makeId("enchantment.bounding.jump_strength"),
                                        Attributes.JUMP_STRENGTH,
                                        LevelBasedValue.perLevel(0.25f, 0.75f),
                                        AttributeModifier.Operation.ADD_VALUE
                                ))
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        makeId("enchantment.bounding.safe_fall_distance"),
                                        Attributes.SAFE_FALL_DISTANCE,
                                        LevelBasedValue.perLevel(3f, 5f),
                                        AttributeModifier.Operation.ADD_VALUE
                                )
                        )
        );
        // endregion
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    private static ResourceKey<Enchantment> registerKey(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, makeId(id));
    }
}
