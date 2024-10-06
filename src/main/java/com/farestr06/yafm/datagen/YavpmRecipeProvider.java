package com.farestr06.yafm.datagen;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.farestr06.yafm.YetAnotherVanillaPlusMod.makeId;

public class YavpmRecipeProvider extends FabricRecipeProvider {
    public YavpmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, List.of(YavpmItems.PEANUT), RecipeCategory.FOOD, YavpmItems.COOKED_PEANUT, 0.35f, 200, "peanut");
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.PEANUT, YavpmItems.COOKED_PEANUT, 0.35f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.CHOCOLATE, 4)
                .input(Items.COCOA_BEANS)
                .input(Items.MILK_BUCKET)
                .input(Items.SUGAR)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.CHOCOLATE)))
        ;

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, YavpmItems.SOUL_POWDER, 4)
                .input(Items.BLAZE_POWDER)
                .input(Items.BLAZE_POWDER)
                .input(Items.BLAZE_POWDER)
                .input(YavpmItems.SOUL_POWDER)
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.SOUL_POWDER)))
        ;

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
                .input(YavpmItems.SOUL_POWDER)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.SOUL_GLOWING_OBSIDIAN)))
        ;
        // endregion

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.MOSS_BLOCK, 4)
                .input(Items.MOSS_BLOCK)
                .input(ConventionalItemTags.FERTILIZERS)
                .criterion(hasItem(Items.MOSS_BLOCK), conditionsFromItem(Items.MOSS_BLOCK))
                .offerTo(exporter, makeId(getRecipeName(Items.MOSS_BLOCK)))
        ;
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.MOLY, 3)
                .input(YavpmItems.MOLY)
                .input(ConventionalItemTags.FERTILIZERS)
                .criterion(hasItem(YavpmItems.MOLY), conditionsFromItem(YavpmItems.MOLY))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.MOLY)))
        ;

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, YavpmItems.DIAMOND_ACORN)
                .input('#', Items.DIAMOND)
                .input('%', YavpmItems.ACORN)
                .pattern("###")
                .pattern("#%#")
                .pattern("###")
                .criterion(hasItem(YavpmItems.ACORN), conditionsFromItem(YavpmItems.ACORN))
                .offerTo(exporter, makeId(getRecipeName(YavpmItems.DIAMOND_ACORN)))
        ;

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, YavpmBlocks.ELECTRO_GLASS)
                .input(Items.GLASS)
                .input(Items.GLOW_INK_SAC)
                .criterion(hasItem(Items.GLOW_INK_SAC), conditionsFromItem(Items.GLOW_INK_SAC))
                .offerTo(exporter, makeId(getRecipeName(YavpmBlocks.ELECTRO_GLASS)))
        ;

        makeStuddedArmorRecipes(exporter);
    }

    private static void makeStuddedArmorRecipes(RecipeExporter exporter) {
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
