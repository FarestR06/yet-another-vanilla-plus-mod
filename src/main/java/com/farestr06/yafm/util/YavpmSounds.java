package com.farestr06.yafm.util;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class YavpmSounds {

    public static final SoundEvent ITEM_REACTOR_CHARGE = register("item.reactor.charge");
    public static final SoundEvent ITEM_SOUL_POWDER_USE = register("item.soul_powder.use");
    public static final SoundEvent ENTITY_CHICKEN_EGG_BREAK = register("entity.chicken.egg_break");

    public static final RegistryEntry<SoundEvent> ITEM_ARMOR_EQUIP_STUDDED = registerReference("item.armor.equip_studded");

    private static SoundEvent register(String id) {
        Identifier identifier = YetAnotherVanillaPlusMod.makeId(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
    private static RegistryEntry<SoundEvent> registerReference(String id) {
        Identifier identifier = YetAnotherVanillaPlusMod.makeId(id);
        return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");
    }
}
