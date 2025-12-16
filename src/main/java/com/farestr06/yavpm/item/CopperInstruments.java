package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.item.custom.CopperHornItem;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.world.component.CopperInstrument;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public final class CopperInstruments {
    public static final ResourceLocation GREAT_SKY_FALLING = makeId("great_sky_falling");

    private static CopperInstrument register(
            Holder<SoundEvent> harmony,
            Holder<SoundEvent> melody,
            Holder<SoundEvent> bass,
            ResourceLocation id) {
        CopperInstrument instrument = new CopperInstrument(
                harmony,
                melody,
                bass,
                7.0F, 256.0F,
                Component.translatable(Util.makeDescriptionId("instrument", id))
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
