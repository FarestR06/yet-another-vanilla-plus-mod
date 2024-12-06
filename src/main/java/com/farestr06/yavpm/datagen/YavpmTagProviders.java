package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class YavpmTagProviders {
    public static class Item extends FabricTagProvider.ItemTagProvider {

        public Item(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                    .add(YavpmItems.STUDDED_HELMET);
            getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                    .add(YavpmItems.STUDDED_CHESTPLATE);
            getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                    .add(YavpmItems.STUDDED_LEGGINGS);
            getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                    .add(YavpmItems.STUDDED_BOOTS);

            getOrCreateTagBuilder(YavpmTags.Items.CRIMSON_MOONGUS_FOOD)
                    .add(
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
            getOrCreateTagBuilder(YavpmTags.Items.WARPED_MOONGUS_FOOD).add(
                    Items.FERMENTED_SPIDER_EYE,
                    Items.WITHER_SKELETON_SKULL,
                    Items.ENDER_EYE
            );
            getOrCreateTagBuilder(YavpmTags.Items.REACTOR_RECHARGERS)
                    .add(
                            Items.BLAZE_POWDER,
                            Items.BLAZE_ROD,
                            Items.FIRE_CHARGE,
                            Items.MAGMA_CREAM,
                            Items.MAGMA_BLOCK,
                            Items.LAVA_BUCKET
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

            getOrCreateTagBuilder(ConventionalItemTags.FOODS).add(
                    YavpmItems.BANANA,
                    YavpmItems.PEANUT,
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.MOLY,
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmItems.MAGIC_BEAN,
                    YavpmItems.SEA_SOUP
            );
            getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS).add(
                    YavpmItems.BANANA
            );
            getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS).add(
                    YavpmItems.SEA_SOUP
            );
            getOrCreateTagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(
                    YavpmItems.PEANUT
            );
            getOrCreateTagBuilder(ConventionalItemTags.ANIMAL_FOODS).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN,
                    YavpmItems.TRUFFLE,
                    YavpmItems.BANANA_SEEDS
            );
            getOrCreateTagBuilder(ConventionalItemTags.CROPS).add(
                    YavpmItems.ACORN,
                    YavpmItems.BANANA_SEEDS
            );

            getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
            );
            getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                    YavpmItems.STUDDED_HELMET,
                    YavpmItems.STUDDED_CHESTPLATE,
                    YavpmItems.STUDDED_LEGGINGS,
                    YavpmItems.STUDDED_BOOTS
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

            getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(
                    YavpmBlocks.COBBLED_GRANITE.asItem(),
                    YavpmBlocks.COBBLED_DIORITE.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE.asItem()
            );

            getOrCreateTagBuilder(ItemTags.STAIRS).add(
                    YavpmBlocks.COBBLED_GRANITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_STAIRS.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_TILE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_TILE_STAIRS.asItem()
            );
            getOrCreateTagBuilder(ItemTags.SLABS).add(
                    YavpmBlocks.COBBLED_GRANITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_SLAB.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_TILE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_TILE_SLAB.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_TILE_SLAB.asItem()
            );
            getOrCreateTagBuilder(ItemTags.WALLS).add(
                    YavpmBlocks.COBBLED_GRANITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_DIORITE_WALL.asItem(),
                    YavpmBlocks.COBBLED_ANDESITE_WALL.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL.asItem(),
                    YavpmBlocks.POLISHED_GRANITE_TILE_WALL.asItem(),
                    YavpmBlocks.POLISHED_DIORITE_TILE_WALL.asItem(),
                    YavpmBlocks.POLISHED_ANDESITE_TILE_WALL.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG.asItem(),
                    YavpmBlocks.APPLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_APPLE_WOOD.asItem()
            );
            getOrCreateTagBuilder(YavpmTags.Items.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG.asItem(),
                    YavpmBlocks.PRICKLE_WOOD.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_LOG.asItem(),
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD.asItem()
            );
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(YavpmTags.Items.APPLE_LOGS).forceAddTag(YavpmTags.Items.PRICKLE_LOGS);
            getOrCreateTagBuilder(ItemTags.LEAVES).add(YavpmBlocks.APPLE_LEAVES.asItem());
            getOrCreateTagBuilder(ItemTags.PLANKS).add(YavpmBlocks.APPLE_PLANKS.asItem(), YavpmBlocks.PRICKLE_PLANKS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(YavpmBlocks.APPLE_STAIRS.asItem(), YavpmBlocks.PRICKLE_STAIRS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(YavpmBlocks.APPLE_SLAB.asItem(), YavpmBlocks.PRICKLE_SLAB.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(YavpmBlocks.APPLE_FENCE.asItem(), YavpmBlocks.PRICKLE_FENCE.asItem());
            getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(YavpmBlocks.APPLE_FENCE_GATE.asItem(), YavpmBlocks.PRICKLE_FENCE_GATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(YavpmBlocks.APPLE_DOOR.asItem(), YavpmBlocks.PRICKLE_DOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(YavpmBlocks.APPLE_TRAPDOOR.asItem(), YavpmBlocks.PRICKLE_TRAPDOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(YavpmBlocks.APPLE_PRESSURE_PLATE.asItem(), YavpmBlocks.PRICKLE_PRESSURE_PLATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(YavpmBlocks.APPLE_BUTTON.asItem(), YavpmBlocks.PRICKLE_BUTTON.asItem());
            getOrCreateTagBuilder(ItemTags.SIGNS).add(YavpmItems.APPLE_SIGN, YavpmItems.PRICKLE_SIGN.asItem());
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(YavpmItems.APPLE_HANGING_SIGN, YavpmItems.PRICKLE_HANGING_SIGN.asItem());
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


            getOrCreateTagBuilder(YavpmTags.Blocks.APPLE_LOGS).add(
                    YavpmBlocks.APPLE_LOG,
                    YavpmBlocks.APPLE_WOOD,
                    YavpmBlocks.STRIPPED_APPLE_LOG,
                    YavpmBlocks.STRIPPED_APPLE_WOOD
            );
            getOrCreateTagBuilder(YavpmTags.Blocks.PRICKLE_LOGS).add(
                    YavpmBlocks.PRICKLE_LOG,
                    YavpmBlocks.PRICKLE_WOOD,
                    YavpmBlocks.STRIPPED_PRICKLE_LOG,
                    YavpmBlocks.STRIPPED_PRICKLE_WOOD
            );
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).forceAddTag(YavpmTags.Blocks.APPLE_LOGS).forceAddTag(YavpmTags.Blocks.PRICKLE_LOGS);

            // Electro Glass isn't here because normal Glass does not have a required tool.
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN,
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
                    YavpmBlocks.POLISHED_GRANITE_TILES,
                    YavpmBlocks.POLISHED_GRANITE_TILE_STAIRS,
                    YavpmBlocks.POLISHED_GRANITE_TILE_SLAB,
                    YavpmBlocks.POLISHED_GRANITE_TILE_WALL,
                    YavpmBlocks.POLISHED_DIORITE_TILES,
                    YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS,
                    YavpmBlocks.POLISHED_DIORITE_TILE_SLAB,
                    YavpmBlocks.POLISHED_DIORITE_TILE_WALL,
                    YavpmBlocks.POLISHED_ANDESITE_TILES,
                    YavpmBlocks.POLISHED_ANDESITE_TILE_STAIRS,
                    YavpmBlocks.POLISHED_ANDESITE_TILE_SLAB,
                    YavpmBlocks.POLISHED_ANDESITE_TILE_WALL
            );

            getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            YavpmBlocks.NETHER_REACTOR_CORE,
                            YavpmBlocks.GRAPHITE_BLOCK,
                            YavpmBlocks.GRAPHENE_BLOCK
                    );

            getOrCreateTagBuilder(BlockTags.CROPS).add(
                    YavpmBlocks.BANANA_CROP,
                    YavpmBlocks.PEANUT_CROP,
                    YavpmBlocks.OAK_SAPLING_CROP
            );

            getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                    YavpmBlocks.GLOWING_OBSIDIAN,
                    YavpmBlocks.SOUL_GLOWING_OBSIDIAN
            );


            getOrCreateTagBuilder(BlockTags.STONE_BRICKS).add(
                    YavpmBlocks.POLISHED_GRANITE_BRICKS,
                    YavpmBlocks.POLISHED_DIORITE_BRICKS,
                    YavpmBlocks.POLISHED_ANDESITE_BRICKS
            );

            getOrCreateTagBuilder(BlockTags.STAIRS).add(
                    YavpmBlocks.COBBLED_GRANITE_STAIRS,
                    YavpmBlocks.COBBLED_DIORITE_STAIRS,
                    YavpmBlocks.COBBLED_ANDESITE_STAIRS,
                    YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS,
                    YavpmBlocks.POLISHED_GRANITE_TILE_STAIRS,
                    YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS,
                    YavpmBlocks.POLISHED_ANDESITE_TILE_STAIRS
            );
            getOrCreateTagBuilder(BlockTags.SLABS).add(
                    YavpmBlocks.COBBLED_GRANITE_SLAB,
                    YavpmBlocks.COBBLED_DIORITE_SLAB,
                    YavpmBlocks.COBBLED_ANDESITE_SLAB,
                    YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB,
                    YavpmBlocks.POLISHED_GRANITE_TILE_SLAB,
                    YavpmBlocks.POLISHED_DIORITE_TILE_SLAB,
                    YavpmBlocks.POLISHED_ANDESITE_TILE_SLAB
            );

             getOrCreateTagBuilder(BlockTags.WALLS).add(
                     YavpmBlocks.COBBLED_GRANITE_WALL,
                     YavpmBlocks.COBBLED_DIORITE_WALL,
                     YavpmBlocks.COBBLED_ANDESITE_WALL,
                     YavpmBlocks.POLISHED_GRANITE_BRICK_WALL,
                     YavpmBlocks.POLISHED_DIORITE_BRICK_WALL,
                     YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL,
                     YavpmBlocks.POLISHED_GRANITE_TILE_WALL,
                     YavpmBlocks.POLISHED_DIORITE_TILE_WALL,
                     YavpmBlocks.POLISHED_ANDESITE_TILE_WALL
             );

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .add(
                            YavpmBlocks.APPLE_LOG,
                            YavpmBlocks.STRIPPED_APPLE_LOG,
                            YavpmBlocks.APPLE_WOOD,
                            YavpmBlocks.STRIPPED_APPLE_WOOD
                    );

            getOrCreateTagBuilder(BlockTags.LEAVES)
                    .add(YavpmBlocks.APPLE_LEAVES);

            getOrCreateTagBuilder(BlockTags.PLANKS)
                    .add(YavpmBlocks.APPLE_PLANKS, YavpmBlocks.PRICKLE_PLANKS);

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                    .add(YavpmBlocks.APPLE_FENCE, YavpmBlocks.PRICKLE_FENCE);

            getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                    .add(YavpmBlocks.APPLE_FENCE_GATE, YavpmBlocks.PRICKLE_FENCE_GATE);

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                    .add(YavpmBlocks.APPLE_DOOR, YavpmBlocks.PRICKLE_DOOR);

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(YavpmBlocks.APPLE_TRAPDOOR, YavpmBlocks.PRICKLE_TRAPDOOR);

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                    .add(YavpmBlocks.APPLE_SIGN, YavpmBlocks.PRICKLE_SIGN);

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                    .add(YavpmBlocks.APPLE_WALL_SIGN, YavpmBlocks.PRICKLE_WALL_SIGN);

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                    .add(YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.PRICKLE_HANGING_SIGN);

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                    .add(YavpmBlocks.APPLE_WALL_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN);
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
                    YavpmMobs.CARBONFOWL
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
        }
    }
}
