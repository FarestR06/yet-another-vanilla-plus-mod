package com.farestr06.yavpm.datagen.condition.vanillatweaks;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.datagen.condition.YavpmResourceConditionTypes;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryOps;
import org.jetbrains.annotations.Nullable;

public record MoreTrapdoorsEnabledResourceCondition() implements ResourceCondition {
    public static final MapCodec<MoreTrapdoorsEnabledResourceCondition> CODEC
            = MapCodec.unit(MoreTrapdoorsEnabledResourceCondition::new);


    @Override
    public ResourceConditionType<?> getType() {
        return YavpmResourceConditionTypes.MORE_TRAPDOORS_ENABLED;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoGetter registryInfo) {
        return YavpmConfig.HANDLER.instance().doubleSlabs;
    }
}
