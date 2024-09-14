package com.farestr06.yafm.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class YavpmFoods {
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
            .saturationModifier(0.3f)
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
}
