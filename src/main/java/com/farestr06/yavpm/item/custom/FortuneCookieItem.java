package com.farestr06.yavpm.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FortuneCookieItem extends Item {
    public FortuneCookieItem(net.minecraft.world.item.Item.Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (user instanceof Player playerEntity) {
            playerEntity.addItem(LuckySlipItemHelper.forEnchantment(LuckySlipItemHelper.choose(world)));
        }
        return super.finishUsingItem(stack, world, user);
    }
}
