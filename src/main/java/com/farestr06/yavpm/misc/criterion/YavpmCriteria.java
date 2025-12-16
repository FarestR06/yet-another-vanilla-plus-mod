package com.farestr06.yavpm.misc.criterion;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmCriteria {
    public static final FakeBlockDestroyedCriterion FAKE_BLOCK_DESTROYED = register("fake_block_destroyed", new FakeBlockDestroyedCriterion());

    public static <T extends CriterionTrigger<?>> T register(String id, T criterion) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, makeId(id), criterion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering advancement criteria for YAVPM!");
    }
}
