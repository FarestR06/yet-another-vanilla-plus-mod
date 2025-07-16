package com.farestr06.yavpm.util;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class YavpmLootTables {
    private static RegistryKey<LootTable> register(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, YetAnotherVanillaPlusMod.makeId(id));
    }

    public static class Shearing {
        public static final RegistryKey<LootTable> MOONGUS = register("shearing/moongus");
        public static final RegistryKey<LootTable> MOONGUS_CRIMSON = register("shearing/moongus/crimson");
        public static final RegistryKey<LootTable> MOONGUS_WARPED = register("shearing/moongus/warped");
    }
}
