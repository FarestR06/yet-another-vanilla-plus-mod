package com.farestr06.yavpm;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.crafting.YavpmRecipeSerializers;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.YavpmTrades;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.ItemGroupHelper;
import com.farestr06.yavpm.item.YavpmArmorMaterials;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.YavpmPotions;
import com.farestr06.yavpm.item.enchantment.condition.YavpmLootConditions;
import com.farestr06.yavpm.item.enchantment.effect.YavpmEnchantmentEffects;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import com.farestr06.yavpm.world.gen.YavpmWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YetAnotherVanillaPlusMod implements ModInitializer {
	public static final String MOD_ID = "yavpm";

	public static Identifier makeId(String path) {
		return Identifier.of(MOD_ID, path);
	}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Go go gadget YAVPM!!");

		YavpmConfig.HANDLER.load();

		ItemGroupHelper.modifyEntries();

		YavpmItems.init();
		YavpmBlocks.init();
		YavpmFluids.init();
		YavpmBlockEntities.init();

		YavpmArmorMaterials.init();

		YavpmSounds.init();

		YavpmStatusEffects.init();
		YavpmPotions.init();

		YavpmWorldGeneration.generateModWorldGen();
		YavpmEnchantmentEffects.init();
		YavpmLootConditions.init();

		YavpmEntities.init();

		YavpmRecipeSerializers.init();

		modifyLoot();
		YavpmTrades.init();

		setUpVanillaTweaksCompat();
    }
	private static void setUpVanillaTweaksCompat() {
		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("back_to_blocks"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Text.literal("Vanilla Tweaks \"Back to Blocks\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("Back to Blocks compat registered successfully!");
		} else {
			LOGGER.error("Failed to register Back to Blocks compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_bark"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Text.literal("Vanilla Tweaks \"More Bark\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Bark compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Bark compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_stairs"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Text.literal("Vanilla Tweaks \"More Stairs\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Stairs compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Stairs compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_trapdoors"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Text.literal("Vanilla Tweaks \"More Trapdoors\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Trapdoors compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Trapdoors compat!");
		}
	}
	private static void modifyLoot() {
		LOGGER.info("Modifying loot for YAVPM!");
		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			RegistryWrapper.Impl<Enchantment> enchantmentImpl = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
			if (source.isBuiltin() && key.equals(LootTables.PIGLIN_BARTERING_GAMEPLAY)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(YavpmItems.GAUNTLET_FRAGMENT))
						.conditionally(RandomChanceLootCondition.builder(0.079f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.SNIFFER_DIGGING_GAMEPLAY)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(YavpmItems.TRUFFLE))
						.with(ItemEntry.builder(YavpmItems.MAGIC_BEAN));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(EntityType.ZOMBIE.getLootTableId())) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.MAGIC_BEAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))))
						.with(ItemEntry.builder(YavpmItems.PEANUT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))))
						.conditionally(KilledByPlayerLootCondition.builder())
						.conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(registries, 0.025F, 0.01F));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.BASTION_TREASURE_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.GAUNTLET_FRAGMENT).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f))
						))
						.conditionally(RandomChanceLootCondition.builder(0.79f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.BASTION_BRIDGE_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.GAUNTLET_FRAGMENT))
						.conditionally(RandomChanceLootCondition.builder(0.11f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.BASTION_HOGLIN_STABLE_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.GAUNTLET_FRAGMENT))
						.conditionally(RandomChanceLootCondition.builder(0.11f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.BASTION_OTHER_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.GAUNTLET_FRAGMENT))
						.conditionally(RandomChanceLootCondition.builder(0.079f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.SIMPLE_DUNGEON_CHEST)) {
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
			if (source.isBuiltin() && key.equals(LootTables.ANCIENT_CITY_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
						.with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
						.with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
						.conditionally(RandomChanceLootCondition.builder(0.05f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.TRIAL_CHAMBER_CONSUMABLES_SPAWNER)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.COOKED_PEANUT)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 4f)))
								.weight(24)
						)
						.with(ItemEntry.builder(YavpmItems.COOKED_PEANUT)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 4f)))
								.weight(24)
						)
						.with(ItemEntry.builder(YavpmItems.PERSIMMON)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)))
								.weight(20)
						)
						.with(ItemEntry.builder(YavpmItems.GOLDEN_PERSIMMON)
								.weight(2)
						)
						.with(ItemEntry.builder(YavpmItems.MOLY)
								.weight(2)
						)
						.conditionally(RandomChanceLootCondition.builder(0.4f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.CAT_MORNING_GIFT_GAMEPLAY)) {
				LootPool.Builder poolBuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.MAGIC_BEAN)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)))
								.weight(12)
						)
						.with(ItemEntry.builder(Items.CARROT)
								.weight(12)
						)
						.with(ItemEntry.builder(YavpmItems.MOLY).weight(1))
						.conditionally(RandomChanceLootCondition.builder(0.18f));
				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.DISC_FRAGMENT_MAGNETIC_CIRCUIT))
						.conditionally(RandomChanceLootCondition.builder(0.2f));

				tableBuilder.pool(poolBuilder1).pool(poolBuilder2);
			}
			if (source.isBuiltin() && key.equals(LootTables.UNDERWATER_RUIN_BIG_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_MALL))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.WOODLAND_MANSION_CHEST)) {
				LootPool.Builder poolBuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_STAL))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
						.with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
						.with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
						.conditionally(RandomChanceLootCondition.builder(0.008f));

				tableBuilder.pool(poolBuilder1).pool(poolBuilder2);
			}
			if (source.isBuiltin() && key.equals(LootTables.DESERT_PYRAMID_CHEST)) {
				LootPool.Builder poolbuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.MOLY))
						.conditionally(RandomChanceLootCondition.builder(ConstantLootNumberProvider.create(0.08f)));

				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_FAR))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				LootPool.Builder poolBuilder3 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
						.with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
						.with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
						.conditionally(RandomChanceLootCondition.builder(0.005f));

				tableBuilder.pool(poolbuilder1).pool(poolBuilder2).pool(poolBuilder3);
			}
			if (source.isBuiltin() && key.equals(LootTables.JUNGLE_TEMPLE_CHEST)) {
				LootPool.Builder poolBuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.BANANA_SEEDS)).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 6f))
						)
						.with(ItemEntry.builder(YavpmItems.RICE)).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 8f))
						)
						.conditionally(RandomChanceLootCondition.builder(0.24f));

				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_CHIRP))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				LootPool.Builder poolBuilder3 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.FORTUNE_COOKIE))
						.conditionally(RandomChanceLootCondition.builder(0.24f));

				tableBuilder.pool(poolBuilder1).pool(poolBuilder2).pool(poolBuilder3);
			}
			if (source.isBuiltin() && key.equals(LootTables.IGLOO_CHEST_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_BLOCKS))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.STRONGHOLD_CORRIDOR_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.RUNE_ATTACK))
						.with(ItemEntry.builder(YavpmItems.RUNE_DURABILITY))
						.with(ItemEntry.builder(YavpmItems.RUNE_SPEED))
						.conditionally(RandomChanceLootCondition.builder(0.012f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.STRONGHOLD_CROSSING_CHEST)) {
				LootPool.Builder poolBuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_11))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1f, 2f))
						.with(ItemEntry.builder(YavpmItems.RICE_SEEDS).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))
						))
						.with(ItemEntry.builder(Items.CARROT).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))
						))
						.with(ItemEntry.builder(Items.POTATO).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))
						))
						.with(ItemEntry.builder(YavpmItems.MAGIC_BEAN).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))
						))
						.with(ItemEntry.builder(YavpmItems.PEANUT).apply(
								SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f))
						));

				LootPool.Builder poolBuilder3 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.APPLE).weight(15))
						.with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(5))
						.with(ItemEntry.builder(YavpmItems.PERSIMMON).weight(15))
						.with(ItemEntry.builder(YavpmItems.GOLDEN_PERSIMMON).weight(5))
						.with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1))
						.conditionally(RandomChanceLootCondition.builder(0.2f));

				tableBuilder.pool(poolBuilder1).pool(poolBuilder2).pool(poolBuilder3);
			}
			if (source.isBuiltin() && key.equals(LootTables.STRONGHOLD_LIBRARY_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder(registries).options(
								enchantmentImpl.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
						.conditionally(RandomChanceLootCondition.builder(0.67f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.END_CITY_TREASURE_CHEST)) {
				LootPool.Builder poolBuilder1 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder(registries).options(
								enchantmentImpl.getOrThrow(YavpmTags.Enchantments.END_ENCHANTMENTS))))
						.conditionally(RandomChanceLootCondition.builder(0.24f));
				LootPool.Builder poolBuilder2 = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(YavpmItems.PHANTOM_CHORD))
						.conditionally(RandomChanceLootCondition.builder(0.011f));

				tableBuilder.pool(poolBuilder1).pool(poolBuilder2);
			}
			if (source.isBuiltin() && key.equals(LootTables.NETHER_BRIDGE_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_WARD))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && key.equals(LootTables.BURIED_TREASURE_CHEST)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1f))
						.with(ItemEntry.builder(Items.MUSIC_DISC_MELLOHI))
						.with(ItemEntry.builder(Items.MUSIC_DISC_WAIT))
						.conditionally(RandomChanceLootCondition.builder(0.19f));

				tableBuilder.pool(poolBuilder);
			}
		});
		LootTableEvents.REPLACE.register((key, original, source, registries) -> {
			BlockLootTableGenerator generator = new VanillaBlockLootTableGenerator(registries);
			if (source.isBuiltin() && key == Blocks.OAK_LEAVES.getLootTableKey()) {
				return newOakLeavesDrops(registries, generator).build();
			}
			if (source.isBuiltin() && key == Blocks.SEAGRASS.getLootTableKey()) {
				return shortSeagrassDrops(registries, generator).build();
			}
			if (source.isBuiltin() && key == Blocks.TALL_SEAGRASS.getLootTableKey()) {
				return tallSeagrassDrops(generator).build();
			}
			if (source.isBuiltin() && key == Blocks.GRANITE.getLootTableKey()) {
				return generator.drops(Blocks.GRANITE, YavpmBlocks.COBBLED_GRANITE).build();
			}
			if (source.isBuiltin() && key == Blocks.DIORITE.getLootTableKey()) {
				return generator.drops(Blocks.DIORITE, YavpmBlocks.COBBLED_DIORITE).build();
			}
			if (source.isBuiltin() && key == Blocks.ANDESITE.getLootTableKey()) {
				return generator.drops(Blocks.ANDESITE, YavpmBlocks.COBBLED_ANDESITE).build();
			}
			return original;
		});
	}
	
	private static LootTable.Builder shortSeagrassDrops(RegistryWrapper.WrapperLookup lookup, BlockLootTableGenerator generator) {
		RegistryWrapper.Impl<Enchantment> impl = lookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return generator.dropsWithShears(
				Blocks.SEAGRASS,
                generator.applyExplosionDecay(
						Blocks.SEAGRASS,
                        ItemEntry.builder(YavpmItems.RICE_SEEDS)
                                .conditionally(RandomChanceLootCondition.builder(0.125F))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 2))
                )
		);
	}
	private static LootTable.Builder tallSeagrassDrops(BlockLootTableGenerator generator) {
		LootPoolEntry.Builder<?> builder = ItemEntry.builder(Blocks.SEAGRASS)
				.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
				.conditionally(BlockLootTableGenerator.WITH_SHEARS)
				.alternatively(
						((LeafEntry.Builder<?>)generator.addSurvivesExplosionCondition(Blocks.TALL_SEAGRASS, ItemEntry.builder(YavpmItems.RICE_SEEDS)))
								.conditionally(RandomChanceLootCondition.builder(0.125F))
				);
		return LootTable.builder()
				.pool(
						LootPool.builder()
								.with(builder)
								.conditionally(
										BlockStatePropertyLootCondition.builder(Blocks.TALL_SEAGRASS).properties(StatePredicate.Builder.create().exactMatch(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER))
								)
								.conditionally(
										LocationCheckLootCondition.builder(
												LocationPredicate.Builder.create()
														.block(BlockPredicate.Builder.create().blocks(Blocks.TALL_SEAGRASS).state(StatePredicate.Builder.create().exactMatch(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER))),
												new BlockPos(0, 1, 0)
										)
								)
				)
				.pool(
						LootPool.builder()
								.with(builder)
								.conditionally(
										BlockStatePropertyLootCondition.builder(Blocks.TALL_SEAGRASS).properties(StatePredicate.Builder.create().exactMatch(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER))
								)
								.conditionally(
										LocationCheckLootCondition.builder(
												LocationPredicate.Builder.create()
														.block(BlockPredicate.Builder.create().blocks(Blocks.TALL_SEAGRASS).state(StatePredicate.Builder.create().exactMatch(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER))),
												new BlockPos(0, -1, 0)
										)
								)
				);
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