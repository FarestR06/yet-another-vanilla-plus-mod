package com.farestr06.yafm.datagen;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.YavpmItems;
import com.farestr06.yafm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static com.farestr06.yafm.block.YavpmBlocks.*;

public class YavpmTagProviders {
    public static class Item extends FabricTagProvider.ItemTagProvider {

        public Item(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture, new Block(output, completableFuture));
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
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
                    YavpmItems.DIAMOND_ACORN
            );

            getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN
            );

            getOrCreateTagBuilder(ItemTags.PIG_FOOD).add(
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN
            );

            getOrCreateTagBuilder(ConventionalItemTags.FOODS).add(
                    YavpmItems.BANANA,
                    YavpmItems.PEANUT,
                    YavpmItems.COOKED_PEANUT,
                    YavpmItems.MOLY,
                    YavpmItems.ACORN,
                    YavpmItems.DIAMOND_ACORN
            );
            getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS).add(
                    YavpmItems.BANANA
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

            getOrCreateTagBuilder(YavpmTags.Items.APPLE_LOGS).add(
                    APPLE_LOG.asItem(),
                    STRIPPED_APPLE_LOG.asItem(),
                    APPLE_WOOD.asItem(),
                    STRIPPED_APPLE_WOOD.asItem()
            );

            getOrCreateTagBuilder(ItemTags.PLANKS).add(APPLE_PLANKS.asItem());
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(YavpmTags.Items.APPLE_LOGS);
            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(APPLE_FENCE.asItem());
            getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(APPLE_FENCE_GATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(APPLE_DOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(APPLE_TRAPDOOR.asItem());
            getOrCreateTagBuilder(ItemTags.SIGNS).add(YavpmItems.APPLE_SIGN);
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(YavpmItems.APPLE_HANGING_SIGN);
            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(APPLE_BUTTON.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(APPLE_PRESSURE_PLATE.asItem());
            getOrCreateTagBuilder(ItemTags.BOATS).add(YavpmItems.APPLE_BOAT);
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(YavpmItems.APPLE_CHEST_BOAT);
        }
    }

    public static class Block extends FabricTagProvider.BlockTagProvider {
        public Block(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS).add(SOUL_GLOWING_OBSIDIAN);
            getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(SOUL_GLOWING_OBSIDIAN);

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                    GLOWING_OBSIDIAN,
                    SOUL_GLOWING_OBSIDIAN,
                    POLISHED_GRANITE_BRICKS,
                    POLISHED_GRANITE_BRICK_STAIRS,
                    POLISHED_GRANITE_BRICK_SLAB,
                    POLISHED_GRANITE_BRICK_WALL,
                    POLISHED_DIORITE_BRICKS,
                    POLISHED_DIORITE_BRICK_STAIRS,
                    POLISHED_DIORITE_BRICK_SLAB,
                    POLISHED_DIORITE_BRICK_WALL,
                    POLISHED_ANDESITE_BRICKS,
                    POLISHED_ANDESITE_BRICK_STAIRS,
                    POLISHED_ANDESITE_BRICK_SLAB,
                    POLISHED_ANDESITE_BRICK_WALL
            );

            getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                    GLOWING_OBSIDIAN,
                    SOUL_GLOWING_OBSIDIAN
            );

            getOrCreateTagBuilder(BlockTags.WALLS).add(
                    POLISHED_GRANITE_BRICK_WALL,
                    POLISHED_DIORITE_BRICK_WALL,
                    POLISHED_ANDESITE_BRICK_WALL
            );

            getOrCreateTagBuilder(YavpmTags.Blocks.APPLE_LOGS).add(
                    APPLE_LOG,
                    STRIPPED_APPLE_LOG,
                    APPLE_WOOD,
                    STRIPPED_APPLE_WOOD
            );

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).forceAddTag(YavpmTags.Blocks.APPLE_LOGS);


            getOrCreateTagBuilder(BlockTags.PLANKS)
                    .add(APPLE_PLANKS);

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                    .add(APPLE_FENCE);

            getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                    .add(APPLE_FENCE_GATE);

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                    .add(APPLE_DOOR);

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(APPLE_TRAPDOOR);

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                    .add(APPLE_BUTTON);

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(APPLE_PRESSURE_PLATE);

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                    .add(APPLE_SIGN);

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                    .add(APPLE_WALL_SIGN);

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                    .add(APPLE_HANGING_SIGN);

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                    .add(APPLE_WALL_HANGING_SIGN);
        }
    }

    public static class EntityType extends FabricTagProvider.EntityTypeTagProvider {

        public EntityType(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
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
