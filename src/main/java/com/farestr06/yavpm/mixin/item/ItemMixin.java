package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.component.YavpmDataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "appendTooltip", at = @At(value = "HEAD"))
    private void injected(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type, CallbackInfo ci) {
        if (stack.isOf(Items.EGG)) {
            if (stack.get(YavpmDataComponentTypes.ALWAYS_HATCHES) != null) {
                textConsumer.accept(Text.translatable("item.minecraft.egg.fertilized").formatted(Formatting.GRAY));
            }
        }
    }
}
