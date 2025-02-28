package com.farestr06.yavpm.datagen.condition;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmResourceConditionTypes {
    public static final ResourceConditionType<RareEquipmentRecipesEnabledResourceCondition> RARE_EQUIPMENT_RECIPES_ENABLED =
            createResourceConditionType("rare_equipment_recipes_enabled", RareEquipmentRecipesEnabledResourceCondition.CODEC);

    private static <T extends ResourceCondition> ResourceConditionType<T> createResourceConditionType(String name, MapCodec<T> codec) {
        return ResourceConditionType.create(makeId(name), codec);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering resource conditions for YAVPM!");

        ResourceConditions.register(RARE_EQUIPMENT_RECIPES_ENABLED);
    }
}
