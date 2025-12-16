package com.farestr06.yavpm.mixin.block;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Redirect(method = "canEntityWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean bootsRedirect(ItemStack instance, Item item) {
        return instance.is(Items.LEATHER_BOOTS) || instance.is(YavpmItems.STUDDED_BOOTS);
    }
}
