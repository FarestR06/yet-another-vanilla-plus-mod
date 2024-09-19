package com.farestr06.yafm.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class YetAnotherVanillaPlusModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(YavpmLangProvider::new);
		pack.addProvider(YavpmModelProvider::new);
		pack.addProvider(YavpmRecipeProvider::new);
		pack.addProvider(YavpmLootProviders.Block::new);
		pack.addProvider(YavpmTagProviders.Item::new);
		pack.addProvider(YavpmTagProviders.Block::new);
		pack.addProvider(YavpmTagProviders.EntityType::new);
	}
}
