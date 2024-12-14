package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.world.YavpmConfiguredFeatures;
import com.farestr06.yavpm.world.YavpmPlacedFeatures;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class YetAnotherVanillaPlusModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(YavpmLangProvider::new);
		pack.addProvider(YavpmModelProvider::new);
		pack.addProvider(YavpmRecipeProvider::new);
		pack.addProvider(YavpmLootProviders.Block::new);
		pack.addProvider(YavpmLootProviders.Entity::new);
		pack.addProvider(YavpmTagProviders.Item::new);
		pack.addProvider(YavpmTagProviders.Block::new);
		pack.addProvider(YavpmTagProviders.Fluid::new);
		pack.addProvider(YavpmTagProviders.EntityType::new);
		pack.addProvider(YavpmTagProviders.Biome::new);
		pack.addProvider(YavpmTagProviders.Enchantment::new);
		pack.addProvider(YavpmAdvancementProvider::new);
		pack.addProvider(YavpmMiscDataGenerator::new);
		pack.addProvider(YavpmWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, YavpmConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, YavpmPlacedFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, YavpmEnchantments::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, YavpmBiomes.Overworld::bootstrap);
	}
}
