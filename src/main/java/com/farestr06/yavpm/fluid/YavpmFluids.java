package com.farestr06.yavpm.fluid;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmFluids {
    public static final FlowingFluid STILL_VOID_WATER = Registry.register(BuiltInRegistries.FLUID, makeId("still_void_water"), new VoidWaterFluid.Still());
    public static final FlowingFluid FLOWING_VOID_WATER = Registry.register(BuiltInRegistries.FLUID, makeId("flowing_void_water"), new VoidWaterFluid.Flowing());

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering fluids for YAVPM!");
    }
}
