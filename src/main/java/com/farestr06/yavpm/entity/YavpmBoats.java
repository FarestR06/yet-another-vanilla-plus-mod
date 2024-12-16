package com.farestr06.yavpm.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBoats {
    public static final Identifier APPLE_BOAT_TYPE_ID = makeId("apple");
    public static final Identifier APPLE_BOAT_ID = makeId("apple_boat");
    public static final Identifier APPLE_CHEST_BOAT_ID = makeId("apple_chest_boat");

    public static final Identifier PERSIMMON_BOAT_TYPE_ID = makeId("persimmon");
    public static final Identifier PERSIMMON_BOAT_ID = makeId("persimmon_boat");
    public static final Identifier PERSIMMON_CHEST_BOAT_ID = makeId("persimmon_chest_boat");

    public static final RegistryKey<TerraformBoatType> APPLE_BOAT_KEY = TerraformBoatTypeRegistry.createKey(APPLE_BOAT_TYPE_ID);
    public static final RegistryKey<TerraformBoatType> PERSIMMON_BOAT_KEY = TerraformBoatTypeRegistry.createKey(PERSIMMON_BOAT_TYPE_ID);

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering boat types for YAVPM!!");
        TerraformBoatType appleBoat = new TerraformBoatType.Builder()
                .item(YavpmItems.APPLE_BOAT)
                .chestItem(YavpmItems.APPLE_CHEST_BOAT)
                .planks(YavpmBlocks.APPLE_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, APPLE_BOAT_KEY, appleBoat);
        TerraformBoatType persimmonBoat = new TerraformBoatType.Builder()
                .item(YavpmItems.PERSIMMON_BOAT)
                .chestItem(YavpmItems.PERSIMMON_CHEST_BOAT)
                .planks(YavpmBlocks.PERSIMMON_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, PERSIMMON_BOAT_KEY, persimmonBoat);

    }
}
