package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.crop.*;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.CropBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class YavpmLootProviders {
    public static class Block extends FabricBlockLootTableProvider {

        final RegistryWrapper.Impl<Enchantment> lookup = registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        protected Block(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {

            addDrop(YavpmBlocks.GLOWING_OBSIDIAN);
            addDrop(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            stoneVariantDrops();
            addDrop(YavpmBlocks.GRAPHITE_BLOCK);
            addDrop(YavpmBlocks.GRAPHENE_BLOCK);

            addDrop(YavpmBlocks.KEYLOCK);
            addDropWithSilkTouch(YavpmBlocks.POLARIZED_GLASS);

            cropDrops();

            appleDrops();
            persimmonDrops();
            prickleDrops();

            fakeDrops();

            // region Warped Wart
            addDrop(
                    YavpmBlocks.WARPED_WART,
                    block -> LootTable.builder()
                            .pool(
                                    applyExplosionDecay(
                                            block,
                                            LootPool.builder()
                                                    .rolls(ConstantLootNumberProvider.create(1f))
                                                    .with(
                                                            ItemEntry.builder(YavpmItems.WARPED_WART)
                                                                    .apply(
                                                                            SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))
                                                                                    .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(WarpedWartCropBlock.AGE, 3)))
                                                                    )
                                                                    .apply(
                                                                            ApplyBonusLootFunction.uniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE))
                                                                                    .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(WarpedWartCropBlock.AGE, 3)))
                                                                    )
                                                    )
                                    )
                            )
            );
            // endregion

        }

        private void fakeDrops() {
            addDrop(YavpmBlocks.FAKE_LOG, LootTable.builder()
                    .pool(
                            LootPool.builder().rolls(ConstantLootNumberProvider.create(1f))
                                    .with(ItemEntry.builder(Items.ACACIA_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.BIRCH_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.CHERRY_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.DARK_OAK_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.JUNGLE_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.OAK_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.MANGROVE_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(Items.SPRUCE_PLANKS)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(YavpmBlocks.APPLE_PLANKS.asItem())
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    )
                                    .with(ItemEntry.builder(YavpmBlocks.PERSIMMON_PLANKS.asItem())
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 5f)))
                                    ).conditionally(RandomChanceLootCondition.builder(0.4f))
                    )
            );
            addDrop(YavpmBlocks.FAKE_ORE, LootTable.builder()
                    .pool(
                            LootPool.builder().rolls(ConstantLootNumberProvider.create(1f))
                                    .with(ItemEntry.builder(Items.COAL)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.RAW_COPPER)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.RAW_IRON)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.RAW_GOLD)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.LAPIS_LAZULI)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.DIAMOND)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.EMERALD)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).with(ItemEntry.builder(Items.REDSTONE)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                    ).conditionally(RandomChanceLootCondition.builder(0.4f))
                    )
            );
        }

        private void cropDrops() {
            addDrop(
                    YavpmBlocks.BITTER_BERRY_BUSH,
                    (net.minecraft.block.Block block) -> this.applyExplosionDecay(
                            block, LootTable.builder().pool(LootPool.builder().conditionally(
                                    BlockStatePropertyLootCondition.builder(YavpmBlocks.BITTER_BERRY_BUSH)
                                            .properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 3)))
                                    .with(ItemEntry.builder(YavpmItems.BITTER_BERRIES))
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f)))
                                    .apply(ApplyBonusLootFunction.uniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE))))
                                    .pool(LootPool.builder().conditionally(
                                            BlockStatePropertyLootCondition.builder(YavpmBlocks.BITTER_BERRY_BUSH)
                                                    .properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 2)))
                                            .with(ItemEntry.builder(YavpmItems.BITTER_BERRIES))
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                                            .apply(ApplyBonusLootFunction.uniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE)))
                                    )
                    )
            );

            BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(YavpmBlocks.RICE_CROP)
                    .properties(StatePredicate.Builder.create()
                            .exactMatch(CropBlock.AGE, 7));
            this.addDrop(YavpmBlocks.RICE_CROP, this.cropDrops(YavpmBlocks.RICE_CROP, YavpmItems.RICE, YavpmItems.RICE_SEEDS, builder2));

            BlockStatePropertyLootCondition.Builder peanutConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.PEANUT_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(PeanutCropBlock.AGE, 3));
            addDrop(YavpmBlocks.PEANUT_CROP, applyExplosionDecay(YavpmBlocks.PEANUT_CROP, LootTable.builder().pool(
                    LootPool.builder().with(
                            ItemEntry.builder(YavpmItems.PEANUT)
                    )).pool(LootPool.builder().conditionally(peanutConditionBuilder)
                    .with(ItemEntry.builder(YavpmItems.PEANUT)
                            .apply(ApplyBonusLootFunction.binomialWithBonusCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 7)
                            )))));

            BlockStatePropertyLootCondition.Builder magicBeanConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.MAGIC_BEAN_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(MagicBeanCropBlock.AGE, 6));
            addDrop(YavpmBlocks.MAGIC_BEAN_CROP, applyExplosionDecay(YavpmBlocks.MAGIC_BEAN_CROP, LootTable.builder().pool(
                    LootPool.builder().with(
                            ItemEntry.builder(YavpmItems.MAGIC_BEAN)
                    )).pool(LootPool.builder().conditionally(magicBeanConditionBuilder)
                    .with(ItemEntry.builder(YavpmItems.MAGIC_BEAN)
                            .apply(ApplyBonusLootFunction.binomialWithBonusCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 4)
                            )))));

            BlockStatePropertyLootCondition.Builder bananaConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.BANANA_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(BananaCropBlock.AGE, 5));

            addDrop(YavpmBlocks.BANANA_CROP, cropDrops(YavpmBlocks.BANANA_CROP, YavpmItems.BANANA, YavpmItems.BANANA_SEEDS, bananaConditionBuilder));

            BlockStatePropertyLootCondition.Builder oakSaplingConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.OAK_SAPLING_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(SaplingCropBlock.AGE, 3));
            addDrop(YavpmBlocks.OAK_SAPLING_CROP, cropDrops(YavpmBlocks.OAK_SAPLING_CROP, Items.OAK_SAPLING, YavpmItems.ACORN, oakSaplingConditionBuilder));
        }

        private void stoneVariantDrops() {
            addDrop(
                    YavpmBlocks.KIMBERLITE,
                    block -> dropsWithSilkTouch(
                            block,
                            addSurvivesExplosionCondition(
                                    block,
                                    ItemEntry.builder(YavpmItems.RAW_DIAMOND)
                                            .conditionally(TableBonusLootCondition.builder(
                                                    lookup.getOrThrow(Enchantments.FORTUNE),
                                                    0.01f, 0.02f, 0.04f, 0.08f))
                                            .alternatively(ItemEntry.builder(block))
                            )
                    )
            );

            addDrop(YavpmBlocks.POLISHED_KIMBERLITE);
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_BRICKS);

            addDrop(YavpmBlocks.KIMBERLITE_STAIRS);
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_STAIRS);
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_STAIRS);

            addDrop(YavpmBlocks.KIMBERLITE_SLAB, slabDrops(YavpmBlocks.KIMBERLITE_SLAB));
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_SLAB, slabDrops(YavpmBlocks.POLISHED_KIMBERLITE_SLAB));
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB, slabDrops(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_SLAB));

            addDrop(YavpmBlocks.KIMBERLITE_WALL);
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_WALL);
            addDrop(YavpmBlocks.POLISHED_KIMBERLITE_BRICK_WALL);

            addDrop(YavpmBlocks.COBBLED_GRANITE);
            addDrop(YavpmBlocks.COBBLED_DIORITE);
            addDrop(YavpmBlocks.COBBLED_ANDESITE);

            addDrop(YavpmBlocks.COBBLED_GRANITE_STAIRS);
            addDrop(YavpmBlocks.COBBLED_DIORITE_STAIRS);
            addDrop(YavpmBlocks.COBBLED_ANDESITE_STAIRS);

            addDrop(YavpmBlocks.COBBLED_GRANITE_SLAB, slabDrops(YavpmBlocks.COBBLED_GRANITE_SLAB));
            addDrop(YavpmBlocks.COBBLED_DIORITE_SLAB, slabDrops(YavpmBlocks.COBBLED_DIORITE_SLAB));
            addDrop(YavpmBlocks.COBBLED_ANDESITE_SLAB, slabDrops(YavpmBlocks.COBBLED_ANDESITE_SLAB));

            addDrop(YavpmBlocks.COBBLED_GRANITE_WALL);
            addDrop(YavpmBlocks.COBBLED_DIORITE_WALL);
            addDrop(YavpmBlocks.COBBLED_ANDESITE_WALL);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICKS);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICKS);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICKS);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, slabDrops(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB));
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, slabDrops(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB));
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, slabDrops(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB));

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);
        }

        private void appleDrops() {
            addDrop(YavpmBlocks.APPLE_LOG);
            addDrop(YavpmBlocks.STRIPPED_APPLE_LOG);
            addDrop(YavpmBlocks.APPLE_WOOD);
            addDrop(YavpmBlocks.STRIPPED_APPLE_WOOD);

            LootTable.Builder leavesBuilder = oakLeavesDrops(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.APPLE_SAPLING, SAPLING_DROP_CHANCE);
            addDrop(YavpmBlocks.APPLE_LEAVES, leavesBuilder);

            addDrop(YavpmBlocks.APPLE_PLANKS);
            addDrop(YavpmBlocks.APPLE_STAIRS);
            addDrop(YavpmBlocks.APPLE_SLAB, slabDrops(YavpmBlocks.APPLE_SLAB));
            addDrop(YavpmBlocks.APPLE_FENCE);
            addDrop(YavpmBlocks.APPLE_FENCE_GATE);
            addDrop(YavpmBlocks.APPLE_BUTTON);
            addDrop(YavpmBlocks.APPLE_PRESSURE_PLATE);

            doorDrops(YavpmBlocks.APPLE_DOOR);
            addDrop(YavpmBlocks.APPLE_TRAPDOOR);

            addDrop(YavpmBlocks.APPLE_SAPLING);
        }
        private void persimmonDrops() {
            addDrop(YavpmBlocks.PERSIMMON_LOG);
            addDrop(YavpmBlocks.STRIPPED_PERSIMMON_LOG);
            addDrop(YavpmBlocks.PERSIMMON_WOOD);
            addDrop(YavpmBlocks.STRIPPED_PERSIMMON_WOOD);

            LootTable.Builder leavesBuilder = leavesDrops(YavpmBlocks.PERSIMMON_LEAVES, YavpmBlocks.PERSIMMON_SAPLING, SAPLING_DROP_CHANCE)
                    .pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1f))
                                    .conditionally(createWithoutShearsOrSilkTouchCondition())
                                    .with(
                                            ((LeafEntry.Builder<?>)addSurvivesExplosionCondition(YavpmBlocks.PERSIMMON_LEAVES, ItemEntry.builder(YavpmItems.PERSIMMON)))
                                                    .conditionally(TableBonusLootCondition.builder(lookup.getOrThrow(Enchantments.FORTUNE), 0.004f, 0.006f, 0.008f, 0.01f, 0.025f))
                                    )
                    );

            addDrop(YavpmBlocks.PERSIMMON_LEAVES, leavesBuilder);

            addDrop(YavpmBlocks.PERSIMMON_PLANKS);
            addDrop(YavpmBlocks.PERSIMMON_STAIRS);
            addDrop(YavpmBlocks.PERSIMMON_SLAB, slabDrops(YavpmBlocks.PERSIMMON_SLAB));
            addDrop(YavpmBlocks.PERSIMMON_FENCE);
            addDrop(YavpmBlocks.PERSIMMON_FENCE_GATE);
            addDrop(YavpmBlocks.PERSIMMON_BUTTON);
            addDrop(YavpmBlocks.PERSIMMON_PRESSURE_PLATE);

            doorDrops(YavpmBlocks.PERSIMMON_DOOR);
            addDrop(YavpmBlocks.PERSIMMON_TRAPDOOR);
        }
        private void prickleDrops() {
            addDrop(YavpmBlocks.PRICKLE_LOG);
            addDrop(YavpmBlocks.STRIPPED_PRICKLE_LOG);
            addDrop(YavpmBlocks.PRICKLE_WOOD);
            addDrop(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

            addDrop(YavpmBlocks.PRICKLE_PLANKS);
            addDrop(YavpmBlocks.PRICKLE_STAIRS);
            addDrop(YavpmBlocks.PRICKLE_SLAB, slabDrops(YavpmBlocks.PRICKLE_SLAB));
            addDrop(YavpmBlocks.PRICKLE_FENCE);
            addDrop(YavpmBlocks.PRICKLE_FENCE_GATE);
            addDrop(YavpmBlocks.PRICKLE_BUTTON);
            addDrop(YavpmBlocks.PRICKLE_PRESSURE_PLATE);

            doorDrops(YavpmBlocks.PRICKLE_DOOR);
            addDrop(YavpmBlocks.PRICKLE_TRAPDOOR);

            addDrop(YavpmBlocks.PRICKLE_SHOOT);
        }
    }

    public static class Entity extends SimpleFabricLootTableProvider {

        final RegistryWrapper.WrapperLookup lookup;

        public Entity(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(output, registryLookup, LootContextTypes.ENTITY);
            lookup = registryLookup.join();
        }

        @Override
        public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer) {
            // region Carbonfowl
            biConsumer.accept(YavpmEntities.CARBONFOWL.getLootTableKey(), LootTable.builder().pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(
                                    ItemEntry.builder(Items.FEATHER)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 2.0F)))
                                            .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0f, 1f)))
                            )
                    ).pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1f))
                                    .with(
                                            ItemEntry.builder(Items.CHICKEN)
                                                    .apply(FurnaceSmeltLootFunction.builder().conditionally(createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0f, 1f)))
                                    )
                    ).pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1.0f))
                                    .with(
                                            ItemEntry.builder(Items.DIAMOND)
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(
                                                            lookup,
                                                            UniformLootNumberProvider.create(
                                                                    0f,
                                                                    1f
                                                            )
                                                    ))
                                    ).conditionally(RandomChanceLootCondition.builder(0.2f))
                    )
            );
            // endregion
            // region Moongus
            biConsumer.accept(YavpmEntities.MOONGUS.getLootTableKey(), LootTable.builder().pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1f))
                                    .with(
                                            ItemEntry.builder(Items.LEATHER)
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 2f)))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0f, 1f)))
                                    )
                    )
                    .pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1f))
                                    .with(
                                            ItemEntry.builder(Items.BEEF)
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                                    .apply(FurnaceSmeltLootFunction.builder().conditionally(createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0f, 1f)))
                                    )
                    ));
            // endregion
            // region Tanuki
            biConsumer.accept(YavpmEntities.TANUKI.getLootTableKey(), LootTable.builder().pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(
                                    ItemEntry.builder(Items.CHERRY_LEAVES)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)))
                                            .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(1f, 3f))))
            ));
            // endregion
            // region Void Phantom
            biConsumer.accept(YavpmEntities.VOID_PHANTOM.getLootTableKey(),
                    LootTable.builder()
                            .pool(
                                    LootPool.builder()
                                            .rolls(ConstantLootNumberProvider.create(1f))
                                            .with(
                                                    ItemEntry.builder(Items.PHANTOM_MEMBRANE)
                                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)))
                                                            .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0f, 1f)))
                                            )
                                            .conditionally(KilledByPlayerLootCondition.builder())
                                            .conditionally(RandomChanceLootCondition.builder(0.67f))
                            ).pool(
                                    LootPool.builder()
                                            .rolls(ConstantLootNumberProvider.create(1f))
                                            .with(
                                                    ItemEntry.builder(YavpmItems.PHANTOM_CHORD)
                                            )
                                            .conditionally(KilledByPlayerLootCondition.builder())
                                            .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(lookup, 0.005f, 0.005f)))
            );
            // endregion
        }

        protected final AnyOfLootCondition.Builder createSmeltLootCondition() {
            RegistryWrapper.Impl<Enchantment> impl = lookup.getOrThrow(RegistryKeys.ENCHANTMENT);
            return AnyOfLootCondition.builder(
                    EntityPropertiesLootCondition.builder(
                            LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))
                    ),
                    EntityPropertiesLootCondition.builder(
                            LootContext.EntityTarget.DIRECT_ATTACKER,
                            EntityPredicate.Builder.create()
                                    .equipment(
                                            EntityEquipmentPredicate.Builder.create()
                                                    .mainhand(
                                                            ItemPredicate.Builder.create()
                                                                    .subPredicate(
                                                                            ItemSubPredicateTypes.ENCHANTMENTS,
                                                                            EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))
                                                                    )
                                                    )
                                    )
                    )
            );
        }
    }
}
