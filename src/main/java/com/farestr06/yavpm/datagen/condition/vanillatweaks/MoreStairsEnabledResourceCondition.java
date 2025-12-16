package com.farestr06.yavpm.datagen.condition.vanillatweaks;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.datagen.condition.YavpmResourceConditionTypes;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;

public record MoreStairsEnabledResourceCondition() implements ResourceCondition {
    public static final MapCodec<MoreStairsEnabledResourceCondition> CODEC
            = MapCodec.unit(MoreStairsEnabledResourceCondition::new);


    @Override
    public ResourceConditionType<?> getType() {
        return YavpmResourceConditionTypes.MORE_STAIRS_ENABLED;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfo) {
        return YavpmConfig.HANDLER.instance().doubleSlabs;
    }
}
