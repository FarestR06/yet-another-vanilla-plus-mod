package com.farestr06.yavpm.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

public record CopperInstrument(
        RegistryEntry<SoundEvent> harmony, RegistryEntry<SoundEvent> melody, RegistryEntry<SoundEvent> bass,
        float useDuration, float range, Text description
) implements TooltipAppender {

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

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        MutableText text = description().copy();
        Texts.setStyleIfAbsent(text, Style.EMPTY.withColor(Formatting.GRAY));
        textConsumer.accept(text);
    }
}
