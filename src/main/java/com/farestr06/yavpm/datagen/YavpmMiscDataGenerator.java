package com.farestr06.yavpm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class YavpmMiscDataGenerator extends FabricDynamicRegistryProvider {

    public YavpmMiscDataGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT));
    }

    @Override
    public String getName() {
        return "Misc Data";
    }
}
