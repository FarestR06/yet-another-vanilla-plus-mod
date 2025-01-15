package com.farestr06.yavpm.item.enchantment.condition;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmLootConditions {
    public static final LootConditionType ENTITY_WETNESS = register("entity_wetness", EntityWetnessLootCondition.CODEC);

    private static LootConditionType register(String id, MapCodec<? extends LootCondition> codec) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, makeId(id), new LootConditionType(codec));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering loot conditions for YAVPM!");
    }
}
