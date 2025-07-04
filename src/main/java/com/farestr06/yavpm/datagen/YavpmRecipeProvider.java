package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.datagen.condition.RareEquipmentRecipesEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.experiment.RecyclerExperimentResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.DoubleSlabsEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.DropperToRecyclerEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.MoreStairsEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.MoreTrapdoorsEnabledResourceCondition;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class YavpmRecipeProvider extends FabricRecipeProvider {
    public YavpmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        final RecipeExporter recyclerExperimentRecipeExporter = withConditions(exporter, new RecyclerExperimentResourceCondition());

        final RecipeExporter rareEquipmentRecipeExporter =
                withConditions(exporter, new RareEquipmentRecipesEnabledResourceCondition());

        final ResourceCondition stairCondition = new MoreStairsEnabledResourceCondition();
        final RecipeExporter ifMoreStairsEnabled = withConditions(exporter, stairCondition);
        final RecipeExporter ifMoreStairsNotEnabled = withConditions(exporter, ResourceConditions.not(stairCondition));

        final ResourceCondition trapdoorCondition = new MoreTrapdoorsEnabledResourceCondition();
        final RecipeExporter ifMoreTrapdoorsEnabled = withConditions(exporter, trapdoorCondition);
        final RecipeExporter ifMoreTrapdoorsNotEnabled = withConditions(exporter, ResourceConditions.not(trapdoorCondition));

        return new RecipeGenerator(registryLookup, exporter) {
            final RegistryWrapper.Impl<Item> itemLookup = registryLookup.getOrThrow(RegistryKeys.ITEM);
            @Override
            public void generate() {
                foodRecipes(exporter);

                kimberlite(exporter);
                granite(exporter);
                andesite(exporter);
                diorite(exporter);
                conglomerate(exporter);

                obsidianRecipes(exporter);
                diamondRecipes(exporter);

                vtCompat(exporter);

                densititeRecipes();

                createShapeless(RecipeCategory.MISC, YavpmItems.CRIMSON_SPORE)
                        .input(Items.NETHER_WART)
                        .input(Ingredient.fromTag(itemLookup.getOrThrow(ConventionalItemTags.MUSHROOMS)))
                        .criterion(hasItem(Items.NETHER_WART), conditionsFromItem(Items.NETHER_WART))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, YavpmItems.WARPED_SPORE)
                        .input(YavpmItems.WARPED_WART)
                        .input(Ingredient.fromTag(itemLookup.getOrThrow(ConventionalItemTags.MUSHROOMS)))
                        .criterion(hasItem(YavpmItems.WARPED_WART), conditionsFromItem(YavpmItems.WARPED_WART))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, YavpmBlocks.PINATA)
                        .input('P', Items.PAPER)
                        .input('S', Items.SLIME_BALL)
                        .input('D', Items.DECORATED_POT)
                        .pattern(" PS")
                        .pattern("PDP")
                        .pattern("SP ")
                        .criterion(hasItem(Items.DECORATED_POT), conditionsFromItem(Items.DECORATED_POT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SHOJI, 2)
                        .input('P', Items.PAPER)
                        .input('B', Items.BAMBOO)
                        .pattern("PB")
                        .pattern("BP")
                        .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, YavpmBlocks.POLARIZED_GLASS, 8)
                        .input('T', Blocks.TINTED_GLASS)
                        .input('G', Items.GLOW_INK_SAC)
                        .pattern("TTT")
                        .pattern("TGT")
                        .pattern("TTT")
                        .criterion(hasItem(Items.GLOW_INK_SAC), conditionsFromItem(Items.GLOW_INK_SAC))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, YavpmBlocks.BURNER)
                        .input('R', Items.REDSTONE)
                        .input('M', Blocks.MAGMA_BLOCK)
                        .pattern(" R ")
                        .pattern("RMR")
                        .pattern(" R ")
                        .criterion(hasItem(Blocks.MAGMA_BLOCK), conditionsFromItem(Blocks.MAGMA_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.REDSTONE, YavpmBlocks.RECYCLER)
                        .input('C', Items.COBBLESTONE)
                        .input('R', Items.REDSTONE)
                        .input('I', Items.IRON_INGOT)
                        .pattern("CCC")
                        .pattern("CIC")
                        .pattern("CRC")
                        .group("recycler")
                        .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                        .offerTo(recyclerExperimentRecipeExporter);

                equipmentRecipes(exporter);

                applewoodRecipes();
                persimmonRecipes();
                prickleWoodRecipes();

                offerCompactingRecipe(RecipeCategory.MISC, YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT);

                this.offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE, Items.SOUL_SAND);
                this.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_SLAB, Ingredient.ofItems(YavpmBlocks.SOULSTONE, YavpmBlocks.CHISELED_SOULSTONE))
                        .criterion("has_sandstone", this.conditionsFromItem(YavpmBlocks.SOULSTONE))
                        .criterion("has_chiseled_sandstone", this.conditionsFromItem(YavpmBlocks.CHISELED_SOULSTONE))
                        .offerTo(this.exporter);
                this.createStairsRecipe(YavpmBlocks.SOULSTONE_STAIRS, Ingredient.ofItems(YavpmBlocks.SOULSTONE, YavpmBlocks.CHISELED_SOULSTONE, YavpmBlocks.CUT_SOULSTONE))
                        .criterion("has_sandstone", this.conditionsFromItem(YavpmBlocks.SOULSTONE))
                        .criterion("has_chiseled_sandstone", this.conditionsFromItem(YavpmBlocks.CHISELED_SOULSTONE))
                        .criterion("has_cut_sandstone", this.conditionsFromItem(YavpmBlocks.CUT_SOULSTONE))
                        .offerTo(this.exporter);

                this.offerWallRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.SOULSTONE_WALL, YavpmBlocks.SOULSTONE);

                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(YavpmBlocks.SOULSTONE), RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SMOOTH_SOULSTONE.asItem(), 0.1F, 200)
                        .criterion("has_sandstone", this.conditionsFromItem(YavpmBlocks.SOULSTONE))
                        .offerTo(this.exporter);

                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE, YavpmBlocks.SOULSTONE);
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_SLAB, YavpmBlocks.SOULSTONE, 2);
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE_SLAB, YavpmBlocks.SOULSTONE, 2);
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE_SLAB, YavpmBlocks.CUT_SOULSTONE, 2);
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_STAIRS, YavpmBlocks.SOULSTONE);
                this.offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.SOULSTONE_WALL, YavpmBlocks.SOULSTONE);
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CHISELED_SOULSTONE, YavpmBlocks.SOULSTONE);
            }

            private void conglomerate(RecipeExporter exporter) {
                this.offerConcretePowderDyeingRecipe(YavpmBlocks.CONGLOMERATE, Items.DRAGON_BREATH);

                createStairsRecipe(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS, Ingredient.ofItems(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB,
                        Ingredient.ofItems(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .offerTo(exporter);
                this.offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, 4)
                        .input('C', YavpmBlocks.HARDENED_CONGLOMERATE)
                        .pattern("CC")
                        .pattern("CC")
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .offerTo(exporter);
                createStairsRecipe(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB,
                        Ingredient.ofItems(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .offerTo(exporter);
                this.offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);


                this.offerSmelting(
                        List.of(YavpmBlocks.HARDENED_CONGLOMERATE), RecipeCategory.BUILDING_BLOCKS,
                        YavpmBlocks.DULL_CONGLOMERATE, 0.1f, 200, "polished_conglomerate"
                );
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE, Ingredient.ofItems(YavpmBlocks.DULL_CONGLOMERATE_SLAB))
                        .criterion(hasItem(YavpmBlocks.HARDENED_CONGLOMERATE), conditionsFromItem(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .offerTo(exporter);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE, YavpmBlocks.HARDENED_CONGLOMERATE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
            }

            private void densititeRecipes() {
                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(Items.HEAVY_CORE), RecipeCategory.MISC, YavpmBlocks.DENSITITE_BLOCK, 4.0F, 200)
                        .criterion(hasItem(Items.HEAVY_CORE), this.conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(this.exporter, "densitite_block_from_smelting");
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItem(Items.HEAVY_CORE), RecipeCategory.MISC, YavpmBlocks.DENSITITE_BLOCK, 4.0F, 100)
                        .criterion(hasItem(Items.HEAVY_CORE), this.conditionsFromItem(Items.HEAVY_CORE))
                        .offerTo(this.exporter, "densitite_block_from_blasting");

                createShapeless(RecipeCategory.MISC, YavpmItems.DENSITITE_INGOT, 3)
                        .input(YavpmBlocks.DENSITITE_BLOCK)
                        .criterion(hasItem(YavpmBlocks.DENSITITE_BLOCK), conditionsFromItem(YavpmBlocks.DENSITITE_BLOCK))
                        .offerTo(exporter);

                offerDensititeUpgradeRecipe(Items.DIAMOND_SWORD, RecipeCategory.TOOLS, YavpmItems.DENSITITE_SWORD);
                offerDensititeUpgradeRecipe(Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, YavpmItems.DENSITITE_SHOVEL);
                offerDensititeUpgradeRecipe(Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, YavpmItems.DENSITITE_PICKAXE);
                offerDensititeUpgradeRecipe(Items.DIAMOND_AXE, RecipeCategory.TOOLS, YavpmItems.DENSITITE_AXE);
                offerDensititeUpgradeRecipe(Items.DIAMOND_HOE, RecipeCategory.TOOLS, YavpmItems.DENSITITE_HOE);

                offerDensititeUpgradeRecipe(Items.DIAMOND_HELMET, RecipeCategory.TOOLS, YavpmItems.DENSITITE_HELMET);
                offerDensititeUpgradeRecipe(Items.DIAMOND_CHESTPLATE, RecipeCategory.TOOLS, YavpmItems.DENSITITE_CHESTPLATE);
                offerDensititeUpgradeRecipe(Items.DIAMOND_LEGGINGS, RecipeCategory.TOOLS, YavpmItems.DENSITITE_LEGGINGS);
                offerDensititeUpgradeRecipe(Items.DIAMOND_BOOTS, RecipeCategory.TOOLS, YavpmItems.DENSITITE_BOOTS);
            }

            public void offerDensititeUpgradeRecipe(Item input, RecipeCategory category, Item result) {
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(YavpmItems.DENSITITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(input),
                                this.ingredientFromTag(YavpmTags.Items.DENSITITE_TOOL_MATERIALS),
                                category,
                                result
                        )
                        .criterion("has_densitite_ingot", this.conditionsFromTag(YavpmTags.Items.DENSITITE_TOOL_MATERIALS))
                        .offerTo(this.exporter, getItemPath(result) + "_smithing");
            }

            private void vtCompat(RecipeExporter exporter) {
                final RecipeExporter dropperToRecycler = withConditions(exporter, new DropperToRecyclerEnabledResourceCondition());
                createShapeless(RecipeCategory.REDSTONE, YavpmBlocks.RECYCLER)
                        .input(Items.DROPPER).input(Items.IRON_INGOT)
                        .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .group("recycler")
                        .offerTo(dropperToRecycler, "recycler_from_dropper");

                doubleSlabs(exporter);
            }

            private void doubleSlabs(RecipeExporter exporter) {
                final RecipeExporter doubleSlabs = withConditions(exporter, new DoubleSlabsEnabledResourceCondition());

                createDoubleSlabRecipe(YavpmBlocks.APPLE_PLANKS, YavpmBlocks.APPLE_SLAB).offerTo(doubleSlabs, "double_apple_slabs");
                createDoubleSlabRecipe(YavpmBlocks.PRICKLE_PLANKS, YavpmBlocks.PRICKLE_SLAB).offerTo(doubleSlabs, "double_prickle_slabs");
                createDoubleSlabRecipe(YavpmBlocks.PERSIMMON_PLANKS, YavpmBlocks.PERSIMMON_SLAB).offerTo(doubleSlabs, "double_persimmon_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_ANDESITE, YavpmBlocks.COBBLED_ANDESITE_SLAB).offerTo(doubleSlabs, "double_cobbled_andesite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_DIORITE, YavpmBlocks.COBBLED_DIORITE_SLAB).offerTo(doubleSlabs, "double_cobbled_diorite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_GRANITE, YavpmBlocks.COBBLED_GRANITE_SLAB).offerTo(doubleSlabs, "double_cobbled_granite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_ANDESITE_BRICKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB).offerTo(doubleSlabs, "double_polished_andesite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_DIORITE_BRICKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB).offerTo(doubleSlabs, "double_polished_diorite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_GRANITE_BRICKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB).offerTo(doubleSlabs, "double_polished_granite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.KIMBERLITE, YavpmBlocks.KIMBERLITE_SLAB).offerTo(doubleSlabs, "double_kimberlite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_KIMBERLITE, YavpmBlocks.POLISHED_KIMBERLITE_SLAB).offerTo(doubleSlabs, "double_polished_kimberlite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB).offerTo(doubleSlabs,"double_polished_kimberlite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.SOULSTONE, YavpmBlocks.SOULSTONE_SLAB).offerTo(doubleSlabs, "double_soulstone_slabs");
                createDoubleSlabRecipe(YavpmBlocks.CUT_SOULSTONE, YavpmBlocks.CUT_SOULSTONE_SLAB).offerTo(doubleSlabs, "double_cut_soulstone_slabs");
                createDoubleSlabRecipe(YavpmBlocks.SMOOTH_SOULSTONE, YavpmBlocks.SMOOTH_SOULSTONE_SLAB).offerTo(doubleSlabs, "double_smooth_soulstone_slabs");

                createDoubleSlabRecipe(YavpmBlocks.HARDENED_CONGLOMERATE, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB).offerTo(exporter, "double_hardened_conglomerate_slabs");
                createDoubleSlabRecipe(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB).offerTo(exporter, "double_hardened_conglomerate_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.DULL_CONGLOMERATE, YavpmBlocks.DULL_CONGLOMERATE_SLAB).offerTo(exporter, "double_polished_conglomerate_slabs");
            }

            private ShapelessRecipeJsonBuilder createDoubleSlabRecipe(ItemConvertible block, ItemConvertible slab){
                return createShapeless(RecipeCategory.BUILDING_BLOCKS, slab, 2)
                        .input(block)
                        .criterion(hasItem(block), conditionsFromItem(block));
            }

            private void diamondRecipes(RecipeExporter exporter) {
                createShaped(RecipeCategory.MISC, YavpmItems.CARBON_EGG)
                        .input('#', YavpmItems.GRAPHITE)
                        .input('%', ItemTags.EGGS)
                        .pattern(" # ")
                        .pattern("#%#")
                        .pattern(" # ")
                        .criterion(hasItem(YavpmItems.GRAPHITE), conditionsFromItem(YavpmItems.GRAPHITE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.BLACK_DYE, 3)
                        .input(YavpmItems.GRAPHITE)
                        .criterion(hasItem(YavpmItems.GRAPHITE), conditionsFromItem(YavpmItems.GRAPHITE))
                        .offerTo(exporter);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, YavpmItems.GRAPHITE, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHITE_BLOCK);
                offerCompactingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHENE_BLOCK, YavpmBlocks.GRAPHITE_BLOCK);

                offerSmelting(List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "graphene_to_diamond");
                offerBlasting(List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "graphene_to_diamond");
                offerSmelting(List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "diamond_from_raw");
                offerBlasting(List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "diamond_from_raw");
            }

            private void obsidianRecipes(RecipeExporter exporter) {
                createShapeless(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GLOWING_OBSIDIAN, 4)
                        .input(Items.BLAZE_POWDER)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOUL_GLOWING_OBSIDIAN, 4)
                        .input(Ingredient.fromTag(itemLookup.getOrThrow(ItemTags.SOUL_FIRE_BASE_BLOCKS)))
                        .input(Items.BLAZE_POWDER)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .input(Items.OBSIDIAN)
                        .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .offerTo(exporter);
            }

            private void foodRecipes(RecipeExporter exporter) {
                foodCooking();
                magicBeanFoods(exporter);
                sweetFoods(exporter);
                seafoods(exporter);
                rareFoods(exporter);

                createShapeless(RecipeCategory.MISC, YavpmItems.PRETZEL, 4)
                        .input(Items.BREAD, 4)
                        .input(YavpmItems.BAKING_SODA)
                        .criterion(hasItem(YavpmItems.BAKING_SODA), conditionsFromItem(YavpmItems.BAKING_SODA))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.EXPERIENCE_BOTTLE)
                        .input(Items.SCULK, 4)
                        .input(Items.GLASS_BOTTLE)
                        .criterion(hasItem(Items.SCULK), conditionsFromItem(Items.SCULK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.CHICKEN_SOUP)
                        .input(Items.COOKED_CHICKEN)
                        .input(Items.CARROT)
                        .input(Items.BROWN_MUSHROOM)
                        .input(YavpmItems.RICE)
                        .input(Items.BOWL)
                        .criterion(hasItem(Items.COOKED_CHICKEN), conditionsFromItem(Items.COOKED_CHICKEN))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, YavpmItems.BREADING, 4)
                        .input(Items.WHEAT)
                        .input(Items.BLAZE_POWDER)
                        .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.FRIED_BANANA)
                        .input(YavpmItems.BANANA)
                        .input(Items.SUGAR)
                        .input(YavpmItems.BREADING)
                        .criterion(hasItem(YavpmItems.BREADING), conditionsFromItem(YavpmItems.BREADING))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.FRIED_COD)
                        .input(Items.COOKED_COD)
                        .input(YavpmItems.BREADING)
                        .criterion(hasItem(YavpmItems.BREADING), conditionsFromItem(YavpmItems.BREADING))
                        .offerTo(exporter);
            }

            private void seafoods(RecipeExporter exporter) {
                createShaped(RecipeCategory.FOOD, YavpmItems.SUSHI, 6)
                        .input('F', Ingredient.ofItems(Items.TROPICAL_FISH, Items.SALMON))
                        .input('K', Items.DRIED_KELP)
                        .input('R', YavpmItems.RICE)
                        .pattern("KRK")
                        .pattern("RFR")
                        .pattern("KRK")
                        .criterion(hasItem(YavpmItems.RICE), conditionsFromItem(YavpmItems.RICE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.SEA_SOUP)
                        .input(Items.TROPICAL_FISH)
                        .input(YavpmItems.RICE)
                        .input(Items.DRIED_KELP)
                        .input(YavpmItems.MAGIC_BEAN)
                        .input(Items.BOWL)
                        .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                        .offerTo(exporter);
            }

            private void rareFoods(RecipeExporter exporter) {
                createShapeless(RecipeCategory.FOOD, YavpmItems.FANCY_MUSHROOM_STEW)
                        .input(Items.RED_MUSHROOM_BLOCK)
                        .input(Items.BROWN_MUSHROOM_BLOCK)
                        .input(YavpmItems.TRUFFLE)
                        .input(Items.BOWL)
                        .criterion(hasItem(YavpmItems.TRUFFLE), conditionsFromItem(YavpmItems.TRUFFLE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, YavpmItems.DIAMOND_ACORN)
                        .input('#', Items.DIAMOND)
                        .input('%', YavpmItems.ACORN)
                        .pattern("###")
                        .pattern("#%#")
                        .pattern("###")
                        .criterion(hasItem(YavpmItems.ACORN), conditionsFromItem(YavpmItems.ACORN))
                        .offerTo(exporter);
            }

            private void sweetFoods(RecipeExporter exporter) {
                createShapeless(RecipeCategory.FOOD, YavpmItems.CHOCOLATE, 4)
                        .input(ConventionalItemTags.COCOA_BEAN_CROPS)
                        .input(ConventionalItemTags.MILK_BUCKETS)
                        .input(Items.SUGAR)
                        .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.JELLY, 8)
                        .input(Items.BONE_MEAL)
                        .input(Items.BONE_MEAL)
                        .input(Items.BONE_MEAL)
                        .input(Items.SUGAR)
                        .input(Items.WATER_BUCKET)
                        .criterion(hasItem(Items.BONE_MEAL), conditionsFromItem(Items.BONE_MEAL))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.SWEET_BERRY_JELLY)
                        .input(YavpmItems.JELLY)
                        .input(Items.SWEET_BERRIES)
                        .criterion(hasItem(YavpmItems.JELLY), conditionsFromItem(YavpmItems.JELLY))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.RICE_BAR, 3)
                        .input(YavpmItems.RICE)
                        .input(YavpmItems.RICE)
                        .input(YavpmItems.RICE)
                        .input(YavpmItems.JELLY)
                        .criterion(hasItem(YavpmItems.JELLY), conditionsFromItem(YavpmItems.JELLY))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.RICE_PASTRY, 2)
                        .input(YavpmItems.RICE)
                        .input(YavpmItems.RICE)
                        .input(YavpmItems.RICE)
                        .input(Items.SUGAR)
                        .criterion(hasItem(YavpmItems.RICE), conditionsFromItem(YavpmItems.RICE))
                        .offerTo(exporter);
            }

            private void magicBeanFoods(RecipeExporter exporter) {
                createShapeless(RecipeCategory.FOOD, YavpmItems.BEAN_TOAST, 4)
                        .input(Items.BREAD)
                        .input(YavpmItems.MAGIC_BEAN, 4)
                        .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.CHEESE, 4)
                        .input(Items.MILK_BUCKET)
                        .input(YavpmItems.WARPED_WART)
                        .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, YavpmItems.FAKE_BEEF, 2)
                        .input('#', YavpmItems.MAGIC_BEAN)
                        .pattern("##")
                        .pattern("##")
                        .pattern("##")
                        .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.FAKE_MILK_BUCKET)
                        .input(YavpmItems.MAGIC_BEAN, 4)
                        .input(Items.BUCKET)
                        .criterion(hasItem(YavpmItems.MAGIC_BEAN), conditionsFromItem(YavpmItems.MAGIC_BEAN))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, YavpmItems.TOFU, 4)
                        .input(YavpmItems.FAKE_MILK_BUCKET)
                        .input(YavpmItems.WARPED_WART)
                        .criterion(hasItem(YavpmItems.FAKE_MILK_BUCKET), conditionsFromItem(YavpmItems.FAKE_MILK_BUCKET))
                        .offerTo(exporter);
            }

            private void foodCooking() {
                offerSmelting(List.of(YavpmItems.PEANUT), RecipeCategory.FOOD, YavpmItems.COOKED_PEANUT, 0.35f, 200, "peanut");
                offerFoodCookingRecipe("smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.PEANUT, YavpmItems.COOKED_PEANUT, 0.35f);
                offerSmelting(List.of(YavpmItems.FAKE_BEEF), RecipeCategory.FOOD, YavpmItems.COOKED_FAKE_BEEF, 0.35f, 200, "fake_beef");
                offerFoodCookingRecipe("smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, YavpmItems.FAKE_BEEF, YavpmItems.COOKED_FAKE_BEEF, 0.35f);
                offerSmelting(List.of(Items.EGG), RecipeCategory.FOOD, YavpmItems.COOKED_EGG, 0.35f, 200, "cooked_egg");
                offerFoodCookingRecipe("smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, Items.EGG, YavpmItems.COOKED_EGG, 0.35f);
            }
            private void granite(RecipeExporter exporter) {
                generateFamily(YavpmBlocks.COBBLED_GRANITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
                generateFamily(YavpmBlocks.POLISHED_GRANITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_GRANITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_GRANITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_GRANITE), conditionsFromItem(YavpmBlocks.COBBLED_GRANITE))
                        .offerTo(ifMoreStairsEnabled, "more_cobbled_granite_stairs");

                createStairsRecipe(YavpmBlocks.COBBLED_GRANITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_GRANITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_GRANITE), conditionsFromItem(YavpmBlocks.COBBLED_GRANITE))
                        .offerTo(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_GRANITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .offerTo(ifMoreStairsEnabled, "more_polished_granite_brick_stairs");

                createStairsRecipe(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_GRANITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .offerTo(ifMoreStairsNotEnabled);

                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);

                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_GRANITE), RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE.asItem(), 0.1F, 200)
                        .criterion("has_cobbled_granite", conditionsFromItem(YavpmBlocks.COBBLED_GRANITE))
                        .offerTo(exporter, "yavpm:" +"granite_from_cobbled");

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, 4)
                        .input('#', Blocks.POLISHED_GRANITE)
                        .pattern("##")
                        .pattern("##")
                        .criterion("has_polished_granite", conditionsFromItem(Blocks.POLISHED_GRANITE))
                        .offerTo(exporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.COBBLED_GRANITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.GRANITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.POLISHED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.POLISHED_GRANITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.POLISHED_GRANITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.POLISHED_GRANITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.POLISHED_GRANITE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.POLISHED_GRANITE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.POLISHED_GRANITE_BRICKS);
            }
            private void andesite(RecipeExporter exporter) {
                generateFamily(YavpmBlocks.COBBLED_ANDESITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
                generateFamily(YavpmBlocks.POLISHED_ANDESITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_ANDESITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_ANDESITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_ANDESITE), conditionsFromItem(YavpmBlocks.COBBLED_ANDESITE))
                        .offerTo(ifMoreStairsEnabled, "more_cobbled_andesite_stairs");

                createStairsRecipe(YavpmBlocks.COBBLED_ANDESITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_ANDESITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_ANDESITE), conditionsFromItem(YavpmBlocks.COBBLED_ANDESITE))
                        .offerTo(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_ANDESITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .offerTo(ifMoreStairsEnabled, "more_polished_andesite_brick_stairs");

                createStairsRecipe(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_ANDESITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .offerTo(ifMoreStairsNotEnabled);

                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);

                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE.asItem(), 0.1F, 200)
                        .criterion("has_cobbled_andesite", conditionsFromItem(YavpmBlocks.COBBLED_ANDESITE))
                        .offerTo(exporter, "yavpm:" +"andesite_from_cobbled");

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 4)
                        .input('#', Blocks.POLISHED_ANDESITE)
                        .pattern("##")
                        .pattern("##")
                        .criterion("has_polished_andesite", conditionsFromItem(Blocks.POLISHED_ANDESITE))
                        .offerTo(exporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.COBBLED_ANDESITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.ANDESITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.POLISHED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.POLISHED_ANDESITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.POLISHED_ANDESITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.POLISHED_ANDESITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
            }
            private void diorite(RecipeExporter exporter) {
                generateFamily(YavpmBlocks.COBBLED_DIORITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
                generateFamily(YavpmBlocks.POLISHED_DIORITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_DIORITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_DIORITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_DIORITE), conditionsFromItem(YavpmBlocks.COBBLED_DIORITE))
                        .offerTo(ifMoreStairsEnabled, "more_cobbled_diorite_stairs");

                createStairsRecipe(YavpmBlocks.COBBLED_DIORITE_STAIRS, Ingredient.ofItems(YavpmBlocks.COBBLED_DIORITE))
                        .criterion(hasItem(YavpmBlocks.COBBLED_DIORITE), conditionsFromItem(YavpmBlocks.COBBLED_DIORITE))
                        .offerTo(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_DIORITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .offerTo(ifMoreStairsEnabled, "more_polished_diorite_brick_stairs");

                createStairsRecipe(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_DIORITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .offerTo(ifMoreStairsNotEnabled);


                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);

                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.COBBLED_DIORITE), RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE.asItem(), 0.1F, 200)
                        .criterion("has_cobbled_diorite", conditionsFromItem(YavpmBlocks.COBBLED_DIORITE))
                        .offerTo(exporter, "yavpm:" +"diorite_from_cobbled");

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, 4)
                        .input('#', Blocks.POLISHED_DIORITE)
                        .pattern("##")
                        .pattern("##")
                        .criterion("has_polished_diorite", conditionsFromItem(Blocks.POLISHED_DIORITE))
                        .offerTo(exporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.COBBLED_DIORITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.DIORITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.POLISHED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.POLISHED_DIORITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.POLISHED_DIORITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.POLISHED_DIORITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.POLISHED_DIORITE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.POLISHED_DIORITE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.POLISHED_DIORITE_BRICKS);
            }
            private void kimberlite(RecipeExporter exporter) {
                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(YavpmBlocks.KIMBERLITE), RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE.asItem(), 0.1F, 200)
                        .criterion("has_kimberlite", conditionsFromItem(YavpmBlocks.KIMBERLITE))
                        .offerTo(exporter, "yavpm:" +"polished_kimberlite_from_cobbled");

                createShaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 4)
                        .input('#', YavpmBlocks.POLISHED_KIMBERLITE)
                        .pattern("##")
                        .pattern("##")
                        .criterion("has_polished_kimberlite", conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE))
                        .offerTo(exporter);

                generateFamily(YavpmBlocks.KIMBERLITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
                generateFamily(YavpmBlocks.POLISHED_KIMBERLITE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));
                generateFamily(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.KIMBERLITE_STAIRS, Ingredient.ofItems(YavpmBlocks.KIMBERLITE))
                        .criterion(hasItem(YavpmBlocks.KIMBERLITE), conditionsFromItem(YavpmBlocks.KIMBERLITE))
                        .offerTo(ifMoreStairsEnabled, "more_kimberlite_stairs");

                createStairsRecipe(YavpmBlocks.KIMBERLITE_STAIRS, Ingredient.ofItems(YavpmBlocks.KIMBERLITE))
                        .criterion(hasItem(YavpmBlocks.KIMBERLITE), conditionsFromItem(YavpmBlocks.KIMBERLITE))
                        .offerTo(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_KIMBERLITE))
                        .criterion(hasItem(YavpmBlocks.POLISHED_KIMBERLITE), conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE))
                        .offerTo(ifMoreStairsEnabled, "more_polished_kimberlite_stairs");

                createStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_KIMBERLITE))
                        .criterion(hasItem(YavpmBlocks.POLISHED_KIMBERLITE), conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE))
                        .offerTo(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .offerTo(ifMoreStairsEnabled, "more_polished_kimberlite_brick_stairs");

                createStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, Ingredient.ofItems(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .criterion(hasItem(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS), conditionsFromItem(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .offerTo(ifMoreStairsNotEnabled);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.KIMBERLITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.KIMBERLITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.POLISHED_KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.POLISHED_KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
            }

            private void applewoodRecipes() {
                offerPlanksRecipe(YavpmBlocks.APPLE_PLANKS, YavpmTags.Items.APPLE_LOGS, 4);
                offerBarkBlockRecipe(YavpmBlocks.APPLE_WOOD, YavpmBlocks.APPLE_LOG);
                offerBarkBlockRecipe(YavpmBlocks.STRIPPED_APPLE_WOOD, YavpmBlocks.STRIPPED_APPLE_LOG);
                generateFamily(YavpmBlocks.APPLE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                offerHangingSignRecipe(YavpmItems.APPLE_HANGING_SIGN, YavpmBlocks.STRIPPED_APPLE_LOG);

                createMoreTrapdoorsRecipe(YavpmBlocks.APPLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                        .offerTo(ifMoreTrapdoorsEnabled, "more_apple_trapdoors");
                createTrapdoorRecipe(YavpmBlocks.APPLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                        .offerTo(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.APPLE_STAIRS, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                        .offerTo(ifMoreStairsEnabled, "more_apple_stairs");
                createStairsRecipe(YavpmBlocks.APPLE_STAIRS, Ingredient.ofItems(YavpmBlocks.APPLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.APPLE_PLANKS), conditionsFromItem(YavpmBlocks.APPLE_PLANKS))
                        .offerTo(ifMoreStairsNotEnabled);
            }

            private CraftingRecipeJsonBuilder createMoreTrapdoorsRecipe(ItemConvertible output, Ingredient input) {
                return this.createShaped(RecipeCategory.REDSTONE, output, 12).input('#', input).pattern("###").pattern("###");
            }

            public CraftingRecipeJsonBuilder createMoreStairsRecipe(ItemConvertible output, Ingredient input) {
                return this.createShaped(RecipeCategory.BUILDING_BLOCKS, output, 8).input('#', input).pattern("#  ").pattern("## ").pattern("###");
            }

            private void persimmonRecipes() {
                offerPlanksRecipe(YavpmBlocks.PERSIMMON_PLANKS, YavpmTags.Items.PERSIMMON_LOGS, 4);
                offerBarkBlockRecipe(YavpmBlocks.PERSIMMON_WOOD, YavpmBlocks.PERSIMMON_LOG);
                offerBarkBlockRecipe(YavpmBlocks.STRIPPED_PERSIMMON_WOOD, YavpmBlocks.STRIPPED_PERSIMMON_LOG);
                generateFamily(YavpmBlocks.PERSIMMON_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreTrapdoorsRecipe(YavpmBlocks.PERSIMMON_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.PERSIMMON_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PERSIMMON_PLANKS), conditionsFromItem(YavpmBlocks.PERSIMMON_PLANKS))
                        .offerTo(ifMoreTrapdoorsEnabled, "more_persimmon_trapdoors");
                createTrapdoorRecipe(YavpmBlocks.PERSIMMON_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.PERSIMMON_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PERSIMMON_PLANKS), conditionsFromItem(YavpmBlocks.PERSIMMON_PLANKS))
                        .offerTo(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.PERSIMMON_STAIRS, Ingredient.ofItems(YavpmBlocks.PERSIMMON_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PERSIMMON_PLANKS), conditionsFromItem(YavpmBlocks.PERSIMMON_PLANKS))
                        .offerTo(ifMoreStairsEnabled, "more_persimmon_stairs");
                createStairsRecipe(YavpmBlocks.PERSIMMON_STAIRS, Ingredient.ofItems(YavpmBlocks.PERSIMMON_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PERSIMMON_PLANKS), conditionsFromItem(YavpmBlocks.PERSIMMON_PLANKS))
                        .offerTo(ifMoreStairsNotEnabled);
            }

            private void prickleWoodRecipes() {
                offerPlanksRecipe(YavpmBlocks.PRICKLE_PLANKS, YavpmTags.Items.PRICKLE_LOGS, 4);
                offerBarkBlockRecipe(YavpmBlocks.PRICKLE_WOOD, YavpmBlocks.PRICKLE_LOG);
                offerBarkBlockRecipe(YavpmBlocks.STRIPPED_PRICKLE_WOOD, YavpmBlocks.STRIPPED_PRICKLE_LOG);
                generateFamily(YavpmBlocks.PRICKLE_FAMILY, FeatureSet.of(FeatureFlags.VANILLA));

                createMoreTrapdoorsRecipe(YavpmBlocks.PRICKLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                        .offerTo(ifMoreTrapdoorsEnabled, "more_prickle_trapdoors");
                createTrapdoorRecipe(YavpmBlocks.PRICKLE_TRAPDOOR, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                        .offerTo(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.PRICKLE_STAIRS, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                        .offerTo(ifMoreStairsEnabled, "more_prickle_stairs");
                createStairsRecipe(YavpmBlocks.PRICKLE_STAIRS, Ingredient.ofItems(YavpmBlocks.PRICKLE_PLANKS))
                        .criterion(hasItem(YavpmBlocks.PRICKLE_PLANKS), conditionsFromItem(YavpmBlocks.PRICKLE_PLANKS))
                        .offerTo(ifMoreStairsNotEnabled);
            }

            private void equipmentRecipes(RecipeExporter exporter) {
                createCondensingRecipe(RecipeCategory.MISC, YavpmItems.CHAINMAIL, Ingredient.ofItem(Items.CHAIN))
                        .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                        .offerTo(rareEquipmentRecipeExporter);
                createShaped(RecipeCategory.TOOLS, Items.CHAINMAIL_HELMET)
                        .input('#', YavpmItems.CHAINMAIL)
                        .pattern("###")
                        .pattern("# #")
                        .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.TOOLS, Items.CHAINMAIL_CHESTPLATE)
                        .input('#', YavpmItems.CHAINMAIL)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.TOOLS, Items.CHAINMAIL_LEGGINGS)
                        .input('#', YavpmItems.CHAINMAIL)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.TOOLS, Items.CHAINMAIL_BOOTS)
                        .input('#', YavpmItems.CHAINMAIL)
                        .pattern("# #")
                        .pattern("# #")
                        .criterion(hasItem(YavpmItems.CHAINMAIL), conditionsFromItem(YavpmItems.CHAINMAIL))
                        .offerTo(rareEquipmentRecipeExporter);


                createShaped(RecipeCategory.MISC, Items.NAME_TAG, 12)
                        .input('N', Items.NETHERITE_SCRAP)
                        .input('I', Items.IRON_INGOT)
                        .pattern(" I")
                        .pattern("N ")
                        .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.TOOLS, YavpmItems.REACTOR)
                        .input('B', Items.BLAZE_ROD)
                        .input('N', Items.NETHERITE_INGOT)
                        .pattern(" B ")
                        .pattern("BNB")
                        .pattern(" B ")
                        .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, YavpmItems.BABY_KEY)
                        .input('G', Items.GOLD_INGOT)
                        .input('N', Items.GOLD_NUGGET)
                        .input('P', Ingredient.ofItems(Items.CARVED_PUMPKIN, Items.JACK_O_LANTERN))
                        .pattern("GN")
                        .pattern("GN")
                        .pattern("P ")
                        .criterion(hasItem(Items.CARVED_PUMPKIN), conditionsFromItem(Items.CARVED_PUMPKIN))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, YavpmItems.GAUNTLET)
                        .input('G', YavpmItems.GAUNTLET_FRAGMENT)
                        .input('N', Items.NETHERITE_SCRAP)
                        .pattern("GGG")
                        .pattern("GNG")
                        .pattern("N N")
                        .criterion(hasItem(YavpmItems.GAUNTLET_FRAGMENT), conditionsFromItem(YavpmItems.GAUNTLET_FRAGMENT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, Items.TRIDENT)
                        .input('P', Items.PRISMARINE_SHARD)
                        .input('Z', YavpmItems.THUNDER_SHARD)
                        .pattern("ZZZ")
                        .pattern(" P ")
                        .pattern(" P ")
                        .criterion(hasItem(YavpmItems.THUNDER_SHARD), conditionsFromItem(YavpmItems.THUNDER_SHARD))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.MISC, YavpmItems.VOID_WATER_BUCKET)
                        .input('D', Items.DRAGON_BREATH)
                        .input('W', Items.WATER_BUCKET)
                        .pattern("D")
                        .pattern("W")
                        .criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, YavpmItems.STUDDED_HELMET)
                        .input(Items.LEATHER_HELMET)
                        .input(Items.CHAINMAIL_HELMET)
                        .criterion(hasItem(Items.CHAINMAIL_HELMET), conditionsFromItem(Items.CHAINMAIL_HELMET))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, YavpmItems.STUDDED_CHESTPLATE)
                        .input(Items.LEATHER_CHESTPLATE)
                        .input(Items.CHAINMAIL_CHESTPLATE)
                        .criterion(hasItem(Items.CHAINMAIL_CHESTPLATE), conditionsFromItem(Items.CHAINMAIL_CHESTPLATE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, YavpmItems.STUDDED_LEGGINGS)
                        .input(Items.LEATHER_LEGGINGS)
                        .input(Items.CHAINMAIL_LEGGINGS)
                        .criterion(hasItem(Items.CHAINMAIL_LEGGINGS), conditionsFromItem(Items.CHAINMAIL_LEGGINGS))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, YavpmItems.STUDDED_BOOTS)
                        .input(Items.LEATHER_BOOTS)
                        .input(Items.CHAINMAIL_BOOTS)
                        .criterion(hasItem(Items.CHAINMAIL_BOOTS), conditionsFromItem(Items.CHAINMAIL_BOOTS))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, Items.ELYTRA)
                        .input('C', YavpmItems.PHANTOM_CHORD)
                        .input('M', Items.PHANTOM_MEMBRANE)
                        .pattern("CCC")
                        .pattern("M M")
                        .pattern("M M")
                        .criterion(hasItem(YavpmItems.PHANTOM_CHORD), conditionsFromItem(YavpmItems.PHANTOM_CHORD))
                        .offerTo(rareEquipmentRecipeExporter);

                createShaped(RecipeCategory.MISC, Items.IRON_HORSE_ARMOR)
                        .input('X', Items.IRON_INGOT)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(rareEquipmentRecipeExporter);
                createShaped(RecipeCategory.MISC, Items.GOLDEN_HORSE_ARMOR)
                        .input('X', Items.GOLD_INGOT)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(rareEquipmentRecipeExporter);
                createShaped(RecipeCategory.MISC, Items.DIAMOND_HORSE_ARMOR)
                        .input('X', Items.DIAMOND)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(rareEquipmentRecipeExporter);
            }
        };
    }


    @Override
    public String getName() {
        return "";
    }
}
