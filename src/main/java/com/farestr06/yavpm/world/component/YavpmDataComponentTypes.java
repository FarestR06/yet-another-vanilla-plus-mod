package com.farestr06.yavpm.world.component;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.mob.FungusCowEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmDataComponentTypes {
    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, makeId(id), builderOperator.apply(DataComponentType.builder()).build());
    }

    public static class Item {

        public static final DataComponentType<CopperInstrument> COPPER_INSTRUMENT = register(
                "copper_instrument", builder ->
                        builder.persistent(CopperInstrument.CODEC).networkSynchronized(CopperInstrument.PACKET_CODEC)
        );
        public static final DataComponentType<AlwaysHatchesComponent> ALWAYS_HATCHES = register("always_hatches", builder -> builder.persistent(AlwaysHatchesComponent.CODEC).networkSynchronized(StreamCodec.unit(AlwaysHatchesComponent.INSTANCE)));
        public static final DataComponentType<Unit> HATCHES_CARBONFOWL = register("hatches_carbonfowl", builder -> builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

        protected static void init() {
            YetAnotherVanillaPlusMod.LOGGER.info("Registering item data component types for YAVPM!");
        }
    }

    public static class Entity {
        public static final DataComponentType<FungusCowEntity.Variant> MOONGUS_VARIANT = register(
                "moongus/variant", builder -> builder.persistent(FungusCowEntity.Variant.CODEC)
                        .networkSynchronized(FungusCowEntity.Variant.PACKET_CODEC)
        );

        protected static void init() {
            YetAnotherVanillaPlusMod.LOGGER.info("Registering entity data component types for YAVPM!");
        }
    }

    public static void init() {
        Item.init();
        Entity.init();
    }
}