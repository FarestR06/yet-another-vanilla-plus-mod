package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.BananaCropBlock;
import com.farestr06.yavpm.block.custom.PeanutCropBlock;
import com.farestr06.yavpm.block.custom.SaplingCropBlock;
import com.farestr06.yavpm.block.custom.WarpedWartCropBlock;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class YavpmLootProviders {
    public static class Block extends FabricBlockLootTableProvider {

        final RegistryWrapper.Impl<Enchantment> lookup = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        protected Block(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

            addDropWithSilkTouch(YavpmBlocks.NETHER_REACTOR_CORE);

            addDrop(YavpmBlocks.GLOWING_OBSIDIAN);
            addDrop(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            addDrop(YavpmBlocks.GRAPHITE_BLOCK);
            addDrop(YavpmBlocks.GRAPHENE_BLOCK);

            stoneVariantDrops();

            addDropWithSilkTouch(YavpmBlocks.ELECTRO_GLASS);

            modCropDrops();

            appleDrops();

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
                                                                            ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))
                                                                                    .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(WarpedWartCropBlock.AGE, 3)))
                                                                    )
                                                    )
                                    )
                            )
            );
            // endregion

        }

        private void modCropDrops() {
            BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(YavpmBlocks.PEANUT_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(PeanutCropBlock.AGE, 3));
            this.addDrop(YavpmBlocks.PEANUT_CROP, this.applyExplosionDecay(YavpmBlocks.PEANUT_CROP, LootTable.builder().pool(
                    LootPool.builder().with(
                            ItemEntry.builder(YavpmItems.PEANUT)
                    )).pool(LootPool.builder().conditionally(builder)
                    .with(ItemEntry.builder(YavpmItems.PEANUT)
                            .apply(ApplyBonusLootFunction.binomialWithBonusCount
                                    (lookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 3)
                            )))));

            BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(YavpmBlocks.BANANA_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(BananaCropBlock.AGE, 5));

            addDrop(YavpmBlocks.BANANA_CROP, cropDrops(YavpmBlocks.BANANA_CROP, YavpmItems.BANANA, YavpmItems.BANANA_SEEDS, builder2));

            BlockStatePropertyLootCondition.Builder oakSaplingBuilder = BlockStatePropertyLootCondition.builder(YavpmBlocks.OAK_SAPLING_CROP).properties(StatePredicate.Builder.create()
                    .exactMatch(SaplingCropBlock.AGE, 3));
            addDrop(YavpmBlocks.OAK_SAPLING_CROP, cropDrops(YavpmBlocks.OAK_SAPLING_CROP, Items.OAK_SAPLING, YavpmItems.ACORN, oakSaplingBuilder));
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
    }
}
