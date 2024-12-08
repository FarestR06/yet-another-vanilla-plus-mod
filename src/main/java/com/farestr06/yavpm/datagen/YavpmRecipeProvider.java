package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.crafting.RuneUpgradeRecipe;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmRecipeProvider extends FabricRecipeProvider {
    public YavpmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        makeFoods(exporter);

        // region Warts
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, YavpmItems.WARPED_WART);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHER_WART, 4)
                .input(Items.NETHER_WART_BLOCK)
                .criterion(hasItem(Items.NETHER_WART_BLOCK), conditionsFromItem(Items.NETHER_WART_BLOCK))
                .offerTo(exporter, makeId(getRecipeName(Items.NETHER_WART)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.WARPED_WART, 4)
                .input(Items.WARPED_WART_BLOCK)
                .criterion(hasItem(Items.WARPED_WART_BLOCK), conditionsFromItem(Items.WARPED_WART_BLOCK))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.WARPED_WART)));
        // endregion

        // region Glowing Obsidians
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GLOWING_OBSIDIAN, 4)
                .input(Items.BLAZE_POWDER)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.GLOWING_OBSIDIAN)))
        ;
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOUL_GLOWING_OBSIDIAN, 4)
                .input(Ingredient.fromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.SOUL_GLOWING_OBSIDIAN)))
        ;
        // endregion
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.MOLY, 3)
                .input(YavpmItems.MOLY)
                .input(ConventionalItemTags.FERTILIZERS)
                .criterion(hasItem(YavpmItems.MOLY), conditionsFromItem(YavpmItems.MOLY))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.MOLY)))
        ;

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.ELECTRO_GLASS)
                .input(Items.GLASS)
                .input(Items.GLOW_INK_SAC)
                .criterion(hasItem(Items.GLOW_INK_SAC), conditionsFromItem(Items.GLOW_INK_SAC))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.ELECTRO_GLASS)))
        ;

        ComplexRecipeJsonBuilder.create(RuneUpgradeRecipe::new).offerTo(exporter, makeId("rune_upgrade"));

        makeStuddedArmorRecipes(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLACK_DYE, 3)
                .input(YavpmItems.GRAPHITE)
                .criterion(hasItem(YavpmItems.GRAPHITE), conditionsFromItem(YavpmItems.GRAPHITE))
                .offerTo(exporter, makeId(getRecipeName(Items.BLACK_DYE)));
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, YavpmItems.GRAPHITE, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHITE_BLOCK);
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHENE_BLOCK, YavpmBlocks.GRAPHITE_BLOCK);

        offerSmelting(exporter, List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "graphene_to_diamond");
        offerBlasting(exporter, List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "graphene_to_diamond");
        offerSmelting(exporter, List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "_diamond_from_raw");
        offerBlasting(exporter, List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "_diamond_from_raw");

        makePrickleWoodRecipes(exporter);
        makeApplewoodRecipes(exporter);

        makeStoneVariantRecipes(exporter);
    }

    private static void makeFoods(RecipeExporter exporter) {
        offerSmelting(exporter, List.of(YavpmItems.PEANUT), RecipeCategory.FOOD, YavpmItems.COOKED_PEANUT, 0.35f, 200, "peanut");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.PEANUT, YavpmItems.COOKED_PEANUT, 0.35f);
        offerSmelting(exporter, List.of(YavpmItems.FAKE_BEEF), RecipeCategory.FOOD, YavpmItems.COOKED_FAKE_BEEF, 0.35f, 200, "fake_beef");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.FAKE_BEEF, YavpmItems.COOKED_FAKE_BEEF, 0.35f);
        offerSmelting(exporter, List.of(Items.EGG), RecipeCategory.FOOD, YavpmItems.COOKED_EGG, 0.35f, 200, "cooked_egg");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, Items.EGG, YavpmItems.COOKED_EGG, 0.35f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.BEAN_TOAST, 4)
                .input(Items.BREAD)
                .input(YavpmItems.MAGIC_BEAN, 4)
                .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.BEAN_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHEESE, 4)
                .input(Items.MILK_BUCKET)
                .input(Items.BROWN_MUSHROOM)
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.CHEESE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.FAKE_BEEF, 2)
                .input('#', YavpmItems.MAGIC_BEAN)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.FAKE_BEEF)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.FAKE_MILK_BUCKET)
                .input(YavpmItems.MAGIC_BEAN, 4)
                .input(Items.BUCKET)
                .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.FAKE_MILK_BUCKET)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.TOFU, 4)
                .input(YavpmItems.FAKE_MILK_BUCKET)
                .input(Items.BROWN_MUSHROOM)
                .criterion(hasItem(YavpmItems.FAKE_MILK_BUCKET), conditionsFromItem(YavpmItems.FAKE_MILK_BUCKET))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.TOFU)));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHOCOLATE, 4)
                .input(Items.COCOA_BEANS)
                .input(Items.MILK_BUCKET)
                .input(Items.SUGAR)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.CHOCOLATE)))
        ;

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.SUSHI, 3)
                .input('T', Items.TROPICAL_FISH)
                .input('K', Items.DRIED_KELP)
                .input('R', YavpmItems.RICE)
                .pattern("KRK")
                .pattern("RTR")
                .pattern("KRK")
                .criterion(hasItem(YavpmItems.RICE), conditionsFromItem(YavpmItems.RICE))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.SUSHI)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.SEA_SOUP)
                .input(Items.TROPICAL_FISH)
                .input(YavpmItems.RICE)
                .input(Items.DRIED_KELP)
                .input(YavpmItems.MAGIC_BEAN)
                .input(Items.BOWL)
                .criterion("has_rice", conditionsFromItem(YavpmItems.RICE))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.SEA_SOUP)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.DIAMOND_ACORN)
                .input('#', Items.DIAMOND)
                .input('%', YavpmItems.ACORN)
                .pattern("###")
                .pattern("#%#")
                .pattern("###")
                .criterion(hasItem(YavpmItems.ACORN), conditionsFromItem(YavpmItems.ACORN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.DIAMOND_ACORN)))
        ;
    }

    private static void makeStoneVariantRecipes(RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_GRANITE), RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_granite", conditionsFromItem(YavpmBlocks.COBBLED_GRANITE))
                .offerTo(exporter, makeId("granite_from_cobbled"));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_DIORITE), RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_diorite", conditionsFromItem(YavpmBlocks.COBBLED_DIORITE))
                .offerTo(exporter, makeId("diorite_from_cobbled"));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_andesite", conditionsFromItem(YavpmBlocks.COBBLED_ANDESITE))
                .offerTo(exporter, makeId("andesite_from_cobbled"));
    }

    private void makePrickleWoodRecipes(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, YavpmBlocks.PRICKLE_PLANKS, YavpmTags.Items.PRICKLE_LOGS, 4);

        createStairsRecipe(YavpmBlocks.PRICKLE_STAIRS, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_STAIRS)));

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.PRICKLE_SLAB, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_SLAB)));

        createFenceRecipe(YavpmBlocks.PRICKLE_FENCE, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_FENCE)));

        createFenceGateRecipe(YavpmBlocks.PRICKLE_FENCE_GATE, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_FENCE_GATE)));

        createDoorRecipe(YavpmBlocks.PRICKLE_DOOR, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_DOOR)));

        createTrapdoorRecipe(YavpmBlocks.PRICKLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_TRAPDOOR)));

        createPressurePlateRecipe(RecipeCategory.REDSTONE, YavpmBlocks.PRICKLE_PRESSURE_PLATE, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_PRESSURE_PLATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.PRICKLE_BUTTON)
                .input(YavpmBlocks.PRICKLE_PLANKS)
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.PRICKLE_BUTTON)));

        createSignRecipe(YavpmItems.PRICKLE_SIGN, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.PRICKLE_SIGN)));

        offerHangingSignRecipe(exporter, YavpmItems.PRICKLE_HANGING_SIGN, YavpmBlocks.STRIPPED_PRICKLE_LOG);
    }

    private void makeApplewoodRecipes(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, YavpmBlocks.APPLE_PLANKS, YavpmTags.Items.APPLE_LOGS, 4);

        createStairsRecipe(YavpmBlocks.APPLE_STAIRS, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_STAIRS)));

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.APPLE_SLAB, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_SLAB)));

        createFenceRecipe(YavpmBlocks.APPLE_FENCE, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_FENCE)));

        createFenceGateRecipe(YavpmBlocks.APPLE_FENCE_GATE, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_FENCE_GATE)));

        createDoorRecipe(YavpmBlocks.APPLE_DOOR, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_DOOR)));

        createTrapdoorRecipe(YavpmBlocks.APPLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_TRAPDOOR)));

        createPressurePlateRecipe(RecipeCategory.REDSTONE, YavpmBlocks.APPLE_PRESSURE_PLATE, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_PRESSURE_PLATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.APPLE_BUTTON)
                .input(YavpmBlocks.APPLE_PLANKS)
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.APPLE_BUTTON)));

        createSignRecipe(YavpmItems.APPLE_SIGN, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.APPLE_SIGN)));

        offerHangingSignRecipe(exporter, YavpmItems.APPLE_HANGING_SIGN, YavpmBlocks.STRIPPED_APPLE_LOG);
    }

    private void makeStuddedArmorRecipes(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.STUDDED_HELMET)
                .input(Items.LEATHER_HELMET)
                .input(Items.CHAINMAIL_HELMET)
                .criterion(hasItem(Items.CHAINMAIL_HELMET), conditionsFromItem(Items.CHAINMAIL_HELMET))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.STUDDED_HELMET)))
        ;
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.STUDDED_CHESTPLATE)
                .input(Items.LEATHER_CHESTPLATE)
                .input(Items.CHAINMAIL_CHESTPLATE)
                .criterion(hasItem(Items.CHAINMAIL_CHESTPLATE), conditionsFromItem(Items.CHAINMAIL_CHESTPLATE))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.STUDDED_CHESTPLATE)))
        ;
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.STUDDED_LEGGINGS)
                .input(Items.LEATHER_LEGGINGS)
                .input(Items.CHAINMAIL_LEGGINGS)
                .criterion(hasItem(Items.CHAINMAIL_LEGGINGS), conditionsFromItem(Items.CHAINMAIL_LEGGINGS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.STUDDED_LEGGINGS)))
        ;
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.STUDDED_BOOTS)
                .input(Items.LEATHER_BOOTS)
                .input(Items.CHAINMAIL_BOOTS)
                .criterion(hasItem(Items.CHAINMAIL_BOOTS), conditionsFromItem(Items.CHAINMAIL_BOOTS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.STUDDED_BOOTS)))
        ;
    }
}
