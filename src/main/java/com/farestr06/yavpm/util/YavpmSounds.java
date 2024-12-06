package com.farestr06.yavpm.util;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

import static com.farestr06.api.sound.SoundHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmSounds {

    public static final SoundEvent BLOCK_PRICKLE_LOG_PLUCK = makeEvent(makeId("block.prickle_log.pluck"));

    public static final SoundEvent ITEM_REACTOR_CHARGE = makeEvent(makeId("item.reactor.charge"));
    public static final SoundEvent ITEM_SOUL_POWDER_USE = makeEvent(makeId("item.soul_powder.use"));

    public static final RegistryEntry<SoundEvent> MUSIC_OVERWORLD_WITHERED_SCAR = makeReferenceEvent(makeId("music.overworld.withered_scar"));

    public static final SoundEvent ENTITY_MOONGUS_SHEAR = makeEvent(makeId("entity.moongus.shear"));
    public static final SoundEvent ENTITY_MOONGUS_EAT = makeEvent(makeId("entity.moongus.eat"));
    public static final SoundEvent ENTITY_MOONGUS_MILK_CRIMSON = makeEvent(makeId("entity.moongus.milk.crimson"));
    public static final SoundEvent ENTITY_MOONGUS_MILK_WARPED = makeEvent(makeId("entity.moongus.milk.warped"));

    public static final RegistryEntry<SoundEvent> ITEM_ARMOR_EQUIP_STUDDED = makeReferenceEvent(makeId("item.armor.equip_studded"));

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering blocks for YAVPM!");
    }
}
