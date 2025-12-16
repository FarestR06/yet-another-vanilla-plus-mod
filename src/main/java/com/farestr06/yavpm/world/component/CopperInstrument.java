package com.farestr06.yavpm.world.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record CopperInstrument(
        Holder<SoundEvent> harmony, Holder<SoundEvent> melody, Holder<SoundEvent> bass,
        float useDuration, float range, Component description
) implements TooltipProvider {

    public static final Codec<CopperInstrument> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            SoundEvent.CODEC.fieldOf("harmony").forGetter(CopperInstrument::harmony),
                            SoundEvent.CODEC.fieldOf("melody").forGetter(CopperInstrument::melody),
                            SoundEvent.CODEC.fieldOf("bass").forGetter(CopperInstrument::bass),
                            ExtraCodecs.POSITIVE_FLOAT.fieldOf("use_duration").forGetter(CopperInstrument::useDuration),
                            ExtraCodecs.POSITIVE_FLOAT.fieldOf("range").forGetter(CopperInstrument::range),
                            ComponentSerialization.CODEC.fieldOf("description").forGetter(CopperInstrument::description)
                    )
                    .apply(instance, CopperInstrument::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, CopperInstrument> PACKET_CODEC = StreamCodec.composite(
            SoundEvent.STREAM_CODEC,
            CopperInstrument::harmony,
            SoundEvent.STREAM_CODEC,
            CopperInstrument::melody,
            SoundEvent.STREAM_CODEC,
            CopperInstrument::bass,
            ByteBufCodecs.FLOAT,
            CopperInstrument::useDuration,
            ByteBufCodecs.FLOAT,
            CopperInstrument::range,
            ComponentSerialization.STREAM_CODEC,
            CopperInstrument::description,
            CopperInstrument::new
    );

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type, DataComponentGetter components) {
        MutableComponent text = description().copy();
        ComponentUtils.mergeStyles(text, Style.EMPTY.withColor(ChatFormatting.GRAY));
        textConsumer.accept(text);
    }
}
