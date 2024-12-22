package com.farestr06.yavpm.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.mob.CarbonfowlEntity;
import com.farestr06.yavpm.entity.mob.MoongusEntity;
import com.farestr06.yavpm.entity.mob.TanukiEntity;
import com.farestr06.yavpm.entity.mob.VoidPhantomEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.Vec3d;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOGGER;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEntities {
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
    public static final EntityType<TanukiEntity> TANUKI = register(
            "tanuki",
            EntityType.Builder.create(TanukiEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6F, 0.7F)
                    .eyeHeight(0.4F)
                    .passengerAttachments(new Vec3d(0.0, 0.6375, -0.25))
                    .maxTrackingRange(8)
    );
    public static final EntityType<VoidPhantomEntity> VOID_PHANTOM = register(
            "void_phantom",
            EntityType.Builder.create(VoidPhantomEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.9F, 0.5F)
                    .eyeHeight(0.175F)
                    .passengerAttachments(0.3375F)
                    .vehicleAttachment(-0.125F)
                    .maxTrackingRange(8)
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, makeId(id), type.build(id));
    }

    public static void init() {
        LOGGER.info("Registering entities for YAVPM!!");

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Moongus attributes...");
        FabricDefaultAttributeRegistry.register(MOONGUS, CowEntity.createCowAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Carbonfowl attributes...");
        FabricDefaultAttributeRegistry.register(CARBONFOWL, CarbonfowlEntity.createCarbonfowlAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Tanuki attributes...");
        FabricDefaultAttributeRegistry.register(TANUKI, TanukiEntity.createTanukiAttributes().build());
    }
}
