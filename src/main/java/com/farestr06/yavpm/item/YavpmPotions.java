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


    public static final RegistryEntry<Potion> VOIDED = register("voided", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOIDED,
                    1000
            )
    ));

    public static final RegistryEntry<Potion> LONG_VOIDED = register("long_voided", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOIDED,
                    1600
            )
    ));

    public static final RegistryEntry<Potion> STRONG_VOIDED = register("strong_voided", new Potion(
            new StatusEffectInstance(
                    YavpmStatusEffects.VOIDED,
                    400,
                    2
            )
    ));

    private static RegistryEntry<Potion> register(String id, Potion potion) {
        return Registry.registerReference(Registries.POTION, YetAnotherVanillaPlusMod.makeId(id), potion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering potions for YAVPM!");

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(Items.WITHER_SKELETON_SKULL), DECAY);
            builder.registerPotionRecipe(DECAY, Ingredient.ofItems(Items.REDSTONE), LONG_DECAY);
            builder.registerPotionRecipe(DECAY, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_DECAY);

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(Items.ENDER_EYE), VOIDED);
            builder.registerPotionRecipe(VOIDED, Ingredient.ofItems(Items.REDSTONE), LONG_VOIDED);
            builder.registerPotionRecipe(VOIDED, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_VOIDED);
        });
    }
}
