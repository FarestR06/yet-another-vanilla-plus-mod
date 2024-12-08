package com.farestr06.yavpm.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;

public class YavpmFoods {
    public static final FoodComponent MOLY = new FoodComponent.Builder()
            .nutrition(20)
            .saturationModifier(2f)
            .build();
    public static final FoodComponent TRUFFLE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(1.0f)
            .build();

    public static final FoodComponent CHEESE = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent MAGIC_BEAN = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent SEA_SOUP = createFoodBowl(7).build();

    public static final FoodComponent GLISTERING_MELON_SLICE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.9f)
            .build();

    public static final FoodComponent BANANA = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent RAW_PEANUT = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 250), 1f)
            .snack()
            .build();

    public static final FoodComponent COOKED_PEANUT = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .snack()
            .build();

    public static final FoodComponent CHOCOLATE = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.8f)
            .build();

    public static final FoodComponent BEAN_TOAST = new FoodComponent.Builder()
            .nutrition(7)
            .saturationModifier(0.9f)
            .build();

    public static final FoodComponent COOKED_EGG = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .build();

    public static final FoodComponent ACORN = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .snack()
            .build();
    public static final FoodComponent DIAMOND_ACORN = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F)
            .alwaysEdible()
            .build();

    private static FoodComponent.Builder createFoodBowl(int hunger) {
        return new FoodComponent.Builder().nutrition(hunger).saturationModifier(0.6F).usingConvertsTo(Items.BOWL);
    }
}
