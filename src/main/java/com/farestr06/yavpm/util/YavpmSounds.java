package com.farestr06.yavpm.util;

import com.farestr06.api.util.registry.NoteblockInstrumentRegistry;
import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

import static com.farestr06.api.sound.SoundHelper.makeEvent;
import static com.farestr06.api.sound.SoundHelper.makeReferenceEvent;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmSounds {

    public static final SoundEvent BLOCK_PRICKLE_LOG_PLUCK = makeEvent(makeId("block.prickle_log.pluck"));
    public static final RegistryEntry.Reference<SoundEvent> BLOCK_NOTE_BLOCK_GRIND = makeReferenceEvent(makeId("block.note_block.grind"));

    public static final SoundEvent ITEM_BABY_KEY_SCARED = makeEvent(makeId("item.baby_key.scared"));
    public static final SoundEvent ITEM_BABY_KEY_LOCK = makeEvent(makeId("item.baby_key.lock"));
    public static final SoundEvent ITEM_BABY_KEY_UNLOCK = makeEvent(makeId("item.baby_key.unlock"));

    public static final RegistryEntry<SoundEvent> MUSIC_OVERWORLD_WITHERED_SCAR = makeReferenceEvent(makeId("music.overworld.withered_scar"));

    public static final SoundEvent ENTITY_MOONGUS_SHEAR = makeEvent(makeId("entity.moongus.shear"));
    public static final SoundEvent ENTITY_MOONGUS_EAT = makeEvent(makeId("entity.moongus.eat"));
    public static final SoundEvent ENTITY_MOONGUS_MILK_CRIMSON = makeEvent(makeId("entity.moongus.milk.crimson"));
    public static final SoundEvent ENTITY_MOONGUS_MILK_WARPED = makeEvent(makeId("entity.moongus.milk.warped"));

    public static final SoundEvent ENTITY_TANUKI_AMBIENT = makeEvent(makeId("entity.tanuki.ambient"));
    public static final SoundEvent ENTITY_TANUKI_DEATH = makeEvent(makeId("entity.tanuki.death"));
    public static final SoundEvent ENTITY_TANUKI_HURT = makeEvent(makeId("entity.tanuki.hurt"));
    public static final SoundEvent ENTITY_TANUKI_EAT = makeEvent(makeId("entity.tanuki.eat"));

    public static final RegistryEntry<SoundEvent> ITEM_ARMOR_EQUIP_STUDDED = makeReferenceEvent(makeId("item.armor.equip_studded"));

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering sounds for YAVPM!");

        NoteblockInstrumentRegistry.INSTANCE.add(YavpmBlocks.GRAPHITE_BLOCK, BLOCK_NOTE_BLOCK_GRIND);
        NoteblockInstrumentRegistry.INSTANCE.add(YavpmBlocks.GRAPHENE_BLOCK, BLOCK_NOTE_BLOCK_GRIND);
    }
}
