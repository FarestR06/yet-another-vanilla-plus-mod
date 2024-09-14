package com.farestr06.yafm;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.YavpmItems;
import com.farestr06.yafm.util.YavpmSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

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

		LOGGER.info("the j");

		YavpmSounds.init();
		YavpmItems.init();
		YavpmBlocks.init();

		try {
			if (ResourceManagerHelper.registerBuiltinResourcePack(
					makeId("programmer_art"),
					FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
					Text.translatable("resourcePack.yavpm.programmer_art.name"),
					ResourcePackActivationType.NORMAL
			)) {
				LOGGER.info("Programmer Art successfully loaded!");
			} else {
				LOGGER.warn("Couldn't load programmer art!");
			}
		} catch (NoSuchElementException e) {
			LOGGER.error("The mod isn't loaded, for some reason.", e);
		}
	}
}