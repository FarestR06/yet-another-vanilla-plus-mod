package com.farestr06.yavpm.entity.mob;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.Vec3d;

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

    public static final EntityType<CarbonfowlEntity> CARBONFOWL = register(
            "carbonfowl",
            EntityType.Builder.create(CarbonfowlEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3d(0.0, 0.7, -0.1))
                    .maxTrackingRange(10)
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, makeId(id), type.build(id));
    }

    public static void init() {
        LOGGER.info("Registering mobs for YAVPM!!");
        FabricDefaultAttributeRegistry.register(MOONGUS, CowEntity.createCowAttributes().build());
        FabricDefaultAttributeRegistry.register(CARBONFOWL, CarbonfowlEntity.createCarbonfowlAttributes().build());
    }
}
