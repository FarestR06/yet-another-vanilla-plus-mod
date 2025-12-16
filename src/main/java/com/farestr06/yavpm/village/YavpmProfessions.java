package com.farestr06.yavpm.village;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.util.YavpmSounds;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmProfessions {
    public static final ResourceKey<PoiType> LUMBERJACK_POI_KEY = registerPoiKey("lumberjack_poi");
    public static final PoiType LUMBERJACK_POI = registerPOI("lumberjack_poi", YavpmBlocks.CHOPPING_BLOCK);

    public static final ResourceKey<VillagerProfession> LUMBERJACK_KEY = registerProfessionKey(makeId("lumberjack"));
    public static final VillagerProfession LUMBERJACK = registerLumberjack();

    //entity." + key.getValue().getNamespace() + ".villager
    private static VillagerProfession registerLumberjack() {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, LUMBERJACK_KEY,
                new VillagerProfession(Component.translatable("entity.yavpm.villager.lumberjack"), entry -> entry.is(YavpmProfessions.LUMBERJACK_POI_KEY),
                        entry -> entry.is(YavpmProfessions.LUMBERJACK_POI_KEY),
                        ImmutableSet.of(), ImmutableSet.of(), YavpmSounds.ENTITY_VILLAGER_WORK_LUMBERJACK));
    }

    private static ResourceKey<VillagerProfession> registerProfessionKey(ResourceLocation id) {
        return ResourceKey.create(Registries.VILLAGER_PROFESSION, id);
    }

    private static PoiType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(makeId(name), 1, 1, block);
    }

    private static ResourceKey<PoiType> registerPoiKey(String name) {
        return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, makeId(name));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering villager professions for YAVPM!!");
    }
}
