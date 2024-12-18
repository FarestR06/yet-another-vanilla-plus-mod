package com.farestr06.yavpm.fluid;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmFluids {
    public static final FlowableFluid STILL_VOID_WATER = Registry.register(Registries.FLUID, makeId("void_water"), new VoidWaterFluid.Still());
    public static final FlowableFluid FLOWING_VOID_WATER = Registry.register(Registries.FLUID, makeId("flowing_void_water"), new VoidWaterFluid.Flowing());

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering fluids for YAVPM!");
    }
}
