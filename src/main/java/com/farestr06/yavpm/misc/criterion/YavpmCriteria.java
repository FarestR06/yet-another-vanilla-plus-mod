package com.farestr06.yavpm.misc.criterion;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmCriteria {
    public static final FakeBlockDestroyedCriterion FAKE_BLOCK_DESTROYED = register("fake_block_destroyed", new FakeBlockDestroyedCriterion());

    public static <T extends Criterion<?>> T register(String id, T criterion) {
        return Registry.register(Registries.CRITERION, makeId(id), criterion);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering advancement criteria for YAVPM!");
    }
}
