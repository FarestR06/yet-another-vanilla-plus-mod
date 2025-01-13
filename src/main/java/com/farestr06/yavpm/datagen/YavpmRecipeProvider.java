package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.crafting.ReactorRechargeRecipe;
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
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmRecipeProvider extends FabricRecipeProvider {
    public YavpmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        foodRecipes(exporter);
        wartRecipes(exporter);

        kimberlite(exporter);
        granite(exporter);
        andesite(exporter);
        diorite(exporter);

        obsidianRecipes(exporter);
        diamondRecipes(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.POLARIZED_GLASS, 8)
                .input('T', Items.TINTED_GLASS)
                .input('G', Items.GLOW_INK_SAC)
                .pattern("TTT")
                .pattern("TGT")
                .pattern("TTT")
                .criterion(hasItem(Items.GLOW_INK_SAC), conditionsFromItem(Items.GLOW_INK_SAC))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.POLARIZED_GLASS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.KEYLOCK)
                .input('C', Items.COBBLESTONE)
                .input('T', Items.TRIPWIRE_HOOK)
                .input('R', Items.REDSTONE)
                .input('Q', Items.QUARTZ)
                .pattern("CCC")
                .pattern("TQR")
                .pattern("CCC")
                .criterion(hasItem(YavpmItems.BABY_KEY), conditionsFromItem(YavpmItems.BABY_KEY))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.KEYLOCK)));

        equipmentRecipes(exporter);


        applewoodRecipes(exporter);
        persimmonRecipes(exporter);
        prickleWoodRecipes(exporter);

        specialRecipes(exporter);

        offerCompactingRecipe(exporter, RecipeCategory.MISC, YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT);
    }

    private static void specialRecipes(RecipeExporter exporter) {
        ComplexRecipeJsonBuilder.create(RuneUpgradeRecipe::new).offerTo(exporter, makeId("rune_upgrade"));
        ComplexRecipeJsonBuilder.create(ReactorRechargeRecipe::new).offerTo(exporter, makeId("reactor_recharge"));
    }

    private static void diamondRecipes(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLACK_DYE, 3)
                .input(YavpmItems.GRAPHITE)
                .criterion(hasItem(YavpmItems.GRAPHITE), conditionsFromItem(YavpmItems.GRAPHITE))
                .offerTo(exporter, makeId(getRecipeName(Items.BLACK_DYE)));
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, YavpmItems.GRAPHITE, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHITE_BLOCK);
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHENE_BLOCK, YavpmBlocks.GRAPHITE_BLOCK);

        offerSmelting(exporter, List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "graphene_to_diamond");
        offerBlasting(exporter, List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "graphene_to_diamond");
        offerSmelting(exporter, List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "diamond_from_raw");
        offerBlasting(exporter, List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "diamond_from_raw");
    }

    private static void obsidianRecipes(RecipeExporter exporter) {
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
                .input(Items.BLAZE_POWDER)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.SOUL_GLOWING_OBSIDIAN)))
        ;
    }

    private static void wartRecipes(RecipeExporter exporter) {
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, YavpmItems.WARPED_WART);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHER_WART, 4)
                .input(Items.NETHER_WART_BLOCK)
                .criterion(hasItem(Items.NETHER_WART_BLOCK), conditionsFromItem(Items.NETHER_WART_BLOCK))
                .offerTo(exporter, makeId(getRecipeName(Items.NETHER_WART)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.WARPED_WART, 4)
                .input(Items.WARPED_WART_BLOCK)
                .criterion(hasItem(Items.WARPED_WART_BLOCK), conditionsFromItem(Items.WARPED_WART_BLOCK))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.WARPED_WART)));
    }

    private static void foodRecipes(RecipeExporter exporter) {
        foodCooking(exporter);
        magicBeanFoods(exporter);
        sweetFoods(exporter);
        seafoods(exporter);
        rareFoods(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHICKEN_SOUP)
                .input(Items.COOKED_CHICKEN)
                .input(Items.CARROT)
                .input(Items.BROWN_MUSHROOM)
                .input(YavpmItems.RICE)
                .input(Items.BOWL)
                .criterion(hasItem(Items.COOKED_CHICKEN), conditionsFromItem(Items.COOKED_CHICKEN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.CHICKEN_SOUP)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.BREADING, 4)
                .input(Items.WHEAT)
                .input(Items.BLAZE_POWDER)
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.BREADING)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.FRIED_BANANA)
                .input(YavpmItems.BANANA)
                .input(Items.SUGAR)
                .input(YavpmItems.BREADING)
                .criterion(hasItem(YavpmItems.BREADING), conditionsFromItem(YavpmItems.BREADING))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.FRIED_BANANA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.FRIED_COD)
                .input(Items.COOKED_COD)
                .input(YavpmItems.BREADING)
                .criterion(hasItem(YavpmItems.BREADING), conditionsFromItem(YavpmItems.BREADING))
                .offerTo(exporter, makeId(getRecipeName(Items.COOKED_COD)));
    }

    private static void seafoods(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.SUSHI, 6)
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
                .criterion(hasItem(Items.TROPICAL_FISH), conditionsFromItem(Items.TROPICAL_FISH))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.SEA_SOUP)));
    }

    private static void rareFoods(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.FANCY_MUSHROOM_STEW)
                .input(Items.RED_MUSHROOM)
                .input(Items.BROWN_MUSHROOM)
                .input(Items.CRIMSON_FUNGUS)
                .input(Items.WARPED_FUNGUS)
                .input(YavpmItems.TRUFFLE)
                .input(Items.BOWL)
                .criterion(hasItem(YavpmItems.TRUFFLE), conditionsFromItem(YavpmItems.TRUFFLE))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.FANCY_MUSHROOM_STEW)));

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

    private static void sweetFoods(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHOCOLATE, 4)
                .input(ConventionalItemTags.COCOA_BEAN_CROPS)
                .input(ConventionalItemTags.MILK_BUCKETS)
                .input(Items.SUGAR)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.CHOCOLATE)))
        ;

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.JELLY, 8)
                .input(Items.BONE_MEAL)
                .input(Items.BONE_MEAL)
                .input(Items.BONE_MEAL)
                .input(Items.SUGAR)
                .input(Items.WATER_BUCKET)
                .criterion(hasItem(Items.BONE_MEAL), conditionsFromItem(Items.BONE_MEAL))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.JELLY)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.SWEET_BERRY_JELLY)
                .input(YavpmItems.JELLY)
                .input(Items.SWEET_BERRIES)
                .criterion(hasItem(YavpmItems.JELLY), conditionsFromItem(YavpmItems.JELLY))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.SWEET_BERRY_JELLY)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.RICE_BAR, 3)
                .input(YavpmItems.RICE)
                .input(YavpmItems.RICE)
                .input(YavpmItems.RICE)
                .input(YavpmItems.JELLY)
                .criterion(hasItem(YavpmItems.JELLY), conditionsFromItem(YavpmItems.JELLY))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.RICE_BAR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.RICE_PASTRY, 2)
                .input(YavpmItems.RICE)
                .input(YavpmItems.RICE)
                .input(YavpmItems.RICE)
                .input(Items.SUGAR)
                .criterion(hasItem(YavpmItems.RICE), conditionsFromItem(YavpmItems.RICE))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.RICE_PASTRY)));
    }

    private static void magicBeanFoods(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.BEAN_TOAST, 4)
                .input(Items.BREAD)
                .input(YavpmItems.MAGIC_BEAN, 4)
                .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.BEAN_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHEESE, 4)
                .input(Items.MILK_BUCKET)
                .input(YavpmItems.WARPED_WART)
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
                .input(YavpmItems.WARPED_WART)
                .criterion(hasItem(YavpmItems.FAKE_MILK_BUCKET), conditionsFromItem(YavpmItems.FAKE_MILK_BUCKET))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.TOFU)));
    }

    private static void foodCooking(RecipeExporter exporter) {
        offerSmelting(exporter, List.of(YavpmItems.PEANUT), RecipeCategory.FOOD, YavpmItems.COOKED_PEANUT, 0.35f, 200, "peanut");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.PEANUT, YavpmItems.COOKED_PEANUT, 0.35f);
        offerSmelting(exporter, List.of(YavpmItems.FAKE_BEEF), RecipeCategory.FOOD, YavpmItems.COOKED_FAKE_BEEF, 0.35f, 200, "fake_beef");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.FAKE_BEEF, YavpmItems.COOKED_FAKE_BEEF, 0.35f);
        offerSmelting(exporter, List.of(Items.EGG), RecipeCategory.FOOD, YavpmItems.COOKED_EGG, 0.35f, 200, "cooked_egg");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, Items.EGG, YavpmItems.COOKED_EGG, 0.35f);
    }
    private static void granite(RecipeExporter exporter) {
        generateFamily(exporter, YavpmBlocks.COBBLED_GRANITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
        generateFamily(exporter, YavpmBlocks.POLISHED_GRANITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_GRANITE), RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_granite", conditionsFromItem(YavpmBlocks.COBBLED_GRANITE))
                .offerTo(exporter, makeId("granite_from_cobbled"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, 4)
                .input('#', Blocks.POLISHED_GRANITE)
                .pattern("##")
                .pattern("##")
                .criterion("has_polished_granite", conditionsFromItem(Blocks.POLISHED_GRANITE))
                .offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.COBBLED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.COBBLED_GRANITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.GRANITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.POLISHED_GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.POLISHED_GRANITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.POLISHED_GRANITE_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.POLISHED_GRANITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.POLISHED_GRANITE_BRICKS);
    }
    private static void andesite(RecipeExporter exporter) {
        generateFamily(exporter, YavpmBlocks.COBBLED_ANDESITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
        generateFamily(exporter, YavpmBlocks.POLISHED_ANDESITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_andesite", conditionsFromItem(YavpmBlocks.COBBLED_ANDESITE))
                .offerTo(exporter, makeId("andesite_from_cobbled"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 4)
                .input('#', Blocks.POLISHED_ANDESITE)
                .pattern("##")
                .pattern("##")
                .criterion("has_polished_andesite", conditionsFromItem(Blocks.POLISHED_ANDESITE))
                .offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.COBBLED_ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.POLISHED_ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.POLISHED_ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
    }
    private static void diorite(RecipeExporter exporter) {
        generateFamily(exporter, YavpmBlocks.COBBLED_DIORITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
        generateFamily(exporter, YavpmBlocks.POLISHED_DIORITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_DIORITE), RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE.asItem(), 0.1F, 200)
                .criterion("has_cobbled_diorite", conditionsFromItem(YavpmBlocks.COBBLED_DIORITE))
                .offerTo(exporter, makeId("diorite_from_cobbled"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, 4)
                .input('#', Blocks.POLISHED_DIORITE)
                .pattern("##")
                .pattern("##")
                .criterion("has_polished_diorite", conditionsFromItem(Blocks.POLISHED_DIORITE))
                .offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.COBBLED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.COBBLED_DIORITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.DIORITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.POLISHED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.POLISHED_DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.POLISHED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.POLISHED_DIORITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.POLISHED_DIORITE_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.POLISHED_DIORITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.POLISHED_DIORITE_BRICKS);
    }
    private static void kimberlite(RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.KIMBERLITE), RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE.asItem(), 0.1F, 200)
                .criterion("has_kimberlite", conditionsFromItem(YavpmBlocks.KIMBERLITE))
                .offerTo(exporter, makeId("polished_kimberlite_from_cobbled"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 4)
                .input('#', YavpmBlocks.POLISHED_KIMBERLITE)
                .pattern("##")
                .pattern("##")
                .criterion("has_polished_kimberlite", conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE))
                .offerTo(exporter);

        generateFamily(exporter, YavpmBlocks.KIMBERLITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
        generateFamily(exporter, YavpmBlocks.POLISHED_KIMBERLITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
        generateFamily(exporter, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.KIMBERLITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.KIMBERLITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.POLISHED_KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.POLISHED_KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
    }

    private void applewoodRecipes(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, YavpmBlocks.APPLE_PLANKS, YavpmTags.Items.APPLE_LOGS, 4);
        offerBarkBlockRecipe(exporter, YavpmBlocks.APPLE_WOOD, YavpmBlocks.APPLE_LOG);
        offerBarkBlockRecipe(exporter, YavpmBlocks.STRIPPED_APPLE_WOOD, YavpmBlocks.STRIPPED_APPLE_LOG);
        generateFamily(exporter, YavpmBlocks.APPLE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
    }

    private void persimmonRecipes(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, YavpmBlocks.PERSIMMON_PLANKS, YavpmTags.Items.PERSIMMON_LOGS, 4);
        offerBarkBlockRecipe(exporter, YavpmBlocks.PERSIMMON_WOOD, YavpmBlocks.PERSIMMON_LOG);
        offerBarkBlockRecipe(exporter, YavpmBlocks.STRIPPED_PERSIMMON_WOOD, YavpmBlocks.STRIPPED_PERSIMMON_LOG);
        generateFamily(exporter, YavpmBlocks.PERSIMMON_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
    }

    private void prickleWoodRecipes(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, YavpmBlocks.PRICKLE_PLANKS, YavpmTags.Items.PRICKLE_LOGS, 4);
        offerBarkBlockRecipe(exporter, YavpmBlocks.PRICKLE_WOOD, YavpmBlocks.PRICKLE_LOG);
        offerBarkBlockRecipe(exporter, YavpmBlocks.STRIPPED_PRICKLE_WOOD, YavpmBlocks.STRIPPED_PRICKLE_LOG);
        generateFamily(exporter, YavpmBlocks.PRICKLE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
    }

    private void equipmentRecipes(RecipeExporter exporter) {
        offerCompactingRecipe(exporter, RecipeCategory.MISC, YavpmItems.CHAINMAIL, Items.CHAIN);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.CHAINMAIL_HELMET)
                .input('#', YavpmItems.CHAINMAIL)
                .pattern("###")
                .pattern("# #")
                .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                .offerTo(exporter, makeId(getRecipeName(Items.CHAINMAIL_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.CHAINMAIL_CHESTPLATE)
                .input('#', YavpmItems.CHAINMAIL)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                .offerTo(exporter, makeId(getRecipeName(Items.CHAINMAIL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.CHAINMAIL_LEGGINGS)
                .input('#', YavpmItems.CHAINMAIL)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                .offerTo(exporter, makeId(getRecipeName(Items.CHAINMAIL_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.CHAINMAIL_BOOTS)
                .input('#', YavpmItems.CHAINMAIL)
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                .offerTo(exporter, makeId(getRecipeName(Items.CHAINMAIL_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, YavpmItems.REACTOR)
                .input('B', Items.BLAZE_ROD)
                .input('N', Items.NETHERITE_INGOT)
                .pattern(" B ")
                .pattern("BNB")
                .pattern(" B ")
                .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.REACTOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, YavpmItems.BABY_KEY)
                .input('G', Items.GOLD_INGOT)
                .input('N', Items.GOLD_NUGGET)
                .input('P', Items.CARVED_PUMPKIN)
                .pattern("GN")
                .pattern("GN")
                .pattern("P ")
                .criterion(hasItem(Items.CARVED_PUMPKIN), conditionsFromItem(Items.CARVED_PUMPKIN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.BABY_KEY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, YavpmItems.GAUNTLET)
                .input('G', YavpmItems.GAUNTLET_FRAGMENT)
                .input('N', Items.NETHERITE_SCRAP)
                .pattern("GGG")
                .pattern("GNG")
                .pattern("N N")
                .criterion(hasItem(YavpmItems.GAUNTLET_FRAGMENT), conditionsFromItem(YavpmItems.GAUNTLET_FRAGMENT))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.GAUNTLET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, YavpmItems.VOID_WATER_BUCKET)
                .input('D', Items.DRAGON_BREATH)
                .input('W', Items.WATER_BUCKET)
                .pattern("D")
                .pattern("W")
                .criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.VOID_WATER_BUCKET)));

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
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.ELYTRA)
                .input('C', YavpmItems.PHANTOM_CHORD)
                .input('M', Items.PHANTOM_MEMBRANE)
                .pattern("CCC")
                .pattern("M M")
                .pattern("M M")
                .criterion(hasItem(YavpmItems.PHANTOM_CHORD), conditionsFromItem(YavpmItems.PHANTOM_CHORD))
                .offerTo(exporter, makeId(getRecipeName(Items.ELYTRA)));
    }
}
