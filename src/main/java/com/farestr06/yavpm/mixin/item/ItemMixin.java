package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "appendHoverText", at = @At(value = "HEAD"))
    private void injected(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type, CallbackInfo ci) {
        if (stack.is(Items.EGG)) {
            if (stack.get(YavpmDataComponentTypes.Item.ALWAYS_HATCHES) != null) {
                textConsumer.accept(Component.translatable("item.minecraft.egg.fertilized").withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
