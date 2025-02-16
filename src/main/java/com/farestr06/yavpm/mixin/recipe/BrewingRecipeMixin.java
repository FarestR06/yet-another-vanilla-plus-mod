package com.farestr06.yavpm.mixin.recipe;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.item.YavpmPotions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeMixin {
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry$Builder;registerRecipes(Lnet/minecraft/item/Item;Lnet/minecraft/registry/entry/RegistryEntry;)V", ordinal = 0))
    private static void windCharged(BrewingRecipeRegistry.Builder instance, Item ingredient, RegistryEntry<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.BREEZE_ROD, Potions.WIND_CHARGED);
        } else {
            instance.registerRecipes(ingredient, potion);
        }
    }
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry$Builder;registerRecipes(Lnet/minecraft/item/Item;Lnet/minecraft/registry/entry/RegistryEntry;)V", ordinal = 1))
    private static void oozing(BrewingRecipeRegistry.Builder instance, Item ingredient, RegistryEntry<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.SLIME_BLOCK, Potions.OOZING);
        } else {
            instance.registerRecipes(ingredient, potion);
        }
    }

    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry$Builder;registerRecipes(Lnet/minecraft/item/Item;Lnet/minecraft/registry/entry/RegistryEntry;)V", ordinal = 2))
    private static void infested(BrewingRecipeRegistry.Builder instance, Item ingredient, RegistryEntry<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.STONE, Potions.INFESTED);
        } else {
        instance.registerRecipes(ingredient, potion);
        }
    }
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry$Builder;registerRecipes(Lnet/minecraft/item/Item;Lnet/minecraft/registry/entry/RegistryEntry;)V", ordinal = 3))
    private static void weaving(BrewingRecipeRegistry.Builder instance, Item ingredient, RegistryEntry<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.COBWEB, Potions.WEAVING);
        } else {
            instance.registerRecipes(ingredient, potion);
        }
    }

    @Unique
    private static void registerRecipes(BrewingRecipeRegistry.Builder builder, Item ingredient, RegistryEntry<Potion> potion) {
        if (potion.value().isEnabled(builder.getEnabledFeatures())) {
            builder.registerPotionRecipe(Potions.WATER, ingredient, Potions.MUNDANE);
            builder.registerPotionRecipe(YavpmPotions.WEIRD, ingredient, potion);
        }
    }
}
