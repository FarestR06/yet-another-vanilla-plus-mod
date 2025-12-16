package com.farestr06.yavpm.world.component;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record AlwaysHatchesComponent() implements TooltipProvider {
    public static final AlwaysHatchesComponent INSTANCE = new AlwaysHatchesComponent();
    public static final Codec<AlwaysHatchesComponent> CODEC = Codec.unit(AlwaysHatchesComponent::new);

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type, DataComponentGetter components) {
        textConsumer.accept(Component.translatable("item.minecraft.egg.fertilized").withStyle(ChatFormatting.GRAY));
    }
}
