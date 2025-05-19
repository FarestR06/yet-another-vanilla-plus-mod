package com.farestr06.yavpm.village;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.util.YavpmSounds;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmProfessions {
    public static final RegistryKey<PointOfInterestType> LUMBERJACK_POI_KEY = registerPoiKey("lumberjack_poi");
    public static final PointOfInterestType LUMBERJACK_POI = registerPOI("lumberjack_poi", YavpmBlocks.CHOPPING_BLOCK);

    public static final VillagerProfession LUMBERJACK = registerLumberjack();

    private static VillagerProfession registerLumberjack() {
        return Registry.register(Registries.VILLAGER_PROFESSION, makeId("lumberjack"),
                new VillagerProfession("lumberjack", entry -> entry.matchesKey(YavpmProfessions.LUMBERJACK_POI_KEY),
                        entry -> entry.matchesKey(YavpmProfessions.LUMBERJACK_POI_KEY),
                        ImmutableSet.of(), ImmutableSet.of(), YavpmSounds.ENTITY_VILLAGER_WORK_LUMBERJACK));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(makeId(name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, makeId(name));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering villager professions for YAVPM!!");
    }
}
