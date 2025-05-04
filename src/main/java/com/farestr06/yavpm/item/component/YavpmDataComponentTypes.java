package com.farestr06.yavpm.item.component;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmDataComponentTypes {
    public static final ComponentType<CopperInstrument> COPPER_INSTRUMENT = register(
            "copper_instrument", builder ->
                    builder.codec(CopperInstrument.CODEC).packetCodec(CopperInstrument.PACKET_CODEC)
    );
    public static final ComponentType<Unit> ALWAYS_HATCHES = register("always_hatches", builder -> builder.codec(Unit.CODEC).packetCodec(PacketCodec.unit(Unit.INSTANCE)));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, makeId(id), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering data component types for YAVPM!");
    }
}