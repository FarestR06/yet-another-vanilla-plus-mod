package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.world.biome.YavpmBiomes;
import com.farestr06.yavpm.world.feature.configured.YavpmConfiguredFeatureBootstrapper;
import com.farestr06.yavpm.world.feature.placed.YavpmPlacedFeatureBootstrapper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

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
		pack.addProvider(YavpmTagProviders.Enchantments::new);
		pack.addProvider(YavpmAdvancementProvider::new);
		pack.addProvider(YavpmMiscDataGenerator::new);
		pack.addProvider(YavpmWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.DAMAGE_TYPE, YavpmDamageTypes::bootstrap);
		registryBuilder.add(Registries.ENCHANTMENT, YavpmEnchantments::bootstrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, YavpmConfiguredFeatureBootstrapper::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, YavpmPlacedFeatureBootstrapper::bootstrap);
		registryBuilder.add(Registries.BIOME, YavpmBiomes::bootstrap);
	}
}
