package com.farestr06.yafm.datagen;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.block.custom.BananaCropBlock;
import com.farestr06.yafm.block.custom.PeanutCropBlock;
import com.farestr06.yafm.block.custom.SaplingCropBlock;
import com.farestr06.yafm.item.YavpmItems;
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

            addDrop(YavpmBlocks.GLOWING_OBSIDIAN);
            addDrop(YavpmBlocks.SOUL_GLOWING_OBSIDIAN);

            stoneVariantDrops();

            addDropWithSilkTouch(YavpmBlocks.ELECTRO_GLASS);

            modCropDrops();
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
    }
}
