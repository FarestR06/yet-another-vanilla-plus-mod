package com.farestr06.yavpm.datagen.condition;

import com.farestr06.yavpm.config.YavpmConfig;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryOps;
import org.jetbrains.annotations.Nullable;

public record RareEquipmentRecipesEnabledResourceCondition() implements ResourceCondition {
    public static final MapCodec<RareEquipmentRecipesEnabledResourceCondition> CODEC
            = MapCodec.unit(RareEquipmentRecipesEnabledResourceCondition::new);


    @Override
    public ResourceConditionType<?> getType() {
        return YavpmResourceConditionTypes.RARE_EQUIPMENT_RECIPES_ENABLED;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoGetter registryInfo) {
        return YavpmConfig.HANDLER.instance().rareEquipmentCraftingRecipes;
    }
}
