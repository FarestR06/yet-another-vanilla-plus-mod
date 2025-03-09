package com.farestr06.yavpm.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.dynamic.Codecs;

public record CopperInstrument(
        RegistryEntry<SoundEvent> harmony, RegistryEntry<SoundEvent> melody, RegistryEntry<SoundEvent> bass,
        float useDuration, float range, Text description
) {

    public static final Codec<CopperInstrument> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            SoundEvent.ENTRY_CODEC.fieldOf("harmony").forGetter(CopperInstrument::harmony),
                            SoundEvent.ENTRY_CODEC.fieldOf("melody").forGetter(CopperInstrument::melody),
                            SoundEvent.ENTRY_CODEC.fieldOf("bass").forGetter(CopperInstrument::bass),
                            Codecs.POSITIVE_FLOAT.fieldOf("use_duration").forGetter(CopperInstrument::useDuration),
                            Codecs.POSITIVE_FLOAT.fieldOf("range").forGetter(CopperInstrument::range),
                            TextCodecs.CODEC.fieldOf("description").forGetter(CopperInstrument::description)
                    )
                    .apply(instance, CopperInstrument::new)
    );

    public static final PacketCodec<RegistryByteBuf, CopperInstrument> PACKET_CODEC = PacketCodec.tuple(
            SoundEvent.ENTRY_PACKET_CODEC,
            CopperInstrument::harmony,
            SoundEvent.ENTRY_PACKET_CODEC,
            CopperInstrument::melody,
            SoundEvent.ENTRY_PACKET_CODEC,
            CopperInstrument::bass,
            PacketCodecs.FLOAT,
            CopperInstrument::useDuration,
            PacketCodecs.FLOAT,
            CopperInstrument::range,
            TextCodecs.REGISTRY_PACKET_CODEC,
            CopperInstrument::description,
            CopperInstrument::new
    );
}
