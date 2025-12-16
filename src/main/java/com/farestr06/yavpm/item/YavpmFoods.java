package com.farestr06.yavpm.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class YavpmFoods {
    public static final FoodProperties MOLY = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(2f)
            .alwaysEdible()
            .build();
    public static final FoodProperties TRUFFLE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1f)
            .build();

    public static final FoodProperties PRETZEL = new FoodProperties.Builder().nutrition(6).saturationModifier(0.7f).build();

    public static final FoodProperties JELLY = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodProperties SWEET_BERRY_JELLY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodProperties RICE_BAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build();
    public static final FoodProperties RICE_PASTRY = new FoodProperties.Builder().nutrition(7).saturationModifier(0.3f).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties MAGIC_BEAN = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties SEA_SOUP = createFoodBowl(7).build();
    public static final FoodProperties CHICKEN_SOUP = createFoodBowl(9).build();

    public static final FoodProperties FANCY_MUSHROOM_STEW = createFoodBowl(16).build();

    public static final FoodProperties GLISTERING_MELON_SLICE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.9f)
            .build();

    public static final FoodProperties BANANA = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .build();
    public static final FoodProperties FRIED_BANANA = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(1f)
            .build();
    public static final FoodProperties FRIED_COD = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(1.2f)
            .build();

    public static final FoodProperties RAW_PEANUT = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.2f)
            .build();

    public static final FoodProperties COOKED_PEANUT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .build();

    public static final FoodProperties CHOCOLATE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.8f)
            .build();

    public static final FoodProperties BEAN_TOAST = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.9f)
            .build();

    public static final FoodProperties COOKED_EGG = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .build();

    public static final FoodProperties ACORN = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .build();
    public static final FoodProperties DIAMOND_ACORN = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.2F)
            .alwaysEdible()
            .build();

    private static FoodProperties.Builder createFoodBowl(int hunger) {
        return new FoodProperties.Builder().nutrition(hunger).saturationModifier(0.6F);
    }

    public static class ConsumableComponents {
        public static final Consumable FANCY_MUSHROOM_STEW = defaultFood().onConsume(
                new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 2), 1f)
        ).build();

        public static final Consumable RAW_PEANUT = defaultFood()
                .consumeSeconds(0.8F)
                .onConsume(new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.7f
                ))
                .build();
        // Magic Herb
        public static final Consumable MOLY_COMPONENT = Consumable.builder()
                .onConsume(ClearAllStatusEffectsConsumeEffect.INSTANCE).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 4))).consumeSeconds(2.4f).build();
    }
}
