package com.farestr06.yafm.util;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class YavpmSounds {

    public static final SoundEvent ITEM_REACTOR_CHARGE = register("item.reactor.charge");
    public static final SoundEvent ITEM_SOUL_POWDER_USE = register("item.soul_powder.use");

    private static SoundEvent register(String id) {
        Identifier identifier = YetAnotherVanillaPlusMod.makeId(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");
    }
}
