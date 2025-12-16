package com.farestr06.yavpm.util;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootHelper {
    public static void modifyLoot() {
        YetAnotherVanillaPlusMod.LOGGER.info("Modifying loot for YAVPM!");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            HolderLookup.RegistryLookup<Enchantment> enchantmentLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
            if (source.isBuiltin() && key == (BuiltInLootTables.PIGLIN_BARTERING)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .add(LootItem.lootTableItem(YavpmItems.GAUNTLET_FRAGMENT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                        )
                        .when(LootItemRandomChanceCondition.randomChance(0.0079f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.SNIFFER_DIGGING)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .add(LootItem.lootTableItem(YavpmItems.TRUFFLE))
                        .add(LootItem.lootTableItem(YavpmItems.MAGIC_BEAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f))))
                        .add(LootItem.lootTableItem(YavpmItems.BITTER_BERRIES).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f))));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (EntityType.ZOMBIE.getDefaultLootTable().orElseThrow())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.MAGIC_BEAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                        .add(LootItem.lootTableItem(YavpmItems.PEANUT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.025F, 0.01F));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.BASTION_TREASURE)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.GAUNTLET_FRAGMENT).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f))
                        ))
                        .when(LootItemRandomChanceCondition.randomChance(0.79f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.BASTION_BRIDGE)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.GAUNTLET_FRAGMENT))
                        .when(LootItemRandomChanceCondition.randomChance(0.11f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.BASTION_HOGLIN_STABLE)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.GAUNTLET_FRAGMENT))
                        .when(LootItemRandomChanceCondition.randomChance(0.11f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.BASTION_OTHER)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.GAUNTLET_FRAGMENT))
                        .when(LootItemRandomChanceCondition.randomChance(0.079f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.SIMPLE_DUNGEON)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1f,3f))
                        .add(LootItem.lootTableItem(YavpmItems.PEANUT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.35f));

                tableBuilder.withPool(poolBuilder1);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_CONSUMABLES)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.COOKED_PEANUT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)))
                                .setWeight(24)
                        )
                        .add(LootItem.lootTableItem(YavpmItems.COOKED_PEANUT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)))
                                .setWeight(24)
                        )
                        .add(LootItem.lootTableItem(YavpmItems.PERSIMMON)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
                                .setWeight(20)
                        )
                        .add(LootItem.lootTableItem(YavpmItems.GOLDEN_PERSIMMON)
                                .setWeight(2)
                        )
                        .add(LootItem.lootTableItem(YavpmItems.MOLY)
                                .setWeight(2)
                        )
                        .when(LootItemRandomChanceCondition.randomChance(0.4f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.CAT_MORNING_GIFT)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.MAGIC_BEAN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                .setWeight(12)
                        )
                        .add(LootItem.lootTableItem(Items.CARROT)
                                .setWeight(12)
                        )
                        .add(LootItem.lootTableItem(YavpmItems.MOLY).setWeight(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.18f));
                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT))
                        .when(LootItemRandomChanceCondition.randomChance(0.2f));

                tableBuilder.withPool(poolBuilder1).withPool(poolBuilder2);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.UNDERWATER_RUIN_BIG)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_MALL))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.WOODLAND_MANSION)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_STAL))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                tableBuilder.withPool(poolBuilder1);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.DESERT_PYRAMID)) {
                LootPool.Builder poolbuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.MOLY))
                        .when(LootItemRandomChanceCondition.randomChance(ConstantValue.exactly(0.08f)));

                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_FAR))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));
                tableBuilder.withPool(poolbuilder1).withPool(poolBuilder2);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.JUNGLE_TEMPLE)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.BANANA_SEEDS)).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 6f))
                        )
                        .add(LootItem.lootTableItem(YavpmItems.RICE)).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(2f, 8f))
                        )
                        .when(LootItemRandomChanceCondition.randomChance(0.24f));

                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CHIRP))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                LootPool.Builder poolBuilder3 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.FORTUNE_COOKIE))
                        .when(LootItemRandomChanceCondition.randomChance(0.24f));

                tableBuilder.withPool(poolBuilder1).withPool(poolBuilder2).withPool(poolBuilder3);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.IGLOO_CHEST)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_BLOCKS))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.STRONGHOLD_CROSSING)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_11))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1f, 2f))
                        .add(LootItem.lootTableItem(YavpmItems.RICE_SEEDS).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))
                        ))
                        .add(LootItem.lootTableItem(Items.CARROT).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))
                        ))
                        .add(LootItem.lootTableItem(Items.POTATO).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))
                        ))
                        .add(LootItem.lootTableItem(YavpmItems.MAGIC_BEAN).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))
                        ))
                        .add(LootItem.lootTableItem(YavpmItems.PEANUT).apply(
                                SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))
                        ));

                LootPool.Builder poolBuilder3 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.APPLE).setWeight(15))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(5))
                        .add(LootItem.lootTableItem(YavpmItems.PERSIMMON).setWeight(15))
                        .add(LootItem.lootTableItem(YavpmItems.GOLDEN_PERSIMMON).setWeight(5))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.2f));

                tableBuilder.withPool(poolBuilder1).withPool(poolBuilder2).withPool(poolBuilder3);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.STRONGHOLD_LIBRARY)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries).withOneOf(
                                enchantmentLookup.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
                        .when(LootItemRandomChanceCondition.randomChance(0.67f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.END_CITY_TREASURE)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries).withOneOf(
                                enchantmentLookup.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
                        .when(LootItemRandomChanceCondition.randomChance(0.24f));
                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(YavpmItems.PHANTOM_CHORD))
                        .when(LootItemRandomChanceCondition.randomChance(0.011f));

                tableBuilder.withPool(poolBuilder1).withPool(poolBuilder2);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.NETHER_BRIDGE)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_WARD))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                tableBuilder.withPool(poolBuilder);
            }
            if (source.isBuiltin() && key == (BuiltInLootTables.BURIED_TREASURE)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_MELLOHI))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_WAIT))
                        .when(LootItemRandomChanceCondition.randomChance(0.19f));

                tableBuilder.withPool(poolBuilder);
            }
        });
        LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            BlockLootSubProvider generator = new VanillaBlockLoot(registries);
            if (source.isBuiltin() && key == Blocks.OAK_LEAVES.getLootTable().orElseThrow()) {
                return newOakLeavesDrops(registries, generator).build();
            }
            if (source.isBuiltin() && key == Blocks.BIRCH_LEAVES.getLootTable().orElseThrow()) {
                return newBirchLeavesDrops(registries, generator).build();
            }
            if (source.isBuiltin() && key == Blocks.SEAGRASS.getLootTable().orElseThrow()) {
                return shortSeagrassDrops(registries, generator).build();
            }
            if (source.isBuiltin() && key == Blocks.TALL_SEAGRASS.getLootTable().orElseThrow()) {
                return tallSeagrassDrops(registries, generator).build();
            }
            if (source.isBuiltin() && key == Blocks.GRANITE.getLootTable().orElseThrow()) {
                return generator.createSingleItemTableWithSilkTouch(Blocks.GRANITE, YavpmBlocks.COBBLED_GRANITE).build();
            }
            if (source.isBuiltin() && key == Blocks.DIORITE.getLootTable().orElseThrow()) {
                return generator.createSingleItemTableWithSilkTouch(Blocks.DIORITE, YavpmBlocks.COBBLED_DIORITE).build();
            }
            if (source.isBuiltin() && key == Blocks.ANDESITE.getLootTable().orElseThrow()) {
                return generator.createSingleItemTableWithSilkTouch(Blocks.ANDESITE, YavpmBlocks.COBBLED_ANDESITE).build();
            }
            return original;
        });
    }

    private static LootTable.Builder shortSeagrassDrops(HolderLookup.Provider lookup, BlockLootSubProvider generator) {
        HolderLookup.RegistryLookup<Enchantment> impl = lookup.lookupOrThrow(Registries.ENCHANTMENT);
        return generator.createShearsDispatchTable(
                Blocks.SEAGRASS,
                generator.applyExplosionDecay(
                        Blocks.SEAGRASS,
                        LootItem.lootTableItem(YavpmItems.RICE_SEEDS)
                                .when(LootItemRandomChanceCondition.randomChance(0.125F))
                                .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 2))
                )
        );
    }
    private static LootTable.Builder tallSeagrassDrops(HolderLookup.Provider lookup, BlockLootSubProvider generator) {
        HolderLookup.RegistryLookup<Block> impl = lookup.lookupOrThrow(Registries.BLOCK);
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(Blocks.SEAGRASS)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                .when(generator.hasShears())
                .otherwise(
                        ((LootPoolSingletonContainer.Builder<?>)generator.applyExplosionCondition(Blocks.TALL_SEAGRASS, LootItem.lootTableItem(YavpmItems.RICE_SEEDS)))
                                .when(LootItemRandomChanceCondition.randomChance(0.125F))
                );
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(builder)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_SEAGRASS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER))
                                )
                                .when(
                                        LocationCheck.checkLocation(
                                                LocationPredicate.Builder.location()
                                                        .setBlock(BlockPredicate.Builder.block().of(impl, Blocks.TALL_SEAGRASS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER))),
                                                new BlockPos(0, 1, 0)
                                        )
                                )
                )
                .withPool(
                        LootPool.lootPool()
                                .add(builder)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_SEAGRASS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER))
                                )
                                .when(
                                        LocationCheck.checkLocation(
                                                LocationPredicate.Builder.location()
                                                        .setBlock(BlockPredicate.Builder.block().of(impl, Blocks.TALL_SEAGRASS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER))),
                                                new BlockPos(0, -1, 0)
                                        )
                                )
                );
    }
    private static LootTable.Builder newOakLeavesDrops(HolderLookup.Provider lookup, BlockLootSubProvider generator) {
        HolderLookup.RegistryLookup<Enchantment> impl = lookup.lookupOrThrow(Registries.ENCHANTMENT);
        return generator.createLeavesDrops(Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, 0.05F, 0.0625F, 0.083333336F, 0.1F)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(generator.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        ((LootPoolSingletonContainer.Builder<?>)generator.applyExplosionCondition(Blocks.OAK_LEAVES, LootItem.lootTableItem(YavpmItems.ACORN)))
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                )
                );
    }
    private static LootTable.Builder newBirchLeavesDrops(HolderLookup.Provider lookup, BlockLootSubProvider generator) {
        HolderLookup.RegistryLookup<Enchantment> impl = lookup.lookupOrThrow(Registries.ENCHANTMENT);
        return generator.createLeavesDrops(Blocks.BIRCH_LEAVES, Blocks.BIRCH_SAPLING, 0.05F, 0.0625F, 0.083333336F, 0.1F)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(generator.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        ((LootPoolSingletonContainer.Builder<?>)generator.applyExplosionCondition(Blocks.BIRCH_LEAVES, LootItem.lootTableItem(YavpmItems.BIRCH_SEEDS)))
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                )
                );
    }
}
