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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmProfessions {
    public static final RegistryKey<PointOfInterestType> LUMBERJACK_POI_KEY = registerPoiKey("lumberjack_poi");
    public static final PointOfInterestType LUMBERJACK_POI = registerPOI("lumberjack_poi", YavpmBlocks.CHOPPING_BLOCK);

    public static final RegistryKey<VillagerProfession> LUMBERJACK_KEY = registerProfessionKey(makeId("lumberjack"));
    public static final VillagerProfession LUMBERJACK = registerLumberjack();

    //entity." + key.getValue().getNamespace() + ".villager
    private static VillagerProfession registerLumberjack() {
        return Registry.register(Registries.VILLAGER_PROFESSION, LUMBERJACK_KEY,
                new VillagerProfession(Text.translatable("entity.yavpm.villager.lumberjack"), entry -> entry.matchesKey(YavpmProfessions.LUMBERJACK_POI_KEY),
                        entry -> entry.matchesKey(YavpmProfessions.LUMBERJACK_POI_KEY),
                        ImmutableSet.of(), ImmutableSet.of(), YavpmSounds.ENTITY_VILLAGER_WORK_LUMBERJACK));
    }

    private static RegistryKey<VillagerProfession> registerProfessionKey(Identifier id) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, id);
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
