package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.util.YavpmTags;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class YavpmTagProviders {
    public static class Item extends FabricTagProvider.ItemTagProvider {

        public Item(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            craftingTags();
            recyclingTags();
            equipmentTags();
            foodTags();
            plantTags();
            buildingBlockTags();
        }
        private void recyclingTags() {
        }

        private void craftingTags() {
            valueLookupBuilder(ItemTags.REPAIRS_CHAIN_ARMOR).setReplace(true).add(YavpmItems.CHAINMAIL);
            valueLookupBuilder(YavpmTags.Items.REPAIRS_STUDDED_ARMOR).add(
                    Items.LEATHER,
                    YavpmItems.CHAINMAIL
            );

            valueLookupBuilder(YavpmTags.Items.DENSITITE_TOOL_MATERIALS).add(YavpmItems.DENSITITE_INGOT);
            valueLookupBuilder(YavpmTags.Items.REPAIRS_DENSITITE_ARMOR).add(YavpmItems.DENSITITE_INGOT);

            valueLookupBuilder(ItemTags.STONE_TOOL_MATERIALS).add(
                    YavpmBlocks.COBBLED_GRANITE.asItem(),
                    YavpmBlocks.COBBLED_DIORITE.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE.asItem()
            );

            valueLookupBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(
                    YavpmBlocks.COBBLED_GRANITE.asItem(),
                    YavpmBlocks.COBBLED_DIORITE.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE.asItem()
            );
            valueLookupBuilder(ItemTags.DYEABLE).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
            valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
            valueLookupBuilder(ConventionalItemTags.RAW_MATERIALS).add(
                    YavpmItems.RAW_DIAMOND,
                    YavpmBlocks.GRAPHENE_BLOCK.asItem()
            );

            valueLookupBuilder(YavpmTags.Items.REACTOR_RECHARGERS).add(
                    Items.BLAZE_POWDER,
                    Items.BLAZE_ROD,
                    Items.FIRE_CHARGE,
                    Items.MAGMA_CREAM,
                    Items.MAGMA_BLOCK,
                    Items.LAVA_BUCKET
            );
        }

        private void equipmentTags() {
            valueLookupBuilder(ItemTags.SWORDS).add(YavpmItems.DENSITITE_SWORD);
            valueLookupBuilder(ItemTags.SHOVELS).add(YavpmItems.DENSITITE_SHOVEL);
            valueLookupBuilder(ItemTags.PICKAXES).add(YavpmItems.DENSITITE_PICKAXE);
            valueLookupBuilder(ItemTags.AXES).add(YavpmItems.DENSITITE_AXE);
            valueLookupBuilder(ItemTags.HOES).add(YavpmItems.DENSITITE_HOE);

            valueLookupBuilder(ConventionalItemTags.ARMORS).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS,
                    YavpmItems.DENSITITE_HELMET,
                    YavpmItems.DENSITITE_CHESTPLATE,
                    YavpmItems.DENSITITE_LEGGINGS,
                    YavpmItems.DENSITITE_BOOTS
            );

            valueLookupBuilder(YavpmTags.Items.ENCHANTABLE_GLIDER).add(
                    Items.ELYTRA
            );
            valueLookupBuilder(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR).add(
                    Items.WOLF_ARMOR
            );
            valueLookupBuilder(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR).add(
                    Items.LEATHER_HORSE_ARMOR,
                    Items.GOLDEN_HORSE_ARMOR,
                    Items.IRON_HORSE_ARMOR,
                    Items.DIAMOND_HORSE_ARMOR
            );

            valueLookupBuilder(ItemTags.HEAD_ARMOR)
                    .add(YavpmItems.STUDDED_HELMET, YavpmItems.DENSITITE_HELMET);
            valueLookupBuilder(ItemTags.CHEST_ARMOR)
                    .add(YavpmItems.STUDDED_CHESTPLATE, YavpmItems.DENSITITE_CHESTPLATE);
            valueLookupBuilder(ItemTags.LEG_ARMOR)
                    .add(YavpmItems.STUDDED_LEGGINGS, YavpmItems.DENSITITE_LEGGINGS);
            valueLookupBuilder(ItemTags.FOOT_ARMOR)
                    .add(YavpmItems.STUDDED_BOOTS, YavpmItems.DENSITITE_BOOTS);
            valueLookupBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
        }

        private void foodTags() {
            valueLookupBuilder(ConventionalItemTags.FOODS).add(
                    YavpmItems.BANANA,
                    YavpmItems.FRIED_BANANA,
                    YavpmItems.FRIED_COD,
                    YavpmBlocks.PEANUT_CROP.asItem(),
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.COOKED_EGG,
                    YavpmItems.CHEESE,
                    YavpmItems.MOLY,
                    YavpmBlocks.OAK_SAPLING_CROP.asItem(),
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmBlocks.OAK_SAPLING_CROP.asItem(),
                    YavpmItems.BEAN_TOAST,
                    YavpmItems.FAKE_BEEF,
                    YavpmItems.COOKED_FAKE_BEEF,
                    YavpmItems.TOFU,
                    YavpmItems.JELLY,
                    YavpmItems.SWEET_BERRY_JELLY,
                    YavpmItems.RICE_BAR,
                    YavpmItems.RICE_PASTRY,
                    YavpmItems.SUSHI,
                    YavpmItems.SEA_SOUP,
                    YavpmItems.CHICKEN_SOUP,
                    YavpmItems.FANCY_MUSHROOM_STEW
            );
            valueLookupBuilder(ConventionalItemTags.MILK_BUCKETS).add(
                    YavpmItems.FAKE_MILK_BUCKET
            );
            valueLookupBuilder(ConventionalItemTags.RAW_MEAT_FOODS).add(
                    YavpmItems.FAKE_BEEF
            );
            valueLookupBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).add(
                    YavpmItems.COOKED_FAKE_BEEF,
                    YavpmItems.FRIED_COD
            );
            valueLookupBuilder(ConventionalItemTags.COOKED_FISH_FOODS)
                    .add(YavpmItems.FRIED_COD);
            valueLookupBuilder(ConventionalItemTags.FRUIT_FOODS).add(
                    YavpmItems.BANANA
            );
            valueLookupBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(
                    YavpmItems.MOLY,
                    YavpmItems.MAGIC_BEAN
            );
            valueLookupBuilder(ConventionalItemTags.SOUP_FOODS).add(
                    YavpmItems.SEA_SOUP,
                    YavpmItems.CHICKEN_SOUP,
                    YavpmItems.FANCY_MUSHROOM_STEW
            );
            valueLookupBuilder(ConventionalItemTags.COOKIE_FOODS)
                    .add(YavpmItems.FORTUNE_COOKIE);
            valueLookupBuilder(ConventionalItemTags.CANDY_FOODS)
                    .add(YavpmItems.CHOCOLATE)
                    .add(YavpmItems.JELLY)
                    .add(YavpmItems.SWEET_BERRY_JELLY);
            valueLookupBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(
                    YavpmItems.PEANUT
            );

            valueLookupBuilder(ConventionalItemTags.ANIMAL_FOODS).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmItems.BANANA_SEEDS
            );
            valueLookupBuilder(ItemTags.PARROT_POISONOUS_FOOD).add(
                    YavpmItems.CHOCOLATE
            );
            valueLookupBuilder(ItemTags.CHICKEN_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.BANANA_SEEDS
            );
            valueLookupBuilder(ItemTags.PARROT_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.BANANA_SEEDS
            );
            valueLookupBuilder(ItemTags.PIG_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE
            );
            valueLookupBuilder(ItemTags.WOLF_FOOD).add(
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.CHEESE,
                    YavpmItems.FAKE_BEEF,
                    YavpmItems.COOKED_FAKE_BEEF,
                    YavpmItems.TOFU
            );
            valueLookupBuilder(YavpmTags.Items.CARBONFOWL_FOODS).add(
                    Items.GLOW_BERRIES,
                    Items.MOSS_BLOCK,
                    Items.MOSS_CARPET,
                    Items.AZALEA,
                    Items.FLOWERING_AZALEA,
                    Items.SCULK,
                    Items.SCULK_VEIN,
                    Items.SCULK_CATALYST,
                    Items.SCULK_SENSOR,
                    Items.SCULK_SHRIEKER,
                    Items.ROTTEN_FLESH,
                    Items.BONE,
                    Items.GUNPOWDER,
                    Items.SPIDER_EYE
            );
            valueLookupBuilder(YavpmTags.Items.TANUKI_FOOD)
                    .forceAddTag(ConventionalItemTags.BERRY_FOODS);

            valueLookupBuilder(YavpmTags.Items.CRIMSON_MOONGUS_FOOD).add(
                    Items.SUGAR,
                    Items.RABBIT_FOOT,
                    Items.BLAZE_POWDER,
                    Items.GLISTERING_MELON_SLICE,
                    Items.SPIDER_EYE,
                    Items.GHAST_TEAR,
                    Items.MAGMA_CREAM,
                    Items.PUFFERFISH,
                    Items.GOLDEN_CARROT,
                    Items.TURTLE_HELMET,
                    Items.PHANTOM_MEMBRANE
            );
            valueLookupBuilder(YavpmTags.Items.CRIMSON_MOONGUS_FOOD_CORRUPTED).add(
                    Items.SUGAR,
                    Items.RABBIT_FOOT,
                    Items.GLISTERING_MELON_SLICE,
                    Items.SPIDER_EYE,
                    Items.GOLDEN_CARROT,
                    Items.PUFFERFISH
            );
            valueLookupBuilder(YavpmTags.Items.WARPED_MOONGUS_FOOD).add(
                    Items.BREEZE_ROD,
                    Items.SLIME_BLOCK,
                    Items.STONE,
                    Items.COBWEB,
                    Items.FERMENTED_SPIDER_EYE,
                    Items.WITHER_ROSE,
                    Items.DRAGON_BREATH,
                    YavpmItems.BITTER_BERRIES,
                    Items.SWEET_BERRIES
            );
        }

        private void plantTags() {
            valueLookupBuilder(ItemTags.LEAVES).add(
                    YavpmBlocks.APPLE_LEAVES.asItem(), YavpmBlocks.FLOWERING_APPLE_LEAVES.asItem(),
                    YavpmBlocks.PERSIMMON_LEAVES.asItem()
            );

            valueLookupBuilder(ConventionalItemTags.SEEDS).add(
                    YavpmItems.BANANA_SEEDS,
                    YavpmItems.PEANUT,
                    YavpmItems.MAGIC_BEAN,
                    YavpmItems.ACORN,
                    YavpmItems.BIRCH_SEEDS,
                    YavpmItems.SPRUCE_CONE
            );

            valueLookupBuilder(ConventionalItemTags.CROPS).add(
                    YavpmItems.ACORN,
                    YavpmItems.BIRCH_SEEDS,
                    YavpmItems.SPRUCE_CONE,
                    YavpmItems.CRIMSON_SPORE,
                    YavpmItems.WARPED_SPORE,
                    Items.OAK_SAPLING,
                    YavpmItems.BANANA,
                    YavpmItems.BANANA_SEEDS,
                    YavpmItems.PEANUT,
                    YavpmItems.MAGIC_BEAN
            );

            valueLookupBuilder(ItemTags.SAPLINGS).add(
                    YavpmBlocks.APPLE_SAPLING.asItem(),
                    YavpmBlocks.PERSIMMON_SAPLING.asItem(),
                    YavpmBlocks.PRICKLE_SHOOT.asItem()
            );
        }

        private void buildingBlockTags() {
            valueLookupBuilder(ConventionalItemTags.OBSIDIANS).add(
                    YavpmBlocks.GLOWING_OBSIDIAN.asItem(),
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN.asItem()
            );

            valueLookupBuilder(ItemTags.STAIRS).add(
                    YavpmBlocks.KIMBERLITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.SCULKY_DEEPSLATE_BRICKS.asItem(),
                    YavpmBlocks.SOULSTONE_STAIRS.asItem(),
                    YavpmBlocks.SMOOTH_SOULSTONE_STAIRS.asItem()
            );
            valueLookupBuilder(ItemTags.SLABS).add(
                    YavpmBlocks.KIMBERLITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB.asItem(),
                    YavpmBlocks.SOULSTONE_SLAB.asItem(),
                    YavpmBlocks.CUT_SOULSTONE_SLAB.asItem(),
                    YavpmBlocks.SMOOTH_SOULSTONE_SLAB.asItem()
            );
            valueLookupBuilder(ItemTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL.asItem(),
                    YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL.asItem(),
                    YavpmBlocks.SOULSTONE_WALL.asItem()
            );

            valueLookupBuilder(ConventionalItemTags.STRIPPED_LOGS).add(
                    YavpmBlocks.STRIPPED_APPLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_LOG.asItem()
            );
            valueLookupBuilder(ConventionalItemTags.STRIPPED_WOODS).add(
                    YavpmBlocks.STRIPPED_APPLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD.asItem()
            );
            valueLookupBuilder(YavpmTags.Items.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG.asItem(),
                    YavpmBlocks.APPLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_WOOD.asItem()
            );
            valueLookupBuilder(YavpmTags.Items.PERSIMMON_LOGS).add(
                    YavpmBlocks.PERSIMMON_LOG.asItem(),
                    YavpmBlocks.PERSIMMON_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD.asItem()
            );
            valueLookupBuilder(YavpmTags.Items.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG.asItem(),
                    YavpmBlocks.PRICKLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD.asItem()
            );
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                    .forceAddTag(YavpmTags.Items.APPLE_LOGS)
                    .forceAddTag(YavpmTags.Items.PERSIMMON_LOGS)
                    .forceAddTag(YavpmTags.Items.PRICKLE_LOGS);

            valueLookupBuilder(ItemTags.PLANKS).add(
                    YavpmBlocks.APPLE_PLANKS.asItem(),
                    YavpmBlocks.PERSIMMON_PLANKS.asItem(),
                    YavpmBlocks.PRICKLE_PLANKS.asItem()
            );

            valueLookupBuilder(ItemTags.WOODEN_STAIRS).add(YavpmBlocks.APPLE_STAIRS.asItem(), YavpmBlocks.PERSIMMON_STAIRS.asItem(), YavpmBlocks.PRICKLE_STAIRS.asItem());
            valueLookupBuilder(ItemTags.WOODEN_SLABS).add(YavpmBlocks.APPLE_SLAB.asItem(), YavpmBlocks.PERSIMMON_SLAB.asItem(), YavpmBlocks.PRICKLE_SLAB.asItem());
            valueLookupBuilder(ItemTags.WOODEN_FENCES).add(YavpmBlocks.APPLE_FENCE.asItem(), YavpmBlocks.PERSIMMON_FENCE.asItem(), YavpmBlocks.PRICKLE_FENCE.asItem());
            valueLookupBuilder(ItemTags.FENCE_GATES).add(YavpmBlocks.APPLE_FENCE_GATE.asItem(), YavpmBlocks.PERSIMMON_FENCE_GATE.asItem(), YavpmBlocks.PRICKLE_FENCE_GATE.asItem());
            valueLookupBuilder(ItemTags.WOODEN_DOORS).add(YavpmBlocks.APPLE_DOOR.asItem() ,YavpmBlocks.PERSIMMON_DOOR.asItem(), YavpmBlocks.PRICKLE_DOOR.asItem());
            valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS).add(YavpmBlocks.APPLE_TRAPDOOR.asItem(), YavpmBlocks.PERSIMMON_TRAPDOOR.asItem(), YavpmBlocks.PRICKLE_TRAPDOOR.asItem());

            valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(
                    YavpmBlocks.APPLE_PRESSURE_PLATE.asItem(),
                    YavpmBlocks.PERSIMMON_PRESSURE_PLATE.asItem(),
                    YavpmBlocks.PRICKLE_PRESSURE_PLATE.asItem()
            );
            valueLookupBuilder(ItemTags.WOODEN_BUTTONS).add(
                    YavpmBlocks.APPLE_BUTTON.asItem(),
                    YavpmBlocks.PERSIMMON_BUTTON.asItem(),
                    YavpmBlocks.PRICKLE_BUTTON.asItem()
            );
        }
    }

    public static class Block extends FabricTagProvider.BlockTagProvider {
        public Block(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            valueLookupBuilder(ConventionalBlockTags.OBSIDIANS).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN
            );

            valueLookupBuilder(YavpmTags.Blocks.RICE_GROWABLE_ON).add(
                    Blocks.SAND,
                    Blocks.GRAVEL,
                    Blocks.CLAY,
                    Blocks.DIRT
            );

            valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                    YavpmBlocks.FAKE_LOG,
                    YavpmBlocks.SHOJI,
                    YavpmBlocks.CHOPPING_BLOCK,
                    YavpmBlocks.ATTACHED_CANTALOUPE_STEM,
                    YavpmBlocks.CANTALOUPE_STEM,
                    YavpmBlocks.CANTALOUPE
            );

            valueLookupBuilder(BlockTags.IMPERMEABLE)
                    .add(YavpmBlocks.POLARIZED_GLASS);

            plantTags();
            mineableTags();
            generalBlockTags();
            woodenBlockTags();
        }
        private void plantTags() {
            valueLookupBuilder(BlockTags.CROPS).add(
                    YavpmBlocks.CANTALOUPE_STEM,
                    YavpmBlocks.BANANA_CROP,
                    YavpmBlocks.PEANUT_CROP,
                    YavpmBlocks.RICE_CROP,
                    YavpmBlocks.MAGIC_BEAN_CROP,
                    YavpmBlocks.WARPED_WART_CROP,
                    YavpmBlocks.OAK_SAPLING_CROP,
                    YavpmBlocks.BIRCH_SAPLING_CROP,
                    YavpmBlocks.CRIMSON_FUNGUS_CROP,
                    YavpmBlocks.WARPED_FUNGUS_CROP
            );
            valueLookupBuilder(BlockTags.MAINTAINS_FARMLAND).add(
                    YavpmBlocks.CANTALOUPE_STEM,
                    YavpmBlocks.ATTACHED_CANTALOUPE_STEM,
                    YavpmBlocks.BANANA_CROP,
                    YavpmBlocks.PEANUT_CROP,
                    YavpmBlocks.MAGIC_BEAN_CROP,
                    YavpmBlocks.WARPED_WART_CROP,
                    YavpmBlocks.OAK_SAPLING_CROP,
                    YavpmBlocks.BIRCH_SAPLING_CROP,
                    YavpmBlocks.CRIMSON_FUNGUS_CROP,
                    YavpmBlocks.WARPED_FUNGUS_CROP
            );

            valueLookupBuilder(BlockTags.SAPLINGS).add(
                    YavpmBlocks.APPLE_SAPLING,
                    YavpmBlocks.PERSIMMON_SAPLING,
                    YavpmBlocks.PRICKLE_SHOOT
            );
        }

        private void mineableTags() {
            valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                    .add(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.FLOWERING_APPLE_LEAVES, YavpmBlocks.PERSIMMON_LEAVES);

            valueLookupBuilder(BlockTags.SWORD_EFFICIENT).add(
                    YavpmBlocks.CANTALOUPE,
                    YavpmBlocks.CANTALOUPE_STEM,
                    YavpmBlocks.ATTACHED_CANTALOUPE_STEM
            );
            valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                    YavpmBlocks.FAKE_LOG,
                    YavpmBlocks.SHOJI,
                    YavpmBlocks.CHOPPING_BLOCK,
                    YavpmBlocks.ATTACHED_CANTALOUPE_STEM,
                    YavpmBlocks.CANTALOUPE_STEM,
                    YavpmBlocks.CANTALOUPE
            );


            // Polarized Glass isn't here because normal Glass does not have a required tool.
            // That's likely a bug, but I want to keep things consistent with vanilla.
            valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
                    YavpmBlocks.GRAPHITE_BLOCK,
                    YavpmBlocks.GRAPHENE_BLOCK,

                    YavpmBlocks.BURNER,

                    YavpmBlocks.COBBLED_GRANITE,
                    YavpmBlocks.COBBLED_DIORITE,
                    YavpmBlocks.COBBLED_ANDESITE,
                    YavpmBlocks.COBBLED_GRANITE_STAIRS,
                    YavpmBlocks.COBBLED_DIORITE_STAIRS,
                    YavpmBlocks.COBBLED_ANDESITE_STAIRS,
                    YavpmBlocks.COBBLED_GRANITE_SLAB,
                    YavpmBlocks.COBBLED_DIORITE_SLAB,
                    YavpmBlocks.COBBLED_ANDESITE_SLAB,
                    YavpmBlocks.COBBLED_GRANITE_WALL,
                    YavpmBlocks.COBBLED_DIORITE_WALL,
                    YavpmBlocks.COBBLED_ANDESITE_WALL,
                    YavpmBlocks.POLISHED_GRANITE_BRICKS,
                    YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_GRANITE_BRICK_WALL,
                    YavpmBlocks.POLISHED_DIORITE_BRICKS,
                    YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_DIORITE_BRICK_WALL,
                    YavpmBlocks.POLISHED_ANDESITE_BRICKS,
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL,

                    YavpmBlocks.NAHCOLITE_ORE,
                    YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE,

                    YavpmBlocks.KIMBERLITE,
                    YavpmBlocks.POLISHED_KIMBERLITE,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICKS,
                    YavpmBlocks.KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS,
                    YavpmBlocks.KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB,
                    YavpmBlocks.KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL,

                    YavpmBlocks.SCULKY_DEEPSLATE_BRICKS,

                    YavpmBlocks.INFESTED_COBBLED_DEEPSLATE,
                    YavpmBlocks.INFESTED_CHISELED_DEEPSLATE,
                    YavpmBlocks.INFESTED_DEEPSLATE_BRICKS,
                    YavpmBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
                    YavpmBlocks.INFESTED_SCULKY_DEEPSLATE_BRICKS,

                    YavpmBlocks.SOULSTONE,
                    YavpmBlocks.CUT_SOULSTONE,
                    YavpmBlocks.CHISELED_SOULSTONE,
                    YavpmBlocks.SMOOTH_SOULSTONE,
                    YavpmBlocks.SOULSTONE_SLAB,
                    YavpmBlocks.CUT_SOULSTONE_SLAB,
                    YavpmBlocks.SMOOTH_SOULSTONE_SLAB,
                    YavpmBlocks.SOULSTONE_STAIRS,
                    YavpmBlocks.SMOOTH_SOULSTONE_STAIRS,
                    YavpmBlocks.SOULSTONE_WALL,

                    YavpmBlocks.HARDENED_CONGLOMERATE,
                    YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS,
                    YavpmBlocks.HARDENED_CONGLOMERATE_SLAB,
                    YavpmBlocks.HARDENED_CONGLOMERATE_WALL,
                    YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS,
                    YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS,
                    YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB,
                    YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL,

                    YavpmBlocks.FAKE_ORE
            ); //.addOptional(makeId("recycler"));

            valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(
                    YavpmBlocks.CONGLOMERATE
            );

            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                    .add(
                            YavpmBlocks.NAHCOLITE_ORE,
                            YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE,
                            YavpmBlocks.KIMBERLITE,
                            YavpmBlocks.POLISHED_KIMBERLITE,
                            YavpmBlocks.POLISHED_KIMBERLITE_BRICKS,
                            YavpmBlocks.KIMBERLITE_STAIRS,
                            YavpmBlocks.POLISHED_KIMBERLITE_STAIRS,
                            YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS,
                            YavpmBlocks.KIMBERLITE_SLAB,
                            YavpmBlocks.POLISHED_KIMBERLITE_SLAB,
                            YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB,
                            YavpmBlocks.KIMBERLITE_WALL,
                            YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                            YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL
                    );

            valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            YavpmBlocks.GRAPHITE_BLOCK,
                            YavpmBlocks.GRAPHENE_BLOCK
                    );

            valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN
            );

        }

        private void generalBlockTags() {
            valueLookupBuilder(ConventionalBlockTags.ORES_IN_GROUND_STONE).add(YavpmBlocks.NAHCOLITE_ORE);
            valueLookupBuilder(ConventionalBlockTags.ORES_IN_GROUND_DEEPSLATE).add(YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE);

            valueLookupBuilder(BlockTags.WITHER_SUMMON_BASE_BLOCKS).add(
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
                    YavpmBlocks.SOULSTONE,
                    YavpmBlocks.CUT_SOULSTONE,
                    YavpmBlocks.CHISELED_SOULSTONE,
                    YavpmBlocks.SMOOTH_SOULSTONE
            );
            valueLookupBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
                    YavpmBlocks.SOULSTONE,
                    YavpmBlocks.CUT_SOULSTONE,
                    YavpmBlocks.CHISELED_SOULSTONE,
                    YavpmBlocks.SMOOTH_SOULSTONE
            );
            valueLookupBuilder(BlockTags.SOUL_SPEED_BLOCKS).add(
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
                    YavpmBlocks.SOULSTONE,
                    YavpmBlocks.CUT_SOULSTONE,
                    YavpmBlocks.CHISELED_SOULSTONE,
                    YavpmBlocks.SMOOTH_SOULSTONE,
                    YavpmBlocks.SOULSTONE_SLAB,
                    YavpmBlocks.CUT_SOULSTONE_SLAB,
                    YavpmBlocks.SMOOTH_SOULSTONE_SLAB,
                    YavpmBlocks.SOULSTONE_STAIRS,
                    YavpmBlocks.SMOOTH_SOULSTONE_STAIRS,
                    YavpmBlocks.SOULSTONE_WALL
            );

            valueLookupBuilder(BlockTags.SCULK_REPLACEABLE).add(
                    YavpmBlocks.SOULSTONE
            );

            valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE).add(YavpmBlocks.CANTALOUPE);

            valueLookupBuilder(BlockTags.STAIRS).add(
                    YavpmBlocks.KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS,

                    YavpmBlocks.SCULKY_DEEPSLATE_BRICK_STAIRS,

                    YavpmBlocks.SOULSTONE_STAIRS,
                    YavpmBlocks.SMOOTH_SOULSTONE_STAIRS
            );
            valueLookupBuilder(BlockTags.SLABS).add(
                    YavpmBlocks.KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB,

                    YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB,

                    YavpmBlocks.SOULSTONE_SLAB,
                    YavpmBlocks.CUT_SOULSTONE_SLAB,
                    YavpmBlocks.SMOOTH_SOULSTONE_SLAB
            );
            valueLookupBuilder(BlockTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL,

                    YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL,

                    YavpmBlocks.SOULSTONE_WALL
            );
            valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(
                    YavpmBlocks.APPLE_STAIRS,
                    YavpmBlocks.PERSIMMON_STAIRS,
                    YavpmBlocks.PRICKLE_STAIRS
            );
            valueLookupBuilder(BlockTags.WOODEN_SLABS).add(
                    YavpmBlocks.APPLE_SLAB,
                    YavpmBlocks.PERSIMMON_SLAB,
                    YavpmBlocks.PRICKLE_SLAB
            );

            valueLookupBuilder(BlockTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL
            );

            valueLookupBuilder(BlockTags.WOODEN_FENCES)
                    .add(YavpmBlocks.APPLE_FENCE, YavpmBlocks.PERSIMMON_FENCE, YavpmBlocks.PRICKLE_FENCE);

            valueLookupBuilder(BlockTags.FENCE_GATES)
                    .add(YavpmBlocks.APPLE_FENCE_GATE, YavpmBlocks.PERSIMMON_FENCE_GATE, YavpmBlocks.PRICKLE_FENCE_GATE);

            valueLookupBuilder(BlockTags.WOODEN_DOORS)
                    .add(YavpmBlocks.APPLE_DOOR, YavpmBlocks.PERSIMMON_DOOR, YavpmBlocks.PRICKLE_DOOR);

            valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(YavpmBlocks.APPLE_TRAPDOOR, YavpmBlocks.PERSIMMON_TRAPDOOR, YavpmBlocks.PRICKLE_TRAPDOOR);

            valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(YavpmBlocks.APPLE_PRESSURE_PLATE, YavpmBlocks.PERSIMMON_PRESSURE_PLATE, YavpmBlocks.PRICKLE_PRESSURE_PLATE);

            valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
                    .add(YavpmBlocks.APPLE_BUTTON, YavpmBlocks.PERSIMMON_BUTTON, YavpmBlocks.PRICKLE_BUTTON);
        }

        private void woodenBlockTags() {
            valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(
                    YavpmBlocks.STRIPPED_APPLE_LOG,
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG,
                    YavpmBlocks.STRIPPED_PRICKLE_LOG
            );
            valueLookupBuilder(ConventionalBlockTags.STRIPPED_WOODS).add(
                    YavpmBlocks.STRIPPED_APPLE_WOOD,
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD,
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD
            );

            valueLookupBuilder(YavpmTags.Blocks.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG,
                    YavpmBlocks.APPLE_WOOD,
                    YavpmBlocks.STRIPPED_APPLE_LOG,
                    YavpmBlocks.STRIPPED_APPLE_WOOD
            );
            valueLookupBuilder(YavpmTags.Blocks.PERSIMMON_LOGS).add(
                    YavpmBlocks.PERSIMMON_LOG,
                    YavpmBlocks.PERSIMMON_WOOD,
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG,
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD
            );
            valueLookupBuilder(YavpmTags.Blocks.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG,
                    YavpmBlocks.PRICKLE_WOOD,
                    YavpmBlocks.STRIPPED_PRICKLE_LOG,
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD
            );

            valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                    .forceAddTag(YavpmTags.Blocks.APPLE_LOGS)
                    .forceAddTag(YavpmTags.Blocks.PERSIMMON_LOGS)
                    .forceAddTag(YavpmTags.Blocks.PRICKLE_LOGS);

            valueLookupBuilder(BlockTags.LEAVES)
                    .add(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.FLOWERING_APPLE_LEAVES, YavpmBlocks.PERSIMMON_LEAVES);


            valueLookupBuilder(BlockTags.PLANKS)
                    .add(YavpmBlocks.APPLE_PLANKS, YavpmBlocks.PERSIMMON_PLANKS, YavpmBlocks.PRICKLE_PLANKS);
        }
    }

    public static class Fluid extends FabricTagProvider.FluidTagProvider {

        public Fluid(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            valueLookupBuilder(YavpmTags.Fluids.VOID_WATER).add(YavpmFluids.FLOWING_VOID_WATER, YavpmFluids.STILL_VOID_WATER);
            valueLookupBuilder(FluidTags.WATER).add(YavpmFluids.FLOWING_VOID_WATER, YavpmFluids.STILL_VOID_WATER);
        }
    }

    public static class EntityType extends FabricTagProvider.EntityTypeTagProvider {

        public EntityType(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {

            valueLookupBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                    YavpmEntities.CARBONFOWL
            );

            valueLookupBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE)
                    .forceAddTag(EntityTypeTags.ILLAGER)
                    .forceAddTag(EntityTypeTags.ILLAGER_FRIENDS);

            valueLookupBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_25).add(
                    net.minecraft.world.entity.EntityType.PHANTOM
            );
            valueLookupBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_50).add(
                    net.minecraft.world.entity.EntityType.ENDERMITE,
                    YavpmEntities.VOID_PHANTOM
            );
            valueLookupBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_75).add(
                    net.minecraft.world.entity.EntityType.ENDERMAN,
                    net.minecraft.world.entity.EntityType.SHULKER
            );
            valueLookupBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_100).add(
                    net.minecraft.world.entity.EntityType.ENDER_DRAGON
            );
        }
    }

    public static class DamageType extends FabricTagProvider<net.minecraft.world.damagesource.DamageType> {
        public DamageType(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.DAMAGE_TYPE, registriesFuture);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            builder(DamageTypeTags.NO_KNOCKBACK).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            builder(DamageTypeTags.BYPASSES_ARMOR).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            builder(DamageTypeTags.NO_IMPACT).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            builder(DamageTypeTags.WITHER_IMMUNE_TO).add(
                    YavpmDamageTypes.CUT
            );
            builder(DamageTypeTags.BYPASSES_EFFECTS).add(
                    YavpmDamageTypes.CUT
            );
            builder(DamageTypeTags.BYPASSES_INVULNERABILITY).add(
                    YavpmDamageTypes.CHOKE
            );
        }
    }

    public static class Biome extends FabricTagProvider<net.minecraft.world.level.biome.Biome> {
        public Biome(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            builder(BiomeTags.IS_END).add(YavpmBiomes.End.END_OASIS);

            builder(YavpmTags.Biomes.SPAWNS_CRIMSON_MOONGUS)
                    .add(Biomes.CRIMSON_FOREST);
            builder(YavpmTags.Biomes.SPAWNS_WARPED_MOONGUS)
                    .add(Biomes.WARPED_FOREST)
                    .forceAddTag(ConventionalBiomeTags.IS_END);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_SPRUCE)
                    .forceAddTag(ConventionalBiomeTags.IS_TAIGA)
                    .forceAddTag(ConventionalBiomeTags.IS_MOUNTAIN)
                    .forceAddTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_BIRCH)
                    .forceAddTag(ConventionalBiomeTags.IS_BIRCH_FOREST);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_JUNGLE)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE_TREE);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_ACACIA)
                    .forceAddTag(ConventionalBiomeTags.IS_SAVANNA)
                    .forceAddTag(ConventionalBiomeTags.IS_SAVANNA_TREE);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_CHERRY)
                    .forceAddTag(ConventionalBiomeTags.IS_FLORAL);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_DARK_OAK)
                    .add(Biomes.DARK_FOREST);

            builder(YavpmTags.Biomes.FAKE_LOG_IS_MANGROVE)
                    .forceAddTag(ConventionalBiomeTags.IS_SWAMP);
        }
    }

    public static class Enchantments extends FabricTagProvider<Enchantment> {

        public Enchantments(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, Registries.ENCHANTMENT, completableFuture);
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {

            builder(EnchantmentTags.NON_TREASURE).add(
                    YavpmEnchantments.CRITICAL_HIT,
                    YavpmEnchantments.ILLAGERS_BANE,
                    YavpmEnchantments.ENDERBANE,
                    YavpmEnchantments.FIGURE_EIGHT,
                    YavpmEnchantments.PARRY,
                    YavpmEnchantments.MAULING,
                    YavpmEnchantments.LAP_DOG,
                    YavpmEnchantments.COUNTER,
                    YavpmEnchantments.PLAGUE,
                    YavpmEnchantments.GALLOP,
                    YavpmEnchantments.BOUNDING
            );
            builder(EnchantmentTags.TREASURE).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.STIFFNESS,
                    YavpmEnchantments.BLEED_OUT,
                    YavpmEnchantments.CRUSHING
            );
            builder(EnchantmentTags.TRADEABLE).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.ENDERBANE
            );
            builder(EnchantmentTags.DOUBLE_TRADE_PRICE).add(
                    YavpmEnchantments.CRITICAL_HIT
            );
            builder(YavpmTags.Enchantments.END_ENCHANTMENTS).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.ENDERBANE,
                    YavpmEnchantments.STIFFNESS
            );
            builder(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE).add(
                    YavpmEnchantments.MAULING,
                    YavpmEnchantments.BLEED_OUT,
                    YavpmEnchantments.CRUSHING
            );
            builder(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE).add(
                    YavpmEnchantments.LAP_DOG,
                    YavpmEnchantments.COUNTER,
                    YavpmEnchantments.PLAGUE
            );

        }
    }
}
