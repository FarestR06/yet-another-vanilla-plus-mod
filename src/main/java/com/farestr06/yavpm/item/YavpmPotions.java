package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class YavpmPotions {

    public static final RegistryEntry<Potion> DECAY = register("decay", new Potion(
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    800
            )
    ));
    public static final RegistryEntry<Potion> LONG_DECAY = register("long_decay", new Potion(
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    1600
            )
    ));
    public static final RegistryEntry<Potion> STRONG_DECAY = register("strong_decay", new Potion(
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    400,
                    1
            )
    ));


    public static final RegistryEntry<Potion> VOID_TOUCHED = register("void_touched", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    1000
            )
    ));

    public static final RegistryEntry<Potion> LONG_VOID_TOUCHED = register("long_void_touched", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    1600
            )
    ));

    public static final RegistryEntry<Potion> STRONG_VOID_TOUCHED = register("strong_void_touched", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    400,
                    2
            )
    ));

    public static final RegistryEntry<Potion> CHOKING = register("choking", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    90
            )
    ));
    public static final RegistryEntry<Potion> LONG_CHOKING = register("long_choking", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    240
            )
    ));

    private static RegistryEntry<Potion> register(String id, Potion potion) {
        return Registry.registerReference(Registries.POTION, YetAnotherVanillaPlusMod.makeId(id), potion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering potions for YAVPM!");

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Decay...");
            builder.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(Items.WITHER_SKELETON_SKULL), DECAY);
            builder.registerPotionRecipe(DECAY, Ingredient.ofItems(Items.REDSTONE), LONG_DECAY);
            builder.registerPotionRecipe(DECAY, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_DECAY);

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of the Void...");
            builder.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(Items.ENDER_EYE), VOID_TOUCHED);
            builder.registerPotionRecipe(VOID_TOUCHED, Ingredient.ofItems(Items.REDSTONE), LONG_VOID_TOUCHED);
            builder.registerPotionRecipe(VOID_TOUCHED, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_VOID_TOUCHED);

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Choking...");
            builder.registerPotionRecipe(
                    Potions.WATER_BREATHING,
                    Ingredient.ofItems(Items.FERMENTED_SPIDER_EYE),
                    CHOKING
            );
            builder.registerPotionRecipe(
                    Potions.LONG_WATER_BREATHING,
                    Ingredient.ofItems(Items.FERMENTED_SPIDER_EYE),
                    LONG_CHOKING
            );
            builder.registerPotionRecipe(
                    CHOKING,
                    Ingredient.ofItems(Items.REDSTONE),
                    LONG_CHOKING
            );
        });
    }
}
