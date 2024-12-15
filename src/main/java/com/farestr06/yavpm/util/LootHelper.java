package com.farestr06.yavpm.util;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

public class LootHelper {
    public static void modifyLoot() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin()) {
                RegistryWrapper.Impl<Enchantment> enchantmentImpl = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
                // region Entities
                if (key == LootTables.SNIFFER_DIGGING_GAMEPLAY) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(YavpmItems.TRUFFLE))
                            .with(ItemEntry.builder(YavpmItems.MAGIC_BEAN));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == EntityType.ZOMBIE.getLootTableId()) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.MAGIC_BEAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))))
                            .with(ItemEntry.builder(YavpmItems.PEANUT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))))
                            .conditionally(KilledByPlayerLootCondition.builder())
                            .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registries, 0.025F, 0.01F));

                    tableBuilder.pool(poolBuilder);
                }
                // endregion

                // region Chests
                if (key == LootTables.SIMPLE_DUNGEON_CHEST) {
                    LootPool.Builder poolBuilder1 = LootPool.builder()
                            .rolls(UniformLootNumberProvider.create(1f,3f))
                            .with(ItemEntry.builder(YavpmItems.PEANUT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                            .conditionally(RandomChanceLootCondition.builder(0.35f));

                    LootPool.Builder poolBuilder2 = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
                            .with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
                            .with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
                            .conditionally(RandomChanceLootCondition.builder(0.005f));


                    tableBuilder.pool(poolBuilder1).pool(poolBuilder2);
                }
                if (key == LootTables.ANCIENT_CITY_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
                            .with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
                            .with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
                            .conditionally(RandomChanceLootCondition.builder(0.015f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.TRIAL_CHAMBER_CONSUMABLES_SPAWNER) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.MAGIC_BEAN))
                            .conditionally(RandomChanceLootCondition.builder(0.08f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.CAT_MORNING_GIFT_GAMEPLAY) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.MAGIC_BEAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f))))
                            .conditionally(RandomChanceLootCondition.builder(0.1f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.UNDERWATER_RUIN_BIG_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_MALL))
                            .conditionally(RandomChanceLootCondition.builder(0.08f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.WOODLAND_MANSION_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_STAL))
                            .conditionally(RandomChanceLootCondition.builder(0.08f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.DESERT_PYRAMID_CHEST) {
                    LootPool.Builder poolbuilder1 = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.MOLY).apply(
                                    SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 8f)))
                            );

                    LootPool.Builder poolBuilder2 = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_FAR))
                            .conditionally(RandomChanceLootCondition.builder(0.08f));

                    tableBuilder.pool(poolbuilder1).pool(poolBuilder2);
                }
                if (key == LootTables.JUNGLE_TEMPLE_CHEST) {
                    LootPool.Builder poolBuilder1 = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.BANANA_SEEDS)).apply(
                                    SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 6f))
                            ).conditionally(RandomChanceLootCondition.builder(0.24f));

                    LootPool.Builder poolBuilder2 = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_CHIRP))
                            .conditionally(RandomChanceLootCondition.builder(0.8f));

                    tableBuilder.pool(poolBuilder1).pool(poolBuilder2);
                }
                if (key == LootTables.IGLOO_CHEST_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_BLOCKS))
                            .conditionally(RandomChanceLootCondition.builder(0.8f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.STRONGHOLD_CORRIDOR_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
                            .with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
                            .with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
                            .conditionally(RandomChanceLootCondition.builder(0.01f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.STRONGHOLD_CROSSING_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_11))
                            .conditionally(RandomChanceLootCondition.builder(0.11f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.STRONGHOLD_LIBRARY_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder(registries).options(
                                    enchantmentImpl.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
                            .conditionally(RandomChanceLootCondition.builder(0.11f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.END_CITY_TREASURE_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder(registries).options(
                                    enchantmentImpl.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
                            .conditionally(RandomChanceLootCondition.builder(0.22f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.NETHER_BRIDGE_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_WARD))
                            .conditionally(RandomChanceLootCondition.builder(0.8f));

                    tableBuilder.pool(poolBuilder);
                }
                if (key == LootTables.BURIED_TREASURE_CHEST) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1f))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_MELLOHI))
                            .with(ItemEntry.builder(Items.MUSIC_DISC_WAIT))
                            .conditionally(RandomChanceLootCondition.builder(0.19f));

                    tableBuilder.pool(poolBuilder);
                }
                // endregion
            }
        });

        LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            BlockLootTableGenerator generator = new VanillaBlockLootTableGenerator(registries);
if (source.isBuiltin()) {
                if (key == Blocks.OAK_LEAVES.getLootTableKey()) {
                    return newOakLeavesDrops(registries, generator).build();
                }
                if (key == Blocks.GRANITE.getLootTableKey()) {
                    return generator.drops(Blocks.GRANITE, YavpmBlocks.COBBLED_GRANITE).build();
                }
                if (key == Blocks.DIORITE.getLootTableKey()) {
                    return generator.drops(Blocks.DIORITE, YavpmBlocks.COBBLED_DIORITE).build();
                }
                if (key == Blocks.ANDESITE.getLootTableKey()) {
                    return generator.drops(Blocks.ANDESITE, YavpmBlocks.COBBLED_ANDESITE).build();
                }
            }
return original;
});
    }

    private static LootTable.Builder newOakLeavesDrops(RegistryWrapper.WrapperLookup lookup, BlockLootTableGenerator generator) {
        RegistryWrapper.Impl<Enchantment> impl = lookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return generator.leavesDrops(Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, 0.05F, 0.0625F, 0.083333336F, 0.1F)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(generator.createWithoutShearsOrSilkTouchCondition())
                                .with(
                                        ((LeafEntry.Builder<?>)generator.addSurvivesExplosionCondition(Blocks.OAK_LEAVES, ItemEntry.builder(YavpmItems.ACORN)))
                                                .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                )
                );
    }
}
