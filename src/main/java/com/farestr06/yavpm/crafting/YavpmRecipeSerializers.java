package com.farestr06.yavpm.crafting;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmRecipeSerializers {
    public static final RecipeSerializer<ReactorRechargeRecipe> REACTOR_RECHARGE = Registry.register(Registries.RECIPE_SERIALIZER,
        makeId("crafting_special_reactor_recharge"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(ReactorRechargeRecipe::new));

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering recipe serializers for YAVPM!");
    }
}
