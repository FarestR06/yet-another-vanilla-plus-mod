package com.farestr06.yavpm.mixin.block;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Redirect(method = "canWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean bootsRedirect(ItemStack instance, Item item) {
        return instance.isOf(Items.LEATHER_BOOTS) || instance.isOf(YavpmItems.STUDDED_BOOTS);
    }
}
