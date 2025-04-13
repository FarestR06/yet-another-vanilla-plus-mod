package com.farestr06.yavpm.datagen.condition;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.datagen.condition.experiment.RecyclerExperimentResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.DoubleSlabsEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.DropperToRecyclerEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.MoreStairsEnabledResourceCondition;
import com.farestr06.yavpm.datagen.condition.vanillatweaks.MoreTrapdoorsEnabledResourceCondition;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmResourceConditionTypes {
    public static final ResourceConditionType<RareEquipmentRecipesEnabledResourceCondition> RARE_EQUIPMENT_RECIPES_ENABLED =
            createResourceConditionType("rare_equipment_recipes_enabled", RareEquipmentRecipesEnabledResourceCondition.CODEC);

    public static final ResourceConditionType<RecyclerExperimentResourceCondition> RECYCLER_EXPERIMENT =
            createResourceConditionType("recycler_experiment", RecyclerExperimentResourceCondition.CODEC);

    public static final ResourceConditionType<DropperToRecyclerEnabledResourceCondition> DROPPER_TO_RECYCLER_ENABLED =
            createResourceConditionType("dropper_to_recycler_enabled", DropperToRecyclerEnabledResourceCondition.CODEC);

    public static final ResourceConditionType<DoubleSlabsEnabledResourceCondition> DOUBLE_SLABS_ENABLED =
            createResourceConditionType("double_slabs_enabled", DoubleSlabsEnabledResourceCondition.CODEC);

    public static final ResourceConditionType<MoreTrapdoorsEnabledResourceCondition> MORE_TRAPDOORS_ENABLED =
            createResourceConditionType("more_trapdoors_enabled", MoreTrapdoorsEnabledResourceCondition.CODEC);

    public static final ResourceConditionType<MoreStairsEnabledResourceCondition> MORE_STAIRS_ENABLED =
            createResourceConditionType("more_stairs_enabled", MoreStairsEnabledResourceCondition.CODEC);

    private static <T extends ResourceCondition> ResourceConditionType<T> createResourceConditionType(String name, MapCodec<T> codec) {
        ResourceConditionType<T> type = ResourceConditionType.create(makeId(name), codec);
        ResourceConditions.register(type);
        return type;
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering resource conditions for YAVPM!");
    }
}
