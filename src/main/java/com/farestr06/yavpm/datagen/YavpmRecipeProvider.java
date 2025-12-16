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
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class YavpmRecipeProvider extends FabricRecipeProvider {
    public YavpmRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        final RecipeOutput recyclerExperimentRecipeExporter = withConditions(exporter, new RecyclerExperimentResourceCondition());

        final RecipeOutput rareEquipmentRecipeExporter =
                withConditions(exporter, new RareEquipmentRecipesEnabledResourceCondition());

        final ResourceCondition stairCondition = new MoreStairsEnabledResourceCondition();
        final RecipeOutput ifMoreStairsEnabled = withConditions(exporter, stairCondition);
        final RecipeOutput ifMoreStairsNotEnabled = withConditions(exporter, ResourceConditions.not(stairCondition));

        final ResourceCondition trapdoorCondition = new MoreTrapdoorsEnabledResourceCondition();
        final RecipeOutput ifMoreTrapdoorsEnabled = withConditions(exporter, trapdoorCondition);
        final RecipeOutput ifMoreTrapdoorsNotEnabled = withConditions(exporter, ResourceConditions.not(trapdoorCondition));

        return new RecipeProvider(registryLookup, exporter) {
            final HolderLookup.RegistryLookup<Item> itemLookup = registryLookup.lookupOrThrow(Registries.ITEM);
            @Override
            public void buildRecipes() {
                foodRecipes(output);

                kimberlite(output);
                granite(output);
                andesite(output);
                diorite(output);
                conglomerate(output);

                obsidianRecipes(output);
                diamondRecipes(output);

                vtCompat(output);

                densititeRecipes();

                shapeless(RecipeCategory.MISC, YavpmItems.CRIMSON_SPORE)
                        .requires(Items.NETHER_WART)
                        .requires(Ingredient.of(itemLookup.getOrThrow(ConventionalItemTags.MUSHROOMS)))
                        .unlockedBy(getHasName(Items.NETHER_WART), has(Items.NETHER_WART))
                        .save(output);
                shapeless(RecipeCategory.MISC, YavpmItems.WARPED_SPORE)
                        .requires(YavpmItems.WARPED_WART)
                        .requires(Ingredient.of(itemLookup.getOrThrow(ConventionalItemTags.MUSHROOMS)))
                        .unlockedBy(getHasName(YavpmItems.WARPED_WART), has(YavpmItems.WARPED_WART))
                        .save(output);

                shaped(RecipeCategory.REDSTONE, YavpmBlocks.PINATA)
                        .define('P', Items.PAPER)
                        .define('S', Items.SLIME_BALL)
                        .define('D', Items.DECORATED_POT)
                        .pattern(" PS")
                        .pattern("PDP")
                        .pattern("SP ")
                        .unlockedBy(getHasName(Items.DECORATED_POT), has(Items.DECORATED_POT))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SHOJI, 2)
                        .define('P', Items.PAPER)
                        .define('B', Items.BAMBOO)
                        .pattern("PB")
                        .pattern("BP")
                        .unlockedBy(getHasName(Items.BAMBOO), has(Items.BAMBOO))
                        .save(output);

                shaped(RecipeCategory.REDSTONE, YavpmBlocks.POLARIZED_GLASS, 8)
                        .define('T', Blocks.TINTED_GLASS)
                        .define('G', Items.GLOW_INK_SAC)
                        .pattern("TTT")
                        .pattern("TGT")
                        .pattern("TTT")
                        .unlockedBy(getHasName(Items.GLOW_INK_SAC), has(Items.GLOW_INK_SAC))
                        .save(output);

                shaped(RecipeCategory.REDSTONE, YavpmBlocks.BURNER)
                        .define('R', Items.REDSTONE)
                        .define('M', Blocks.MAGMA_BLOCK)
                        .pattern(" R ")
                        .pattern("RMR")
                        .pattern(" R ")
                        .unlockedBy(getHasName(Blocks.MAGMA_BLOCK), has(Blocks.MAGMA_BLOCK))
                        .save(output);

                shaped(RecipeCategory.REDSTONE, YavpmBlocks.RECYCLER)
                        .define('C', Items.COBBLESTONE)
                        .define('R', Items.REDSTONE)
                        .define('I', Items.IRON_INGOT)
                        .pattern("CCC")
                        .pattern("CIC")
                        .pattern("CRC")
                        .group("recycler")
                        .unlockedBy(getHasName(Items.DROPPER), has(Items.DROPPER))
                        .save(recyclerExperimentRecipeExporter);

                equipmentRecipes(output);

                applewoodRecipes();
                persimmonRecipes();
                prickleWoodRecipes();

                threeByThreePacker(RecipeCategory.MISC, YavpmItems.MUSIC_DISC_MAGNETIC_CIRCUIT, YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT);

                this.twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE, Items.SOUL_SAND);
                this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_SLAB, Ingredient.of(YavpmBlocks.SOULSTONE, YavpmBlocks.CHISELED_SOULSTONE))
                        .unlockedBy("has_sandstone", this.has(YavpmBlocks.SOULSTONE))
                        .unlockedBy("has_chiseled_sandstone", this.has(YavpmBlocks.CHISELED_SOULSTONE))
                        .save(this.output);
                this.stairBuilder(YavpmBlocks.SOULSTONE_STAIRS, Ingredient.of(YavpmBlocks.SOULSTONE, YavpmBlocks.CHISELED_SOULSTONE, YavpmBlocks.CUT_SOULSTONE))
                        .unlockedBy("has_sandstone", this.has(YavpmBlocks.SOULSTONE))
                        .unlockedBy("has_chiseled_sandstone", this.has(YavpmBlocks.CHISELED_SOULSTONE))
                        .unlockedBy("has_cut_sandstone", this.has(YavpmBlocks.CUT_SOULSTONE))
                        .save(this.output);

                this.wall(RecipeCategory.DECORATIONS, YavpmBlocks.SOULSTONE_WALL, YavpmBlocks.SOULSTONE);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(YavpmBlocks.SOULSTONE), RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SMOOTH_SOULSTONE.asItem(), 0.1F, 200)
                        .unlockedBy("has_sandstone", this.has(YavpmBlocks.SOULSTONE))
                        .save(this.output);

                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE, YavpmBlocks.SOULSTONE);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_SLAB, YavpmBlocks.SOULSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE_SLAB, YavpmBlocks.SOULSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CUT_SOULSTONE_SLAB, YavpmBlocks.CUT_SOULSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOULSTONE_STAIRS, YavpmBlocks.SOULSTONE);
                this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.SOULSTONE_WALL, YavpmBlocks.SOULSTONE);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.CHISELED_SOULSTONE, YavpmBlocks.SOULSTONE);
            }

            private void conglomerate(RecipeOutput exporter) {
                this.concretePowder(YavpmBlocks.CONGLOMERATE, Items.DRAGON_BREATH);

                stairBuilder(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS, Ingredient.of(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE), has(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .save(exporter);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB,
                        Ingredient.of(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE), has(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .save(exporter);
                this.wall(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, 4)
                        .define('C', YavpmBlocks.HARDENED_CONGLOMERATE)
                        .pattern("CC")
                        .pattern("CC")
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE), has(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .save(exporter);
                stairBuilder(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS), has(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .save(exporter);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB,
                        Ingredient.of(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS), has(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS))
                        .save(exporter);
                this.wall(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);


                this.oreSmelting(
                        List.of(YavpmBlocks.HARDENED_CONGLOMERATE), RecipeCategory.BUILDING_BLOCKS,
                        YavpmBlocks.DULL_CONGLOMERATE, 0.1f, 200, "polished_conglomerate"
                );
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE, Ingredient.of(YavpmBlocks.DULL_CONGLOMERATE_SLAB))
                        .unlockedBy(getHasName(YavpmBlocks.HARDENED_CONGLOMERATE), has(YavpmBlocks.HARDENED_CONGLOMERATE))
                        .save(exporter);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL, YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE, YavpmBlocks.HARDENED_CONGLOMERATE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.DULL_CONGLOMERATE_SLAB, YavpmBlocks.HARDENED_CONGLOMERATE, 2);
            }

            private void densititeRecipes() {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.HEAVY_CORE), RecipeCategory.MISC, YavpmBlocks.DENSITITE_BLOCK, 4.0F, 200)
                        .unlockedBy(getHasName(Items.HEAVY_CORE), this.has(Items.HEAVY_CORE))
                        .save(this.output, "densitite_block_from_smelting");
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.HEAVY_CORE), RecipeCategory.MISC, YavpmBlocks.DENSITITE_BLOCK, 4.0F, 100)
                        .unlockedBy(getHasName(Items.HEAVY_CORE), this.has(Items.HEAVY_CORE))
                        .save(this.output, "densitite_block_from_blasting");

                shapeless(RecipeCategory.MISC, YavpmItems.DENSITITE_INGOT, 3)
                        .requires(YavpmBlocks.DENSITITE_BLOCK)
                        .unlockedBy(getHasName(YavpmBlocks.DENSITITE_BLOCK), has(YavpmBlocks.DENSITITE_BLOCK))
                        .save(output);

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
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(YavpmItems.DENSITITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(input),
                                this.tag(YavpmTags.Items.DENSITITE_TOOL_MATERIALS),
                                category,
                                result
                        )
                        .unlocks("has_densitite_ingot", this.has(YavpmTags.Items.DENSITITE_TOOL_MATERIALS))
                        .save(this.output, getItemName(result) + "_smithing");
            }

            private void vtCompat(RecipeOutput exporter) {
                final RecipeOutput dropperToRecycler = withConditions(exporter, new DropperToRecyclerEnabledResourceCondition());
                shapeless(RecipeCategory.REDSTONE, YavpmBlocks.RECYCLER)
                        .requires(Items.DROPPER).requires(Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.DROPPER), has(Items.DROPPER))
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .group("recycler")
                        .save(dropperToRecycler, "recycler_from_dropper");

                doubleSlabs(exporter);
            }

            private void doubleSlabs(RecipeOutput exporter) {
                final RecipeOutput doubleSlabs = withConditions(exporter, new DoubleSlabsEnabledResourceCondition());

                createDoubleSlabRecipe(YavpmBlocks.APPLE_PLANKS, YavpmBlocks.APPLE_SLAB).save(doubleSlabs, "double_apple_slabs");
                createDoubleSlabRecipe(YavpmBlocks.PRICKLE_PLANKS, YavpmBlocks.PRICKLE_SLAB).save(doubleSlabs, "double_prickle_slabs");
                createDoubleSlabRecipe(YavpmBlocks.PERSIMMON_PLANKS, YavpmBlocks.PERSIMMON_SLAB).save(doubleSlabs, "double_persimmon_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_ANDESITE, YavpmBlocks.COBBLED_ANDESITE_SLAB).save(doubleSlabs, "double_cobbled_andesite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_DIORITE, YavpmBlocks.COBBLED_DIORITE_SLAB).save(doubleSlabs, "double_cobbled_diorite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.COBBLED_GRANITE, YavpmBlocks.COBBLED_GRANITE_SLAB).save(doubleSlabs, "double_cobbled_granite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_ANDESITE_BRICKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB).save(doubleSlabs, "double_polished_andesite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_DIORITE_BRICKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB).save(doubleSlabs, "double_polished_diorite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_GRANITE_BRICKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB).save(doubleSlabs, "double_polished_granite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.KIMBERLITE, YavpmBlocks.KIMBERLITE_SLAB).save(doubleSlabs, "double_kimberlite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_KIMBERLITE, YavpmBlocks.POLISHED_KIMBERLITE_SLAB).save(doubleSlabs, "double_polished_kimberlite_slabs");
                createDoubleSlabRecipe(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB).save(doubleSlabs,"double_polished_kimberlite_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.SOULSTONE, YavpmBlocks.SOULSTONE_SLAB).save(doubleSlabs, "double_soulstone_slabs");
                createDoubleSlabRecipe(YavpmBlocks.CUT_SOULSTONE, YavpmBlocks.CUT_SOULSTONE_SLAB).save(doubleSlabs, "double_cut_soulstone_slabs");
                createDoubleSlabRecipe(YavpmBlocks.SMOOTH_SOULSTONE, YavpmBlocks.SMOOTH_SOULSTONE_SLAB).save(doubleSlabs, "double_smooth_soulstone_slabs");

                createDoubleSlabRecipe(YavpmBlocks.HARDENED_CONGLOMERATE, YavpmBlocks.HARDENED_CONGLOMERATE_SLAB).save(exporter, "double_hardened_conglomerate_slabs");
                createDoubleSlabRecipe(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS, YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB).save(exporter, "double_hardened_conglomerate_brick_slabs");
                createDoubleSlabRecipe(YavpmBlocks.DULL_CONGLOMERATE, YavpmBlocks.DULL_CONGLOMERATE_SLAB).save(exporter, "double_polished_conglomerate_slabs");
            }

            private ShapelessRecipeBuilder createDoubleSlabRecipe(ItemLike block, ItemLike slab){
                return shapeless(RecipeCategory.BUILDING_BLOCKS, slab, 2)
                        .requires(block)
                        .unlockedBy(getHasName(block), has(block));
            }

            private void diamondRecipes(RecipeOutput exporter) {
                shaped(RecipeCategory.MISC, YavpmItems.CARBON_EGG)
                        .define('#', YavpmItems.GRAPHITE)
                        .define('%', ItemTags.EGGS)
                        .pattern(" # ")
                        .pattern("#%#")
                        .pattern(" # ")
                        .unlockedBy(getHasName(YavpmItems.GRAPHITE), has(YavpmItems.GRAPHITE))
                        .save(exporter);

                shapeless(RecipeCategory.MISC, Items.BLACK_DYE, 3)
                        .requires(YavpmItems.GRAPHITE)
                        .unlockedBy(getHasName(YavpmItems.GRAPHITE), has(YavpmItems.GRAPHITE))
                        .save(exporter);
                nineBlockStorageRecipes(RecipeCategory.MISC, YavpmItems.GRAPHITE, RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHITE_BLOCK);
                threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GRAPHENE_BLOCK, YavpmBlocks.GRAPHITE_BLOCK);

                oreSmelting(List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "graphene_to_diamond");
                oreBlasting(List.of(YavpmBlocks.GRAPHENE_BLOCK), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "graphene_to_diamond");
                oreSmelting(List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 400, "diamond_from_raw");
                oreBlasting(List.of(YavpmItems.RAW_DIAMOND), RecipeCategory.MISC, Items.DIAMOND, 1f, 200, "diamond_from_raw");
            }

            private void obsidianRecipes(RecipeOutput exporter) {
                shapeless(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.GLOWING_OBSIDIAN, 4)
                        .requires(Items.BLAZE_POWDER)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                        .save(exporter);
                shapeless(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.SOUL_GLOWING_OBSIDIAN, 4)
                        .requires(Ingredient.of(itemLookup.getOrThrow(ItemTags.SOUL_FIRE_BASE_BLOCKS)))
                        .requires(Items.BLAZE_POWDER)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .requires(Items.OBSIDIAN)
                        .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                        .save(exporter);
            }

            private void foodRecipes(RecipeOutput exporter) {
                foodCooking();
                magicBeanFoods(exporter);
                sweetFoods(exporter);
                seafoods(exporter);
                rareFoods(exporter);

                shapeless(RecipeCategory.MISC, YavpmItems.PRETZEL, 4)
                        .requires(Items.BREAD, 4)
                        .requires(YavpmItems.BAKING_SODA)
                        .unlockedBy(getHasName(YavpmItems.BAKING_SODA), has(YavpmItems.BAKING_SODA))
                        .save(exporter);

                shapeless(RecipeCategory.MISC, Items.EXPERIENCE_BOTTLE)
                        .requires(Items.SCULK, 4)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.SCULK), has(Items.SCULK))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.CHICKEN_SOUP)
                        .requires(Items.COOKED_CHICKEN)
                        .requires(Items.CARROT)
                        .requires(Items.BROWN_MUSHROOM)
                        .requires(YavpmItems.RICE)
                        .requires(Items.BOWL)
                        .unlockedBy(getHasName(Items.COOKED_CHICKEN), has(Items.COOKED_CHICKEN))
                        .save(exporter);

                shapeless(RecipeCategory.MISC, YavpmItems.BREADING, 4)
                        .requires(Items.WHEAT)
                        .requires(Items.BLAZE_POWDER)
                        .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.FRIED_BANANA)
                        .requires(YavpmItems.BANANA)
                        .requires(Items.SUGAR)
                        .requires(YavpmItems.BREADING)
                        .unlockedBy(getHasName(YavpmItems.BREADING), has(YavpmItems.BREADING))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.FRIED_COD)
                        .requires(Items.COOKED_COD)
                        .requires(YavpmItems.BREADING)
                        .unlockedBy(getHasName(YavpmItems.BREADING), has(YavpmItems.BREADING))
                        .save(exporter);
            }

            private void seafoods(RecipeOutput exporter) {
                shaped(RecipeCategory.FOOD, YavpmItems.SUSHI, 6)
                        .define('F', Ingredient.of(Items.TROPICAL_FISH, Items.SALMON))
                        .define('K', Items.DRIED_KELP)
                        .define('R', YavpmItems.RICE)
                        .pattern("KRK")
                        .pattern("RFR")
                        .pattern("KRK")
                        .unlockedBy(getHasName(YavpmItems.RICE), has(YavpmItems.RICE))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.SEA_SOUP)
                        .requires(Items.TROPICAL_FISH)
                        .requires(YavpmItems.RICE)
                        .requires(Items.DRIED_KELP)
                        .requires(YavpmItems.MAGIC_BEAN)
                        .requires(Items.BOWL)
                        .unlockedBy(getHasName(Items.BOWL), has(Items.BOWL))
                        .save(exporter);
            }

            private void rareFoods(RecipeOutput exporter) {
                shapeless(RecipeCategory.FOOD, YavpmItems.FANCY_MUSHROOM_STEW)
                        .requires(Items.RED_MUSHROOM_BLOCK)
                        .requires(Items.BROWN_MUSHROOM_BLOCK)
                        .requires(YavpmItems.TRUFFLE)
                        .requires(Items.BOWL)
                        .unlockedBy(getHasName(YavpmItems.TRUFFLE), has(YavpmItems.TRUFFLE))
                        .save(exporter);

                shaped(RecipeCategory.FOOD, YavpmItems.DIAMOND_ACORN)
                        .define('#', Items.DIAMOND)
                        .define('%', YavpmItems.ACORN)
                        .pattern("###")
                        .pattern("#%#")
                        .pattern("###")
                        .unlockedBy(getHasName(YavpmItems.ACORN), has(YavpmItems.ACORN))
                        .save(exporter);
            }

            private void sweetFoods(RecipeOutput exporter) {
                shapeless(RecipeCategory.FOOD, YavpmItems.CHOCOLATE, 4)
                        .requires(ConventionalItemTags.COCOA_BEAN_CROPS)
                        .requires(ConventionalItemTags.MILK_BUCKETS)
                        .requires(Items.SUGAR)
                        .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.JELLY, 8)
                        .requires(Items.BONE_MEAL)
                        .requires(Items.BONE_MEAL)
                        .requires(Items.BONE_MEAL)
                        .requires(Items.SUGAR)
                        .requires(Items.WATER_BUCKET)
                        .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.SWEET_BERRY_JELLY)
                        .requires(YavpmItems.JELLY)
                        .requires(Items.SWEET_BERRIES)
                        .unlockedBy(getHasName(YavpmItems.JELLY), has(YavpmItems.JELLY))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.RICE_BAR, 3)
                        .requires(YavpmItems.RICE)
                        .requires(YavpmItems.RICE)
                        .requires(YavpmItems.RICE)
                        .requires(YavpmItems.JELLY)
                        .unlockedBy(getHasName(YavpmItems.JELLY), has(YavpmItems.JELLY))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.RICE_PASTRY, 2)
                        .requires(YavpmItems.RICE)
                        .requires(YavpmItems.RICE)
                        .requires(YavpmItems.RICE)
                        .requires(Items.SUGAR)
                        .unlockedBy(getHasName(YavpmItems.RICE), has(YavpmItems.RICE))
                        .save(exporter);
            }

            private void magicBeanFoods(RecipeOutput exporter) {
                shapeless(RecipeCategory.FOOD, YavpmItems.BEAN_TOAST, 4)
                        .requires(Items.BREAD)
                        .requires(YavpmItems.MAGIC_BEAN, 4)
                        .unlockedBy(getHasName(YavpmItems.MAGIC_BEAN), has(YavpmItems.MAGIC_BEAN))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.CHEESE, 4)
                        .requires(Items.MILK_BUCKET)
                        .requires(YavpmItems.WARPED_WART)
                        .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                        .save(exporter);

                shaped(RecipeCategory.FOOD, YavpmItems.FAKE_BEEF, 2)
                        .define('#', YavpmItems.MAGIC_BEAN)
                        .pattern("##")
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(YavpmItems.MAGIC_BEAN), has(YavpmItems.MAGIC_BEAN))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.FAKE_MILK_BUCKET)
                        .requires(YavpmItems.MAGIC_BEAN, 4)
                        .requires(Items.BUCKET)
                        .unlockedBy(getHasName(YavpmItems.MAGIC_BEAN), has(YavpmItems.MAGIC_BEAN))
                        .save(exporter);

                shapeless(RecipeCategory.FOOD, YavpmItems.TOFU, 4)
                        .requires(YavpmItems.FAKE_MILK_BUCKET)
                        .requires(YavpmItems.WARPED_WART)
                        .unlockedBy(getHasName(YavpmItems.FAKE_MILK_BUCKET), has(YavpmItems.FAKE_MILK_BUCKET))
                        .save(exporter);
            }

            private void foodCooking() {
                oreSmelting(List.of(YavpmItems.PEANUT), RecipeCategory.FOOD, YavpmItems.COOKED_PEANUT, 0.35f, 200, "peanut");
                simpleCookingRecipe("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, YavpmItems.PEANUT, YavpmItems.COOKED_PEANUT, 0.35f);
                oreSmelting(List.of(YavpmItems.FAKE_BEEF), RecipeCategory.FOOD, YavpmItems.COOKED_FAKE_BEEF, 0.35f, 200, "fake_beef");
                simpleCookingRecipe("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, YavpmItems.FAKE_BEEF, YavpmItems.COOKED_FAKE_BEEF, 0.35f);
                oreSmelting(List.of(Items.EGG), RecipeCategory.FOOD, YavpmItems.COOKED_EGG, 0.35f, 200, "cooked_egg");
                simpleCookingRecipe("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, Items.EGG, YavpmItems.COOKED_EGG, 0.35f);
            }
            private void granite(RecipeOutput exporter) {
                generateRecipes(YavpmBlocks.COBBLED_GRANITE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(YavpmBlocks.POLISHED_GRANITE_BRICK_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_GRANITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_GRANITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_GRANITE), has(YavpmBlocks.COBBLED_GRANITE))
                        .save(ifMoreStairsEnabled, "more_cobbled_granite_stairs");

                stairBuilder(YavpmBlocks.COBBLED_GRANITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_GRANITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_GRANITE), has(YavpmBlocks.COBBLED_GRANITE))
                        .save(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_GRANITE_BRICKS), has(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .save(ifMoreStairsEnabled, "more_polished_granite_brick_stairs");

                stairBuilder(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_GRANITE_BRICKS), has(YavpmBlocks.POLISHED_GRANITE_BRICKS))
                        .save(ifMoreStairsNotEnabled);

                wall(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(YavpmBlocks.COBBLED_GRANITE), RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE.asItem(), 0.1F, 200)
                        .unlockedBy("has_cobbled_granite", has(YavpmBlocks.COBBLED_GRANITE))
                        .save(exporter, "yavpm:" +"granite_from_cobbled");

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, 4)
                        .define('#', Blocks.POLISHED_GRANITE)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy("has_polished_granite", has(Blocks.POLISHED_GRANITE))
                        .save(exporter);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.COBBLED_GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.COBBLED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.COBBLED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.GRANITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICKS, Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, Blocks.POLISHED_GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, YavpmBlocks.POLISHED_GRANITE_BRICKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, YavpmBlocks.POLISHED_GRANITE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, YavpmBlocks.POLISHED_GRANITE_BRICKS);
            }
            private void andesite(RecipeOutput exporter) {
                generateRecipes(YavpmBlocks.COBBLED_ANDESITE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(YavpmBlocks.POLISHED_ANDESITE_BRICK_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_ANDESITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_ANDESITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_ANDESITE), has(YavpmBlocks.COBBLED_ANDESITE))
                        .save(ifMoreStairsEnabled, "more_cobbled_andesite_stairs");

                stairBuilder(YavpmBlocks.COBBLED_ANDESITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_ANDESITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_ANDESITE), has(YavpmBlocks.COBBLED_ANDESITE))
                        .save(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_ANDESITE_BRICKS), has(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .save(ifMoreStairsEnabled, "more_polished_andesite_brick_stairs");

                stairBuilder(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_ANDESITE_BRICKS), has(YavpmBlocks.POLISHED_ANDESITE_BRICKS))
                        .save(ifMoreStairsNotEnabled);

                wall(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(YavpmBlocks.COBBLED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE.asItem(), 0.1F, 200)
                        .unlockedBy("has_cobbled_andesite", has(YavpmBlocks.COBBLED_ANDESITE))
                        .save(exporter, "yavpm:" +"andesite_from_cobbled");

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 4)
                        .define('#', Blocks.POLISHED_ANDESITE)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy("has_polished_andesite", has(Blocks.POLISHED_ANDESITE))
                        .save(exporter);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.COBBLED_ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.COBBLED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.COBBLED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.ANDESITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICKS, Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, Blocks.POLISHED_ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, YavpmBlocks.POLISHED_ANDESITE_BRICKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, YavpmBlocks.POLISHED_ANDESITE_BRICKS);
            }
            private void diorite(RecipeOutput exporter) {
                generateRecipes(YavpmBlocks.COBBLED_DIORITE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(YavpmBlocks.POLISHED_DIORITE_BRICK_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.COBBLED_DIORITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_DIORITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_DIORITE), has(YavpmBlocks.COBBLED_DIORITE))
                        .save(ifMoreStairsEnabled, "more_cobbled_diorite_stairs");

                stairBuilder(YavpmBlocks.COBBLED_DIORITE_STAIRS, Ingredient.of(YavpmBlocks.COBBLED_DIORITE))
                        .unlockedBy(getHasName(YavpmBlocks.COBBLED_DIORITE), has(YavpmBlocks.COBBLED_DIORITE))
                        .save(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_DIORITE_BRICKS), has(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .save(ifMoreStairsEnabled, "more_polished_diorite_brick_stairs");

                stairBuilder(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_DIORITE_BRICKS), has(YavpmBlocks.POLISHED_DIORITE_BRICKS))
                        .save(ifMoreStairsNotEnabled);


                wall(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(YavpmBlocks.COBBLED_DIORITE), RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE.asItem(), 0.1F, 200)
                        .unlockedBy("has_cobbled_diorite", has(YavpmBlocks.COBBLED_DIORITE))
                        .save(exporter, "yavpm:" +"diorite_from_cobbled");

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, 4)
                        .define('#', Blocks.POLISHED_DIORITE)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy("has_polished_diorite", has(Blocks.POLISHED_DIORITE))
                        .save(exporter);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.COBBLED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.COBBLED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.COBBLED_DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.COBBLED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.COBBLED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.DIORITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICKS, Blocks.POLISHED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, Blocks.POLISHED_DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, Blocks.POLISHED_DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, YavpmBlocks.POLISHED_DIORITE_BRICKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, YavpmBlocks.POLISHED_DIORITE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, YavpmBlocks.POLISHED_DIORITE_BRICKS);
            }
            private void kimberlite(RecipeOutput exporter) {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(YavpmBlocks.KIMBERLITE), RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE.asItem(), 0.1F, 200)
                        .unlockedBy("has_kimberlite", has(YavpmBlocks.KIMBERLITE))
                        .save(exporter, "yavpm:" +"polished_kimberlite_from_cobbled");

                shaped(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 4)
                        .define('#', YavpmBlocks.POLISHED_KIMBERLITE)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy("has_polished_kimberlite", has(YavpmBlocks.POLISHED_KIMBERLITE))
                        .save(exporter);

                generateRecipes(YavpmBlocks.KIMBERLITE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(YavpmBlocks.POLISHED_KIMBERLITE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreStairsRecipe(YavpmBlocks.KIMBERLITE_STAIRS, Ingredient.of(YavpmBlocks.KIMBERLITE))
                        .unlockedBy(getHasName(YavpmBlocks.KIMBERLITE), has(YavpmBlocks.KIMBERLITE))
                        .save(ifMoreStairsEnabled, "more_kimberlite_stairs");

                stairBuilder(YavpmBlocks.KIMBERLITE_STAIRS, Ingredient.of(YavpmBlocks.KIMBERLITE))
                        .unlockedBy(getHasName(YavpmBlocks.KIMBERLITE), has(YavpmBlocks.KIMBERLITE))
                        .save(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_KIMBERLITE))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_KIMBERLITE), has(YavpmBlocks.POLISHED_KIMBERLITE))
                        .save(ifMoreStairsEnabled, "more_polished_kimberlite_stairs");

                stairBuilder(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_KIMBERLITE))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_KIMBERLITE), has(YavpmBlocks.POLISHED_KIMBERLITE))
                        .save(ifMoreStairsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS), has(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .save(ifMoreStairsEnabled, "more_polished_kimberlite_brick_stairs");

                stairBuilder(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, Ingredient.of(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .unlockedBy(getHasName(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS), has(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS))
                        .save(ifMoreStairsNotEnabled);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.KIMBERLITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.KIMBERLITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.KIMBERLITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_WALL, YavpmBlocks.POLISHED_KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, YavpmBlocks.POLISHED_KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL, YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);
            }

            private void applewoodRecipes() {
                planksFromLogs(YavpmBlocks.APPLE_PLANKS, YavpmTags.Items.APPLE_LOGS, 4);
                woodFromLogs(YavpmBlocks.APPLE_WOOD, YavpmBlocks.APPLE_LOG);
                woodFromLogs(YavpmBlocks.STRIPPED_APPLE_WOOD, YavpmBlocks.STRIPPED_APPLE_LOG);
                generateRecipes(YavpmBlocks.APPLE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                hangingSign(YavpmItems.APPLE_HANGING_SIGN, YavpmBlocks.STRIPPED_APPLE_LOG);

                createMoreTrapdoorsRecipe(YavpmBlocks.APPLE_TRAPDOOR, Ingredient.of(YavpmBlocks.APPLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.APPLE_PLANKS), has(YavpmBlocks.APPLE_PLANKS))
                        .save(ifMoreTrapdoorsEnabled, "more_apple_trapdoors");
                trapdoorBuilder(YavpmBlocks.APPLE_TRAPDOOR, Ingredient.of(YavpmBlocks.APPLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.APPLE_PLANKS), has(YavpmBlocks.APPLE_PLANKS))
                        .save(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.APPLE_STAIRS, Ingredient.of(YavpmBlocks.APPLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.APPLE_PLANKS), has(YavpmBlocks.APPLE_PLANKS))
                        .save(ifMoreStairsEnabled, "more_apple_stairs");
                stairBuilder(YavpmBlocks.APPLE_STAIRS, Ingredient.of(YavpmBlocks.APPLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.APPLE_PLANKS), has(YavpmBlocks.APPLE_PLANKS))
                        .save(ifMoreStairsNotEnabled);
            }

            private RecipeBuilder createMoreTrapdoorsRecipe(ItemLike output, Ingredient input) {
                return this.shaped(RecipeCategory.REDSTONE, output, 12).define('#', input).pattern("###").pattern("###");
            }

            public RecipeBuilder createMoreStairsRecipe(ItemLike output, Ingredient input) {
                return this.shaped(RecipeCategory.BUILDING_BLOCKS, output, 8).define('#', input).pattern("#  ").pattern("## ").pattern("###");
            }

            private void persimmonRecipes() {
                planksFromLogs(YavpmBlocks.PERSIMMON_PLANKS, YavpmTags.Items.PERSIMMON_LOGS, 4);
                woodFromLogs(YavpmBlocks.PERSIMMON_WOOD, YavpmBlocks.PERSIMMON_LOG);
                woodFromLogs(YavpmBlocks.STRIPPED_PERSIMMON_WOOD, YavpmBlocks.STRIPPED_PERSIMMON_LOG);
                generateRecipes(YavpmBlocks.PERSIMMON_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreTrapdoorsRecipe(YavpmBlocks.PERSIMMON_TRAPDOOR, Ingredient.of(YavpmBlocks.PERSIMMON_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PERSIMMON_PLANKS), has(YavpmBlocks.PERSIMMON_PLANKS))
                        .save(ifMoreTrapdoorsEnabled, "more_persimmon_trapdoors");
                trapdoorBuilder(YavpmBlocks.PERSIMMON_TRAPDOOR, Ingredient.of(YavpmBlocks.PERSIMMON_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PERSIMMON_PLANKS), has(YavpmBlocks.PERSIMMON_PLANKS))
                        .save(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.PERSIMMON_STAIRS, Ingredient.of(YavpmBlocks.PERSIMMON_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PERSIMMON_PLANKS), has(YavpmBlocks.PERSIMMON_PLANKS))
                        .save(ifMoreStairsEnabled, "more_persimmon_stairs");
                stairBuilder(YavpmBlocks.PERSIMMON_STAIRS, Ingredient.of(YavpmBlocks.PERSIMMON_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PERSIMMON_PLANKS), has(YavpmBlocks.PERSIMMON_PLANKS))
                        .save(ifMoreStairsNotEnabled);
            }

            private void prickleWoodRecipes() {
                planksFromLogs(YavpmBlocks.PRICKLE_PLANKS, YavpmTags.Items.PRICKLE_LOGS, 4);
                woodFromLogs(YavpmBlocks.PRICKLE_WOOD, YavpmBlocks.PRICKLE_LOG);
                woodFromLogs(YavpmBlocks.STRIPPED_PRICKLE_WOOD, YavpmBlocks.STRIPPED_PRICKLE_LOG);
                generateRecipes(YavpmBlocks.PRICKLE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createMoreTrapdoorsRecipe(YavpmBlocks.PRICKLE_TRAPDOOR, Ingredient.of(YavpmBlocks.PRICKLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PRICKLE_PLANKS), has(YavpmBlocks.PRICKLE_PLANKS))
                        .save(ifMoreTrapdoorsEnabled, "more_prickle_trapdoors");
                trapdoorBuilder(YavpmBlocks.PRICKLE_TRAPDOOR, Ingredient.of(YavpmBlocks.PRICKLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PRICKLE_PLANKS), has(YavpmBlocks.PRICKLE_PLANKS))
                        .save(ifMoreTrapdoorsNotEnabled);

                createMoreStairsRecipe(YavpmBlocks.PRICKLE_STAIRS, Ingredient.of(YavpmBlocks.PRICKLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PRICKLE_PLANKS), has(YavpmBlocks.PRICKLE_PLANKS))
                        .save(ifMoreStairsEnabled, "more_prickle_stairs");
                stairBuilder(YavpmBlocks.PRICKLE_STAIRS, Ingredient.of(YavpmBlocks.PRICKLE_PLANKS))
                        .unlockedBy(getHasName(YavpmBlocks.PRICKLE_PLANKS), has(YavpmBlocks.PRICKLE_PLANKS))
                        .save(ifMoreStairsNotEnabled);
            }

            private void equipmentRecipes(RecipeOutput exporter) {
                polishedBuilder(RecipeCategory.MISC, YavpmItems.CHAINMAIL, Ingredient.of(Items.IRON_CHAIN))
                        .unlockedBy(getHasName(Items.IRON_CHAIN), has(Items.IRON_CHAIN))
                        .save(rareEquipmentRecipeExporter);
                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_HELMET)
                        .define('#', YavpmItems.CHAINMAIL)
                        .pattern("###")
                        .pattern("# #")
                        .unlockedBy(getHasName(YavpmItems.CHAINMAIL), has(YavpmItems.CHAINMAIL))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_CHESTPLATE)
                        .define('#', YavpmItems.CHAINMAIL)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(getHasName(YavpmItems.CHAINMAIL), has(YavpmItems.CHAINMAIL))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_LEGGINGS)
                        .define('#', YavpmItems.CHAINMAIL)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .unlockedBy(getHasName(YavpmItems.CHAINMAIL), has(YavpmItems.CHAINMAIL))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_BOOTS)
                        .define('#', YavpmItems.CHAINMAIL)
                        .pattern("# #")
                        .pattern("# #")
                        .unlockedBy(getHasName(YavpmItems.CHAINMAIL), has(YavpmItems.CHAINMAIL))
                        .save(rareEquipmentRecipeExporter);


                shaped(RecipeCategory.MISC, Items.NAME_TAG, 12)
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('I', Items.IRON_INGOT)
                        .pattern(" I")
                        .pattern("N ")
                        .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.TOOLS, YavpmItems.REACTOR)
                        .define('B', Items.BLAZE_ROD)
                        .define('N', Items.NETHERITE_INGOT)
                        .pattern(" B ")
                        .pattern("BNB")
                        .pattern(" B ")
                        .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                        .save(exporter);

                shaped(RecipeCategory.TOOLS, YavpmItems.BABY_KEY)
                        .define('G', Items.GOLD_INGOT)
                        .define('N', Items.GOLD_NUGGET)
                        .define('P', Ingredient.of(Items.CARVED_PUMPKIN, Items.JACK_O_LANTERN))
                        .pattern("GN")
                        .pattern("GN")
                        .pattern("P ")
                        .unlockedBy(getHasName(Items.CARVED_PUMPKIN), has(Items.CARVED_PUMPKIN))
                        .save(exporter);

                shaped(RecipeCategory.TOOLS, YavpmItems.GAUNTLET)
                        .define('G', YavpmItems.GAUNTLET_FRAGMENT)
                        .define('N', Items.NETHERITE_SCRAP)
                        .pattern("GGG")
                        .pattern("GNG")
                        .pattern("N N")
                        .unlockedBy(getHasName(YavpmItems.GAUNTLET_FRAGMENT), has(YavpmItems.GAUNTLET_FRAGMENT))
                        .save(exporter);

                shaped(RecipeCategory.TOOLS, Items.TRIDENT)
                        .define('P', Items.PRISMARINE_SHARD)
                        .define('Z', YavpmItems.THUNDER_SHARD)
                        .pattern("ZZZ")
                        .pattern(" P ")
                        .pattern(" P ")
                        .unlockedBy(getHasName(YavpmItems.THUNDER_SHARD), has(YavpmItems.THUNDER_SHARD))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.MISC, YavpmItems.VOID_WATER_BUCKET)
                        .define('D', Items.DRAGON_BREATH)
                        .define('W', Items.WATER_BUCKET)
                        .pattern("D")
                        .pattern("W")
                        .unlockedBy(getHasName(Items.DRAGON_BREATH), has(Items.DRAGON_BREATH))
                        .save(exporter);

                shapeless(RecipeCategory.MISC, YavpmItems.STUDDED_HELMET)
                        .requires(Items.LEATHER_HELMET)
                        .requires(Items.CHAINMAIL_HELMET)
                        .unlockedBy(getHasName(Items.CHAINMAIL_HELMET), has(Items.CHAINMAIL_HELMET))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, YavpmItems.STUDDED_CHESTPLATE)
                        .requires(Items.LEATHER_CHESTPLATE)
                        .requires(Items.CHAINMAIL_CHESTPLATE)
                        .unlockedBy(getHasName(Items.CHAINMAIL_CHESTPLATE), has(Items.CHAINMAIL_CHESTPLATE))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, YavpmItems.STUDDED_LEGGINGS)
                        .requires(Items.LEATHER_LEGGINGS)
                        .requires(Items.CHAINMAIL_LEGGINGS)
                        .unlockedBy(getHasName(Items.CHAINMAIL_LEGGINGS), has(Items.CHAINMAIL_LEGGINGS))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, YavpmItems.STUDDED_BOOTS)
                        .requires(Items.LEATHER_BOOTS)
                        .requires(Items.CHAINMAIL_BOOTS)
                        .unlockedBy(getHasName(Items.CHAINMAIL_BOOTS), has(Items.CHAINMAIL_BOOTS))
                        .save(exporter);
                shaped(RecipeCategory.TOOLS, Items.ELYTRA)
                        .define('C', YavpmItems.PHANTOM_CHORD)
                        .define('M', Items.PHANTOM_MEMBRANE)
                        .pattern("CCC")
                        .pattern("M M")
                        .pattern("M M")
                        .unlockedBy(getHasName(YavpmItems.PHANTOM_CHORD), has(YavpmItems.PHANTOM_CHORD))
                        .save(rareEquipmentRecipeExporter);

                shaped(RecipeCategory.MISC, Items.IRON_HORSE_ARMOR)
                        .define('X', Items.IRON_INGOT)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(rareEquipmentRecipeExporter);
                shaped(RecipeCategory.MISC, Items.GOLDEN_HORSE_ARMOR)
                        .define('X', Items.GOLD_INGOT)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(rareEquipmentRecipeExporter);
                shaped(RecipeCategory.MISC, Items.DIAMOND_HORSE_ARMOR)
                        .define('X', Items.DIAMOND)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("X X")
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .save(rareEquipmentRecipeExporter);
            }
        };
    }


    @Override
    public String getName() {
        return "";
    }
}
