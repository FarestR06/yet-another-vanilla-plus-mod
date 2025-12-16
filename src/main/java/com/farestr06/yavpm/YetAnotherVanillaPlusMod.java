package com.farestr06.yavpm;

import com.farestr06.api.util.LocationHelper;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResultRegistryHelper;
import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.datagen.condition.YavpmResourceConditionTypes;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.ItemGroupHelper;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.YavpmPotions;
import com.farestr06.yavpm.item.enchantment.condition.YavpmLootConditions;
import com.farestr06.yavpm.item.enchantment.effect.YavpmEnchantmentEffects;
import com.farestr06.yavpm.misc.YavpmHelpCommand;
import com.farestr06.yavpm.misc.YavpmStats;
import com.farestr06.yavpm.misc.criterion.YavpmCriteria;
import com.farestr06.yavpm.util.LootHelper;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.village.YavpmProfessions;
import com.farestr06.yavpm.village.YavpmTrades;
import com.farestr06.yavpm.world.YavpmGameRules;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import com.farestr06.yavpm.world.gen.YavpmWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YetAnotherVanillaPlusMod implements ModInitializer {
	public static final String MOD_ID = "yavpm";

	public static ResourceLocation makeId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
	public static final LocationHelper LOCATIONS = new LocationHelper(MOD_ID);

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
		if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
			LOGGER.warn("Also, YAVPM is a work in progress, so please report any issues you find!");
		}

		// Load config settings
		YavpmConfig.HANDLER.load();
		YavpmConfig.HANDLER.instance().experimentSetup();

		YavpmDataComponentTypes.init();

		YavpmItems.init();
		YavpmBlocks.init();
		YavpmFluids.init();
		YavpmBlockEntities.init();

		ItemGroupHelper.modifyEntries();

		YavpmSounds.init();

		YavpmStatusEffects.init();
		YavpmPotions.init();

		YavpmWorldGeneration.generateModWorldGen();
		YavpmCriteria.init();
		YavpmEnchantmentEffects.init();
		YavpmLootConditions.init();

		YavpmEntities.init();

		RecyclingResultRegistryHelper.init();
		YavpmResourceConditionTypes.init();

		LootHelper.modifyLoot();
		YavpmProfessions.init();
		YavpmTrades.init();

		YavpmGameRules.init();
		YavpmStats.init();
		if (YavpmConfig.HANDLER.instance().yavpmHelpExperiment) {
			YavpmHelpCommand.init();
		}

		// setUpVanillaTweaksCompat();
	}

	@Deprecated // Will be implemented using resource conditions rather than built-in packs
	private static void setUpVanillaTweaksCompat() {
		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("back_to_blocks"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.literal("Vanilla Tweaks \"Back to Blocks\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("Back to Blocks compat registered successfully!");
		} else {
			LOGGER.error("Failed to register Back to Blocks compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_bark"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.literal("Vanilla Tweaks \"More Bark\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Bark compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Bark compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_stairs"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.literal("Vanilla Tweaks \"More Stairs\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Stairs compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Stairs compat!");
		}

		if (ResourceManagerHelper.registerBuiltinResourcePack(
				makeId("more_trapdoors"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.literal("Vanilla Tweaks \"More Trapdoors\" Compatibility"),
				ResourcePackActivationType.NORMAL
		)) {
			LOGGER.info("More Trapdoors compat registered successfully!");
		} else {
			LOGGER.error("Failed to register More Trapdoors compat!");
		}
	}
}