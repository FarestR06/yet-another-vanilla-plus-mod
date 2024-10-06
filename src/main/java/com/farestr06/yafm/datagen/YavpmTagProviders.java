package com.farestr06.yafm.datagen;

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
            super(output, completableFuture);
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
