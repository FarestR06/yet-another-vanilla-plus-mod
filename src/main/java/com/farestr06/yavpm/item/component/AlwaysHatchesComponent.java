package com.farestr06.yavpm.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public record AlwaysHatchesComponent() implements TooltipAppender {
    public static final AlwaysHatchesComponent INSTANCE = new AlwaysHatchesComponent();
    public static final Codec<AlwaysHatchesComponent> CODEC = Codec.unit(AlwaysHatchesComponent::new);

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        textConsumer.accept(Text.translatable("item.minecraft.egg.fertilized").formatted(Formatting.GRAY));
    }
}
