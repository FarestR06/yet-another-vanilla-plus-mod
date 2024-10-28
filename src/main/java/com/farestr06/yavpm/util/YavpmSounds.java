package com.farestr06.yavpm.util;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class YavpmSounds {

    public static final SoundEvent ITEM_REACTOR_CHARGE = register("item.reactor.charge");
    public static final SoundEvent ITEM_SOUL_POWDER_USE = register("item.soul_powder.use");

    public static final SoundEvent ENTITY_CHICKEN_EGG_BREAK = register("entity.chicken.egg_break");
    public static final SoundEvent ENTITY_MOONGUS_SHEAR = register("entity.moongus.shear");
    public static final SoundEvent ENTITY_MOONGUS_MILK_CRIMSON = register("entity.moongus.milk.crimson");
    public static final SoundEvent ENTITY_MOONGUS_MILK_WARPED = register("entity.moongus.milk.warped");

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
