package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
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

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "appendTooltip", at = @At(value = "HEAD"))
    private void injected(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (stack.isOf(Items.EGG)) {
            if (stack.get(YavpmDataComponentTypes.ALWAYS_HATCHES) != null) {
                tooltip.add(Text.translatable("item.minecraft.egg.fertilized").formatted(Formatting.GRAY));
            }
        }
    }
}
