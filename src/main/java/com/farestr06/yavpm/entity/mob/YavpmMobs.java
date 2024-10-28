package com.farestr06.yavpm.entity.mob;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOGGER;

public class YavpmMobs {
    public static final EntityType<MoongusEntity> MOONGUS = register(
            "moongus",
            EntityType.Builder.create(MoongusEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .maxTrackingRange(10)
                    .makeFireImmune()
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, makeId(id), type.build(id));
    }

    public static void init() {
        LOGGER.info("Registering mobs for YAVPM!!");
        FabricDefaultAttributeRegistry.register(MOONGUS, CowEntity.createCowAttributes().build());
    }
}
