package com.farestr06.yavpm.datagen;

import com.farestr06.api.util.LootHelper;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.*;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
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

        final RegistryWrapper.Impl<Enchantment> lookup = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        protected Block(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {

            addDrop(YavpmBlocks.GLOWING_OBSIDIAN);
            addDrop(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            LootHelper.BlockHelper.createGravelLikeDrop(this, lookup, YavpmBlocks.KIMBERLITE, YavpmItems.RAW_DIAMOND);
            addDrop(YavpmBlocks.GRAPHITE_BLOCK);
            addDrop(YavpmBlocks.GRAPHENE_BLOCK);

            stoneVariantDrops();

            addDropWithSilkTouch(YavpmBlocks.ELECTRO_GLASS);

            modCropDrops();

            appleDrops();
            prickleDrops();

            // region Warped Wart
            this.addDrop(
                    YavpmBlocks.WARPED_WART,
                    block -> LootTable.builder()
                            .pool(
                                    this.applyExplosionDecay(
                                            block,
                                            LootPool.builder()
                                                    .rolls(ConstantLootNumberProvider.create(1.0F))
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

        private void modCropDrops() {
            BlockStatePropertyLootCondition.Builder peanutConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.PEANUT_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(PeanutCropBlock.AGE, 3));
            this.addDrop(YavpmBlocks.PEANUT_CROP, this.applyExplosionDecay(YavpmBlocks.PEANUT_CROP, LootTable.builder().pool(
                    LootPool.builder().with(
                            ItemEntry.builder(YavpmItems.PEANUT)
                    )).pool(LootPool.builder().conditionally(peanutConditionBuilder)
                    .with(ItemEntry.builder(YavpmItems.PEANUT)
                            .apply(ApplyBonusLootFunction.binomialWithBonusCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 7)
                            )))));

            BlockStatePropertyLootCondition.Builder magicBeanConditionBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.MAGIC_BEAN_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(MagicBeanCropBlock.AGE, 6));
            this.addDrop(YavpmBlocks.MAGIC_BEAN_CROP, this.applyExplosionDecay(YavpmBlocks.MAGIC_BEAN_CROP, LootTable.builder().pool(
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

            addDrop(YavpmBlocks.COBBLED_GRANITE);
            addDrop(YavpmBlocks.COBBLED_DIORITE);
            addDrop(YavpmBlocks.COBBLED_ANDESITE);

            addDrop(YavpmBlocks.COBBLED_GRANITE_STAIRS);
            addDrop(YavpmBlocks.COBBLED_DIORITE_STAIRS);
            addDrop(YavpmBlocks.COBBLED_ANDESITE_STAIRS);

            slabDrops(YavpmBlocks.COBBLED_GRANITE_SLAB);
            slabDrops(YavpmBlocks.COBBLED_DIORITE_SLAB);
            slabDrops(YavpmBlocks.COBBLED_ANDESITE_SLAB);

            addDrop(YavpmBlocks.COBBLED_GRANITE_WALL);
            addDrop(YavpmBlocks.COBBLED_DIORITE_WALL);
            addDrop(YavpmBlocks.COBBLED_ANDESITE_WALL);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICKS);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICKS);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICKS);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS);

            slabDrops(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB);
            slabDrops(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB);
            slabDrops(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB);

            addDrop(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL);
            addDrop(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL);
            addDrop(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL);
        }

        private void appleDrops() {
            addDrop(YavpmBlocks.APPLE_LOG);
            addDrop(YavpmBlocks.STRIPPED_APPLE_LOG);
            addDrop(YavpmBlocks.APPLE_WOOD);
            addDrop(YavpmBlocks.STRIPPED_APPLE_WOOD);

            oakLeavesDrops(YavpmBlocks.APPLE_LEAVES, YavpmBlocks.APPLE_SAPLING, SAPLING_DROP_CHANCE);

            addDrop(YavpmBlocks.APPLE_PLANKS);
            addDrop(YavpmBlocks.APPLE_STAIRS);
            slabDrops(YavpmBlocks.APPLE_SLAB);
            addDrop(YavpmBlocks.APPLE_FENCE);
            addDrop(YavpmBlocks.APPLE_FENCE_GATE);
            addDrop(YavpmBlocks.APPLE_BUTTON);
            addDrop(YavpmBlocks.APPLE_PRESSURE_PLATE);

            doorDrops(YavpmBlocks.APPLE_DOOR);
            addDrop(YavpmBlocks.APPLE_TRAPDOOR);

            addDrop(YavpmBlocks.APPLE_SIGN, YavpmItems.APPLE_SIGN);
            addDrop(YavpmBlocks.APPLE_WALL_SIGN, YavpmItems.APPLE_SIGN);
            addDrop(YavpmBlocks.APPLE_HANGING_SIGN, YavpmItems.APPLE_HANGING_SIGN);
            addDrop(YavpmBlocks.APPLE_WALL_HANGING_SIGN, YavpmItems.APPLE_HANGING_SIGN);

            addDrop(YavpmBlocks.APPLE_SAPLING);
        }
        private void prickleDrops() {
            addDrop(YavpmBlocks.PRICKLE_LOG);
            addDrop(YavpmBlocks.STRIPPED_PRICKLE_LOG);
            addDrop(YavpmBlocks.PRICKLE_WOOD);
            addDrop(YavpmBlocks.STRIPPED_PRICKLE_WOOD);

            addDrop(YavpmBlocks.PRICKLE_PLANKS);
            addDrop(YavpmBlocks.PRICKLE_STAIRS);
            slabDrops(YavpmBlocks.PRICKLE_SLAB);
            addDrop(YavpmBlocks.PRICKLE_FENCE);
            addDrop(YavpmBlocks.PRICKLE_FENCE_GATE);
            addDrop(YavpmBlocks.PRICKLE_BUTTON);
            addDrop(YavpmBlocks.PRICKLE_PRESSURE_PLATE);

            doorDrops(YavpmBlocks.PRICKLE_DOOR);
            addDrop(YavpmBlocks.PRICKLE_TRAPDOOR);

            addDrop(YavpmBlocks.PRICKLE_SIGN, YavpmItems.PRICKLE_SIGN);
            addDrop(YavpmBlocks.PRICKLE_WALL_SIGN, YavpmItems.PRICKLE_SIGN);
            addDrop(YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmItems.PRICKLE_HANGING_SIGN);
            addDrop(YavpmBlocks.PRICKLE_WALL_HANGING_SIGN, YavpmItems.PRICKLE_HANGING_SIGN);

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
        public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
            // region Carbonfowl
            lootTableBiConsumer.accept(YavpmEntities.CARBONFOWL.getLootTableId(), LootTable.builder().pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(
                                    ItemEntry.builder(Items.FEATHER)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                            .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                            )
                    ).pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1.0F))
                                    .with(
                                            ItemEntry.builder(Items.CHICKEN)
                                                    .apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(lookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
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
            lootTableBiConsumer.accept(YavpmEntities.MOONGUS.getLootTableId(), LootTable.builder().pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1.0F))
                                    .with(
                                            ItemEntry.builder(Items.LEATHER)
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 2f)))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(this.lookup, UniformLootNumberProvider.create(0f, 1f)))
                                    )
                    )
                    .pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1f))
                                    .with(
                                            ItemEntry.builder(Items.BEEF)
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                                    .apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition()))
                                                    .apply(EnchantedCountIncreaseLootFunction.builder(this.lookup, UniformLootNumberProvider.create(0f, 1f)))
                                    )
                    ));
            // endregion
            lootTableBiConsumer.accept(YavpmEntities.TANUKI.getLootTableId(), LootTable.builder().pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(
                                    ItemEntry.builder(Items.CHERRY_LEAVES)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
                                            .apply(EnchantedCountIncreaseLootFunction.builder(this.lookup, UniformLootNumberProvider.create(0f, 2f))))
            ));
        }

        protected final AnyOfLootCondition.Builder createSmeltLootCondition() {
            RegistryWrapper.Impl<Enchantment> impl = this.lookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
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
