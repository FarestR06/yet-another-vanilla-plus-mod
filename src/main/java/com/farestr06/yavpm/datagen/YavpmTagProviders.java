package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.*;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class YavpmTagProviders {
    public static class Item extends FabricTagProvider.ItemTagProvider {

        public Item(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            craftingTags();
            equipmentTags();
            foodTags();
            plantTags();
            buildingBlockTags();
        }
        private void craftingTags() {
            getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(
                    YavpmBlocks.COBBLED_GRANITE.asItem(),
                    YavpmBlocks.COBBLED_DIORITE.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE.asItem()
            );

            getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(
                    YavpmBlocks.COBBLED_GRANITE.asItem(),
                    YavpmBlocks.COBBLED_DIORITE.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.RUNES).add(
                    YavpmItems.RUNE_ATTACK,
                    YavpmItems.RUNE_DURABILITY,
                    YavpmItems.RUNE_SPEED
            );
            getOrCreateTagBuilder(ItemTags.DYEABLE).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
            getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
            getOrCreateTagBuilder(ConventionalItemTags.RAW_MATERIALS).add(
                    YavpmItems.RAW_DIAMOND,
                    YavpmBlocks.GRAPHENE_BLOCK.asItem()
            );

            getOrCreateTagBuilder(YavpmTags.Items.REACTOR_RECHARGERS).add(
                    Items.BLAZE_POWDER,
                    Items.BLAZE_ROD,
                    Items.FIRE_CHARGE,
                    Items.MAGMA_CREAM,
                    Items.MAGMA_BLOCK,
                    Items.LAVA_BUCKET
            );
            getOrCreateTagBuilder(YavpmTags.Items.RUNE_ATTACK_APPLICABLE)
                    .forceAddTag(ItemTags.SWORDS)
                    .forceAddTag(ItemTags.AXES);

            getOrCreateTagBuilder(YavpmTags.Items.RUNE_DURABILITY_APPLICABLE)
                    .forceAddTag(ItemTags.DURABILITY_ENCHANTABLE);

            getOrCreateTagBuilder(YavpmTags.Items.RUNE_SPEED_APPLICABLE)
                    .forceAddTag(ItemTags.SWORDS)
                    .forceAddTag(ItemTags.AXES)
                    .forceAddTag(ItemTags.HOES)
                    .forceAddTag(ItemTags.PICKAXES)
                    .forceAddTag(ItemTags.SHOVELS);
        }

        private void equipmentTags() {
            getOrCreateTagBuilder(ConventionalItemTags.ARMORS).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );

            getOrCreateTagBuilder(YavpmTags.Items.ENCHANTABLE_GLIDER).add(
                    Items.ELYTRA
            );
            getOrCreateTagBuilder(YavpmTags.Items.ENCHANTABLE_WOLF_ARMOR).add(
                    Items.WOLF_ARMOR
            );
            getOrCreateTagBuilder(YavpmTags.Items.ENCHANTABLE_HORSE_ARMOR).add(
                    Items.LEATHER_HORSE_ARMOR,
                    Items.GOLDEN_HORSE_ARMOR,
                    Items.IRON_HORSE_ARMOR,
                    Items.DIAMOND_HORSE_ARMOR
            );

            getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                    .add(YavpmItems.STUDDED_HELMET);
            getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                    .add(YavpmItems.STUDDED_CHESTPLATE);
            getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                    .add(YavpmItems.STUDDED_LEGGINGS);
            getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                    .add(YavpmItems.STUDDED_BOOTS);
            getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
        }

        private void foodTags() {
            getOrCreateTagBuilder(ConventionalItemTags.FOODS).add(
                    YavpmItems.BANANA,
                    YavpmItems.PEANUT,
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.COOKED_EGG,
                    YavpmItems.CHEESE,
                    YavpmItems.MOLY,
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmItems.MAGIC_BEAN,
                    YavpmItems.BEAN_TOAST,
                    YavpmItems.FAKE_BEEF,
                    YavpmItems.COOKED_FAKE_BEEF,
                    YavpmItems.TOFU,
                    YavpmItems.SUSHI,
                    YavpmItems.SEA_SOUP,
                    YavpmItems.CHICKEN_SOUP,
                    YavpmItems.FANCY_MUSHROOM_STEW
            );
            getOrCreateTagBuilder(ConventionalItemTags.MILK_BUCKETS).add(
                    YavpmItems.FAKE_MILK_BUCKET
            );
            getOrCreateTagBuilder(ConventionalItemTags.RAW_MEAT_FOODS).add(
                    YavpmItems.FAKE_BEEF
            );
            getOrCreateTagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).add(
                    YavpmItems.COOKED_FAKE_BEEF
            );
            getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS).add(
                    YavpmItems.BANANA
            );
            getOrCreateTagBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(
                    YavpmItems.MOLY,
                    YavpmItems.MAGIC_BEAN
            );
            getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS).add(
                    YavpmItems.SEA_SOUP,
                    YavpmItems.CHICKEN_SOUP,
                    YavpmItems.FANCY_MUSHROOM_STEW
            );
            getOrCreateTagBuilder(ConventionalItemTags.COOKIE_FOODS)
                    .add(YavpmItems.FORTUNE_COOKIE);
            getOrCreateTagBuilder(ConventionalItemTags.CANDY_FOODS)
                    .add(YavpmItems.CHOCOLATE);
            getOrCreateTagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(
                    YavpmItems.PEANUT
            );


            getOrCreateTagBuilder(ConventionalItemTags.ANIMAL_FOODS).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmItems.BANANA_SEEDS
            );
            getOrCreateTagBuilder(ItemTags.PARROT_POISONOUS_FOOD).add(
                    YavpmItems.CHOCOLATE
            );
            getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.BANANA_SEEDS
            );
            getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.BANANA_SEEDS
            );
            getOrCreateTagBuilder(ItemTags.PIG_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE
            );
            getOrCreateTagBuilder(ItemTags.WOLF_FOOD).add(
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.CHEESE,
                    YavpmItems.FAKE_BEEF,
                    YavpmItems.COOKED_FAKE_BEEF,
                    YavpmItems.TOFU
            );
            getOrCreateTagBuilder(YavpmTags.Items.TANUKI_FOOD)
                    .add(Items.GLOW_BERRIES)
                    .add(Items.SWEET_BERRIES);

            getOrCreateTagBuilder(YavpmTags.Items.CRIMSON_MOONGUS_FOOD).add(
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
                    Items.PHANTOM_MEMBRANE,
                    Items.BREEZE_ROD,
                    Items.SLIME_BLOCK,
                    Items.STONE,
                    Items.WITHER_SKELETON_SKULL,
                    Items.ENDER_EYE
            );
            getOrCreateTagBuilder(YavpmTags.Items.CRIMSON_MOONGUS_FOOD_CORRUPTED).add(
                    Items.SUGAR,
                    Items.RABBIT_FOOT,
                    Items.GLISTERING_MELON_SLICE,
                    Items.SPIDER_EYE,
                    Items.GOLDEN_CARROT,
                    Items.PUFFERFISH
            );
            getOrCreateTagBuilder(YavpmTags.Items.WARPED_MOONGUS_FOOD).add(
                    Items.FERMENTED_SPIDER_EYE,
                    Items.WITHER_SKELETON_SKULL,
                    Items.ENDER_EYE
            );
        }

        private void plantTags() {
            getOrCreateTagBuilder(ItemTags.LEAVES).add(YavpmBlocks.APPLE_LEAVES.asItem(), YavpmBlocks.PERSIMMON_LEAVES.asItem());

            getOrCreateTagBuilder(ConventionalItemTags.CROPS).add(
                    YavpmItems.ACORN,
                    Items.OAK_SAPLING,
                    YavpmItems.BANANA,
                    YavpmItems.BANANA_SEEDS,
                    YavpmItems.PEANUT,
                    YavpmItems.MAGIC_BEAN
            );

            getOrCreateTagBuilder(ItemTags.SAPLINGS).add(
                    YavpmBlocks.APPLE_SAPLING.asItem(),
                    YavpmBlocks.PERSIMMON_SAPLING.asItem(),
                    YavpmBlocks.PRICKLE_SHOOT.asItem()
            );
        }

        private void buildingBlockTags() {
            getOrCreateTagBuilder(ConventionalItemTags.OBSIDIANS).add(
                    YavpmBlocks.GLOWING_OBSIDIAN.asItem(),
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN.asItem()
            );

            getOrCreateTagBuilder(ItemTags.STAIRS).add(
                    YavpmBlocks.KIMBERLITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS.asItem()
            );
            getOrCreateTagBuilder(ItemTags.SLABS).add(
                    YavpmBlocks.KIMBERLITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB.asItem()
            );
            getOrCreateTagBuilder(ItemTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL.asItem(),
                    YavpmBlocks.COBBLED_GRANITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL.asItem()
            );

            getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_LOGS).add(
                    YavpmBlocks.STRIPPED_APPLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_LOG.asItem()
            );
            getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_WOODS).add(
                    YavpmBlocks.STRIPPED_APPLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG.asItem(),
                    YavpmBlocks.APPLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_WOOD.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.PERSIMMON_LOGS).add(
                    YavpmBlocks.PERSIMMON_LOG.asItem(),
                    YavpmBlocks.PERSIMMON_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG.asItem(),
                    YavpmBlocks.PRICKLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD.asItem()
            );
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                    .forceAddTag(YavpmTags.Items.APPLE_LOGS)
                    .forceAddTag(YavpmTags.Items.PERSIMMON_LOGS)
                    .forceAddTag(YavpmTags.Items.PRICKLE_LOGS);

            getOrCreateTagBuilder(ItemTags.PLANKS).add(
                    YavpmBlocks.APPLE_PLANKS.asItem(),
                    YavpmBlocks.PERSIMMON_PLANKS.asItem(),
                    YavpmBlocks.PRICKLE_PLANKS.asItem()
            );

            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(YavpmBlocks.APPLE_STAIRS.asItem(), YavpmBlocks.PERSIMMON_STAIRS.asItem(), YavpmBlocks.PRICKLE_STAIRS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(YavpmBlocks.APPLE_SLAB.asItem(), YavpmBlocks.PERSIMMON_SLAB.asItem(), YavpmBlocks.PRICKLE_SLAB.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(YavpmBlocks.APPLE_FENCE.asItem(), YavpmBlocks.PERSIMMON_FENCE.asItem(), YavpmBlocks.PRICKLE_FENCE.asItem());
            getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(YavpmBlocks.APPLE_FENCE_GATE.asItem(), YavpmBlocks.PERSIMMON_FENCE_GATE.asItem(), YavpmBlocks.PRICKLE_FENCE_GATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(YavpmBlocks.APPLE_DOOR.asItem() ,YavpmBlocks.PERSIMMON_DOOR.asItem(), YavpmBlocks.PRICKLE_DOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(YavpmBlocks.APPLE_TRAPDOOR.asItem(), YavpmBlocks.PERSIMMON_TRAPDOOR.asItem(), YavpmBlocks.PRICKLE_TRAPDOOR.asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(
                    YavpmBlocks.APPLE_PRESSURE_PLATE.asItem(),
                    YavpmBlocks.PERSIMMON_PRESSURE_PLATE.asItem(),
                    YavpmBlocks.PRICKLE_PRESSURE_PLATE.asItem()
            );
            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(
                    YavpmBlocks.APPLE_BUTTON.asItem(),
                    YavpmBlocks.PERSIMMON_BUTTON.asItem(),
                    YavpmBlocks.PRICKLE_BUTTON.asItem()
            );
        }
    }

    public static class Block extends FabricTagProvider.BlockTagProvider {
        public Block(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS).add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);
            getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            getOrCreateTagBuilder(ConventionalBlockTags.OBSIDIANS).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN
            );

            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(YavpmBlocks.FAKE_LOG);

            getOrCreateTagBuilder(BlockTags.IMPERMEABLE)
                    .add(YavpmBlocks.POLARIZED_GLASS);

            plantTags();
            mineableTags();
            generalBlockTags();
            woodenBlockTags();
        }
        private void plantTags() {
            getOrCreateTagBuilder(BlockTags.CROPS).add(
                    YavpmBlocks.BANANA_CROP,
                    YavpmBlocks.PEANUT_CROP,
                    YavpmBlocks.MAGIC_BEAN_CROP,
                    YavpmBlocks.WARPED_WART,
                    YavpmBlocks.OAK_SAPLING_CROP
            );
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(
                    YavpmBlocks.APPLE_SAPLING,
                    YavpmBlocks.PERSIMMON_SAPLING,
                    YavpmBlocks.PRICKLE_SHOOT
            );
        }

        private void mineableTags() {
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                    .add(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.PERSIMMON_LEAVES);

            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                    YavpmBlocks.FAKE_LOG
            );

            // Polarized Glass isn't here because normal Glass does not have a required tool.
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
                    YavpmBlocks.GRAPHITE_BLOCK,
                    YavpmBlocks.GRAPHENE_BLOCK,

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
                    YavpmBlocks.FAKE_ORE
            );

            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                    .add(
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

            getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            YavpmBlocks.GRAPHITE_BLOCK,
                            YavpmBlocks.GRAPHENE_BLOCK
                    );

            getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN
            );

        }

        private void generalBlockTags() {
            getOrCreateTagBuilder(BlockTags.STAIRS).add(
                    YavpmBlocks.KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_STAIRS,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS
            );
            getOrCreateTagBuilder(BlockTags.SLABS).add(
                    YavpmBlocks.KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_SLAB,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB
            );
            getOrCreateTagBuilder(BlockTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL
            );
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(
                    YavpmBlocks.APPLE_STAIRS,
                    YavpmBlocks.PERSIMMON_STAIRS,
                    YavpmBlocks.PRICKLE_STAIRS
            );
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(
                    YavpmBlocks.APPLE_SLAB,
                    YavpmBlocks.PERSIMMON_SLAB,
                    YavpmBlocks.PRICKLE_SLAB
            );

            getOrCreateTagBuilder(BlockTags.WALLS).add(
                    YavpmBlocks.KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_WALL,
                    YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL
            );

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                    .add(YavpmBlocks.APPLE_FENCE, YavpmBlocks.PERSIMMON_FENCE, YavpmBlocks.PRICKLE_FENCE);

            getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                    .add(YavpmBlocks.APPLE_FENCE_GATE, YavpmBlocks.PERSIMMON_FENCE_GATE, YavpmBlocks.PRICKLE_FENCE_GATE);

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                    .add(YavpmBlocks.APPLE_DOOR, YavpmBlocks.PERSIMMON_DOOR, YavpmBlocks.PRICKLE_DOOR);

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(YavpmBlocks.APPLE_TRAPDOOR, YavpmBlocks.PERSIMMON_TRAPDOOR, YavpmBlocks.PRICKLE_TRAPDOOR);

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(YavpmBlocks.APPLE_PRESSURE_PLATE, YavpmBlocks.PERSIMMON_PRESSURE_PLATE, YavpmBlocks.PRICKLE_PRESSURE_PLATE);

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                    .add(YavpmBlocks.APPLE_BUTTON, YavpmBlocks.PERSIMMON_BUTTON, YavpmBlocks.PRICKLE_BUTTON);
        }

        private void woodenBlockTags() {
            getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(
                    YavpmBlocks.STRIPPED_APPLE_LOG,
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG,
                    YavpmBlocks.STRIPPED_PRICKLE_LOG
            );
            getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_WOODS).add(
                    YavpmBlocks.STRIPPED_APPLE_WOOD,
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD,
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD
            );

            getOrCreateTagBuilder(YavpmTags.Blocks.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG,
                    YavpmBlocks.APPLE_WOOD,
                    YavpmBlocks.STRIPPED_APPLE_LOG,
                    YavpmBlocks.STRIPPED_APPLE_WOOD
            );
            getOrCreateTagBuilder(YavpmTags.Blocks.PERSIMMON_LOGS).add(
                    YavpmBlocks.PERSIMMON_LOG,
                    YavpmBlocks.PERSIMMON_WOOD,
                    YavpmBlocks.STRIPPED_PERSIMMON_LOG,
                    YavpmBlocks.STRIPPED_PERSIMMON_WOOD
            );
            getOrCreateTagBuilder(YavpmTags.Blocks.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG,
                    YavpmBlocks.PRICKLE_WOOD,
                    YavpmBlocks.STRIPPED_PRICKLE_LOG,
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD
            );

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .forceAddTag(YavpmTags.Blocks.APPLE_LOGS)
                    .forceAddTag(YavpmTags.Blocks.PERSIMMON_LOGS)
                    .forceAddTag(YavpmTags.Blocks.PRICKLE_LOGS);

            getOrCreateTagBuilder(BlockTags.LEAVES)
                    .add(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.PERSIMMON_LEAVES);


            getOrCreateTagBuilder(BlockTags.PLANKS)
                    .add(YavpmBlocks.APPLE_PLANKS, YavpmBlocks.PERSIMMON_PLANKS, YavpmBlocks.PRICKLE_PLANKS);
        }
    }

    public static class Fluid extends FabricTagProvider.FluidTagProvider {

        public Fluid(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(YavpmTags.Fluids.VOID_WATER).add(YavpmFluids.FLOWING_VOID_WATER, YavpmFluids.STILL_VOID_WATER);
            getOrCreateTagBuilder(FluidTags.WATER).add(YavpmFluids.FLOWING_VOID_WATER, YavpmFluids.STILL_VOID_WATER);
        }
    }

    public static class EntityType extends FabricTagProvider.EntityTypeTagProvider {

        public EntityType(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

            getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                    YavpmEntities.CARBONFOWL
            );

            getOrCreateTagBuilder(YavpmTags.EntityTypes.HUMANOID_ZOMBIES).add(
                    net.minecraft.entity.EntityType.ZOMBIE,
                    net.minecraft.entity.EntityType.ZOMBIE_VILLAGER,
                    net.minecraft.entity.EntityType.HUSK,
                    net.minecraft.entity.EntityType.DROWNED
            );
            getOrCreateTagBuilder(YavpmTags.EntityTypes.HUMANOID_SKELETONS).add(
                    net.minecraft.entity.EntityType.SKELETON,
                    net.minecraft.entity.EntityType.STRAY,
                    net.minecraft.entity.EntityType.BOGGED,
                    net.minecraft.entity.EntityType.WITHER_SKELETON
            );

            getOrCreateTagBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ILLAGERS_BANE)
                    .forceAddTag(EntityTypeTags.ILLAGER)
                    .forceAddTag(EntityTypeTags.ILLAGER_FRIENDS);

            getOrCreateTagBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_25).add(
                    net.minecraft.entity.EntityType.PHANTOM
            );
            getOrCreateTagBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_50).add(
                    net.minecraft.entity.EntityType.ENDERMITE,
                    YavpmEntities.VOID_PHANTOM
            );
            getOrCreateTagBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_75).add(
                    net.minecraft.entity.EntityType.ENDERMAN,
                    net.minecraft.entity.EntityType.SHULKER
            );
            getOrCreateTagBuilder(YavpmTags.EntityTypes.SENSITIVE_TO_ENDERBANE_100).add(
                    net.minecraft.entity.EntityType.ENDER_DRAGON
            );
        }
    }

    public static class DamageType extends FabricTagProvider<net.minecraft.entity.damage.DamageType> {
        public DamageType(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            getOrCreateTagBuilder(DamageTypeTags.NO_IMPACT).add(
                    YavpmDamageTypes.CUT,
                    YavpmDamageTypes.CHOKE
            );
            getOrCreateTagBuilder(DamageTypeTags.WITHER_IMMUNE_TO).add(
                    YavpmDamageTypes.CUT
            );
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_EFFECTS).add(
                    YavpmDamageTypes.CUT
            );
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_INVULNERABILITY).add(
                    YavpmDamageTypes.CHOKE
            );
        }
    }

    public static class Biome extends FabricTagProvider<net.minecraft.world.biome.Biome> {
        public Biome(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.BIOME, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(YavpmTags.Biomes.SPAWNS_CRIMSON_MOONGUS)
                    .add(BiomeKeys.CRIMSON_FOREST);
            getOrCreateTagBuilder(YavpmTags.Biomes.SPAWNS_WARPED_MOONGUS)
                    .add(BiomeKeys.WARPED_FOREST)
                    .forceAddTag(ConventionalBiomeTags.IS_END);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_SPRUCE)
                    .forceAddTag(ConventionalBiomeTags.IS_TAIGA)
                    .forceAddTag(ConventionalBiomeTags.IS_MOUNTAIN)
                    .forceAddTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_BIRCH)
                    .forceAddTag(ConventionalBiomeTags.IS_BIRCH_FOREST);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_JUNGLE)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE_TREE);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_ACACIA)
                    .forceAddTag(ConventionalBiomeTags.IS_SAVANNA)
                    .forceAddTag(ConventionalBiomeTags.IS_SAVANNA_TREE);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_CHERRY)
                    .forceAddTag(ConventionalBiomeTags.IS_FLORAL);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_DARK_OAK)
                    .add(BiomeKeys.DARK_FOREST);

            getOrCreateTagBuilder(YavpmTags.Biomes.FAKE_LOG_IS_MANGROVE)
                    .forceAddTag(ConventionalBiomeTags.IS_SWAMP);
        }
    }

    public static class Enchantment extends FabricTagProvider.EnchantmentTagProvider {

        public Enchantment(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE).add(
                    YavpmEnchantments.CRITICAL_HIT,
                    YavpmEnchantments.ILLAGERS_BANE,
                    YavpmEnchantments.ENDERBANE,
                    YavpmEnchantments.PARRY,
                    YavpmEnchantments.MAULING,
                    YavpmEnchantments.LAP_DOG,
                    YavpmEnchantments.COUNTER,
                    YavpmEnchantments.PLAGUE,
                    YavpmEnchantments.GALLOP,
                    YavpmEnchantments.BOUNDING
            );
            getOrCreateTagBuilder(EnchantmentTags.TREASURE).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.STIFFNESS,
                    YavpmEnchantments.BLEED_OUT,
                    YavpmEnchantments.CRUSHING
            );
            getOrCreateTagBuilder(EnchantmentTags.TRADEABLE).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.ENDERBANE
            );
            getOrCreateTagBuilder(EnchantmentTags.DOUBLE_TRADE_PRICE).add(
                    YavpmEnchantments.CRITICAL_HIT
            );
            getOrCreateTagBuilder(YavpmTags.Enchantments.END_ENCHANTMENTS).add(
                    YavpmEnchantments.VOID_STRIKE,
                    YavpmEnchantments.ENDERBANE,
                    YavpmEnchantments.STIFFNESS
            );
            getOrCreateTagBuilder(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_OFFENSE).add(
                    YavpmEnchantments.MAULING,
                    YavpmEnchantments.BLEED_OUT,
                    YavpmEnchantments.CRUSHING
            );
            getOrCreateTagBuilder(YavpmTags.Enchantments.EXCLUSIVE_SET_WOLF_ARMOR_DEFENSE).add(
                    YavpmEnchantments.LAP_DOG,
                    YavpmEnchantments.COUNTER,
                    YavpmEnchantments.PLAGUE
            );
        }
    }
}
