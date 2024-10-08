package com.farestr06.yafm.entity;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.YavpmItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.farestr06.yafm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBoats {
    public static final Identifier APPLE_BOAT_TYPE_ID = makeId("apple");
    public static final Identifier APPLE_BOAT_ID = makeId("apple_boat");
    public static final Identifier APPLE_CHEST_BOAT_ID = makeId("apple_chest_boat");

    public static final RegistryKey<TerraformBoatType> APPLE_BOAT_KEY = TerraformBoatTypeRegistry.createKey(APPLE_BOAT_TYPE_ID);

    public static void register() {

        TerraformBoatType appleBoat = new TerraformBoatType.Builder()
                .item(YavpmItems.APPLE_BOAT)
                .chestItem(YavpmItems.APPLE_CHEST_BOAT)
                .planks(YavpmBlocks.APPLE_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, APPLE_BOAT_KEY, appleBoat);
    }

    public static TerraformBoatType getAppleBoat() {
        return TerraformBoatTypeRegistry.INSTANCE.get(APPLE_BOAT_TYPE_ID);
    }
}
