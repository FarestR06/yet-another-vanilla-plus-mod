package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.crop.*;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class YavpmLootProviders {
    public static class Block extends FabricBlockLootTableProvider {

        final HolderLookup.RegistryLookup<Enchantment> lookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

        protected Block(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            dropSelf(YavpmBlocks.GLOWING_OBSIDIAN);
            dropSelf(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            stoneVariantDrops();
            add(YavpmBlocks.NAHCOLITE_ORE, this::nahcoliteOreDrops);
            add(YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE, this::nahcoliteOreDrops);
            dropSelf(YavpmBlocks.GRAPHITE_BLOCK);
            dropSelf(YavpmBlocks.GRAPHENE_BLOCK);

            dropSelf(YavpmBlocks.DENSITITE_BLOCK);

            dropWhenSilkTouch(YavpmBlocks.POLARIZED_GLASS);
            dropSelf(YavpmBlocks.RECYCLER);
            dropSelf(YavpmBlocks.BURNER);
            dropSelf(YavpmBlocks.NULL_TORCH);

            cropDrops();

            dropSelf(YavpmBlocks.SHOJI);
            dropSelf(YavpmBlocks.CHOPPING_BLOCK);
            appleDrops();
            persimmonDrops();
            prickleDrops();

            fakeDrops();

            dropWhenSilkTouch(YavpmBlocks.PINATA);
        }

        private void fakeDrops() {
            add(YavpmBlocks.FAKE_LOG, LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                                    .add(LootItem.lootTableItem(Items.ACACIA_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.BIRCH_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.CHERRY_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.DARK_OAK_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.JUNGLE_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.OAK_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.MANGROVE_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(Items.SPRUCE_PLANKS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(YavpmBlocks.APPLE_PLANKS.asItem())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                                    .add(LootItem.lootTableItem(YavpmBlocks.PERSIMMON_PLANKS.asItem())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                                    )
                    )
            );
            add(YavpmBlocks.FAKE_ORE, LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                                    .add(LootItem.lootTableItem(Items.COAL)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.RAW_COPPER)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.RAW_IRON)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.RAW_GOLD)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.LAPIS_LAZULI)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.DIAMOND)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.EMERALD)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    ).add(LootItem.lootTableItem(Items.REDSTONE)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    )
                    )
            );
        }

        private void cropDrops() {
            add(
                    YavpmBlocks.BITTER_BERRY_BUSH,
                    (net.minecraft.world.level.block.Block block) -> this.applyExplosionDecay(
                            block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.BITTER_BERRY_BUSH)
                                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)))
                                    .add(LootItem.lootTableItem(YavpmItems.BITTER_BERRIES))
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f)))
                                    .apply(ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE))))
                                    .withPool(LootPool.lootPool().when(
                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.BITTER_BERRY_BUSH)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2)))
                                            .add(LootItem.lootTableItem(YavpmItems.BITTER_BERRIES))
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                            .apply(ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE)))
                                    )
                    )
            );

            LootItemBlockStatePropertyCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.RICE_CROP)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(CropBlock.AGE, 7));
            this.add(YavpmBlocks.RICE_CROP, this.createCropDrops(YavpmBlocks.RICE_CROP, YavpmItems.RICE, YavpmItems.RICE_SEEDS, builder2));

            LootItemBlockStatePropertyCondition.Builder peanutConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.PEANUT_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(PeanutCropBlock.AGE, 3));
            add(YavpmBlocks.PEANUT_CROP, applyExplosionDecay(YavpmBlocks.PEANUT_CROP, LootTable.lootTable().withPool(
                    LootPool.lootPool().add(
                            LootItem.lootTableItem(YavpmItems.PEANUT)
                    )).withPool(LootPool.lootPool().when(peanutConditionBuilder)
                    .add(LootItem.lootTableItem(YavpmItems.PEANUT)
                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 7)
                            )))));

            LootItemBlockStatePropertyCondition.Builder magicBeanConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.MAGIC_BEAN_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(MagicBeanCropBlock.AGE, 6));
            add(YavpmBlocks.MAGIC_BEAN_CROP, applyExplosionDecay(YavpmBlocks.MAGIC_BEAN_CROP, LootTable.lootTable().withPool(
                    LootPool.lootPool().add(
                            LootItem.lootTableItem(YavpmItems.MAGIC_BEAN)
                    )).withPool(LootPool.lootPool().when(magicBeanConditionBuilder)
                    .add(LootItem.lootTableItem(YavpmItems.MAGIC_BEAN)
                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 4)
                            )))));

            LootItemBlockStatePropertyCondition.Builder bananaConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.BANANA_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(BananaCropBlock.AGE, 5));

            add(YavpmBlocks.BANANA_CROP, createCropDrops(YavpmBlocks.BANANA_CROP, YavpmItems.BANANA, YavpmItems.BANANA_SEEDS, bananaConditionBuilder));

            add(
                    YavpmBlocks.WARPED_WART_CROP,
                    block -> LootTable.lootTable()
                            .withPool(
                                    applyExplosionDecay(
                                            block,
                                            LootPool.lootPool()
                                                    .setRolls(ConstantValue.exactly(1f))
                                                    .add(
                                                            LootItem.lootTableItem(YavpmItems.WARPED_WART)
                                                                    .apply(
                                                                            SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))
                                                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WarpedWartCropBlock.AGE, 3)))
                                                                    )
                                                                    .apply(
                                                                            ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE))
                                                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WarpedWartCropBlock.AGE, 3)))
                                                                    )
                                                    )
                                    )
                            )
            );

            this.add(YavpmBlocks.CANTALOUPE_STEM, block -> this.createStemDrops(block, YavpmItems.CANTALOUPE_SEEDS));
            this.add(YavpmBlocks.ATTACHED_CANTALOUPE_STEM, block -> this.createAttachedStemDrops(block, YavpmItems.CANTALOUPE_SEEDS));
            add(
                    YavpmBlocks.CANTALOUPE,
                    block -> this.createSilkTouchDispatchTable(
                            block,
                            this.applyExplosionDecay(
                                    block,
                                    LootItem.lootTableItem(YavpmItems.CANTALOUPE_SLICE)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F)))
                                            .apply(ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE)))
                                            .apply(LimitCount.limitCount(IntRange.upperBound(9)))
                            )
                    )
            );

            LootItemBlockStatePropertyCondition.Builder oakSaplingConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.OAK_SAPLING_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(SaplingCropBlock.AGE, 3));
            add(YavpmBlocks.OAK_SAPLING_CROP, createCropDrops(YavpmBlocks.OAK_SAPLING_CROP, Items.OAK_SAPLING, YavpmItems.ACORN, oakSaplingConditionBuilder));

            LootItemBlockStatePropertyCondition.Builder birchSaplingConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.BIRCH_SAPLING_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(SaplingCropBlock.AGE, 3));
            add(YavpmBlocks.BIRCH_SAPLING_CROP, createCropDrops(YavpmBlocks.BIRCH_SAPLING_CROP, Items.BIRCH_SAPLING, YavpmItems.BIRCH_SEEDS, birchSaplingConditionBuilder));

            LootItemBlockStatePropertyCondition.Builder crimsonFungusConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.CRIMSON_FUNGUS_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(SaplingCropBlock.AGE, 3));
            add(YavpmBlocks.CRIMSON_FUNGUS_CROP, createCropDrops(YavpmBlocks.CRIMSON_FUNGUS_CROP, Items.CRIMSON_FUNGUS, Items.NETHER_WART, crimsonFungusConditionBuilder));

            LootItemBlockStatePropertyCondition.Builder warpedFungusConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(YavpmBlocks.WARPED_FUNGUS_CROP).setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(SaplingCropBlock.AGE, 3));
            add(YavpmBlocks.WARPED_FUNGUS_CROP, createCropDrops(YavpmBlocks.WARPED_FUNGUS_CROP, Items.WARPED_FUNGUS, YavpmItems.WARPED_WART, warpedFungusConditionBuilder));
        }

        private void stoneVariantDrops() {
            add(
                    YavpmBlocks.KIMBERLITE,
                    block -> createSilkTouchDispatchTable(
                            block,
                            applyExplosionCondition(
                                    block,
                                    LootItem.lootTableItem(YavpmItems.RAW_DIAMOND)
                                            .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                                    lookup.getOrThrow(Enchantments.FORTUNE),
                                                    0.01f, 0.02f, 0.04f, 0.08f))
                                            .otherwise(LootItem.lootTableItem(block))
                            )
                    )
            );

            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE);
            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);

            dropSelf(YavpmBlocks.KIMBERLITE_STAIRS);
            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);

            add(YavpmBlocks.KIMBERLITE_SLAB, createSlabItemTable(YavpmBlocks.KIMBERLITE_SLAB));
            add(YavpmBlocks.POLISHED_KIMBERLITE_SLAB, createSlabItemTable(YavpmBlocks.POLISHED_KIMBERLITE_SLAB));
            add(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, createSlabItemTable(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB));

            dropSelf(YavpmBlocks.KIMBERLITE_WALL);
            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE_WALL);
            dropSelf(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);

            dropSelf(YavpmBlocks.COBBLED_GRANITE);
            dropSelf(YavpmBlocks.COBBLED_DIORITE);
            dropSelf(YavpmBlocks.COBBLED_ANDESITE);

            dropSelf(YavpmBlocks.COBBLED_GRANITE_STAIRS);
            dropSelf(YavpmBlocks.COBBLED_DIORITE_STAIRS);
            dropSelf(YavpmBlocks.COBBLED_ANDESITE_STAIRS);

            add(YavpmBlocks.COBBLED_GRANITE_SLAB, createSlabItemTable(YavpmBlocks.COBBLED_GRANITE_SLAB));
            add(YavpmBlocks.COBBLED_DIORITE_SLAB, createSlabItemTable(YavpmBlocks.COBBLED_DIORITE_SLAB));
            add(YavpmBlocks.COBBLED_ANDESITE_SLAB, createSlabItemTable(YavpmBlocks.COBBLED_ANDESITE_SLAB));

            dropSelf(YavpmBlocks.COBBLED_GRANITE_WALL);
            dropSelf(YavpmBlocks.COBBLED_DIORITE_WALL);
            dropSelf(YavpmBlocks.COBBLED_ANDESITE_WALL);

            dropSelf(YavpmBlocks.POLISHED_GRANITE_BRICKS);
            dropSelf(YavpmBlocks.POLISHED_DIORITE_BRICKS);
            dropSelf(YavpmBlocks.POLISHED_ANDESITE_BRICKS);

            dropSelf(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
            dropSelf(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
            dropSelf(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);

            add(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, createSlabItemTable(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB));
            add(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, createSlabItemTable(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB));
            add(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, createSlabItemTable(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB));

            dropSelf(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);
            dropSelf(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);
            dropSelf(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);

            dropSelf(YavpmBlocks.SCULKY_DEEPSLATE_BRICKS);
            add(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_SLAB, this::createSlabItemTable);
            dropSelf(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_STAIRS);
            dropSelf(YavpmBlocks.SCULKY_DEEPSLATE_BRICK_WALL);

            otherWhenSilkTouch(YavpmBlocks.INFESTED_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE);
            otherWhenSilkTouch(YavpmBlocks.INFESTED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS);
            otherWhenSilkTouch(YavpmBlocks.INFESTED_SCULKY_DEEPSLATE_BRICKS, YavpmBlocks.SCULKY_DEEPSLATE_BRICKS);
            otherWhenSilkTouch(YavpmBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
            otherWhenSilkTouch(YavpmBlocks.INFESTED_CHISELED_DEEPSLATE, Blocks.CHISELED_DEEPSLATE);

            dropSelf(YavpmBlocks.SOULSTONE);
            dropSelf(YavpmBlocks.CUT_SOULSTONE);
            dropSelf(YavpmBlocks.CHISELED_SOULSTONE);
            dropSelf(YavpmBlocks.SMOOTH_SOULSTONE);
            add(YavpmBlocks.SOULSTONE_SLAB, this::createSlabItemTable);
            add(YavpmBlocks.CUT_SOULSTONE_SLAB, this::createSlabItemTable);
            add(YavpmBlocks.SMOOTH_SOULSTONE_SLAB, this::createSlabItemTable);
            dropSelf(YavpmBlocks.SOULSTONE_STAIRS);
            dropSelf(YavpmBlocks.SMOOTH_SOULSTONE_STAIRS);
            dropSelf(YavpmBlocks.SOULSTONE_WALL);

            dropSelf(YavpmBlocks.CONGLOMERATE);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE);
            add(YavpmBlocks.HARDENED_CONGLOMERATE_SLAB, this::createSlabItemTable);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE_STAIRS);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE_WALL);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE_BRICKS);
            add(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_SLAB, this::createSlabItemTable);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_STAIRS);
            dropSelf(YavpmBlocks.HARDENED_CONGLOMERATE_BRICK_WALL);
            dropSelf(YavpmBlocks.DULL_CONGLOMERATE);
            add(YavpmBlocks.DULL_CONGLOMERATE_SLAB, this::createSlabItemTable);
        }

        private void appleDrops() {
            dropSelf(YavpmBlocks.APPLE_LOG);
            dropSelf(YavpmBlocks.STRIPPED_APPLE_LOG);
            dropSelf(YavpmBlocks.APPLE_WOOD);
            dropSelf(YavpmBlocks.STRIPPED_APPLE_WOOD);

            add(YavpmBlocks.APPLE_LEAVES, block -> createOakLeavesDrops(
                    block, YavpmBlocks.APPLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES
            ));
            add(YavpmBlocks.FLOWERING_APPLE_LEAVES, block -> createOakLeavesDrops(
                    block, YavpmBlocks.APPLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES
            ));

            dropSelf(YavpmBlocks.APPLE_PLANKS);
            dropSelf(YavpmBlocks.APPLE_STAIRS);
            add(YavpmBlocks.APPLE_SLAB, createSlabItemTable(YavpmBlocks.APPLE_SLAB));
            dropSelf(YavpmBlocks.APPLE_FENCE);
            dropSelf(YavpmBlocks.APPLE_FENCE_GATE);
            dropSelf(YavpmBlocks.APPLE_BUTTON);
            dropSelf(YavpmBlocks.APPLE_PRESSURE_PLATE);

            createDoorTable(YavpmBlocks.APPLE_DOOR);
            dropSelf(YavpmBlocks.APPLE_TRAPDOOR);

            dropSelf(YavpmBlocks.APPLE_SIGN);
            dropSelf(YavpmBlocks.APPLE_WALL_SIGN);
            dropSelf(YavpmBlocks.APPLE_HANGING_SIGN);
            dropSelf(YavpmBlocks.APPLE_WALL_HANGING_SIGN);

            dropSelf(YavpmBlocks.APPLE_SAPLING);
        }
        private void persimmonDrops() {
            dropSelf(YavpmBlocks.PERSIMMON_LOG);
            dropSelf(YavpmBlocks.STRIPPED_PERSIMMON_LOG);
            dropSelf(YavpmBlocks.PERSIMMON_WOOD);
            dropSelf(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);

            LootTable.Builder leavesBuilder = createLeavesDrops(YavpmBlocks.PERSIMMON_LEAVES, YavpmBlocks.PERSIMMON_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1f))
                                    .when(doesNotHaveShearsOrSilkTouch())
                                    .add(
                                            ((LootPoolSingletonContainer.Builder<?>)applyExplosionCondition(YavpmBlocks.PERSIMMON_LEAVES, LootItem.lootTableItem(YavpmItems.PERSIMMON)))
                                                    .when(BonusLevelTableCondition.bonusLevelFlatChance(lookup.getOrThrow(Enchantments.FORTUNE), 0.004f, 0.006f, 0.008f, 0.01f, 0.025f))
                                    )
                    );

            add(YavpmBlocks.PERSIMMON_LEAVES, leavesBuilder);

            dropSelf(YavpmBlocks.PERSIMMON_PLANKS);
            dropSelf(YavpmBlocks.PERSIMMON_STAIRS);
            add(YavpmBlocks.PERSIMMON_SLAB, createSlabItemTable(YavpmBlocks.PERSIMMON_SLAB));
            dropSelf(YavpmBlocks.PERSIMMON_FENCE);
            dropSelf(YavpmBlocks.PERSIMMON_FENCE_GATE);
            dropSelf(YavpmBlocks.PERSIMMON_BUTTON);
            dropSelf(YavpmBlocks.PERSIMMON_PRESSURE_PLATE);

            createDoorTable(YavpmBlocks.PERSIMMON_DOOR);
            dropSelf(YavpmBlocks.PERSIMMON_TRAPDOOR);

            dropSelf(YavpmBlocks.PERSIMMON_SIGN);
            dropSelf(YavpmBlocks.PERSIMMON_WALL_SIGN);
            dropSelf(YavpmBlocks.PERSIMMON_HANGING_SIGN);
            dropSelf(YavpmBlocks.PERSIMMON_WALL_HANGING_SIGN);

            dropSelf(YavpmBlocks.PERSIMMON_SAPLING);
        }
        private void prickleDrops() {
            dropSelf(YavpmBlocks.PRICKLE_LOG);
            dropSelf(YavpmBlocks.STRIPPED_PRICKLE_LOG);
            dropSelf(YavpmBlocks.PRICKLE_WOOD);
            dropSelf(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

            dropSelf(YavpmBlocks.PRICKLE_PLANKS);
            dropSelf(YavpmBlocks.PRICKLE_STAIRS);
            add(YavpmBlocks.PRICKLE_SLAB, createSlabItemTable(YavpmBlocks.PRICKLE_SLAB));
            dropSelf(YavpmBlocks.PRICKLE_FENCE);
            dropSelf(YavpmBlocks.PRICKLE_FENCE_GATE);
            dropSelf(YavpmBlocks.PRICKLE_BUTTON);
            dropSelf(YavpmBlocks.PRICKLE_PRESSURE_PLATE);

            createDoorTable(YavpmBlocks.PRICKLE_DOOR);
            dropSelf(YavpmBlocks.PRICKLE_TRAPDOOR);

            dropSelf(YavpmBlocks.PRICKLE_SIGN);
            dropSelf(YavpmBlocks.PRICKLE_WALL_SIGN);
            dropSelf(YavpmBlocks.PRICKLE_HANGING_SIGN);
            dropSelf(YavpmBlocks.PRICKLE_WALL_HANGING_SIGN);

            dropSelf(YavpmBlocks.PRICKLE_SHOOT);
        }

        public LootTable.Builder nahcoliteOreDrops(net.minecraft.world.level.block.Block drop) {
            return this.createSilkTouchDispatchTable(
                    drop,
                    this.applyExplosionDecay(
                            drop,
                            LootItem.lootTableItem(YavpmItems.BAKING_SODA)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
                                    .apply(ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE)))
                    )
            );
        }
    }

    public static class Entity extends SimpleFabricLootTableProvider {

        final HolderLookup.Provider lookup;

        public Entity(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(output, registryLookup, LootContextParamSets.ENTITY);
            lookup = registryLookup.join();
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
            // region Carbonfowl
            biConsumer.accept(YavpmEntities.CARBONFOWL.getDefaultLootTable().orElseThrow(), LootTable.lootTable().withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1f))
                            .add(
                                    LootItem.lootTableItem(Items.FEATHER)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 2.0F)))
                                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(0f, 1f)))
                            )
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1f))
                                    .add(
                                            LootItem.lootTableItem(Items.CHICKEN)
                                                    .apply(SmeltItemFunction.smelted().when(createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(0f, 1f)))
                                    )
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0f))
                                    .add(
                                            LootItem.lootTableItem(Items.DIAMOND)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(
                                                            lookup,
                                                            UniformGenerator.between(
                                                                    0f,
                                                                    1f
                                                            )
                                                    ))
                                    ).when(LootItemRandomChanceCondition.randomChance(0.2f))
                    )
            );
            // endregion
            // region Moongus
            biConsumer.accept(YavpmEntities.MOONGUS.getDefaultLootTable().orElseThrow(), LootTable.lootTable().withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1f))
                                    .add(
                                            LootItem.lootTableItem(Items.LEATHER)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 2f)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(0f, 1f)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1f))
                                    .add(
                                            LootItem.lootTableItem(Items.BEEF)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                                    .apply(SmeltItemFunction.smelted().when(createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(0f, 1f)))
                                    )
                    ));
            // endregion
            // region Sunburn
            biConsumer.accept(YavpmEntities.SUNBURN.getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                    .withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1f))
                            .add(
                                    LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 3f)))
                                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(1f, 3f)))
                            )
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceCondition.randomChance(0.67f))
            ));
            // endregion
            // region Tanuki
            biConsumer.accept(YavpmEntities.TANUKI.getDefaultLootTable().orElseThrow(), LootTable.lootTable().withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1f))
                            .add(
                                    LootItem.lootTableItem(Items.CHERRY_LEAVES)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 1f)))
                                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(1f, 3f))))
            ));
            // endregion
            // region Void Phantom
            biConsumer.accept(YavpmEntities.VOID_PHANTOM.getDefaultLootTable().orElseThrow(),
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1f))
                                            .add(
                                                    LootItem.lootTableItem(Items.PHANTOM_MEMBRANE)
                                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 1f)))
                                                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup, UniformGenerator.between(0f, 1f)))
                                            )
                                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                            .when(LootItemRandomChanceCondition.randomChance(0.67f))
                            ).withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1f))
                                            .add(
                                                    LootItem.lootTableItem(YavpmItems.PHANTOM_CHORD)
                                            )
                                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                            .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(lookup, 0.005f, 0.005f)))
            );
            // endregion
        }

        protected final AnyOfCondition.Builder createSmeltLootCondition() {
            HolderLookup.RegistryLookup<Enchantment> impl = lookup.lookupOrThrow(Registries.ENCHANTMENT);
            return AnyOfCondition.anyOf(
                    LootItemEntityPropertyCondition.hasProperties(
                            LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
                    ),
                    LootItemEntityPropertyCondition.hasProperties(
                            LootContext.EntityTarget.DIRECT_ATTACKER,
                            EntityPredicate.Builder.entity()
                                    .equipment(
                                            EntityEquipmentPredicate.Builder.equipment()
                                                    .mainhand(
                                                            ItemPredicate.Builder.item()
                                                                    .withComponents(DataComponentMatchers.Builder.components()
                                                                            .partial(DataComponentPredicates.ENCHANTMENTS,
                                                                                    EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))))
                                                                            .build()
                                                                    )
                                                    )
                                    )
                    )
            );
        }
    }
}
