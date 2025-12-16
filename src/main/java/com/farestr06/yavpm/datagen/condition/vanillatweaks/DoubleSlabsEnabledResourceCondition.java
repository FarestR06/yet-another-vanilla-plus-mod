package com.farestr06.yavpm.datagen.condition.vanillatweaks;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.datagen.condition.YavpmResourceConditionTypes;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;

public record DoubleSlabsEnabledResourceCondition() implements ResourceCondition {
    public static final MapCodec<DoubleSlabsEnabledResourceCondition> CODEC
            = MapCodec.unit(DoubleSlabsEnabledResourceCondition::new);


    @Override
    public ResourceConditionType<?> getType() {
        return YavpmResourceConditionTypes.DOUBLE_SLABS_ENABLED;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfo) {
        return YavpmConfig.HANDLER.instance().doubleSlabs;
    }
}
