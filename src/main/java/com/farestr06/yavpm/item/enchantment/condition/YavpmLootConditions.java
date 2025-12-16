package com.farestr06.yavpm.item.enchantment.condition;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmLootConditions {
    public static final LootItemConditionType ENTITY_WETNESS = register("entity_wetness", EntityWetnessLootCondition.CODEC);

    private static LootItemConditionType register(String id, MapCodec<? extends LootItemCondition> codec) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, makeId(id), new LootItemConditionType(codec));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering loot conditions for YAVPM!");
    }
}
