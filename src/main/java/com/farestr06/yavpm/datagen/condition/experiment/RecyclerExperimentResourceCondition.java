package com.farestr06.yavpm.datagen.condition.experiment;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.datagen.condition.YavpmResourceConditionTypes;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryOps;
import org.jetbrains.annotations.Nullable;

public record RecyclerExperimentResourceCondition() implements ResourceCondition {
    public static final MapCodec<RecyclerExperimentResourceCondition> CODEC
            = MapCodec.unit(RecyclerExperimentResourceCondition::new);

    @Override
    public ResourceConditionType<?> getType() {
        return YavpmResourceConditionTypes.RECYCLER_EXPERIMENT;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoGetter registryInfo) {
        return YavpmConfig.HANDLER.instance().recyclerExperiment;
    }
}
