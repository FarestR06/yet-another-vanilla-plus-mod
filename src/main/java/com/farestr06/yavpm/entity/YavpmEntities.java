package com.farestr06.yavpm.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.entity.mob.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.LOGGER;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmEntities {
    public static final EntityType<FungusCowEntity> MOONGUS = register(
            "moongus",
            EntityType.Builder.of(FungusCowEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10)
                    .fireImmune()
    );

    public static final EntityType<DiamondChickenEntity> CARBONFOWL = register(
            "carbonfowl",
            EntityType.Builder.of(DiamondChickenEntity::new, MobCategory.MONSTER)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10)
    );
    public static final EntityType<TanukiEntity> TANUKI = register(
            "tanuki",
            EntityType.Builder.of(TanukiEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.7F)
                    .eyeHeight(0.4F)
                    .passengerAttachments(new Vec3(0.0, 0.6375, -0.25))
                    .clientTrackingRange(8)
    );
    public static final EntityType<SunburnEntity> SUNBURN = register(
            "sunburn",
            EntityType.Builder.of(SunburnEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.8f)
                    .eyeHeight(0.5f)
                    .passengerAttachments(1)
                    .ridingOffset(0.2f)
                    .clientTrackingRange(8)
    );
    public static final EntityType<VoidPhantomEntity> VOID_PHANTOM = register(
            "void_phantom",
            EntityType.Builder.of(VoidPhantomEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 0.5F)
                    .eyeHeight(0.175F)
                    .passengerAttachments(0.3375F)
                    .ridingOffset(-0.125F)
                    .clientTrackingRange(8)
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        ResourceLocation namespacedId = makeId(id);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, namespacedId);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, namespacedId, type.build(key));
    }

    public static void init() {
        LOGGER.info("Registering entities for YAVPM!!");

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Moongus attributes...");
        FabricDefaultAttributeRegistry.register(MOONGUS, FungusCowEntity.createAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Carbonfowl attributes...");
        FabricDefaultAttributeRegistry.register(CARBONFOWL, DiamondChickenEntity.createCarbonfowlAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Tanuki attributes...");
        FabricDefaultAttributeRegistry.register(TANUKI, TanukiEntity.createTanukiAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Sunburn attributes...");
        FabricDefaultAttributeRegistry.register(SUNBURN, SunburnEntity.createSunburnAttributes().build());

        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Void Phantom attributes...");
        FabricDefaultAttributeRegistry.register(VOID_PHANTOM, Monster.createMonsterAttributes().build());
    }
}
