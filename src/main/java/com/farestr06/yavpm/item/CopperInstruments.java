package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.item.component.CopperInstrument;
import com.farestr06.yavpm.item.custom.CopperHornItem;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public final class CopperInstruments {
    public static final Identifier GREAT_SKY_FALLING = makeId("great_sky_falling");

    private static CopperInstrument register(
            RegistryEntry<SoundEvent> harmony,
            RegistryEntry<SoundEvent> melody,
            RegistryEntry<SoundEvent> bass,
            Identifier id) {
        CopperInstrument instrument = new CopperInstrument(
                harmony,
                melody,
                bass,
                7.0F, 256.0F,
                Text.translatable(Util.createTranslationKey("instrument", id))
        );
        CopperHornItem.registerCopperInstrument(id, instrument);
        return instrument;
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Registering Copper Horn instruments...");
        register(
                YavpmSounds.ITEM_COPPER_HORN_SOUND_HARMONY_0,
                YavpmSounds.ITEM_COPPER_HORN_SOUND_MELODY_0,
                YavpmSounds.ITEM_COPPER_HORN_SOUND_BASS_0,
                GREAT_SKY_FALLING
        );
    }
}
