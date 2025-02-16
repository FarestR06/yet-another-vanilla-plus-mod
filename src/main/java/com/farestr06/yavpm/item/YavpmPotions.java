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
    private static final Ingredient EXTENDER = Ingredient.ofItems(Items.REDSTONE);
    private static final Ingredient AMPLIFIER = Ingredient.ofItems(Items.GLOWSTONE_DUST);

    public static final RegistryEntry<Potion> WEIRD = register("weird", new Potion("weird"));

    public static final RegistryEntry<Potion> DECAY = register("decay", new Potion(
            "decay",
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    600
            )
    ));
    public static final RegistryEntry<Potion> LONG_DECAY = register("long_decay", new Potion(
            "decay",
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    1200
            )
    ));
    public static final RegistryEntry<Potion> STRONG_DECAY = register("strong_decay", new Potion(
            "decay",
            new StatusEffectInstance(
                    StatusEffects.WITHER,
                    300,
                    1
            )
    ));


    public static final RegistryEntry<Potion> VOID_TOUCHED = register("void_touched", new Potion(
            "void_touched",
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    800
            )
    ));

    public static final RegistryEntry<Potion> LONG_VOID_TOUCHED = register("long_void_touched", new Potion(
            "void_touched",
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    1600
            )
    ));

    public static final RegistryEntry<Potion> STRONG_VOID_TOUCHED = register("strong_void_touched", new Potion(
            "void_touched",
            new StatusEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    400,
                    1
            )
    ));

    public static final RegistryEntry<Potion> CHOKING = register("choking", new Potion(
            "choking",
            new StatusEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    90
            )
    ));
    public static final RegistryEntry<Potion> LONG_CHOKING = register("long_choking", new Potion(
            "choking",
            new StatusEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    180
            )
    ));

    public static final RegistryEntry<Potion> HASTE = register("haste", new Potion(
            "haste",
            new StatusEffectInstance(
                    StatusEffects.HASTE,
                    2400
            )
    ));
    public static final RegistryEntry<Potion> LONG_HASTE = register("long_haste", new Potion(
            "haste",
            new StatusEffectInstance(
                    StatusEffects.HASTE,
                    4800
            )
    ));

    public static final RegistryEntry<Potion> STRONG_HASTE = register("strong_haste", new Potion(
            "haste",
            new StatusEffectInstance(
                    StatusEffects.HASTE,
                    1200,
                    2
            )
    ));


    public static final RegistryEntry<Potion> INTOXICATION = register("intoxication", new Potion(
            "intoxication",
            new StatusEffectInstance(
                    StatusEffects.NAUSEA, 300
            )
    ));

    public static final RegistryEntry<Potion> LONG_INTOXICATION = register("long_intoxication", new Potion(
            "intoxication",
            new StatusEffectInstance(
                    StatusEffects.NAUSEA, 900
            )
    ));

    private static RegistryEntry<Potion> register(String id, Potion potion) {
        return Registry.registerReference(Registries.POTION, YetAnotherVanillaPlusMod.makeId(id), potion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering potions for YAVPM!");

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Decay...");
            builder.registerPotionRecipe(Potions.WATER, YavpmItems.WARPED_WART, WEIRD);
            builder.registerPotionRecipe(WEIRD, Ingredient.ofItems(Items.WITHER_ROSE), DECAY);
            builder.registerPotionRecipe(DECAY, EXTENDER, LONG_DECAY);
            builder.registerPotionRecipe(DECAY, AMPLIFIER, STRONG_DECAY);

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of the Void...");
            builder.registerPotionRecipe(WEIRD, Ingredient.ofItems(YavpmItems.VOID_WATER_BUCKET), VOID_TOUCHED);
            builder.registerPotionRecipe(VOID_TOUCHED, EXTENDER, LONG_VOID_TOUCHED);
            builder.registerPotionRecipe(VOID_TOUCHED, AMPLIFIER, STRONG_VOID_TOUCHED);

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
                    EXTENDER,
                    LONG_CHOKING
            );

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Haste...");
            builder.registerPotionRecipe(
                    WEIRD,
                    Ingredient.ofItems(YavpmItems.BITTER_BERRIES),
                    HASTE
            );
            builder.registerPotionRecipe(
                    HASTE,
                    EXTENDER,
                    LONG_HASTE
            );
            builder.registerPotionRecipe(
                    HASTE,
                    AMPLIFIER,
                    STRONG_HASTE
            );

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Intoxication...");
            builder.registerPotionRecipe(
                    WEIRD,
                    Ingredient.ofItems(Items.SWEET_BERRIES),
                    INTOXICATION
            );
            builder.registerPotionRecipe(
                    INTOXICATION,
                    EXTENDER,
                    LONG_INTOXICATION
            );
        });
    }
}
