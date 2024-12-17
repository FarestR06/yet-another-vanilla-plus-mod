package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.world.feature.configured.YavpmConfiguredFeatureBootstrapper;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import com.farestr06.yavpm.world.feature.placed.YavpmPlacedFeatureBootstrapper;
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
		pack.addProvider(YavpmTagProviders.DamageType::new);
		pack.addProvider(YavpmTagProviders.Biome::new);
		pack.addProvider(YavpmTagProviders.Enchantment::new);
		pack.addProvider(YavpmAdvancementProvider::new);
		pack.addProvider(YavpmMiscDataGenerator::new);
		pack.addProvider(YavpmWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, YavpmDamageTypes::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, YavpmEnchantments::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, YavpmConfiguredFeatureBootstrapper::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, YavpmPlacedFeatureBootstrapper::boostrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, YavpmBiomes.Overworld::bootstrap);
	}
}
