package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class YavpmPotions {
    private static final Ingredient EXTENDER = Ingredient.of(Items.REDSTONE);
    private static final Ingredient AMPLIFIER = Ingredient.of(Items.GLOWSTONE_DUST);

    public static final Holder<Potion> WEIRD = register("weird", new Potion("weird"));

    public static final Holder<Potion> DECAY = register("decay", new Potion(
            "decay",
            new MobEffectInstance(
                    MobEffects.WITHER,
                    600
            )
    ));
    public static final Holder<Potion> LONG_DECAY = register("long_decay", new Potion(
            "decay",
            new MobEffectInstance(
                    MobEffects.WITHER,
                    1200
            )
    ));
    public static final Holder<Potion> STRONG_DECAY = register("strong_decay", new Potion(
            "decay",
            new MobEffectInstance(
                    MobEffects.WITHER,
                    300,
                    1
            )
    ));


    public static final Holder<Potion> VOID_TOUCHED = register("void_touched", new Potion(
            "void_touched",
            new MobEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    800
            )
    ));

    public static final Holder<Potion> LONG_VOID_TOUCHED = register("long_void_touched", new Potion(
            "void_touched",
            new MobEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    1600
            )
    ));

    public static final Holder<Potion> STRONG_VOID_TOUCHED = register("strong_void_touched", new Potion(
            "void_touched",
            new MobEffectInstance(
                    YavpmStatusEffects.VOID_TOUCHED,
                    400,
                    1
            )
    ));

    public static final Holder<Potion> CHOKING = register("choking", new Potion(
            "choking",
            new MobEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    90
            )
    ));
    public static final Holder<Potion> LONG_CHOKING = register("long_choking", new Potion(
            "choking",
            new MobEffectInstance(
                    YavpmStatusEffects.CHOKING,
                    180
            )
    ));

    public static final Holder<Potion> HASTE = register("haste", new Potion(
            "haste",
            new MobEffectInstance(
                    MobEffects.HASTE,
                    2400
            )
    ));
    public static final Holder<Potion> LONG_HASTE = register("long_haste", new Potion(
            "haste",
            new MobEffectInstance(
                    MobEffects.HASTE,
                    4800
            )
    ));

    public static final Holder<Potion> STRONG_HASTE = register("strong_haste", new Potion(
            "haste",
            new MobEffectInstance(
                    MobEffects.HASTE,
                    1200,
                    2
            )
    ));


    public static final Holder<Potion> SILENCE = register("silence", new Potion(
            "silence",
            new MobEffectInstance(
                    YavpmStatusEffects.SILENCE,
                    500
            )
    ));
    public static final Holder<Potion> LONG_SILENCE = register("long_silence", new Potion(
            "silence",
            new MobEffectInstance(
                    YavpmStatusEffects.SILENCE,
                    1100
            )
    ));


    public static final Holder<Potion> INTOXICATION = register("intoxication", new Potion(
            "intoxication",
            new MobEffectInstance(
                    MobEffects.NAUSEA, 300
            )
    ));

    public static final Holder<Potion> LONG_INTOXICATION = register("long_intoxication", new Potion(
            "intoxication",
            new MobEffectInstance(
                    MobEffects.NAUSEA, 900
            )
    ));

    private static Holder<Potion> register(String id, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, YetAnotherVanillaPlusMod.makeId(id), potion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering potions for YAVPM!");

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Decay...");
            builder.addMix(Potions.WATER, YavpmItems.WARPED_WART, WEIRD);
            builder.registerPotionRecipe(WEIRD, Ingredient.of(Items.WITHER_ROSE), DECAY);
            builder.registerPotionRecipe(DECAY, EXTENDER, LONG_DECAY);
            builder.registerPotionRecipe(DECAY, AMPLIFIER, STRONG_DECAY);

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of the Void...");
            // Base Void potion is smelted
            builder.registerPotionRecipe(VOID_TOUCHED, EXTENDER, LONG_VOID_TOUCHED);
            builder.registerPotionRecipe(VOID_TOUCHED, AMPLIFIER, STRONG_VOID_TOUCHED);

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Choking...");
            builder.registerPotionRecipe(
                    Potions.WATER_BREATHING,
                    Ingredient.of(Items.FERMENTED_SPIDER_EYE),
                    CHOKING
            );
            builder.registerPotionRecipe(
                    Potions.LONG_WATER_BREATHING,
                    Ingredient.of(Items.FERMENTED_SPIDER_EYE),
                    LONG_CHOKING
            );
            builder.registerPotionRecipe(
                    CHOKING,
                    EXTENDER,
                    LONG_CHOKING
            );

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Silence...");
            builder.registerPotionRecipe(
                    WEIRD,
                    Ingredient.of(
                            Items.WHITE_WOOL,
                            Items.ORANGE_WOOL,
                            Items.MAGENTA_WOOL,
                            Items.LIGHT_BLUE_WOOL,
                            Items.YELLOW_WOOL,
                            Items.LIME_WOOL,
                            Items.PINK_WOOL,
                            Items.GRAY_WOOL,
                            Items.LIGHT_GRAY_WOOL,
                            Items.CYAN_WOOL,
                            Items.PURPLE_WOOL,
                            Items.BLUE_WOOL,
                            Items.BROWN_WOOL,
                            Items.GREEN_WOOL,
                            Items.RED_WOOL,
                            Items.BLACK_WOOL
                    ),
                    SILENCE
            );
            builder.registerPotionRecipe(
                    SILENCE,
                    EXTENDER,
                    LONG_SILENCE
            );

            YetAnotherVanillaPlusMod.LOGGER.debug("Creating Potions of Haste...");
            builder.registerPotionRecipe(
                    WEIRD,
                    Ingredient.of(YavpmItems.BITTER_BERRIES),
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
                    Ingredient.of(Items.SWEET_BERRIES),
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
